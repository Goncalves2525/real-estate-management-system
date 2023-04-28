package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Role;
import pt.ipp.isep.dei.esoft.project.repository.AgencyListRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RegisterEmployeeUI implements Runnable {


    private final RegisterEmployeeController controller = new RegisterEmployeeController();

    public RegisterEmployeeUI() {
    }

    private RegisterEmployeeController getController() {
        return controller;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        Set<Role> selectedRoles = new HashSet<>();

        boolean addMoreRoles;
        do {
            showRoles();
            System.out.println();
            System.out.print("Select a role option: ");
            Role role = selectRole(scanner.nextInt());
            scanner.nextLine(); // Consume newline character from previous input
            selectedRoles.add(role);

            System.out.println("Do you want to add more roles? (Y/N)");
            String answer = scanner.nextLine();
            addMoreRoles = answer.equalsIgnoreCase("Y");
        } while (addMoreRoles);

        System.out.println();
        System.out.println();

        listAgencies();
        System.out.print("Select an agency by ID: ");
        int agencyId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character from previous input
        Agency agency = getAgencyById(agencyId);


        System.out.print("Name: ");
        String name = scanner.nextLine();


        System.out.print("Email: ");
        String email = scanner.nextLine();
        while (!email.contains("@")) {
            System.out.println("Invalid email. Please enter a valid email address:");
            System.out.print("Email: ");
            email = scanner.nextLine();
        }


        System.out.print("Passport Card Number: ");
        int passportCardNumber = scanner.nextInt();

        System.out.print("Tax Number: ");
        int taxNumber = scanner.nextInt();

        System.out.print("Telephone Number: ");
        int telephoneNumber = scanner.nextInt();

        while (String.valueOf(telephoneNumber).length() != 10) {
            System.out.println("Invalid telephone number. Please enter a 10-digit telephone number:");
            System.out.print("Telephone Number: ");
            telephoneNumber = scanner.nextInt();
        }


        scanner.nextLine(); // Consume newline character from previous input

        System.out.print("Do you want to add an address? (Y/N)");
        String answerAdress = scanner.nextLine();

        Address address = new Address(" "," "," "," ",0);

        if (answerAdress.equalsIgnoreCase("Y")) {
            System.out.print("Street: ");
            String street = scanner.nextLine();

            System.out.print("City: ");
            String city = scanner.nextLine();

            System.out.print("District: ");
            String district = scanner.nextLine();

            System.out.print("State: ");
            String state = scanner.nextLine();

            System.out.print("Zipcode: ");
            int zipcode = scanner.nextInt();
            scanner.nextLine(); // Consume newline character from previous input

            address = new Address(street, city, district, state, zipcode);
        }

        String password = controller.generatePassword();

        String answer = displayConfirmation(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, selectedRoles);
        processRegistration(password, answer, name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, selectedRoles);


    }

    private void showRoles() {
        for (Role role : controller.getRolesToCreate()) {
            System.out.println(role.getValue () + " - " + role.toString());
        }
    }

    private Role selectRole(int value) {
        return controller.getRoleByVale(value);
    }

    private void listAgencies() {
        AgencyListRepository agencyListRepository = Repositories.getInstance().getAgencyListRepository();
        List<Agency> agencies = agencyListRepository.getAgencies();
        for (Agency agency : agencies) {
            System.out.println(agency.getId() + " - " + agency.getName());
        }
    }

    private Agency getAgencyById(int id) {
        AgencyListRepository agencyListRepository = Repositories.getInstance().getAgencyListRepository();
        return agencyListRepository.getAgencyById(id);
    }

    private String displayConfirmation(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, Set<Role> selectedRoles) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please confirm the following information:");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Passport Card Number: " + passportCardNumber);
        System.out.println("Tax Number: " + taxNumber);
        System.out.println("Telephone Number: " + telephoneNumber);
        System.out.println("Address: " + address.toString());
        System.out.println("Agency: " + agency.getName());
        System.out.print("Roles: ");
        for (Role role : selectedRoles) {
            System.out.print(role.toString() + " ");
        }
        System.out.println();
        System.out.println("Is this information correct? (Y/N)");
        return scanner.nextLine();
    }


    private void processRegistration(String password, String answer, String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, Set<Role> selectedRoles) {
        if (answer.equalsIgnoreCase("Y")) {
            boolean success = controller.registerEmployee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, selectedRoles);
            if (success) {
                writePasswordToFile(name, email, password, "email.txt");
                System.out.println();
                System.out.println("|------------------------------------------------------------|\n" +
                        "| Employee registered successfully and password sent to email|\n" +
                        "|------------------------------------------------------------|");

            } else {
                System.out.println("Failed to register employee. Employee with the same email may already exist.");
            }
        } else {
            System.out.println("|--------------------------|\n" +
                    "| Employee not registered. |\n" +
                    "|--------------------------|");
        }
    }


    private void writePasswordToFile(String name, String email, String password, String fileName) {
        String welcomeMessage = String.format("Dear %s,%n%n" +
                "I would like to extend a warm welcome to you as a new member of our team.%n" +
                "Please find your login details below:%n%n" +
                "Username: %s%n" +
                "Password: %s%n%n" +
                "Please keep this information secure and do not share it with anyone else.%n%n" +
                "Best regards,%n" +
                "admin", name, email, password);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            writer.write(welcomeMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing password to file: " + e.getMessage());
        }
    }





}

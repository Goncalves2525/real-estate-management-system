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
import java.util.List;
import java.util.Scanner;
public class RegisterEmployeeUI implements Runnable {


    private final RegisterEmployeeController controller = new RegisterEmployeeController();

    public RegisterEmployeeUI() {
    }

    private RegisterEmployeeController getController() {
        return controller;
    }

    @Override
    public void run() {
        Role role;
        Scanner scanner = new Scanner(System.in);

        showRoles();
        System.out.println();
        System.out.print("Select a role option: ");
        role = selectRole(scanner.nextInt());
        scanner.nextLine(); // Consume newline character from previous input

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

        System.out.print("Passport Card Number: ");
        int passportCardNumber = scanner.nextInt();

        System.out.print("Tax Number: ");
        int taxNumber = scanner.nextInt();

        System.out.print("Telephone Number: ");
        int telephoneNumber = scanner.nextInt();

        scanner.nextLine(); // Consume newline character from previous input

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

        Address address = new Address(street, city, district, state, zipcode);

        String password = controller.generatePassword();

        String answer = displayConfirmation(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, role);
        processRegistration(password, answer, name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, role);


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

    private String displayConfirmation(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, Role role) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please confirm the following information:");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Passport Card Number: " + passportCardNumber);
        System.out.println("Tax Number: " + taxNumber);
        System.out.println("Telephone Number: " + telephoneNumber);
        System.out.println("Address: " + address.toString());
        System.out.println("Agency: " + agency.getName());
        System.out.println("Role: " + role.toString());
        System.out.println();
        System.out.println("Is this information correct? (Y/N)");
        return scanner.nextLine();
    }

    private void processRegistration(String password, String answer, String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, Role role) {
        if (answer.equalsIgnoreCase("Y")) {
            boolean success = controller.registerEmployee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, role);
            if (success) {
                writePasswordToFile(password, "passwords.txt");
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

    private void writePasswordToFile(String password, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(password);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing password to file: " + e.getMessage());
        }
    }



}

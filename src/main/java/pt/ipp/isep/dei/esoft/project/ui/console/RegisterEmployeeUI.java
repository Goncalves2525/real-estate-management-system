package pt.ipp.isep.dei.esoft.project.ui.console;



import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Address;

import pt.ipp.isep.dei.esoft.project.domain.Agency;

import pt.ipp.isep.dei.esoft.project.domain.Role;

import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;


import java.io.BufferedWriter;

import java.io.FileWriter;

import java.io.IOException;

import java.util.*;


public class RegisterEmployeeUI implements Runnable {


    /**
     * Instance variables.
     */
    private final RegisterEmployeeController controller = new RegisterEmployeeController();


    public RegisterEmployeeUI() {

    }



    @Override

    public void run() {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Role> selectedRoles = selectRoles(scanner);

        System.out.println();

        Agency agency = selectAgency(scanner);

        String name = inputName(scanner);

        String email = inputEmail(scanner);

        int passportCardNumber = inputPassportCardNumber(scanner);

        int taxNumber = inputTaxNumber(scanner);

        int telephoneNumber = inputTelephoneNumber(scanner);

        Address address = inputAddress(scanner);

        String password = controller.generatePassword();

        String answer = displayConfirmation(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, selectedRoles);

        processRegistration(password, answer, name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, selectedRoles);


    }


    /**
     * @param scanner
     * @return
     */
    private ArrayList<Role> selectRoles(Scanner scanner) {
        ArrayList<Role> selectedRoles = new ArrayList<>();
        boolean addMoreRoles;
        do {
            showRoles();
            int roleValue = inputRoleValue(scanner);
            Role role = selectRole(roleValue);
            selectedRoles.add(role);
            addMoreRoles = inputAddMoreRoles(scanner);
        } while (addMoreRoles);
        return selectedRoles;
    }

    /**
     * @param scanner
     * @return
     */
    private int inputRoleValue(Scanner scanner) {
        int roleValue;
        do {
            System.out.print("Select a role option: ");
            roleValue = getIntInput(scanner);
            if (roleValue < 1 || roleValue > controller.getRolesToCreate().size()) {
                System.out.println("Invalid role option. Please select a value between 1 and " + controller.getRolesToCreate().size() + ":");
            }
        } while (roleValue < 1 || roleValue > controller.getRolesToCreate().size());
        return roleValue;
    }

    /**
     * @param scanner
     * @return
     */
    private int getIntInput(Scanner scanner) {
        int input;
        while (true) {
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                scanner.nextLine(); // consume invalid input
            }
        }
        return input;
    }


    /**
     * @param value
     * @return
     */
    private Role selectRole(int value) {
        return controller.getRoleByVale(value);
    }


    /**
     *
     */
    private void showRoles() {
        for (Role role : controller.getRolesToCreate()) {
            System.out.println(role.getValue() + " - " + role.toString());
        }
    }


    /**
     * @param scanner
     * @return
     */
    private boolean inputAddMoreRoles(Scanner scanner) {
        String answer;
        do {
            System.out.println("Do you want to add more roles? (Y/N)");
            answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
                System.out.println("You have to select Y to add another Role or N if you don't want to add another Role");
            }
        } while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N"));
        return answer.equalsIgnoreCase("Y");
    }





    private Agency selectAgency(Scanner scanner) {
        listAgencies();

        System.out.print("Select an agency by ID: ");

        int agencyId;

        do {
            try {
                agencyId = scanner.nextInt();
                scanner.nextLine(); // Consume newline character from previous input

                if (agencyId < 1 || agencyId > Repositories.getInstance().getAgencyListRepository().getAgencies().size()) {
                    System.out.println("Invalid agency ID. Please select a value between 1 and " + Repositories.getInstance().getAgencyListRepository().getAgencies().size() + ":");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                scanner.nextLine(); // Consume the invalid input
                agencyId = -1; // Set agencyId to an invalid value to trigger the loop again
            }
        } while (agencyId < 1 || agencyId > Repositories.getInstance().getAgencyListRepository().getAgencies().size());

        return getAgencyById(agencyId);
    }

    private void listAgencies() {

        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyListRepository();

        List<Agency> agencies = agencyRepository.getAgencies();

        for (Agency agency : agencies) {

            System.out.println(agency.getId() + " - " + agency.getName());

        }

    }

    private Agency getAgencyById(int id) {

        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyListRepository();

        return agencyRepository.getAgencyById(id);

    }





    private String inputName(Scanner scanner) {
        System.out.print("Name: ");
        String name;

        do {
            name = scanner.nextLine();
            String trimmedName = name.trim(); // Remove leading and trailing spaces

            if (trimmedName.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name:");
            } else {
                name = trimmedName;
            }
        } while (name.trim().isEmpty());

        return name;
    }


    private String inputEmail(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();

        while (!email.contains("@")) {
            System.out.println("Invalid email. Please enter a valid email address:");
            System.out.print("Email: ");
            email = scanner.nextLine();
        }

        return email;
    }

    private int inputPassportCardNumber(Scanner scanner) {
        System.out.print("Passport Card Number: ");

        int passportCardNumber;

        while (true) {

            try {

                passportCardNumber = scanner.nextInt();

                scanner.nextLine(); // Consume newline character from previous input

                break;

            } catch (InputMismatchException e) {

                System.out.println("Invalid input. Please enter an integer value for the Passport Card Number:");

                scanner.nextLine(); // Consume the invalid input

            }

        }

        return passportCardNumber;
    }


    private int inputTaxNumber(Scanner scanner) {
        System.out.print("Tax Number: ");

        int taxNumber;

        while (true) {

            try {

                taxNumber = scanner.nextInt();

                scanner.nextLine(); // Consume newline character from previous input

                break;

            } catch (InputMismatchException e) {

                System.out.println("Invalid input. Please enter an integer value for the Tax Number:");

                scanner.nextLine(); // Consume the invalid input

            }

        }

        return taxNumber;
    }




    private int inputTelephoneNumber(Scanner scanner) {
        System.out.print("Telephone Number: ");

        int telephoneNumber;

        while (true) {
            try {
                telephoneNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline character from previous input

                if (String.valueOf(telephoneNumber).length() == 10 && telephoneNumber != 0) {
                    break;
                } else if (telephoneNumber == 0) {
                    System.out.println("Telephone number cannot be empty. Please enter a valid 10-digit telephone number:");
                } else {
                    System.out.println("Invalid telephone number. Please enter a 10-digit telephone number:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value for the Telephone Number:");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        return telephoneNumber;
    }


    private Address inputAddress(Scanner scanner) {
        System.out.print("Do you want to add an address? (Y/N): ");
        String answerAdress;

        while (true) {
            answerAdress = scanner.nextLine();

            if (answerAdress.equalsIgnoreCase("Y") || answerAdress.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter Y or N:");
            }
        }

        if (answerAdress.equalsIgnoreCase("Y")) {
            System.out.print("Street: ");
            String street;
            do {
                street = scanner.nextLine();

                if (street.trim().isEmpty()) {
                    System.out.println("Street cannot be empty. Please enter a street name:");
                }
            } while (street.trim().isEmpty());

            System.out.print("City: ");
            String city;
            do {
                city = scanner.nextLine();

                if (city.trim().isEmpty()) {
                    System.out.println("City cannot be empty. Please enter a city name:");
                }
            } while (city.trim().isEmpty());

            System.out.print("District: ");
            String district;
            do {
                district = scanner.nextLine();

                if (district.trim().isEmpty()) {
                    System.out.println("District cannot be empty. Please enter a district name:");
                }
            } while (district.trim().isEmpty());

            System.out.print("State: ");
            String state;
            do {
                state = scanner.nextLine();

                if (state.trim().isEmpty()) {
                    System.out.println("State cannot be empty. Please enter a state name:");
                }
            } while (state.trim().isEmpty());

            System.out.print("Zipcode: ");
            int zipcode;
            while (true) {
                try {
                    zipcode = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character from previous input
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value for the Zipcode:");
                    scanner.nextLine(); // Consume the invalid input
                }
            }

            return new Address(street, city, district, state, zipcode);
        } else {
            return new Address(" ", " ", " ", " ", 0);
        }
    }


    /**
     * Formats a 10-digit telephone number to the format (XXX) XXX-XXXX
     *
     * @param telephoneNumber The 10-digit telephone number to be formatted
     * @return The formatted telephone number
     */
    private String displayConfirmation(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, ArrayList<Role> selectedRoles) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please confirm the following information:");

        System.out.println("Name: " + name);

        System.out.println("Email: " + email);

        System.out.println("Passport Card Number: " + passportCardNumber);

        System.out.println("Tax Number: " + taxNumber);

        System.out.println("Telephone Number: " + formatPhoneNumber(telephoneNumber));

        if (!(address.getStreet().trim().isEmpty() && address.getCity().trim().isEmpty() && address.getDistrict().trim().isEmpty() && address.getState().trim().isEmpty())) {
            System.out.println("Address: " + address.toString());
        }

        System.out.println("Agency: " + agency.getName());

        System.out.print("Roles: ");

        for (Role role : selectedRoles) {
            System.out.print(role.toString() + "; ");
        }

        String answer;
        System.out.println();

        do {
            System.out.println("Is this information correct? (Y/N)");

            answer = scanner.nextLine();

            if (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
                System.out.println("Invalid input. Please enter Y or N:");
            }

        } while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N"));

        return answer;
    }

    /**
     * Formats a 10-digit telephone number to the format (XXX) XXX-XXXX
     *
     * @param phoneNumber The 10-digit telephone number to be formatted
     * @return The formatted telephone number
     */
    private String formatPhoneNumber(int phoneNumber) {
        String rawNumber = String.valueOf(phoneNumber);
        String areaCode = rawNumber.substring(0, 3);
        String firstThreeDigits = rawNumber.substring(3, 6);
        String lastFourDigits = rawNumber.substring(6);

        return String.format("(%s) %s-%s", areaCode, firstThreeDigits, lastFourDigits);
    }


    /**
     * @param password
     * @param answer
     * @param name
     * @param email
     * @param passportCardNumber
     * @param taxNumber
     * @param telephoneNumber
     * @param address
     * @param agency
     * @param selectedRoles
     */
    private void processRegistration(String password, String answer, String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, List<Role> selectedRoles) {

        if (answer.equalsIgnoreCase("Y")) {

            boolean success = controller.registerEmployee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, selectedRoles);

            if (success) {

                controller.createUser(name, email, password, AuthenticationController.ROLE_EMPLOYEE);

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


    /**
     * @param name
     * @param email
     * @param password
     * @param fileName
     */
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







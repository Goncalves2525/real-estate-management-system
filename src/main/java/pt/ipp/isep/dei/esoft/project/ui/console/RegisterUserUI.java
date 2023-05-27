package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterUserController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Address;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RegisterUserUI implements Runnable {

    private final RegisterUserController controller = new RegisterUserController();

    /**
     * Runs the UI
     */
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        String name = inputName(sc);

        String email = inputEmail(sc);

        int passportCardNumber = inputPassportCardNumber(sc);

        int taxNumber = inputTaxNumber(sc);

        long telephoneNumber = inputTelephoneNumber(sc);

        Address address = inputAddress(sc);

        String password = controller.generatePassword();

        processRegistration(password, name, email, passportCardNumber, taxNumber, telephoneNumber, address);
    }

    /** Verifies input name
     *
     * @param scanner
     * @return name
     */
    private String inputName(Scanner scanner) {
        System.out.println();
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

    /** Verifies input email
     *
     * @param scanner
     * @return email
     */
    private String inputEmail(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();

        while (!isValidEmail(email)) {
            System.out.println("Invalid email. Please enter a valid email address like x@x.xx");
            System.out.print("Email: ");
            email = scanner.nextLine();
        }

        return email;
    }

    /**Verifies if email is valid
     *
     * @param email
     * @return boolean
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    /** Verifies input passport card number
     *
     * @param scanner
     * @return passport card number
     */
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

    /** Verifies input tax number
     *
     * @param scanner
     * @return tax number
     */
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

    /** Verifies input telephone number
     *
     * @param scanner
     * @return telephone number
     */
    private long inputTelephoneNumber(Scanner scanner) {
        System.out.print("Telephone Number: ");

        long telephoneNumber;
        String telephoneNumberStr;

        while (true) {
            try {
                telephoneNumberStr = scanner.nextLine();
                telephoneNumber = Long.parseLong(telephoneNumberStr);

                if (telephoneNumberStr.length() == 10 && telephoneNumber != 0) {
                    break;
                } else if (telephoneNumber == 0) {
                    System.out.println("Telephone number cannot be empty. Please enter a valid 10-digit telephone number:");
                } else {
                    System.out.println("Invalid telephone number. Please enter a 10-digit telephone number:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer value for the Telephone Number:");
            }
        }

        return telephoneNumber;
    }

    /** Verifies input address
     *
     * @param scanner
     * @return address
     */
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
     * writes password to file
     * @param name
     * @param email
     * @param password
     * @param fileName
     */
    private void writePasswordToFile(String name, String email, String password, String fileName) {

        String welcomeMessage = String.format("Dear %s,%n%n" +

                "Welcome to Real Estate USA%n" +

                "Please find your login details below:%n%n" +

                "Username: %s%n" +

                "Password: %s%n%n" +

                "Please keep this information secure and do not share it with anyone else.%n%n", name, email, password);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {

            writer.write(welcomeMessage);

            writer.newLine();

        } catch (IOException e) {

            System.err.println("Error writing password to file: " + e.getMessage());

        }

    }

    /**
     * processes registration
     * @param password
     * @param name
     * @param email
     * @param passportCardNumber
     * @param taxNumber
     * @param telephoneNumber
     * @param address
     */
    private void processRegistration(String password, String name, String email, int passportCardNumber, int taxNumber, long telephoneNumber, Address address) {


        boolean success = controller.registerClient(name, email, passportCardNumber, taxNumber, telephoneNumber, address);

        if (success) {

            controller.createUser(name, email, password, AuthenticationController.ROLE_CLIENT);

            writePasswordToFile(name, email, password, "email.txt");

            System.out.println();

            System.out.println("|------------------------------------------------------------|\n" +

                    "| Registered successfully and password sent to email|\n" +

                    "|------------------------------------------------------------|");


        } else {

            System.out.println("Failed to register. Account with the same email may already exist.");

        }

    }
}

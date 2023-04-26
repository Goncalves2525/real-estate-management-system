package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Role;


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

        System.out.println("----- Register Employee -----");

        showRoles();
        role = selectRole(scanner.nextInt());
        scanner.nextLine();
        System.out.println("You have selected " + role + " as a role");

        // For demonstration purposes, you can hardcode an Agency
        Agency agency = new Agency(2, "Agencia 1", "s", 1233, new Address("street1", "City1", "District1", "State1", 1234), null);

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

        Address address = new Address(street, city, district, state, zipcode);

        boolean success = controller.registerEmployee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, role);

        if (success) {
            System.out.println("Employee registered successfully!");
        } else {
            System.out.println("Failed to register employee. Employee with the same email may already exist.");
        }


    }

    private void showRoles() {
        System.out.println("------------------\n" +
                "|     Roles    |\n" +
                "------------------\n\n" +
                "Select a role\n\n" +
                "1 - Employee\n" +
                "2 - Store Network Manager\n" +
                "3 - Store Manager\n");
    }

    private Role selectRole(int option) {
        switch (option) {
            case 1:
                return Role.EMPLOYEE;
            case 2:
                return Role.STORE_NETWORK_MANAGER;
            case 3:
                return Role.STORE_MANAGER;
            default:
                return null;
        }
    }
}

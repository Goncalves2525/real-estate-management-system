package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
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
        Scanner scanner = new Scanner(System.in);

        System.out.println("----- Register Employee -----");

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


        // For demonstration purposes, you can hardcode a Role
        Role role = Role.EMPLOYEE;


        boolean success = controller.registerEmployee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, role);

        if (success) {
            System.out.println("Employee registered successfully!");
        } else {
            System.out.println("Failed to register employee. Employee with the same email may already exist.");
        }






    }
}

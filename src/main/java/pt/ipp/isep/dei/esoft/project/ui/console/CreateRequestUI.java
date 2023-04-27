package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateRequestUI implements Runnable {


    private final CreateRequestController controller = new CreateRequestController();
    private boolean hasCentralHeating;
    private boolean hasAirConditioning;
    private boolean hasLoft;
    private boolean hasBasement;
    private double price;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private int numberOfParkingSpaces;
    private int contractDuration;
    private double distanceFromCenter;
    private double area;
    private int sunExposureOption;



    public CreateRequestUI() {
    }

    private CreateRequestController getController() {
        return controller;
    }

    @Override
    public void run() {

        TypeOfProperty typeOfProperty;
        TransactionType transactionType;
        Property property;
        SunExposure sunExposure = null;
        Scanner sc = new Scanner(System.in);

        System.out.println("Please insert the name of the agency: ");
        String agencyName = sc.nextLine();
        AgentOptions();
        System.out.println("From the list, choose an Agent and enter their email: ");
        Agent agent = controller.getAgentByEmail(sc.nextLine());
        String agentemail = sc.nextLine();
        System.out.println("Please insert you passport ID: ");
        String passportID = sc.nextLine();
        TransactionTypeOptions();
        transactionType = checkTransactionType(sc.nextInt());

        if (transactionType.equals(TransactionType.RENT)) {
            System.out.println("Please insert contract duration: ");
            contractDuration = sc.nextInt();
        } else {
            System.out.println("Please insert the price: ");
            price = sc.nextDouble();
        }

        /**
         * Address
         *
         */
        sc.nextLine();
        System.out.println("Pleses insert Street");
        String street = sc.nextLine();
        System.out.println("Pleses insert City");
        String city = sc.nextLine();
        System.out.println("Pleses insert District");
        String district = sc.nextLine();
        System.out.println("Pleses insert State");
        String state = sc.nextLine();
        System.out.println("Pleses insert ZipCode");
        int zipcode = sc.nextInt();
        Address address = new Address(street, city, district, state, zipcode);


        /**
         * Property
         *
         */
        System.out.println("Please insert the area (m2): ");
        area = sc.nextDouble();
        System.out.println("Please insert the distance from the center (km): ");
        distanceFromCenter = sc.nextDouble();

        TypeOfPropertyOptions();
        TypeOfProperty typeOfProprety = checkTypeOfProperty(sc.nextInt());


        if (typeOfProprety.equals(TypeOfProperty.HOUSE)) {
            System.out.println("Please insert the number of bedrooms: ");
            numberOfBedrooms = sc.nextInt();
            System.out.println("Please insert the number of bathrooms: ");
            numberOfBathrooms = sc.nextInt();
            System.out.println("Please insert the number of parking spaces: ");
            numberOfParkingSpaces = sc.nextInt();


            System.out.println("Has central heating? (y/n): ");
            String centralHeating = sc.nextLine();
            System.out.println("Has air conditioning? (y/n): ");
            String airConditioning = sc.nextLine();
            if (centralHeating.equals("y")) {
                hasCentralHeating = true;
            } else if (centralHeating.equals("n")) {
                hasCentralHeating = false;
            }
            if (airConditioning.equals("y")) {
                hasAirConditioning = true;
            } else if (airConditioning.equals("n")) {
                hasAirConditioning = false;
            }
            checkSunExposure();
            sunExposureOption = sc.nextInt();
            System.out.println("The house have basement? (Y/N)");
            String basement = sc.nextLine();
            System.out.println("The house inhabitable loft? (Y/N)");
            String loft = sc.nextLine();
            if (basement.equals("y")) {
                hasBasement = true;
            } else if (basement.equals("n")) {
                hasBasement = false;
            }
            if (loft.equals("y")) {
                hasLoft = true;
            } else if (loft.equals("n")) {
                hasLoft = false;
            }

        } else if (typeOfProprety.equals(TypeOfProperty.APARTMENT)) {
            System.out.println("Please insert the number of bedrooms: ");
            numberOfBedrooms = sc.nextInt();
            System.out.println("Please insert the number of bathrooms: ");
            numberOfBathrooms = sc.nextInt();
            System.out.println("Please insert the number of parking spaces: ");
            numberOfParkingSpaces = sc.nextInt();


            System.out.println("Has central heating? (y/n): ");
            String centralHeating = sc.nextLine();
            System.out.println("Has air conditioning? (y/n): ");
            String airConditioning = sc.nextLine();
            if (centralHeating.equals("y")) {
                hasCentralHeating = true;
            } else if (centralHeating.equals("n")) {
                hasCentralHeating = false;
            }
            if (airConditioning.equals("y")) {
                hasAirConditioning = true;
            } else if (airConditioning.equals("n")) {
                hasAirConditioning = false;
            }


        } else {


        }

        Land land = new Land(area,price, distanceFromCenter, address);

        House house = new House(area, distanceFromCenter, price,address,numberOfBedrooms,numberOfBathrooms,numberOfParkingSpaces,hasCentralHeating,hasAirConditioning,hasBasement,hasLoft, sunExposure);

        Apartment apartment = new Apartment(area, distanceFromCenter, price , address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning);
        




       //controller.createAnnouncement();

    }
    

    private void AgentOptions() {
        for (Agent agent : this.controller.getAgentList()) {
            System.out.println(agent.toString());
        }
    }

    private void TransactionTypeOptions() {
        System.out.println(
                "Please select the business option you want:\n" +
                        "1- Sale\n" +
                        "2- Rent\n");
    }

    private TransactionType checkTransactionType(int option) {
        switch (option) {
            case 1:
                return TransactionType.SALE;
            case 2:
                return TransactionType.RENT;
            default:
                return null;
        }
    }

    private void TypeOfPropertyOptions() {
        System.out.println("Type of Property:\n" +
                "1- House\n" +
                "2- Apartment\n" +
                "3- Land");
    }

    private TypeOfProperty checkTypeOfProperty(int option) {
        switch (option) {
            case 1:
                return TypeOfProperty.HOUSE;
            case 2:
                return TypeOfProperty.APARTMENT;
            case 3:
                return TypeOfProperty.LAND;
            default:
                return null;
        }
    }

    private SunExposure SunExposureOption(int option) {
        switch (option) {
            case 1:
                return SunExposure.NORTH;
            case 2:
                return SunExposure.SOUTH;
            case 3:
                return SunExposure.EAST;
            case 4:
                return SunExposure.WEST;
            default:
                return null;
        }

    }
    private void checkSunExposure() {
        System.out.println("Please insert the sun exposure:\n" +
                "1- North\n" +
                "2- South\n" +
                "3- East\n" +
                "4- West");
    }
}






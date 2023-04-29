package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateRequestUI implements Runnable {


    private final CreateRequestController controller = new CreateRequestController();


    public CreateRequestUI() {
    }

    private CreateRequestController getController() {
        return controller;
    }

    @Override
    public void run() {
        boolean hasCentralHeating = false;
        boolean hasAirConditioning = false;
        boolean hasLoft = false;
        boolean hasBasement = false;
        double price = 0;
        int numberOfBedrooms = 0;
        int numberOfBathrooms = 0;
        int numberOfParkingSpaces = 0;
        int contractDuration = 0;
        double distanceFromCenter = 0;
        double area = 0;
        int sunExposureOption;


        TransactionType transactionType;
        Property property = null;
        SunExposure sunExposure = null;
        Scanner sc = new Scanner(System.in);

        System.out.println("Please insert the name of the agency: ");
        AgencyOptions();
        System.out.println();
        int agencyID = sc.nextInt();

        List<Employee> agents = controller.getAgentList();
        for (Employee agent : agents) {
            if (agent.getAgency().getId() == agencyID) {
                System.out.println(agent.toString() + "\n");
            }
        }









        /*System.out.println("List of Agents: ");
        for (Employee agent : agents) {
            System.out.println(agent.toString());
        }*/


        System.out.println("From the list, choose an Agent and enter their email: ");
        Employee agent = controller.getAgentByEmail(sc.nextLine());
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
        TypeOfProperty typeOfProperty = checkTypeOfProperty(sc.nextInt());


        if (typeOfProperty.equals(TypeOfProperty.HOUSE)) {
            System.out.println("Please insert the number of bedrooms: ");
            numberOfBedrooms = sc.nextInt();
            System.out.println("Please insert the number of bathrooms: ");
            numberOfBathrooms = sc.nextInt();
            System.out.println("Please insert the number of parking spaces: ");
            numberOfParkingSpaces = sc.nextInt();
            sc.nextLine();
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
            sc.nextLine();
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

        } else if (typeOfProperty.equals(TypeOfProperty.APARTMENT)) {
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

        property = new Property(area, distanceFromCenter, price, address);

        if (typeOfProperty.equals(TypeOfProperty.HOUSE)) {
            property = new House(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning, hasBasement, hasLoft, sunExposure);
            controller.createannouncemntHouse(agent, transactionType, contractDuration, typeOfProperty, property);
        } else if (typeOfProperty.equals(TypeOfProperty.LAND)) {
            property = new Land(area, price, distanceFromCenter, address);
            //controller.createannouncemntLand(agent, transactionType, contractDuration, typeOfProperty, property);
        } else if (typeOfProperty.equals(TypeOfProperty.APARTMENT)) {
            property = new Apartment(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning);
            //controller.createannouncemntApartment(agent, transactionType, contractDuration, typeOfProperty, property);
        }

        System.out.println("Property added successfully!");

    }

    private void AgentOptions() {
        for (Employee agent : this.controller.AgentOptions()) {

            System.out.println(agent.toString());
        }
    }

    private void AgencyOptions() {
        int i = 1;
        for (Agency agency : this.controller.AgencyOptions()) {
            System.out.printf(" %10s %15s %s%n", agency.getId(), agency.getName(), agency.getEmailAddress());
            i++;
            //System.out.println(agency.toString());
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

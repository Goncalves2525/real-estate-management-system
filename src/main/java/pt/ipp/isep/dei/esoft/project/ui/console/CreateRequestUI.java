package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgencyListRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateRequestUI implements Runnable {


    private final CreateRequestController controller = new CreateRequestController();
    private EmployeeRepository employeeRepository;


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
        String passportID;
        int numberOfBedrooms = 0;
        int numberOfBathrooms = 0;
        int numberOfParkingSpaces = 0;
        int contractDuration = 0;
        double distanceFromCenter = 0;
        double area = 0;
        int sunExposureOption;
        boolean control=false;
        int agencyID=0;


        TransactionType transactionType;
        Property property = null;
        SunExposure sunExposure = null;
        Scanner sc = new Scanner(System.in);


        /**
         * Select Agency
         * @param agencyID
         * @return Agency
         */

        while(control == false) {
            System.out.println("Please select the option number of the Agency you want to work with: ");
            AgencyOptions();
            System.out.println();
            agencyID = sc.nextInt();

            if (listAgents(agencyID) == 0) {
                System.out.println("This Agency not exist. ");

            } else {
                control = true;
            }
        }

        /**
         * Select Agent
         * @param agentEmail
         * @return Agent
         */

        System.out.print("From the list above choose Agent choose an agent by entering email:");
        String agentEmail = sc.nextLine();
        sc.nextLine();
        Employee agent = getAgentByEmail(agentEmail);

        /**
         * Insert Passport ID and check if it is valid
         * @param passportID
         * @return boolean
         */

        System.out.println("Please insert your passport ID: ");
        passportID = sc.nextLine();
        while (validatePassportNumber(passportID) == false ) {
            System.out.println("Please insert a valid passport ID: ");
            passportID = sc.nextLine();
        }

        /**
         * Select Transaction Type (Rent or Sale) and contract duration an price
         * @param transactionType
         * @param contractDuration
         * @param price
         */

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
         * Select Type of Property
         * @param typeOfProperty
         * @return TypeOfProperty
         */

        TypeOfPropertyOptions();
        TypeOfProperty typeOfProperty = checkTypeOfProperty(sc.nextInt());

        /**
         * Insert Area and Distance from the center
         * @param area
         * @param distanceFromCenter
         */

        System.out.println("Please insert the area (m2): ");
        area = sc.nextDouble();
        System.out.println("Please insert the distance from the center (km): ");
        distanceFromCenter = sc.nextDouble();

        /**
         * Insert Address, Street, City, District, State, ZipCode
         * @param address
         * @param street
         * @param city
         * @param district
         * @param state
         * @param zipcode
         * @return Address
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

    private Employee getAgentByEmail(String agentEmail) {
        return controller.getAgentByEmail(agentEmail);
    }

    private void AgentOptions() {
        for (Employee agent : this.controller.AgentOptions()) {

            System.out.println(agent.toString());
        }
    }

    private void AgencyOptions() {
        int i = 1;
        System.out.printf("%-10s %-10s %s%n", "Option", "Name", "Email");
        for (Agency agency : this.controller.AgencyOptions()) {
            System.out.printf("%-10s %-10s %s%n", agency.getId(), agency.getName(), agency.getEmailAddress());
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

    public boolean validatePassportNumber(String numeroPassaporte) {
        if (numeroPassaporte.length() != 9) {
            System.out.println("Invalid passport number!\n"+
                    "The passport number must have exactly 9 characters.");
            return false;
        }

        int i = 0;
        while (i < numeroPassaporte.length()) {
            char c = numeroPassaporte.charAt(i);
            if (!Character.isLetterOrDigit(c) || (Character.isLetter(c) && !Character.isUpperCase(c))) {
                System.out.println("Invalid passport number!\n"+
                        "The character is not a letter or a digit, or is a lowercase letter.");
                return false;
            }
            i++;
        }

        return true;
    }
    private int listAgents(int id) {
        int control = 0;
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        List<Employee> agents = employeeRepository.getEmployeeList();
        for (Employee agent : agents) {
            if (agent.getAgency().getId() == id) {
                System.out.printf("%-8s %15s%n", agent.getEmail() + " - " + agent.getName(), "");

                control++;                ;
            }

        }
        return control;
    }

}

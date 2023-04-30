package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * this class is responsible for creating a request
 *
 */
public class CreateRequestUI implements Runnable {



    private final CreateRequestController controller = new CreateRequestController();
    private EmployeeRepository employeeRepository;


    Scanner sc = new Scanner(System.in);
    @Override
    public void run() {
        boolean hasBasement = false;
        double price = 0;
        String passportID;
        int numberOfBedrooms = 0;
        int numberOfBathrooms = 0;
        int numberOfParkingSpaces = 0;
        int contractDuration = 0;
        double distanceFromCenter = 0;
        double area = 0;
        boolean control=false;
        int agencyID=0;
        boolean hasCentralHeating= false;
        boolean hasAirConditioning= false;
        boolean hasInhabitableLoft= false;
        String photoURI = null;
        String street = null;
        String city = null;
        String district = null;
        String state = null;




        TransactionType transactionType;
        Property property = null;
        SunExposure sunExposure = null;



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
            sc.nextLine();
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
        while (getAgentByEmail(agentEmail) == null){
            System.out.println("Please insert a valid email: ");
            agentEmail = sc.nextLine();
        }
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
            contractDuration = validatePositiveIntegerInput("Please insert contract duration in months: ");
        } else {
            price = validatePositiveDoubleInput("Please insert the price for sale or rent value per month.: ");
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

        area = validatePositiveDoubleInput("Please insert the area (m2): ");
        distanceFromCenter = validatePositiveDoubleInput("Please insert the distance from the center (km): ");

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

        street = validateNotEmpty("Please insert Street");
        city = validateNotEmpty("Please insert City");
        district = validateNotEmpty("Please insert District");
        state = validateNotEmpty("Please insert State");

        int zipcode = validatePositiveIntegerInput("Please insert the ZipCode: ");
        Address address = new Address(street, city, district, state, zipcode);

        /**
         * Insert number of bedrooms, bathrooms, parking spaces, central heating, air conditioning, basement, inhabitable loft and sun exposure
         * @param numberOfBedrooms
         * @param numberOfBathrooms
         * @param numberOfParkingSpaces
         * @param hasCentralHeating
         * @param hasAirConditioning
         * @param hasBasement
         * @param hasInhabitableLoft
         * @param sunExposure
         * @return Property
         *
         */

        if (typeOfProperty.equals(TypeOfProperty.HOUSE) || typeOfProperty.equals(TypeOfProperty.APARTMENT)) {
            numberOfBedrooms = validatePositiveIntegerInput("Please insert the number of bedrooms: ");
            numberOfBathrooms = validatePositiveIntegerInput("Please insert the number of bathrooms: ");
            numberOfParkingSpaces = validatePositiveIntegerInput("Please insert the number of parking spaces: ");
            sc.nextLine();
            hasCentralHeating = validationYN("Central Heating");
            System.out.println();
            hasAirConditioning = validationYN("Air Conditioning");
            System.out.println();
            if (typeOfProperty.equals(TypeOfProperty.HOUSE)) {
                TypeOfSunExposureOptions();
                sunExposure = SunExposureOption(sc.nextInt());
                sc.nextLine();
                hasBasement = validationYN("Basement");
                hasInhabitableLoft = validationYN("Loft");
            }
        }
        System.out.println("Please insert the Photo URI: ");
        photoURI = sc.nextLine();
        while (photoURI.isEmpty()) {
            System.out.println("Invalid input. Please insert the Photo URI: ");
            photoURI = sc.nextLine();
        }


        /**
         * Create Announcement
         * @param property
         *
         */

        if (typeOfProperty.equals(TypeOfProperty.HOUSE)) {
            property = new House(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning, hasBasement, hasInhabitableLoft, sunExposure);
            controller.createannouncemntHouse(agent, transactionType, contractDuration, typeOfProperty, property);
        } else if (typeOfProperty.equals(TypeOfProperty.LAND)) {
            property = new Land(area, price, distanceFromCenter, address);
            controller.createannouncemntLand(agent, transactionType, contractDuration, typeOfProperty, property);
        } else if (typeOfProperty.equals(TypeOfProperty.APARTMENT)) {
            property = new Apartment(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning);
            controller.createannouncemntApartment(agent, transactionType, contractDuration, typeOfProperty, property);
        }

        System.out.println("Property added successfully!");

    }

    /**
     * Get Agent by Email
     * @param agentEmail
     * @return
     */
    private Employee getAgentByEmail(String agentEmail) {
        return controller.getAgentByEmail(agentEmail);
    }

    /**
     * List agencies
     */
    private void AgencyOptions() {
        int i = 1;
        System.out.printf("%-10s %-10s %s%n", "Option", "Name", "Email");
        for (Agency agency : this.controller.AgencyOptions()) {
            System.out.printf("%-10s %-10s %s%n", agency.getId(), agency.getName(), agency.getEmailAddress());
            i++;
        }
    }


    private void TransactionTypeOptions() {
        System.out.println(
                "Please select the business option you want:\n" +
                        "1- Sale\n" +
                        "2- Rent\n");
    }
    /**
     * Select Transaction Type
     * @param option
     * @return TransactionType
     */

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
    /**
     * Select Type of Property
     * @param option
     * @return TypeOfProperty
     */

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
    /**
     * Select Sun Exposure
     * @param option
     * @return SunExposure option
     */

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

    private void TypeOfSunExposureOptions() {
        System.out.println("Please insert the sun exposure:\n" +
                "1- North\n" +
                "2- South\n" +
                "3- East\n" +
                "4- West");
    }
    /**
     * Check if the passport number is valid
     * @param passportNumber
     * @return boolean
     */
    public boolean validatePassportNumber(String passportNumber) {
        if (passportNumber.length() != 9) {
            System.out.println("Invalid passport number!\n"+
                    "The passport number must have exactly 9 characters.");
            return false;
        }

        int i = 0;
        while (i < passportNumber.length()) {
            char c = passportNumber.charAt(i);
            if (!Character.isLetterOrDigit(c) || (Character.isLetter(c) && !Character.isUpperCase(c))) {
                System.out.println("Invalid passport number!\n"+
                        "The character is not a letter or a digit, or is a lowercase letter.");
                return false;
            }
            i++;
        }

        return true;
    }

    /**
     * list all the agents of an agency by id
     * @param id
     */

    private int listAgents(int id) {
        int control = 0;
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        ArrayList<Employee> agents = employeeRepository.getEmployeeList();
        for (Employee agent : agents) {
            if (agent.getAgency().getId() == id && agent.getRoles().equals(Role.AGENT)){
                System.out.printf("%-8s %15s%n", agent.getEmail() + " - " + agent.getName(), "");

                control++;                ;
            }
        }
        return control;
    }
    /**
     * Validates a yes or no input.
     * @param option the option to be displayed
     * @return true if the input is yes, false if the input is no
     */

    public boolean validationYN(String option) {
       String optionMenuToValidate = "";
        while (!optionMenuToValidate.equalsIgnoreCase("y") && !optionMenuToValidate.equalsIgnoreCase("n")) {
            System.out.println("Has "+option+" ? (y/n): ");

            optionMenuToValidate = sc.nextLine();
            if (!optionMenuToValidate.equalsIgnoreCase("y") && !optionMenuToValidate.equalsIgnoreCase("n")) {
                System.out.println("Please enter 'y' for yes or 'n' for no.");
            }
        }
        if (optionMenuToValidate.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Validates a positive integer input.
     * @param message the message to be displayed
     * @return the input
     */
    public int validatePositiveIntegerInput(String message) {
        int input = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println(message);
            try {
                input = sc.nextInt();
                if (input <= 0) {
                    System.out.println("Invalid input. Please enter a positive number.");
                } else {
                    isValidInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
        return input;
    }

    /**
     * Validates a positive double input.
     * @param message the message to be displayed
     * @return the input
     */
    public double validatePositiveDoubleInput(String message) {
        double input = 0.0;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println(message);
            try {
                input = sc.nextDouble();
                if (input <= 0) {
                    System.out.println("Invalid input. Please enter a positive number.");
                } else {
                    isValidInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
        return input;
    }
    private String validateNotEmpty(String prompt) {
        String input = "";
        do {
            System.out.println(prompt);
            input = sc.nextLine();
        } while (input.isEmpty());
        return input;
    }



}

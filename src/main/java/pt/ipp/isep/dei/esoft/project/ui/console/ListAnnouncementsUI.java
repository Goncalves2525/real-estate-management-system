package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateOrderController;
import pt.ipp.isep.dei.esoft.project.application.controller.ListAnnouncementsController;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.TransactionType;
import pt.ipp.isep.dei.esoft.project.domain.TypeOfProperty;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

/**
 * List Announcements UI class implements Runnable.
 * <p>
 * This class is responsible for creating the UI for feature of listing announcements.
 */

public class ListAnnouncementsUI implements Runnable {

    private final ListAnnouncementsController controller = new ListAnnouncementsController();


    /**
     * Creates a new instance of ListAnnouncementsUI.
     */

    public ListAnnouncementsUI() {
    }

    /**
     * Gets the ListAnnouncementsController.
     *
     * @return ListAnnouncementsController
     */
    private ListAnnouncementsController getController() {
        return controller;
    }

    /**
     * Runs the UI for listing announcements.
     * Asks user for filters and sort criteria and shows the announcements list.
     */

    public void run() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Announcement> announcementsList;
        TypeOfProperty typeOfProperty = null;
        TransactionType transactionType = null;
        Integer integerNumberOfRooms = -1;
        int numberOfRooms;
        int menu;
        String sortCriteria = null, order = null;
        Object[] filters;
        String[] sort;

        filters = requestFilters();
        sort = requestSortCriteria(sortCriteria, order);

        clearScreen();
        showOptions(filters, sort);
        sc.nextLine();

        typeOfProperty = (TypeOfProperty) filters[0];
        transactionType = (TransactionType) filters[1];
        integerNumberOfRooms = (Integer) filters[2];
        numberOfRooms = integerNumberOfRooms;
        sortCriteria = sort[0];
        order = sort[1];
        announcementsList = getAnnouncements(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
        showAnnouncements(announcementsList);
        if (userIsLoggedInWithClientRole()) {
            createOrder();
        }
        menuChoice();
    }

    private void menuChoice() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        System.out.println("1 - List Again");
        System.out.println("0 - Return to Main Menu");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Insert number please.");
            menuChoice();
        }
        switch (choice) {
            case 1:
                run();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option");
                menuChoice();
        }
    }

    private boolean userIsLoggedInWithClientRole() {
        if (getController().userIsLoggedInWithClientRole()) {
            return true;
        }
        return false;
    }

    private void createOrder() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to create an order? (Y/N)");
        try {
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                CreateOrderUI ui = new CreateOrderUI();
                ui.run();
            }
        } catch (InputMismatchException e) {
            System.out.println("Insert Y or N please.");
            createOrder();
        }
    }


    /**
     * Gets a list of announcements regarding the filters and sort criteria chosen by the user.
     *
     * @param typeOfProperty  - type of property
     * @param transactionType - transaction type
     * @param numberOfRooms   - number of rooms
     * @param sortCriteria    - sort criteria
     * @param order           - order
     * @return announcementsList
     */
    private ArrayList<Announcement> getAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        ArrayList<Announcement> announcementsList;
        AnnouncementListOptionType inputType = checkInputType(typeOfProperty, transactionType, numberOfRooms, sortCriteria);
        switch (inputType) {
            case NO_FILTER_NO_SORT:
                announcementsList = getController().getAllAnnouncementsSortedByDefualtCriteria();
                break;
            case ONLY_SORT:
                announcementsList = getController().getAllAnnouncementsSortedBySortCriteria(sortCriteria, order);
                break;
            case ONLY_FILTER:
                announcementsList = getController().getFilteredAnnouncements(typeOfProperty, transactionType, numberOfRooms);
                break;
            case FILTER_AND_SORT:
                announcementsList = getController().getFilteredAndSortedAnnouncements(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
                break;
            default:
                announcementsList = null;
        }
        return announcementsList;
    }

    /**
     * Checks the choice the user made to list announcements.
     *
     * @param typeOfProperty  - type of property
     * @param transactionType - transaction type
     * @param numberOfRooms   - number of rooms
     * @param sortCriteria    - sort criteria
     * @return inputType
     */
    private AnnouncementListOptionType checkInputType(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria) {
        AnnouncementListOptionType inputType;
        if (typeOfProperty == null && transactionType == null && numberOfRooms == -1 && sortCriteria == null) {
            inputType = AnnouncementListOptionType.NO_FILTER_NO_SORT;
        } else if (typeOfProperty == null && transactionType == null && numberOfRooms == -1) {
            inputType = AnnouncementListOptionType.ONLY_SORT;
        } else if (sortCriteria == null) {
            inputType = AnnouncementListOptionType.ONLY_FILTER;
        } else {
            inputType = AnnouncementListOptionType.FILTER_AND_SORT;
        }
        return inputType;
    }

    /**
     * Shows the users choice for listing announcements.
     *
     * @param filters - filters
     * @param sort    - sort criterias
     */
    private void showOptions(Object[] filters, String[] sort) {

        System.out.println("You have chosen these options:\n");
        if (filters[0] == null) {
            System.out.println("Type of Property: None");
        } else {
            System.out.println("Type of Property: " + filters[0]);
        }
        if (filters[1] == null) {
            System.out.println("Type of Transaction: None");
        } else {
            System.out.println("Type of Transaction: " + filters[1]);
        }
        if (filters[2].equals(-1)) {
            System.out.println("Number of Rooms: None");
        } else {
            System.out.println("Number of Rooms: " + filters[2]);
        }
        if (sort[0] == null) {
            System.out.println("Sort Criteria: None");
        } else {
            System.out.println("Sort Criteria: " + sort[0]);
            System.out.println("Order: " + sort[1]);
        }
        System.out.println();

        if (filters[0] == null && filters[1] == null && filters[2].equals(-1) && sort[0] == null) {
            System.out.println("All properties will be listed and sorted by most recently added.");
        } else if (filters[0] != null && filters[1] != null && !filters[2].equals(-1) && sort[0] != null) {
            System.out.println("Properties will be listed using your filters and sort criteria");
        } else if (filters[0] == null && filters[1] == null && filters[2].equals(-1) && sort[0] != null) {
            System.out.println("All properties will be listed using your sort criteria.");
        } else if (filters[0] != null && filters[1] != null && !filters[2].equals(-1) && sort[0] == null) {
            System.out.println("Properties will be listed using your filters and sorted by most recently added.");
        }


        System.out.println("(Press any key to continue...)");
    }

    /**
     * Requests filter options from the user.
     *
     * @return filters
     */
    private Object[] requestFilters() {
        boolean invalidFilters;
        Object[] filters;

        clearScreen();
        TypeOfProperty typeOfProperty;
        TransactionType transactionType;
        Integer numberOfRooms;
        do {
            int[] filterValidater = {0, 0, 0};
            System.out.println("PLEASE CHOSE FILTERS AND SORT CRITERIA FOR THE LIST OF PROPERTIES\n" +
                    "(You must choose all 3 filters or none)");
            showTypeOfPropertyOptions();
            typeOfProperty = getTypeOfPropertyFilterOption(filterValidater);
            showTypeOfBusinessOptions();
            transactionType = getTypeOfTransactionFilterOption(filterValidater);
            showNumberOfRoomsOptions();
            numberOfRooms = getNumberOfRoomsFilterOption(filterValidater);

            invalidFilters = verifyFilters(filterValidater);
        } while (invalidFilters);

        filters = writeToArray(typeOfProperty, transactionType, numberOfRooms);

        return filters;
    }

    /**
     * Validates users input for choosing type of property.
     *
     * @param filterValidater - filter validater
     * @return typeOfProperty
     */
    private TypeOfProperty getTypeOfPropertyFilterOption(int[] filterValidater) {
        Scanner sc = new Scanner(System.in);
        TypeOfProperty typeOfProperty;

        try {
            typeOfProperty = checkTypeOfProperty(filterValidater, sc.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please use numbers only.");
            typeOfProperty = getTypeOfPropertyFilterOption(filterValidater);
            sc.nextLine();
        }

        return typeOfProperty;
    }

    /**
     * Validates users input for choosing type of transaction.
     *
     * @param filterValidater - filter validater
     * @return transactionType
     */
    private TransactionType getTypeOfTransactionFilterOption(int[] filterValidater) {
        Scanner sc = new Scanner(System.in);
        TransactionType transactionType;

        try {
            transactionType = checkTransactionType(filterValidater, sc.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please use numbers only.");
            transactionType = getTypeOfTransactionFilterOption(filterValidater);
            sc.nextLine();
        }
        return transactionType;
    }

    /**
     * Validates users input for choosing number of rooms.
     *
     * @param filterValidater - filter validater
     * @return numberOfRooms
     */
    private Integer getNumberOfRoomsFilterOption(int[] filterValidater) {
        Scanner sc = new Scanner(System.in);
        Integer numberOfRooms;

        try {
            numberOfRooms = checkNumberOfRooms(filterValidater, sc.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please use numbers only.");
            numberOfRooms = getNumberOfRoomsFilterOption(filterValidater);
            sc.nextLine();
        }
        return numberOfRooms;
    }

    /**
     * Validates users input for choosing sort criteria.
     *
     * @param sortCriteria - sort criteria
     * @param order        - order
     * @return sort
     */
    private String[] requestSortCriteria(String sortCriteria, String order) {
        String[] sort = new String[2];
        Scanner sc = new Scanner(System.in);
        boolean exception;

        clearScreen();
        showSortOptions();
        do {
            exception = false;
            try {
                sortCriteria = checkSortCriteria(sc.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please use numbers only.");
                exception = true;
                sc.nextLine();
            }
        } while (exception);

        if (Objects.equals(sortCriteria, "price") || Objects.equals(sortCriteria, "state") || Objects.equals(sortCriteria, "city")) {
            clearScreen();
            do {
                showOrderOptions();
                do {
                    exception = false;
                    try {
                        order = checkOrder(sc.nextInt());
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please use numbers only.");
                        exception = true;
                        sc.nextLine();
                    }
                } while (exception);
                verifyOrder(order);
            } while (order == null);
        }
        sort[0] = sortCriteria;
        sort[1] = order;

        return sort;
    }

    /**
     * Writes filter options to an array of filters.
     *
     * @param typeOfProperty  - type of property
     * @param transactionType - transaction type
     * @param numberOfRooms   - number of rooms
     * @return filters
     */
    private Object[] writeToArray(TypeOfProperty typeOfProperty, TransactionType transactionType, Integer numberOfRooms) {
        Object[] filters = new Object[3];
        filters[0] = typeOfProperty;
        filters[1] = transactionType;
        filters[2] = numberOfRooms;
        return filters;
    }

    /**
     * Shows Type of Property options.
     */
    private void showTypeOfPropertyOptions() {
        System.out.println("------------------\n" +
                "|     FILTERS    |\n" +
                "------------------\n\n" +

                "Type of Property:\n" +
                "1- House\n" +
                "2- Apartment\n" +
                "3- Land" +
                "(Insert any other number to skip this filter)");
    }

    /**
     * Checks type of property filter chosen by user.
     *
     * @param filterValidater - filter validater
     * @param option          - option
     * @return typeOfProperty
     */
    private TypeOfProperty checkTypeOfProperty(int[] filterValidater, int option) {
        switch (option) {
            case 1:
                filterValidater[0] = 1;
                return TypeOfProperty.HOUSE;
            case 2:
                filterValidater[0] = 1;
                return TypeOfProperty.APARTMENT;
            case 3:
                filterValidater[0] = 1;
                return TypeOfProperty.LAND;
            default:
                return null;
        }
    }

    /**
     * Shows Type of Business options.
     */
    private void showTypeOfBusinessOptions() {
        System.out.println("------------------\n" +
                "|     FILTERS    |\n" +
                "------------------\n\n" +

                "Type of Business:\n" +
                "1- Sale\n" +
                "2- Rent\n" +
                "(Insert any other number to skip this filter)");

    }

    /**
     * Checks type of business filter chosen by user.
     *
     * @param filterValidater - filter validater
     * @param option          - option
     * @return transactionType
     */
    private TransactionType checkTransactionType(int[] filterValidater, int option) {
        switch (option) {
            case 1:
                filterValidater[1] = 1;
                return TransactionType.SALE;
            case 2:
                filterValidater[1] = 1;
                return TransactionType.RENT;
            default:
                return null;
        }
    }

    /**
     * Shows Number of Rooms options.
     */
    private void showNumberOfRoomsOptions() {
        System.out.println("------------------\n" +
                "|     FILTERS    |\n" +
                "------------------\n\n" +

                "Number of rooms:\n" +
                "1- T1\n" +
                "2- T2\n" +
                "3- T3\n" +
                "4- T4\n" +
                "5- T5\n" +
                "(Insert any other number to skip this filter)");
    }

    /**
     * Checks number of rooms filter chosen by user.
     *
     * @param filterValidater - filter validater
     * @param option          - option
     * @return numberOfRooms
     */
    private int checkNumberOfRooms(int[] filterValidater, int option) {
        switch (option) {
            case 1:
                filterValidater[2] = 1;
                return 1;
            case 2:
                filterValidater[2] = 1;
                return 2;
            case 3:
                filterValidater[2] = 1;
                return 3;
            case 4:
                filterValidater[2] = 1;
                return 4;
            case 5:
                filterValidater[2] = 1;
                return 5;
            default:
                return -1;
        }
    }

    /**
     * Shows Sort Criteria options.
     */
    private void showSortOptions() {
        System.out.println("------------------\n" +
                "|  Sort Criteria  |\n" +
                "------------------\n\n" +

                "1- Price\n" +
                "2- State\n" +
                "3- City\n" +
                "(Insert another number to skip this option)");
    }

    /**
     * Checks sort criteria chosen by user.
     *
     * @param option - option
     * @return sortCriteria
     */
    private String checkSortCriteria(int option) {
        switch (option) {
            case 1:
                return "price";
            case 2:
                return "state";
            case 3:
                return "city";
            default:
                return null;
        }
    }

    /**
     * Shows Order options.
     */
    private void showOrderOptions() {
        System.out.println("------------------\n" +
                "|    Sort Order   |\n" +
                "------------------\n\n" +

                "1- Ascending\n" +
                "2- Descending\n");
    }

    /**
     * Checks sort order chosen by user.
     *
     * @param option - option
     * @return order
     */
    private String checkOrder(int option) {
        switch (option) {
            case 1:
                return "ascending";
            case 2:
                return "descending";
            default:
                return null;
        }
    }

    /**
     * Shows announcements to user using filters and the sort criteria he chose.
     *
     * @param publishedPropertiesList - published properties list
     */
    private void showAnnouncements(ArrayList<Announcement> publishedPropertiesList) {
        System.out.println("-------------------------\n" +
                "|  Published Properties  |\n" +
                "-------------------------\n\n");

        for (Announcement announcement : publishedPropertiesList) {
            System.out.println("-----------------------------");
            System.out.print(announcement.toString());
            System.out.println(controller.getPropertyByAnnouncement(announcement));
            System.out.println("-----------------------------\n");

        }
    }

    /**
     * This method is used to verify if the user has chosen all filters or none.
     *
     * @param filterValidater - filter validater
     * @return notValid
     */
    private boolean verifyFilters(int[] filterValidater) {
        boolean notValid = true;
        int counter = 0;

        for (int i = 0; i < filterValidater.length; i++) {
            if (filterValidater[i] == 1) {
                counter++;
            }
        }
        if (counter == 0 || counter == 3) {
            notValid = false;
        } else {
            System.out.println("You must choose all filters or none.");
        }
        return notValid;
    }

    /**
     * This method is used to verify if the user has chosen a sort order.
     *
     * @param order - order
     */
    private void verifyOrder(String order) {
        if (order == null) {
            System.out.println("You must choose an order.");
        }
    }

    /**
     * Simulates clearing the screen by printing 50 blank lines.
     */
    private void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


}

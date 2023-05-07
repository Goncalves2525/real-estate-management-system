package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateOrderController;
import pt.ipp.isep.dei.esoft.project.application.controller.ListAnnouncementsController;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.TransactionType;
import pt.ipp.isep.dei.esoft.project.domain.TypeOfProperty;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

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

        announcementsList = chooseOption();

        showAnnouncements(announcementsList);

        if (userIsLoggedInWithClientRole()) {
            createOrder();
        }
        menuChoice();
    }

    private ArrayList<Announcement> chooseOption() {
        ArrayList<Announcement> announcementsList = new ArrayList<>();
        TypeOfProperty typeOfProperty;
        TransactionType transactionType;
        Integer integerNumberOfRooms;
        int numberOfRooms;
        String sortCriteria = null, order = null;
        Object[] filters;
        String[] sort;
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose an option: ");
        System.out.println("1 - No filter and no sort");
        System.out.println("2 - Only Filter");
        System.out.println("3 - Only Sort");
        System.out.println("4 - Filter and Sort");
        System.out.println();
        System.out.println("0 - Return to Main Menu");

        int option = -1;
        try {
            option = sc.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Insert a number please.");
            sc.nextLine();
            return chooseOption();
        }

        switch (option) {
            case 1:
                return getController().getAllAnnouncementsSortedByDefualtCriteria();
            case 2:
                filters = requestFilters();
                typeOfProperty = (TypeOfProperty) filters[0];
                transactionType = (TransactionType) filters[1];
                integerNumberOfRooms = (Integer) filters[2];
                numberOfRooms = integerNumberOfRooms;
                announcementsList = getController().getFilteredAnnouncements(typeOfProperty, transactionType, numberOfRooms);
                break;
            case 3:
                sort = requestSortCriteria();
                sortCriteria = sort[0];
                order = sort[1];
                announcementsList = getController().getAllAnnouncementsSortedBySortCriteria(sortCriteria, order);
                break;
            case 4:
                filters = requestFilters();
                sort = requestSortCriteria();
                typeOfProperty = (TypeOfProperty) filters[0];
                transactionType = (TransactionType) filters[1];
                integerNumberOfRooms = (Integer) filters[2];
                numberOfRooms = integerNumberOfRooms;
                sortCriteria = sort[0];
                order = sort[1];
                announcementsList = getController().getFilteredAndSortedAnnouncements(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
                break;
            case 0:
                MainMenuUI menu = new MainMenuUI();
                menu.run();
                break;
            default:
                System.out.println("Invalid option");
                return chooseOption();
        }
        return announcementsList;
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
     * Requests filter options from the user.
     *
     * @return filters
     */
    private Object[] requestFilters() {
        Scanner sc = new Scanner(System.in);
        boolean invalidFilters;
        Object[] filters;
        int propertyOption;
        int transactionOption;
        int numberOfRoomsOption;
        TypeOfProperty typeOfProperty;
        TransactionType transactionType;
        Integer numberOfRooms;
        do {
            int[] filterValidater = {0, 0, 0};
            System.out.println("PLEASE CHOSE FILTERS FOR THE LIST OF PROPERTIES\n" +
                    "(You must choose all 3 filters or none)");
            showTypeOfPropertyOptions();
            try {
                propertyOption = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please use numbers only.");
                sc.nextLine();
                return requestFilters();
            }

            switch (propertyOption){
                case 1:
                    typeOfProperty = TypeOfProperty.HOUSE;
                    filterValidater[0] = 1;
                    break;
                case 2:
                    typeOfProperty = TypeOfProperty.APARTMENT;
                    filterValidater[0] = 1;
                    break;
                case 3:
                    typeOfProperty = TypeOfProperty.LAND;
                    filterValidater[0] = 1;
                    break;
                case 0:
                    run();
                default:
                    System.out.println("Invalid option");
                    return requestFilters();
            }

            showTypeOfBusinessOptions();

            try {
                transactionOption = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please use numbers only.");
                sc.nextLine();
                return requestFilters();
            }

            switch (transactionOption){
                case 1:
                    transactionType = TransactionType.SALE;
                    filterValidater[1] = 1;
                    break;
                case 2:
                    transactionType = TransactionType.RENT;
                    filterValidater[1] = 1;
                    break;
                case 0:
                    run();
                default:
                    System.out.println("Invalid option");
                    return requestFilters();
            }

            showNumberOfRoomsOptions();

            try {
                numberOfRoomsOption = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please use numbers only.");
                sc.nextLine();
                return requestFilters();
            }

            switch (numberOfRoomsOption){
                case 1:
                    numberOfRooms = 1;
                    filterValidater[2] = 1;
                    break;
                case 2:
                    numberOfRooms = 2;
                    filterValidater[2] = 1;
                    break;
                case 3:
                    numberOfRooms = 3;
                    filterValidater[2] = 1;
                    break;
                case 4:
                    numberOfRooms = 4;
                    filterValidater[2] = 1;
                    break;
                case 5:
                    numberOfRooms = 5;
                    filterValidater[2] = 1;
                    break;
                case 0:
                    run();
                default:
                    System.out.println("Invalid option");
                    return requestFilters();
            }

            invalidFilters = verifyFilters(filterValidater);
        } while (invalidFilters);

        filters = writeToArray(typeOfProperty, transactionType, numberOfRooms);

        return filters;
    }


    /**
     * Validates users input for choosing sort criteria.
     *
     * @return sort
     */
    private String[] requestSortCriteria() {
        String[] sort = new String[2];
        String sortCriteria = "";
        String order = "";
        Scanner sc = new Scanner(System.in);
        int sortOption = -1;
        int orderOption = -1;

        System.out.println("PLEASE CHOSE A SORT CRITERIA FOR THE LIST OF PROPERTIES");
        showSortOptions();
        try {
            sortOption = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please use numbers only.");
            sc.nextLine();
            return requestSortCriteria();

        }

        switch (sortOption){
            case 1:
                sortCriteria = "price";
                sort[0] = sortCriteria;
                break;
            case 2:
                sortCriteria = "state";
                sort[0] = sortCriteria;
                break;
            case 3:
                sortCriteria = "city";
                sort[0] = sortCriteria;
                break;
            case 0:
                run();
            default:
                System.out.println("Invalid input.");
                return requestSortCriteria();
        }

        showOrderOptions();

        try {
            orderOption = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please use numbers only.");
            sc.nextLine();
            return requestSortCriteria();
        }

        switch (orderOption){
            case 1:
                order = "ascending";
                sort[1] = order;
                break;
            case 2:
                order = "descending";
                sort[1] = order;
                break;
            case 0:
                run();
            default:
                System.out.println("Invalid input.");
                return requestSortCriteria();
        }

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
                "3- Land\n\n" +
                "0- Exit\n");
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
                "2- Rent\n\n" +
                "0- Exit\n");

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
                "5- T5\n\n" +
                "0 - Exit\n");
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
                "3- City\n\n" +
                "0- Exit\n");
    }

    /**
     * Shows Order options.
     */
    private void showOrderOptions() {
        System.out.println("------------------\n" +
                "|    Sort Order   |\n" +
                "------------------\n\n" +

                "1- Ascending\n" +
                "2- Descending\n\n" +
                "0- Exit\n");
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
     * Simulates clearing the screen by printing 50 blank lines.
     */
    private void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


}

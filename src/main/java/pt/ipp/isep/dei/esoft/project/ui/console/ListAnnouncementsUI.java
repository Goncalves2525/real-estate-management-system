package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListAnnouncementsController;
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
 * The type List announcements ui.
 */

public class ListAnnouncementsUI implements Runnable {

    private final ListAnnouncementsController controller = new ListAnnouncementsController();

    public ListAnnouncementsUI() {
    }

    private ListAnnouncementsController getController() {
        return controller;
    }

    public void run() {
        ArrayList<Announcement> announcementsList;
        TypeOfProperty typeOfProperty = null;
        TransactionType transactionType = null;
        Integer integerNumberOfRooms = -1;
        int numberOfRooms;
        String sortCriteria = null, order = null;
        Object[] filters;
        String[] sort;

        filters = requestFilters();
        sort = requestSortCriteria(sortCriteria, order);
        clearScreen();

        typeOfProperty = (TypeOfProperty) filters[0];
        transactionType = (TransactionType) filters[1];
        integerNumberOfRooms = (Integer) filters[2];
        numberOfRooms = integerNumberOfRooms;
        sortCriteria = sort[0];
        order = sort[1];

        announcementsList = getAnnouncements(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
        showAnnouncements(announcementsList);
    }

    public ArrayList<Announcement> getAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        ArrayList<Announcement> announcementsList;
        AnnouncementListOptionType inputType = checkInputType(typeOfProperty, transactionType, numberOfRooms, sortCriteria);
        switch (inputType) {
            case NO_FILTER_NO_SORT:
                announcementsList = getController().sortAllAnnouncementsByDefaultCriteria();
                break;
            case ONLY_SORT:
                announcementsList = getController().sortAllAnnouncementsBySortCriteria(sortCriteria, order);
                break;
            case ONLY_FILTER:
                announcementsList = getController().filterAnnouncements(typeOfProperty, transactionType, numberOfRooms);
                break;
            case FILTER_AND_SORT:
                announcementsList = getController().filterAndSortAnnouncements(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
                break;
            default:
                announcementsList = null;
        }
        return announcementsList;
    }
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

    public TypeOfProperty getTypeOfPropertyFilterOption(int[] filterValidater){
        Scanner sc = new Scanner(System.in);
        TypeOfProperty typeOfProperty;

        try {
            typeOfProperty =  checkTypeOfProperty(filterValidater, sc.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please use numbers only.");
            typeOfProperty = getTypeOfPropertyFilterOption(filterValidater);
            sc.nextLine();
        }

        return typeOfProperty;
    }

    private TransactionType getTypeOfTransactionFilterOption(int[] filterValidater) {
        Scanner sc = new Scanner(System.in);
        TransactionType transactionType;

        try {
            transactionType =  checkTransactionType(filterValidater, sc.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please use numbers only.");
            transactionType = getTypeOfTransactionFilterOption(filterValidater);
            sc.nextLine();
        }
        return transactionType;
    }

    private Integer getNumberOfRoomsFilterOption(int[] filterValidater) {
        Scanner sc = new Scanner(System.in);
        Integer numberOfRooms;

        try {
            numberOfRooms =  checkNumberOfRooms(filterValidater, sc.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please use numbers only.");
            numberOfRooms = getNumberOfRoomsFilterOption(filterValidater);
            sc.nextLine();
        }
        return numberOfRooms;
    }


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

        if (Objects.equals(sortCriteria, "price") || Objects.equals(sortCriteria, "state")) {
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

    private Object[] writeToArray(TypeOfProperty typeOfProperty, TransactionType transactionType, Integer numberOfRooms) {
        Object[] filters = new Object[3];
        filters[0] = typeOfProperty;
        filters[1] = transactionType;
        filters[2] = numberOfRooms;
        return filters;
    }

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

    private void showTypeOfBusinessOptions() {
        System.out.println("------------------\n" +
                "|     FILTERS    |\n" +
                "------------------\n\n" +

                "Type of Business:\n" +
                "1- Sale\n" +
                "2- Rent\n" +
                "(Insert any other number to skip this filter)");

    }

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

    private void showSortOptions() {
        System.out.println("------------------\n" +
                "|  Sort Criteria  |\n" +
                "------------------\n\n" +

                "1- Price\n" +
                "2- State\n" +
                "(Insert another number to skip this option)");
    }

    private String checkSortCriteria(int option) {
        switch (option) {
            case 1:
                return "price";
            case 2:
                return "state";
            default:
                return null;
        }
    }

    private void showOrderOptions() {
        System.out.println("------------------\n" +
                "|    Sort Order   |\n" +
                "------------------\n\n" +

                "1- Ascending\n" +
                "2- Descending\n");
    }

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

    private void showAnnouncements(ArrayList<Announcement> publishedPropertiesList) {
        System.out.println("-------------------------\n" +
                "|  Published Properties  |\n" +
                "-------------------------\n\n");

        for (Announcement announcement : publishedPropertiesList) {
            System.out.println("-----------------------------");
            System.out.println(announcement.toString());
            System.out.println("-----------------------------\n");

        }
    }

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

    private void verifyOrder(String order) {
        if (order == null) {
            System.out.println("You must choose an order.");
        }
    }

    // Prints multiple empty lines to simulate a cleared screen
    private void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


}

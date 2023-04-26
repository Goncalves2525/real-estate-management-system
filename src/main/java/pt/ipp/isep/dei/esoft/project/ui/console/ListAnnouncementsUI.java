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

public class ListAnnouncementsUI implements Runnable {

    private final ListAnnouncementsController controller = new ListAnnouncementsController();

    public ListAnnouncementsUI() {
    }

    private ListAnnouncementsController getController() {
        return controller;
    }

    public void run() {
        TypeOfProperty typeOfProperty = null;
        TransactionType transactionType = null;
        Integer integerNumberOfRooms = -1;
        int numberOfRooms;
        String sortCriteria = null, order = null;
        Object[] filters;
        String[] sort;

        filters = requestFilters(typeOfProperty, transactionType, integerNumberOfRooms);
        sort = requestSortCriteria(sortCriteria, order);
        clearScreen();

        typeOfProperty = (TypeOfProperty) filters[0];
        transactionType = (TransactionType) filters[1];
        integerNumberOfRooms = (Integer) filters[2];
        numberOfRooms = integerNumberOfRooms;
        sortCriteria = sort[0];
        order = sort[1];

        ArrayList<Announcement> anouncementsList = getController().getAnnouncements(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
        showAnnouncements(anouncementsList);
    }

    private Object[] requestFilters(TypeOfProperty typeOfProperty, TransactionType transactionType, Integer numberOfRooms){
        Scanner sc = new Scanner(System.in);
        boolean invalidFilters;
        boolean exception;
        Object[] filters;

        clearScreen();
        do {
            int[] filterValidater = {0, 0, 0};
            System.out.println("PLEASE CHOSE FILTERS AND SORT CRITERIA FOR THE LIST OF PROPERTIES\n" +
                    "(You must choose all 3 filters or none)");
            showTypeOfPropertyOptions();
            do {
                exception = false;
                try {
                    typeOfProperty = checkTypeOfProperty(filterValidater, sc.nextInt());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please use numbers only.");
                    exception = true;
                    sc.nextLine();
                }
            }while(exception);

            showTypeOfBusinessOptions();
            do {
                exception = false;
                try {
                    transactionType = checkTransactionType(filterValidater, sc.nextInt());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please use numbers only.");
                    exception = true;
                    sc.nextLine();
                }
            }while(exception);

            clearScreen();
            showNumberOfRoomsOptions();

            do {
                exception = false;
                try {
                    numberOfRooms = checkNumberOfRooms(filterValidater, sc.nextInt());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please use numbers only.");
                    exception = true;
                    sc.nextLine();
                }
            }while(exception);

            invalidFilters = verifyFilters(filterValidater);
        } while (invalidFilters);

        filters = writeToArray(typeOfProperty, transactionType, numberOfRooms);

        return filters;
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
        }while(exception);

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
                }while (exception);
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
                "3- Land");
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
                "2- Rent\n");
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
                "5- T5\n");
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
                "2- State\n");
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

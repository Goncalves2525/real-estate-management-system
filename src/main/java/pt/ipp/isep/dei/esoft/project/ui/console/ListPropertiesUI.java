package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListPropertiesController;
import pt.ipp.isep.dei.esoft.project.domain.PublishedProperty;
import pt.ipp.isep.dei.esoft.project.domain.TransactionType;
import pt.ipp.isep.dei.esoft.project.domain.TypeOfProperty;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class ListPropertiesUI implements Runnable {

    private final ListPropertiesController controller = new ListPropertiesController();

    public ListPropertiesUI() {
    }

    private ListPropertiesController getController() {
        return controller;
    }

    public void run() {
        TypeOfProperty typeOfProperty;
        TransactionType transactionType;
        int numberOfRooms;
        String sortCriteria, order;
        Scanner sc = new Scanner(System.in);

        clearScreen();
        System.out.println("PLEASE CHOSE FILTERS AND SORT CRITERIAS FOR THE LIST OF PROPERTIES");
        showTypeOfPropertyOptions();
        typeOfProperty = checkTypeOfProperty(sc.nextInt());
        clearScreen();
        showTypeOfBusinessOptions();
        transactionType = checkTransactionType(sc.nextInt());
        clearScreen();
        showNumberOfRoomsOptions();
        numberOfRooms = checkNumberOfRooms(sc.nextInt());
        clearScreen();
        showSortOptions();
        sortCriteria = checkSortCriteria(sc.nextInt());
        clearScreen();
        showOrderOptions();
        order = checkOrder(sc.nextInt());
        clearScreen();

        ArrayList<PublishedProperty> publishedPropertiesList = getController().getPublishedProperties(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
        showPublishedProperties(publishedPropertiesList);

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

    private void showTypeOfBusinessOptions() {
        System.out.println("------------------\n" +
                "|     FILTERS    |\n" +
                "------------------\n\n" +

                "Type of Business:\n" +
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

    private int checkNumberOfRooms(int option) {
        switch (option) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
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

    private void showPublishedProperties(ArrayList<PublishedProperty> publishedPropertiesList) {
        System.out.println("-------------------------\n" +
                           "|  Published Properties  |\n" +
                           "-------------------------\n\n");

        for (PublishedProperty publishedProperty : publishedPropertiesList) {
            System.out.println("-----------------------------");
            System.out.println(publishedProperty.toString());
            System.out.println("-----------------------------\n");

        }
    }

    // Prints multiple empty lines to simulate a cleared screen
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


}

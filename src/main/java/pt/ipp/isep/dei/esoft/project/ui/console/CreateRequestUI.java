package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.TransactionType;
import pt.ipp.isep.dei.esoft.project.domain.TypeOfProperty;
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
        TypeOfProperty typeOfProperty;
        TransactionType transactionType;
        Property property;
        Scanner sc = new Scanner(System.in);

        System.out.println("Please insert the name of the agency: ");
        String agencyName = sc.nextLine();
        System.out.println("Please insert the name of the agent: ");
        String agentName = sc.nextLine();
        System.out.println("Please insert you passport ID: ");
        String passportID = sc.nextLine();
        TransactionTypeOptions();
        transactionType = checkTransactionType(sc.nextInt());

        if (transactionType.equals(TransactionType.RENT)) {
            System.out.println("Please insert contract duration: ");
            int contractDuration = sc.nextInt();
        }else {
                System.out.println("Please insert the minimum price: ");
                double minPrice = sc.nextDouble();
                System.out.println("Please insert the maximum price: ");
                double maxPrice = sc.nextDouble();
            }

        TypeOfPropertyOptions();
        typeOfProperty = checkTypeOfProperty(sc.nextInt());
        if (typeOfProperty.equals(TypeOfProperty.LAND)) {
            System.out.println("Please insert the area: ");
            double Area = sc.nextDouble();

        } else if (typeOfProperty.equals(TypeOfProperty.HOUSE)) {
            System.out.println("Please insert the number of rooms: ");
            int numberOfRooms = sc.nextInt();
            System.out.println("Please insert the number of bathrooms: ");
            int numberOfBathrooms = sc.nextInt();


        } else if (typeOfProperty.equals(TypeOfProperty.APARTMENT)) {
            System.out.println("Please insert the number of rooms: ");
            int numberOfRooms = sc.nextInt();
            System.out.println("Please insert the number of bathrooms: ");
            int numberOfBathrooms = sc.nextInt();




        }


    }



        private void TransactionTypeOptions() {
            System.out.println(
                    "Please insert the type of business:\n" +
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



    }





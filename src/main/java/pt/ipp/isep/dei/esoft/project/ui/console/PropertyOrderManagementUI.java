package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PropertyOrderManagementController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.EmployeeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PropertyOrderManagementUI implements Runnable {

    private final PropertyOrderManagementController controller;

    public PropertyOrderManagementUI() {
        controller = new PropertyOrderManagementController();
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        ArrayList<Announcement> announcements;
        try {
            announcements = controller.getAllAnnouncementsSortedBypropertyAndIdCriteria();
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving announcements: " + e.getMessage());
            return;
        }

        while (true) {
            showAnnouncements(announcements);
            System.out.println("Insert the id of the property you want to view orders:");
            int idAnnouncement;
            try {
                idAnnouncement = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid property id.");
                sc.nextLine();
                continue;
            }

            if (isValidAnnouncement(idAnnouncement)) {
                if (!showOrders(idAnnouncement)) {
                    System.out.println("There are no orders for this property\n");
                    break;
                }

                System.out.println("Please insert the order number you want to analyze:");
                int idOrder;
                try {
                    idOrder = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid order number.");
                    sc.nextLine();
                    continue;
                }

                while (!isValidOrder(idOrder, idAnnouncement)) {
                    System.out.println("Invalid order number. Please try again.");
                    try {
                        idOrder = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid order number.");
                        sc.nextLine();
                    }
                }

                orderAnalysis(idAnnouncement, idOrder);
                int option;

                do {
                    showOptions();
                    try {
                        option = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid option number.");
                        sc.nextLine();
                        option = -1;
                    }

                    if (option == 1) {
                        acceptOrder(idOrder, idAnnouncement);
                        rejectAllOrdersFromAnnouncement(idAnnouncement);
                        unpublishAnnouncement(idAnnouncement);

                        System.out.println("Order accepted and all other orders rejected");
                        controller.sendEmailToCliente(idOrder, idAnnouncement, "accepted");
                        controller.sendEmailToRejectedOrders(idAnnouncement);
                        menuChoice();
                        return;
                    } else if (option == 2) {
                        rejectOrder(idOrder, idAnnouncement);
                        System.out.println("Order rejected\n");
                        controller.sendEmailToCliente(idOrder, idAnnouncement, "rejected");

                        if (!controller.getOrdersByAnnouncementId(idAnnouncement).isEmpty()) {
                            showOrders(idAnnouncement);
                            System.out.println("Please insert the order number you want to analyze:");
                            try {
                                idOrder = sc.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid order number.");
                                sc.nextLine();
                                continue;
                            }

                            while (!isValidOrder(idOrder, idAnnouncement)) {
                                System.out.println("Invalid order number. Please try again.");
                                try {
                                    idOrder = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid order number.");
                                    sc.nextLine();
                                }
                            }
                        }
                    }
                } while (!controller.getOrdersByAnnouncementId(idAnnouncement).isEmpty());

                if (controller.getOrdersByAnnouncementId(idAnnouncement).isEmpty()) {
                    System.out.println("There are no more orders for this Announcement\n");

                    menuChoice();
                    return;
                }
            } else {
                System.out.println("Invalid property id. Please try again.");
            }
        }

        System.out.println("------- Press enter to return to Employee menu -------");
        sc.nextLine();
        sc.nextLine();
    }

    private boolean isValidAnnouncement(int idAnnouncement) {
        try {
            Announcement announcement = controller.getAnnouncementById(idAnnouncement);
            return announcement != null && announcement.getTransactionType() == TransactionType.SALE && announcement.isPublished();
        } catch (Exception e) {
            System.out.println("An error occurred while validating the announcement: " + e.getMessage());
            return false;
        }
    }

    private boolean isValidOrder(int idOrder, int idAnnouncement) {
        try {
            OrderState orderState = controller.getOrderStateById(idOrder, idAnnouncement);
            return !controller.getOrderById(idOrder, idAnnouncement).isEmpty() && orderState == OrderState.PENDING;
        } catch (Exception e) {
            System.out.println("An error occurred while validating the order: " + e.getMessage());
            return false;
        }
    }

    private void showAnnouncements(ArrayList<Announcement> publishedPropertiesList) {
        System.out.println("-------------------------\n" +
                "|  Published Properties  |\n" +
                "-------------------------\n");
        TransactionType desiredTransactionType = TransactionType.SALE;

        for (Announcement announcement : publishedPropertiesList) {
            if (announcement.getTransactionType() == desiredTransactionType) {
                System.out.println("-----------------------------");
                System.out.print(announcement.toString());
                System.out.println(controller.getPropertyByAnnouncement(announcement));
                System.out.println("-----------------------------\n");
            }
        }
    }

    private boolean showOrders(int id) {
        System.out.println("-------------------------\n" +
                "|  Existing Orders       |\n" +
                "-------------------------\n");

        ArrayList<Order> orders;
        try {
            orders = controller.getOrdersByAnnouncementId(id);
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving orders: " + e.getMessage());
            return false;
        }

        if (orders.isEmpty()) {
            return false;
        }

        for (Order order : orders) {
            System.out.println("-----------------------------");
            System.out.print(order.toString());
            System.out.println("-----------------------------\n");
        }

        return true;
    }

    private void showOptions() {
        System.out.println("1 - Accept Order");
        System.out.println("2 - Reject Order");
        System.out.println("3 - Back");
    }

    private void acceptOrder(int idOrder, int idAnnouncement) {
        try {
            controller.acceptOrder(idOrder, idAnnouncement);
        } catch (Exception e) {
            System.out.println("An error occurred while accepting the order: " + e.getMessage());
        }
    }

    private void rejectOrder(int idOrder, int idAnnouncement) {
        try {
            controller.rejectOrder(idOrder, idAnnouncement);
        } catch (Exception e) {
            System.out.println("An error occurred while rejecting the order: " + e.getMessage());
        }
    }

    private void rejectAllOrdersFromAnnouncement(int idAnnouncement) {
        try {
            controller.rejectAllOrdersFromAnnouncement(idAnnouncement);
        } catch (Exception e) {
            System.out.println("An error occurred while rejecting all orders from the announcement: " + e.getMessage());
        }
    }

    private void unpublishAnnouncement(int idAnnouncement) {
        try {
            controller.unpublishAnnouncement(idAnnouncement);
        } catch (Exception e) {
            System.out.println("An error occurred while unpublishing the announcement: " + e.getMessage());
        }
    }

    private void orderAnalysis(int idAnnouncement, int idOrder) {
        System.out.println("-------------------------\n" +
                "|  Order                 |\n" +
                "-------------------------\n");

        ArrayList<Order> orders;
        try {
            orders = controller.getOrderById(idOrder, idAnnouncement);
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving the order: " + e.getMessage());
            return;
        }

        if (orders.isEmpty()) {
            System.out.println("There is no order with that id");
        } else {
            for (Order order : orders) {
                System.out.println("-----------------------------");
                System.out.print(order.toString());
                System.out.println("-----------------------------\n");
            }
        }
    }

    private void menuChoice() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        System.out.println("1 - List Announcements Again");
        System.out.println("0 - Return to Main Menu");
        System.out.print("Choose an option: ");
        System.out.println();
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            sc.nextLine();
            menuChoice();
            return;
        }
        switch (choice) {
            case 1:
                run();
                break;
            case 0:
                MainMenuUI menu = new MainMenuUI();
                break;
            default:
                System.out.println("Invalid option");
                menuChoice();
        }
    }

}

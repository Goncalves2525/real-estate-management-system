package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PropertyOrderManagementController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Order;

import java.util.*;

public class PropertyOrderManagementUI implements Runnable {

    private final PropertyOrderManagementController controller;
    private ArrayList<Order> order;

    /**
     * Instantiates a controller for Property Order Management.
     */
    public PropertyOrderManagementUI() {
        controller = new PropertyOrderManagementController();
    }

    @Override
    public void run() {

        Scanner sc = new Scanner(System.in);
        int idAnnouncement;
        int idOrder;
        int option;

        ArrayList<Announcement> announcements = controller.getAllAnnouncements();

        showAnnouncements(announcements);

        System.out.println("Insert the id of the property you want to view orders:");
        idAnnouncement = sc.nextInt();
        showOrders(idAnnouncement);
        System.out.println("Please insert a number of the order you want to analyze:");
        idOrder = sc.nextInt();
        showOrderByID(idOrder);
        showOptions();
        option = sc.nextInt();
        if (option == 1) {
            //controller.acceptOrder(idOrder);
            System.out.println("Order accepted");
        } else if (option == 2) {
            //controller.rejectOrder(idOrder);
            System.out.println("Order rejected");
        }
    }
    /**
     * Shows the announcements.
     *
     * @param publishedPropertiesList published properties list
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
     * Shows the orders.
     *
     * @param id id
     */
    private void showOrders(int id) {
        System.out.println("-------------------------\n" +
                "|  Existing Orderes      |\n" +
                "-------------------------\n\n");
        for (Order order : controller.getOrdersByAnnouncementId(id)) {
            System.out.println("-----------------------------");
            System.out.print(order.toString());
            System.out.println("-----------------------------\n");
        }
    }

    private void showOrderByID(int id) {
        System.out.println("-------------------------\n" +
                "|  Order     |\n" +
                "-------------------------\n\n");
        for (Order order : controller.getOrderById(id)) {
            System.out.println("-----------------------------");
            System.out.print(order.toString());
            System.out.println("-----------------------------\n");
        }
    }

    private void showOptions() {
        System.out.println("1 - Accept Order");
        System.out.println("2 - Reject Order");
        System.out.println("3 - Back");
    }
}


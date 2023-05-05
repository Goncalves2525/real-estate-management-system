package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Order;

import java.util.ArrayList;

/**
 * This class represents an Order Repository.
 */
public class OrderRepository {
    private ArrayList<Order> orders = new ArrayList<>();

    /**
     * This method checks if this client already made an order for a specific announcement.
     * @param clientEmail  current user email
     * @param announcementId announcement id
     * @return true if this client already made order for this announcement, false otherwise
     */
    public boolean clientAlreadyMadeOrderForThisAnnouncement(String clientEmail, int announcementId) {
        for (Order order : orders) {
            if (order.getClientEmail().equals(clientEmail) && order.getAnnouncementId() == announcementId) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks if someone already made an order with same amount for a specific announcement.
     * @param orderAmount amount of the order
     * @param announcementId id of the announcement
     * @return true if someone already made order with same amount for this announcement, false otherwise
     */
    public boolean someoneAlreadyMadeOrderWithSameAmountForThisAnnouncement(double orderAmount, int announcementId) {
        for (Order order : orders) {
            if (order.getOrderAmount() == orderAmount && order.getAnnouncementId() == announcementId) {
                return true;
            }
        }
        return false;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }


}

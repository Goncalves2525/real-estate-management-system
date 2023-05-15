package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.OrderState;

import java.util.ArrayList;
import java.util.Date;

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

    public void addOrder(double orderAmount, int announcementId, String clientEmail, Date date, OrderState orderState) {
        ArrayList<Announcement> announcements = Repositories.getInstance().announcementRepository.getAllAnnouncementsSortedByDefualtCriteria();
        boolean announcementExists = false;
        for (Announcement announcement : announcements) {
            if (announcement.getId() == announcementId) {
                announcementExists = true;
            }
        }
        if (announcementExists) {
            Order order = new Order(orderAmount, announcementId, clientEmail, date, orderState);
            orders.add(order);
        }
        else {
            System.out.println("Announcement with id " + announcementId + " does not exist.");
        }
    }


    public void removeAllOrders(){
        orders.clear();
    }

    public int getSize(){
        return orders.size();
    }



}

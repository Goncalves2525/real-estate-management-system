package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents an Order made by a Client
 */
public class Order extends ArrayList<Order> {
    private final int id;

    private static int idCounter = 1;

    private double orderAmount;

    private int announcementId;

    private String clientEmail;

    private Date date;

    private OrderState state;


    /**
     * @param orderAmount amount of the order
     * @param announcementId id of the announcement
     * @param clientEmail email of the client
     */
    public Order(double orderAmount, int announcementId, String clientEmail, Date date, OrderState state) {
        this.orderAmount = orderAmount;
        this.announcementId = announcementId;
        this.clientEmail = clientEmail;
        this.state = state;
        id = idCounter++;
    }


    /**
     * @return the amount of the order
     */
    public double getOrderAmount() {
        return orderAmount;
    }

    /**
     * @param orderAmount amount of the order
     *
     */
    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * @return the id of the announcement
     */
    public int getAnnouncementId() {
        return announcementId;
    }

    /**
     * @param announcementId id of the announcement
     */
    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

    /**
     * @return the email of the client
     */
    public String getClientEmail() {
        return clientEmail;
    }

    /**
     * @param clientEmail email of the client
     */
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    /**
     * @return the date of the order
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date date of the order
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the state of the order
     */
    public OrderState getState() {
        return state;
    }


    /**
     * @param state state of the order
     */
    public void setState(OrderState state) {
        this.state = state;
    }

    /**
     * @return the id of the order
     */
    public int getId() {return id;
    }


    @Override
    public String toString() {
        return String.format("Order ID: %d%nOrder Amount: %.2f%nClient Email: %s%nDate: %s%n", id, orderAmount, clientEmail, date);

    }
}

package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

/**
 * This class represents an Order made by a Client
 */
public class Order {
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
        id = idCounter++;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderAmount=" + orderAmount +
                ", announcementId=" + announcementId +
                ", clientEmail='" + clientEmail + '\'' +
                '}';
    }
}

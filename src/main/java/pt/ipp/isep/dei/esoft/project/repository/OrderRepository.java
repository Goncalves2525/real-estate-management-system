package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.OrderState;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * This class represents an Order Repository.
 */
public class OrderRepository implements Serializable{
    private ArrayList<Order> orders = new ArrayList<>();

    public ArrayList<Order> getOrders() {
        return orders;
    }


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

    /**
     * Creates and adds an order to the repository.
     * @param orderAmount amount of the order
     * @param announcementId id of the announcement
     * @param clientEmail current user email
     * @param date date of the order
     * @param orderState state of the order
     */
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


    /**
     * This method removes all orders from the repository.
     */
    public void removeAllOrders(){
        orders.clear();
    }

    /**
     * @return size of the repository
     */
    public int getSize(){
        return orders.size();
    }


    /**
     * Adds an order that already exists to the repository.
     * @param order order
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * @param id id of the order
     * @param idAnnouncement id of the announcement
     * @return order by id
     */
    public ArrayList<Order> getOrderByID(int id,int idAnnouncement) {
        ArrayList<Order> ordersByID = new ArrayList<>();
        for (Order order : orders) {
            if (order.getId() == id && order.getAnnouncementId()==idAnnouncement) {
                ordersByID.add(order);
            }
        }
        return ordersByID;
    }
    /**
     * This method gets all orders by AnnouncementId and sorts them by amount.
     * @param id announcement id
     * @return all orders by AnnouncementId and sorts them by amount
     */
    public ArrayList<Order> getOrdersByAnnouncementId(int id) {
        ArrayList<Order> ordersByAnnouncementId = new ArrayList<>();

        for (Order order : orders) {
            try {

                if (order.getAnnouncementId() == id && order.getState().equals(OrderState.PENDING)) {
                    ordersByAnnouncementId.add(order);
                }
            } catch (NullPointerException e) {
            }
            Collections.sort(ordersByAnnouncementId, new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return Double.compare(o2.getOrderAmount(), o1.getOrderAmount());
                }
            });

        }
        return ordersByAnnouncementId;
    }


    /**
     * @param idOrder id of the order
     * @param idAnnouncement id of the announcement
     */
    public void acceptOrder(int idOrder, int idAnnouncement) {
        for (Order order : orders) {
            if (order.getId() == idOrder && order.getAnnouncementId() == idAnnouncement) {
                order.setState(OrderState.ACCEPTED);
            }
        }
    }

    /**
     * @param idOrder id of the order
     * @param idAnnouncement id of the announcement
     */
    public void rejectOrder(int idOrder, int idAnnouncement) {
        for (Order order : orders) {
            if (order.getId() == idOrder && order.getAnnouncementId() == idAnnouncement) {
                order.setState(OrderState.REJECTED);
            }
        }
    }

    /**
     * @param idAnnouncement id of the announcement
     */
    public void rejectAllOrdersFromAnnouncement(int idAnnouncement) {
       // ArrayList<Order> ordersByAnnouncementId = new ArrayList<>();
        for (Order order : orders) {
            try {
                    if (order.getAnnouncementId()==idAnnouncement) {
                        order.setState(OrderState.REJECTED);
                    }

            } catch (NullPointerException e) {
            }

        }

    }

    /**
     * @param idOrder id of the order
     * @param idAnnouncement id of the announcement
     * @return state of the order
     */
    public OrderState getOrderStateById(int idOrder, int idAnnouncement) {
        for (Order order : orders) {
            if (order.getId() == idOrder && order.getAnnouncementId() == idAnnouncement) {
                return order.getState();
            }
        }
        return null;
    }

    public void serialize(String fileName) {
        try{
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(orders);

            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize(String fileName) {
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            orders = (ArrayList<Order>) in.readObject();

            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.OrderState;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PropertyOrderManagementController {
    private PropertyRepository propertyRepository = null;
    private PropertyRepository propertyDealRepository = null;
    private OrderRepository orderRepository = null;
    private AnnouncementRepository announcementRepository;
    private AuthenticationRepository authenticationRepository;


    public PropertyOrderManagementController() {
        getPropertyRepository();
        getOrderRepository();
        getAnnouncementRepository();
        getPropertyDealRepository();
    }

    private void getPropertyDealRepository() {
        if (propertyDealRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyDealRepository = repositories.getPropertyDealRepository();
        }
    }

    private void getPropertyRepository() {
        if (propertyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyRepository = repositories.getPropertyRepository();
        }
    }

    private OrderRepository getOrderRepository() {
        if (orderRepository == null) {
            Repositories repositories = Repositories.getInstance();
            orderRepository = repositories.getOrderRepository();
        }
        return orderRepository;
    }
    public List<Announcement> getAnnouncementsByUser(){
        String userEmail = authenticationRepository.getCurrentUserSession().getUserId().getEmail();
        return announcementRepository.getAnnouncementsByAgent(userEmail);
    }
    public Property getPropertyByAnnouncement(Announcement announcement){
        return announcementRepository.getPropertyByAnnouncement(announcement);
    }
    public Announcement getAnnouncementById(int id){
        try {
            return announcementRepository.getAnnouncementById(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    private AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }

    public ArrayList<Announcement> getAllAnnouncements(){
        return getAnnouncementRepository().getAllAnnouncementsSortedByDefualtCriteria();
    }

    public ArrayList<Order> getOrdersByAnnouncementId(int id) {
        return getOrderRepository().getOrdersByAnnouncementId(id);
    }

    public ArrayList<Order> getOrderById(int id,int idAnnouncement) {
        return getOrderRepository().getOrderByID(id,idAnnouncement);
    }

    public ArrayList<Announcement> getAllAnnouncementsSortedBypropertyAndIdCriteria() {
        return getAnnouncementRepository().getAllAnnouncementsSortedBypropertyAndIdCriteria();
    }

    public void acceptOrder(int idOrder, int idAnnouncement) {
        getOrderRepository().acceptOrder(idOrder,idAnnouncement);
    }

    public void rejectOrder(int idOrder, int idAnnouncement) {
        getOrderRepository().rejectOrder(idOrder,idAnnouncement);
    }

    public void rejectAllOrdersFromAnnouncement(int idAnnouncement) {
        getOrderRepository().rejectAllOrdersFromAnnouncement(idAnnouncement);
    }
    public void sendEmailToRejectedOrders(int idAnnouncement) {
        for (Order order : orderRepository.getOrders()) {
            try {
                if (order.getAnnouncementId() == idAnnouncement && order.getState().equals(OrderState.REJECTED)) {
                    sendEmailToCliente(order.getId(), order.getAnnouncementId(), "rejected");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }





    public void unpublishAnnouncement(int idAnnouncement) {
        getAnnouncementRepository().unpublishAnnouncement(idAnnouncement);
    }

    public OrderState getOrderStateById(int idOrder, int idAnnouncement) {
        return getOrderRepository().getOrderStateById(idOrder,idAnnouncement);
    }

    public void sendEmailToCliente(int idOrder, int idAnnouncement, String decison) {
        try {
            Order order = getOrderById(idOrder, idAnnouncement).get(0);
            Announcement announcement = getAnnouncementById(idAnnouncement);
            Property property = getPropertyByAnnouncement(announcement);
            String email = order.getClientEmail();
            String text = "Dear customer,\n\n" +
                    "Your order for the property " + property.getAddress() + " was " + decison + ".\n" +
                    "Best regards,\n" +
                    "The Real Estate team";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Decisons", true))) {
                writer.write(email);
                writer.write(text);
                writer.newLine();
            } catch (IOException e) {
                System.err.println("An error occurred while sending the email: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }




    }
    public ArrayList<Property> getPropertiesInsertionSortByAreaDescending() {
        return propertyDealRepository.getPropertiesInsertionSortByAreaDescending();
    }
    public ArrayList<Property> getPropertiesInsertionSortByAreaAscending() {
        return propertyDealRepository.getPropertiesInsertionSortByAreaAscending();
    }
    public ArrayList<Property> getPropertiesSelectionSortByAreaAscending() {
        return propertyDealRepository.getPropertiesSelectionSortByAreaAscending();
    }
    public ArrayList<Property> getPropertiesSelectionSortByAreaDescending() {
        return propertyDealRepository.getPropertiesSelectionSortByAreaDescending();
    }

}


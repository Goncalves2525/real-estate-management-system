package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.OrderState;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class PropertyOrderManagementController {
    private OrderRepository orderRepository = null;
    private AnnouncementRepository announcementRepository;
    private AuthenticationRepository authenticationRepository;

    public PropertyOrderManagementController() {

        getOrderRepository();
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

    public void unpublishAnnouncement(int idAnnouncement) {
        getAnnouncementRepository().unpublishAnnouncement(idAnnouncement);
    }

    public OrderState getOrderStateById(int idOrder, int idAnnouncement) {
        return getOrderRepository().getOrderStateById(idOrder,idAnnouncement);
    }
}


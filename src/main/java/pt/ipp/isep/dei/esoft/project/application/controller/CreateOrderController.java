package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.OrderState;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Date;

/**
 * Create Order Controller.
 */
public class CreateOrderController {
    private OrderRepository orderRepository = null;
    private AuthenticationRepository authenticationRepository = null;

    private AnnouncementRepository announcementRepository = null;

    /**
     * Instantiates a new Create Order Controller.
     */
    public CreateOrderController() {
        getOrderRepository();
        getAuthenticationRepository();
        getAnnouncementRepository();
    }

    /**
     * @return order repository
     */
    private OrderRepository getOrderRepository() {
        if (orderRepository == null) {
            Repositories repositories = Repositories.getInstance();
            orderRepository = repositories.getOrderRepository();
        }
        return orderRepository;
    }

    /**
     * @return authentication repository
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * @return announcement repository
     */
    private AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }

    /**
     * @param announcementId announcement id
     * @return true if current user already made order for this announcement, false otherwise
     */
    public boolean clientAlreadyMadeOrderForThisAnnouncement(int announcementId, String userEmail) {
        OrderRepository orderRepository = getOrderRepository();
        return orderRepository.clientAlreadyMadeOrderForThisAnnouncement(userEmail, announcementId);
    }

    /**
     * @param orderAmount  order amount
     * @param announcementId announcement id
     * @return true if someone already made order with same amount for this announcement, false otherwise
     */
    public boolean someoneAlreadyMadeOrderWithSameAmountForThisAnnouncement(double orderAmount, int announcementId) {
        OrderRepository orderRepository = getOrderRepository();
        return orderRepository.someoneAlreadyMadeOrderWithSameAmountForThisAnnouncement(orderAmount, announcementId);
    }

    /**
     * @param id announcement id
     * @return property price by announcement id
     */
    public double getPropertyPriceByAnnouncementId(int id){
        return getAnnouncementRepository().getPropertyPriceByAnnouncmentId(id);
    }

    /**
     * @return client email
     */
    public String getClientEmail(){
        return getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
    }

    /**
     * @param orderAmount order amount
     * @param announcementId announcement id
     * @param clientEmail client email
     * @param date date
     * @param orderState order state
     */
    public void createOrder(double orderAmount, int announcementId, String clientEmail, Date date, OrderState orderState){
        OrderRepository orderRepository = getOrderRepository();
        orderRepository.addOrder(orderAmount, announcementId, clientEmail, date, orderState);
    }

    /**
     * @return all announcements
     */
    public ArrayList<Announcement> getAllAnnouncements(){
        return getAnnouncementRepository().getAllAnnouncementsSortedByDefualtCriteria();
    }

    /**
     * @param announcement announcement
     * @return property by announcement
     */
    public Property getPropertyByAnnouncement(Announcement announcement){
        return getAnnouncementRepository().getPropertyByAnnouncement(announcement);
    }

}

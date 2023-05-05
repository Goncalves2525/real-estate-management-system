package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * Create Order Controller.
 */
public class CreateOrderController {
    private OrderRepository orderRepository = null;
    private AuthenticationRepository authenticationRepository = null;

    /**
     * Instantiates a new Create Order Controller.
     */
    public CreateOrderController() {
        getOrderRepository();
        getAuthenticationRepository();
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
     * @param announcementId announcement id
     * @return true if current user already made order for this announcement, false otherwise
     */
    public boolean clientAlreadyMadeOrderForThisAnnouncement(int announcementId) {
        OrderRepository orderRepository = getOrderRepository();
        String userEmail = getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
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


}

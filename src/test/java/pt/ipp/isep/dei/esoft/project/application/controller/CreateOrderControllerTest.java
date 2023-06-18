package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrderControllerTest {
    CreateOrderController ctrl = new CreateOrderController();
    Repositories repos = Repositories.getInstance();
    OrderRepository orderRepo = repos.getOrderRepository();
    AnnouncementRepository announcementRepository = repos.getAnnouncementRepository();
    PropertyRepository propertyRepository = repos.getPropertyRepository();
    String email = "ricardo@this.app";

    @BeforeEach
    void setUp() {
        orderRepo.removeAllOrders();
        announcementRepository.removeAllAnnouncements();
        Commission c1 = new Commission();
        Date d1 = new Date(2019, 10, 10);
        Date d2 = new Date(2019, 10, 11);
        Date d3 = new Date(2019, 10, 12);
        TypeOfProperty typeHouse = TypeOfProperty.HOUSE;
        TypeOfProperty typeApartment = TypeOfProperty.APARTMENT;
        TypeOfProperty typeLand = TypeOfProperty.LAND;
        TransactionType typeRent = TransactionType.RENT;
        TransactionType typeSale = TransactionType.SALE;
        Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        Address a2 = new Address("Rua 2", "Porto", "Porto", "Porto", 54321);
        Address a3 = new Address("Rua 3", "Lisboa", "Lisboa", "Lisboa", 123111);
        Address a4 = new Address("Rua 4", "Faro", "Faro", "Algarve", 123222);
        Property.idCounter = 1;
        propertyRepository.addHouse(150, 30, 250000, a1, 1, 2, 1, true, true, false, false, SunExposure.EAST);
        propertyRepository.addApartment(150, 30, 100000, a2, 4, 2, 1, true, true);
        propertyRepository.addLand(150, 30, 50000, a3);
        propertyRepository.addHouse(150, 30, 3000000, a4, 1, 2, 1, true, true, false, false, SunExposure.EAST);
        Announcement.idCounter = 1;
        announcementRepository.addAnnouncement(0, typeHouse, typeSale, d1, c1, null, true);
        announcementRepository.addAnnouncement(1, typeApartment, typeRent, d2, c1, null, true);
        announcementRepository.addAnnouncement(2, typeLand, typeRent, d3, c1, null, true);
        announcementRepository.addAnnouncement(3, typeHouse, typeSale, d3, c1, null, true);


    }

    @Test
    void ensure_ClientAlreadyMadeOrderForThisAnnouncement_returnsTrue() {
        // Arrange
        Date d1 = new Date(2023, 10, 10);
        ctrl.createOrder(100000, 0, email, d1, OrderState.PENDING);

        // Act
        boolean result = ctrl.clientAlreadyMadeOrderForThisAnnouncement(0, email);
        boolean expected = true;

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensure_ClientAlreadyMadeOrderForThisAnnouncement_returnsFalse() {
        // Act
        boolean result = ctrl.clientAlreadyMadeOrderForThisAnnouncement(0, email);
        boolean expected = false;

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensure_SomeoneAlreadyMadeOrderWithSameAmountForThisAnnouncement_returnsTrue() {
        // Arrange
        Date d1 = new Date(2023, 10, 10);
        ctrl.createOrder(100000, 0, email, d1, OrderState.PENDING);

        // Act
        boolean result = ctrl.someoneAlreadyMadeOrderWithSameAmountForThisAnnouncement(100000, 0);
        boolean expected = true;

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensure_SomeoneAlreadyMadeOrderWithSameAmountForThisAnnouncement_returnsFalse() {
        // Arrange
        Date d1 = new Date(2023, 10, 10);
        ctrl.createOrder(500000, 0, email, d1, OrderState.PENDING);

        // Act
        boolean result = ctrl.someoneAlreadyMadeOrderWithSameAmountForThisAnnouncement(100000, 0);
        boolean expected = false;

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensure_GetPropertyPriceByAnnouncementId_returnsCorrectPrice() {
        //Act
        double result = ctrl.getPropertyPriceByAnnouncementId(2);
        double expected = 50000;

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensure_GetPropertyPriceByAnnouncementId_validatesIfAnnouncementExists(){
        //Act
        double result = ctrl.getPropertyPriceByAnnouncementId(5);
        double expected = -1;

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void ensure_createOrder_addsOrderToRepository() {
        //Arrange
        Date d1 = new Date(2023, 10, 10);
        ctrl.createOrder(100000, 0, email, d1, OrderState.PENDING);

        //Act
        int result = orderRepo.getSize();
        int expected = 1;

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensure_createOrder_validatesIfAnnouncementExists() {
        //Arrange
        Date d1 = new Date(2023, 10, 10);
        ctrl.createOrder(100000, 5, email, d1, OrderState.PENDING);

        //Act
        int result = orderRepo.getSize();
        int expected = 0;

        //Assert
        assertEquals(expected, result);
    }
}
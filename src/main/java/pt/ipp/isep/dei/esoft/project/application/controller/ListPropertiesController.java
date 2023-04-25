package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class ListPropertiesController {

    private PublishedPropertyRepository publishedPropertyRepository = null;

    //Repository instances are obtained from the Repositories class
    public ListPropertiesController() {
        getPublishedPropertiesRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public ListPropertiesController(PublishedPropertyRepository publishedPropertyRepository, AuthenticationRepository authenticationRepository) {
        this.publishedPropertyRepository = publishedPropertyRepository;
    }

    private PublishedPropertyRepository getPublishedPropertiesRepository() {
        if (publishedPropertyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            publishedPropertyRepository = repositories.getPublishedPropertyRepository();
        }
        return publishedPropertyRepository;

    }

    //returns the list of properties
    public ArrayList<PublishedProperty> getPublishedProperties(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        PublishedPropertyRepository publishedPropertyRepository = getPublishedPropertiesRepository();
        //SÓ PARA TESTES!!!!!!!!!!!
        Comission c1 = new Comission();
        Date d1 = new Date(2019, 10, 10);
        Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        Property p1 = new House(150, 30, 250000, a1, 1, 2, true, true);
        Property p2 = new House(150, 30, 100000, a1, 2, 2, true, true);
        Property p3 = new House(150, 30, 500000, a1, 3, 2, true, true);
        Property p4 = new House(150, 30, 300000, a1, 4, 2, true, true);
        Property p5 = new House(150, 30, 400000, a1, 5, 2, true, true);
        Property p6 = new Apartment(150, 30, 250000, a1, 1, 2);
        Property p7 = new Apartment(150, 30, 100000, a1, 2, 2);
        Property p8 = new Apartment(150, 30, 500000, a1, 3, 2);
        Property p9 = new Apartment(150, 30, 300000, a1, 4, 2);
        Property p10 = new Apartment(150, 30, 400000, a1, 5, 2);
        Property p11 = new Land(150, 30, 250000, a1);
        Property p12 = new Land(150, 30, 100000, a1);
        Property p13 = new Land(150, 30, 500000, a1);
        Property p14 = new Land(150, 30, 300000, a1);
        Property p15 = new Land(150, 30, 400000, a1);
        publishedPropertyRepository.addPublishedProperty(p1, TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p2, TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p3, TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p4, TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p5, TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p6, TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p7, TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p8, TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p9, TypeOfProperty.APARTMENT, TransactionType.RENT, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p10, TypeOfProperty.APARTMENT, TransactionType.RENT, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p11, TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p12, TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p13, TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p14, TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null);
        publishedPropertyRepository.addPublishedProperty(p15, TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null);

        return publishedPropertyRepository.getPublishedProperties(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
    }
}

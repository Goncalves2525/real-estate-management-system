package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Optional;

public class ListPropertiesController {

    private PublishedPropertyRepository publishedPropertyRepository = null;
    private AuthenticationRepository authenticationRepository = null;


    //Repository instances are obtained from the Repositories class
    public ListPropertiesController() {
        getPublishedPropertiesRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public ListPropertiesController(OrganizationRepository organizationRepository,
                                    TaskCategoryRepository taskCategoryRepository,
                                    AuthenticationRepository authenticationRepository) {
        this.publishedPropertyRepository = publishedPropertyRepository;
        this.authenticationRepository = this.authenticationRepository;
    }

    private PublishedPropertyRepository getPublishedPropertiesRepository() {
        if (publishedPropertyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            publishedPropertyRepository = repositories.getPropertyRepository();
        }
        return publishedPropertyRepository;

    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }



    //returns the list of properties
    public ArrayList<PublishedProperty> getPublishedProperties(String propertyType, String businessType, int numberOfRooms, String sortCriteria, String order) {
        PublishedPropertyRepository publishedPropertyRepository = getPublishedPropertiesRepository();
        return publishedPropertyRepository.getPropertiesByFilterAndSort(propertyType, businessType, numberOfRooms, sortCriteria, order);
    }
}

package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Date;

public class ListAnnouncementsController {

    private AnnouncementRepository announcementRepository = null;

    //Repository instances are obtained from the Repositories class
    public ListAnnouncementsController() {
        //getPublishedPropertiesRepository();
        this.announcementRepository = Repositories.getInstance().getPublishedPropertyRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public ListAnnouncementsController(AnnouncementRepository announcementRepository, AuthenticationRepository authenticationRepository) {
        this.announcementRepository = announcementRepository;
    }

    private AnnouncementRepository getPublishedPropertiesRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getPublishedPropertyRepository();
        }
        return announcementRepository;

    }

    //returns the list of properties
    public ArrayList<Announcement> getPublishedProperties(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        AnnouncementRepository announcementRepository = Repositories.getInstance().getPublishedPropertyRepository();
        //AnnouncementRepository announcementRepository = getPublishedPropertiesRepository();


        return announcementRepository.getPublishedProperties(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
    }
}

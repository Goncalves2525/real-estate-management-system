package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;

public class ListAnnouncementsController {

    private AnnouncementRepository announcementRepository = null;

    //Repository instances are obtained from the Repositories class
    public ListAnnouncementsController() {
        getAnnoucementRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    private AnnouncementRepository getAnnoucementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;

    }

    //returns the list of properties
    public ArrayList<Announcement> getAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getAnnouncements(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
    }
}

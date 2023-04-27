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

    public ArrayList<Announcement> sortAllAnnouncementsByDefaultCriteria() {
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.sortAllAnnouncementsByDefaultCriteria();
    }

    public ArrayList<Announcement> sortAllAnnouncementsBySortCriteria(String sortCriteria, String order){
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.sortAllAnnouncementsBySortCriteria(sortCriteria, order);
    }

    public ArrayList<Announcement> filterAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms) {
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.filterAnnouncements(typeOfProperty, transactionType, numberOfRooms);
    }

    public ArrayList<Announcement> filterAndSortAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order){
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.filterAndSortAnnouncements(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
    }
}

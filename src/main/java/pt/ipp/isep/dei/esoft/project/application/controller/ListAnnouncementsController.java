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

    public ArrayList<Announcement> getAllAnnouncementsSortedByDefualtCriteria() {
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getAllAnnouncementsSortedByDefualtCriteria();
    }

    public ArrayList<Announcement> getAllAnnouncementsSortedBySortCriteria(String sortCriteria, String order){
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getAllAnnouncementsSortedBySortCriteria(sortCriteria, order);
    }

    public ArrayList<Announcement> getFilteredAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms) {
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getFilteredAnnouncements(typeOfProperty, transactionType, numberOfRooms);
    }

    public ArrayList<Announcement> getFilteredAndSortedAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order){
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getFilteredAndSortedAnnouncements(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
    }
}

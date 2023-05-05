package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * List Announcements Controller Class.
 *
 * This class is the controller for the user story of listing announcements.
 *
 * This class serves as an intermediary between the user interface and the repository and domain classes.
 */
public class ListAnnouncementsController {

    private AnnouncementRepository announcementRepository = null;
    private AuthenticationRepository authenticationRepository = null;

    /**
     * Instantiates a new List Announcements Controller.
     *
     * Repository instances are obtained from the Repositories class
     */

    public ListAnnouncementsController() {
        getAnnoucementRepository();
        getAuthenticationRepository();
    }

    /**
     * Allows receiving the repositories as parameters for testing purposes
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
     * Allows receiving the repositories as parameters for testing purposes
     * @return announcementRepository
     */

    private AnnouncementRepository getAnnoucementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }

    /**
     * This is the method used for when the user doesn't want to filter or sort the announcements
     * @return announcementList
     */
    public ArrayList<Announcement> getAllAnnouncementsSortedByDefualtCriteria() {
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getAllAnnouncementsSortedByDefualtCriteria();
    }

    /**
     * This is the method used for when the user wants to only sort the announcements
     * @param sortCriteria - sort criteria
     * @param order - sort order
     * @return announcementList
     */
    public ArrayList<Announcement> getAllAnnouncementsSortedBySortCriteria(String sortCriteria, String order){
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getAllAnnouncementsSortedBySortCriteria(sortCriteria, order);
    }

    /**
     * This is the method used for when the user wants to only filter the announcements
     * @param typeOfProperty - type of property
     * @param transactionType - transaction type
     * @param numberOfRooms - number of rooms
     * @return announcementList
     */
    public ArrayList<Announcement> getFilteredAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms) {
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getFilteredAnnouncements(typeOfProperty, transactionType, numberOfRooms);
    }

    /**
     * This is the method used for when the user wants to filter and sort the announcements
     * @param typeOfProperty - type of property
     * @param transactionType - transaction type
     * @param numberOfRooms - number of rooms
     * @param sortCriteria - sort criteria
     * @param order - sort order
     * @return announcementList
     */
    public ArrayList<Announcement> getFilteredAndSortedAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order){
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getFilteredAndSortedAnnouncements(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
    }

    /**
     * @return true if user is logged in, false otherwise
     */
    public boolean userIsLoggedInWithClientRole() {
        return getAuthenticationRepository().getCurrentUserSession().isLoggedInWithRole("CLIENT");
    }


}

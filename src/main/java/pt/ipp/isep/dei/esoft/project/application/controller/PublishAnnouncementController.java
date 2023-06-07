package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Commission;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;

/**
 * This class is the controller for the user story of publishing announcements.
 */
public class PublishAnnouncementController {
    /**
     * The Announcement repository.
     */
    private AnnouncementRepository announcementRepository;
    /**
     * The Authentication repository.
     */
    private AuthenticationRepository authenticationRepository;

    private EmployeeRepository employeeRepository;


    /**
     * Instantiates a new Publish announcement controller.
     */
    public PublishAnnouncementController() {
        this.announcementRepository = Repositories.getInstance().getAnnouncementRepository();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        this.employeeRepository = Repositories.getInstance().getEmployeeRepository();
    }


    /**
     * @return List<Announcement> the list of announcements
     */
    public List<Announcement> getAnnouncementsByUser() {
        String userEmail = authenticationRepository.getCurrentUserSession().getUserId().getEmail();
        return announcementRepository.getAnnouncementsByAgent(userEmail);
    }

    /**
     * Publishes an announcement by its id
     *
     * @param id the id of the announcement
     */
    public void publishAnnouncement(int id) {
        Announcement announcement = announcementRepository.getAnnouncementById(id);
        try {
            if (announcement.getId() >= 0) {
                announcement.setIsPublished();
            }
        } catch (Exception e) {
            System.out.println("Invalid announcement id");
        }
    }

    /**
     * Sets the commission of an announcement by its id
     *
     * @param id         the id of the announcement
     * @param commission the commission of the announcement
     */
    public void setCommission(int id, Commission commission) {
        Announcement announcement = announcementRepository.getAnnouncementById(id);
        try {
            if (announcement.getId() >= 0) {
                announcement.setCommission(commission);
            }
        } catch (Exception e) {
            System.out.println("Invalid announcement id");
        }
    }

    /**
     * Gets the announcement by its id
     *
     * @param id the id of the announcement
     * @return Announcement the announcement
     */
    public Announcement getAnnouncementById(int id) {
        try {
            return announcementRepository.getAnnouncementById(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Gets the property by its announcement
     *
     * @param announcement the announcement
     * @return Property the property
     */
    public Property getPropertyByAnnouncement(Announcement announcement) {
        return announcementRepository.getPropertyByAnnouncement(announcement);
    }

    /**
     * Sends a notification to the owner of an announcement's property
     *
     * @param announcement the announcement
     */
    public void sendNotification(Announcement announcement) {
        String agent = authenticationRepository.getCurrentUserSession().getUserId().getEmail();
        Employee employee = employeeRepository.getEmployeeByEmail(agent);
        String employeeName = employee.getName();
        String employeeNumber = "" + employee.getTelephoneNumber();
        String ownerNumber = "" + announcement.getOwner().getTelephoneNumber();
        Utils.sendSMS(ownerNumber, "Your property has been published! By: " + employeeName + ", with the contact: " + employeeNumber);
    }
}

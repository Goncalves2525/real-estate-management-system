package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.VisitSchedule;
import pt.ipp.isep.dei.esoft.project.repository.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author Luis Leal 1100253@isep.ipp.pt
 */

/**
 * The Visit schedule controller.
 */
public class VisitScheduleController {
    private AuthenticationRepository authenticationRepository = null;
    private VisitScheduleRepository visitScheduleRepository = null;
    private AnnouncementRepository announcementRepository = null;
    private PropertyRepository propertyRepository = null;
    private ClientRepository clientRepository = null;
    private AuthenticationController authController = new AuthenticationController();


    /**
     * Instantiates a new Visit schedule controller.
     */
    public VisitScheduleController() {
        Repositories repositories = Repositories.getInstance();
        this.clientRepository = repositories.getClientRepository();
        this.visitScheduleRepository = repositories.getVisitScheduleRepository();
        getAnnouncementRepository();

    }


    /**
     * Get announcement sorted by default criteria array list.
     */
    public ArrayList<Announcement> getAllAnnouncementsSortedByDefualtCriteria() {
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getAllAnnouncementsSortedByDefualtCriteria();
    }

    /**
     * Get announcement repository
     */
    private AnnouncementRepository getAnnoucementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }

    /**
     * Get property by id announcement.
     * @param announcement
     * @return property
     */
    public Property getPropertyByAnnouncement(Announcement announcement){
        return getAnnouncementRepository().getPropertyByAnnouncement(announcement);
    }

    /**
     * Get announcement repository
     *
     */
    private AnnouncementRepository getAnnouncementRepository(){
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }


    /**
     * verify if announcement id is valid
     * @param id
     * @return
     */
    public boolean verifyAnnouncementID(int id) {
        Announcement announcement = getAnnouncementRepository().getAnnouncementById(id);
        return announcement != null;
    }


    /**
     * Get current user email
     * @return
     */
    public String getCurrentUserEmail() {
        return authController.getUserEmail();
    }


    /**
     * Get current user phone
     * @return user phone
     */
    public int getCurrentUserPhone() {
        String currentUserEmail = getCurrentUserEmail();
        if (currentUserEmail != null) {
            Client client = clientRepository.getClientByEmail(currentUserEmail);
            if (client != null) {
                return client.getTelephoneNumber();
            }
        }
        return 0;
    }


    /**
     * Get current user name
     * @return user name
     */
    public String getCurrentUserName() {
        String currentUserEmail = getCurrentUserEmail();
        if (currentUserEmail != null) {
            Client client = clientRepository.getClientByEmail(currentUserEmail);
            if (client != null) {
                return client.getName();
            }
        }
        return null;
    }


    /**
     * Check if the visit schedule is valid and not overlapping with existing schedules
     * @param userPhone
     * @param visitDate
     * @param startTime
     * @param endTime
     * @return true if the visit schedule is valid and not overlapping with existing schedules
     */
    public boolean isOverlappingWithExistingSchedules(int userPhone, LocalDate visitDate, LocalTime startTime, LocalTime endTime) {
        // Convert string times to LocalTime objects
        LocalTime start = startTime;
        LocalTime end = endTime;

        // Get the list of VisitSchedule objects
        ArrayList<VisitSchedule> visitSchedules = this.visitScheduleRepository.getVisitSchedulesByUserPhoneAndDate(userPhone, visitDate);

        // If the list is null or empty, there are no existing schedules to overlap with
        if (visitSchedules == null || visitSchedules.isEmpty()) {
            return false;
        }

        // Check each VisitSchedule object for overlap
        for (VisitSchedule visit : visitSchedules) {
            if (visit.isOverlapping(start, end)) {
                return true;
            }
        }

        // If none of the VisitSchedule objects overlap, return false
        return false;
    }


    /**
     * Get agent email by announcement id
     * @param id announcement id
     * @return agent email
     */
    public String getAgentEmailByAnnouncementID(int id) {
        Announcement announcement = getAnnouncementRepository().getAnnouncementById(id);
        if (announcement != null) {
            return announcement.getAgentEmail();
        }
        return null;
    }


    /**
     * Save visit schedule
     * @param announcementID announcement id
     * @param name client name
     * @param telephoneNumber client phone
     * @param date visit date
     * @param startTime visit start time
     * @param endTime visit end time
     * @param approvedbyAgent true if approved by agent
     */
    public void saveVisitSchedule(int announcementID , String name, int telephoneNumber, LocalDate date, LocalTime startTime, LocalTime endTime, boolean approvedbyAgent){
        String agentEmail = getAgentEmailByAnnouncementID(announcementID);
        VisitSchedule visitSchedule = new VisitSchedule(announcementID, name, telephoneNumber, date, startTime, endTime, approvedbyAgent, agentEmail);
        this.visitScheduleRepository.addVisitSchedule(visitSchedule);
        sendEmailToAgent(announcementID, name, telephoneNumber, date, startTime, endTime, agentEmail);
    }

    /**
     * Send email to agent
     * @param announcementID announcement id
     * @param name client name
     * @param telephoneNumber client phone
     * @param date visit date
     * @param startTime visit start time
     * @param endTime visit end time
     * @param agentEmail agent email
     */
    private void sendEmailToAgent(int announcementID, String name, int telephoneNumber, LocalDate date, LocalTime startTime, LocalTime endTime, String agentEmail) {
        String filename = "EmailToAgent_" + agentEmail + "_Announcement_" + announcementID + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            if (agentEmail != null) {
                writer.write("To: " + agentEmail + "\n\n");
            }
            writer.write("Subject: Visit Schedule - Announcement " + announcementID + "\n\n");
            writer.write("Dear Agent,\n\n");
            if (name != null) {
                writer.write("My name is " + name + ". I am interested in the property listed under announcement number " + announcementID + ".\n\n");
            }
            if (date != null && startTime != null && endTime != null) {
                writer.write("I would like to propose a visit on " + date + " starting from " + startTime + " until " + endTime + ". Please feel free to contact me at the following telephone number: " + telephoneNumber + " if there are any issues with the proposed timing.\n\n");
            }
            writer.write("I look forward to your confirmation of the visit.\n");
            writer.write("Thank you for your attention.\n\n");
            if (name != null) {
                writer.write("Best regards,\n");
                writer.write(name);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
            e.printStackTrace();
        }
    }


    /**
     * Approve visit by agent
     * @param visitSchedule
     */
    public void approveVisit(VisitSchedule visitSchedule) {
        visitSchedule.setApprovedByAgent(true);
    }


    /**
     * Remove visit schedule from repository
     * @param visit
     */
    public void removeVisit(VisitSchedule visit) {
        visitScheduleRepository.removeVisitSchedule(visit);
    }

    /**
     * Get pending visits by agent email
     * @param agentEmail agent email
     * @return pending visits
     */
    public ArrayList<VisitSchedule> getPendingVisitsByAgentEmail(String agentEmail) {
        ArrayList<VisitSchedule> pendingVisits = new ArrayList<>();
        for (VisitSchedule visit : visitScheduleRepository.getVisitSchedules()) {
            if (!visit.isApprovedByAgent() && visit.getAgentEmail().equals(agentEmail)) {
                pendingVisits.add(visit);
            }
        }
        return pendingVisits;
    }



}

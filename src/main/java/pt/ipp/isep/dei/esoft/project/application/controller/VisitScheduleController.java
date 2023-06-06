package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.gui.VisitScheduleRequestsWindow;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Luis Leal 1100253@isep.ipp.pt
 */

/**
 * The Visit schedule controller.
 */
public class VisitScheduleController {
    private VisitScheduleRepository visitScheduleRepository = null;
    private AnnouncementRepository announcementRepository = null;
    private ClientRepository clientRepository = null;
    private AuthenticationController authController = new AuthenticationController();
    private EmployeeRepository employeeRepository = null;
    private EmailSender emailSender = new EmailSender();


    /**
     * Instantiates a new Visit schedule controller.
     */
    public VisitScheduleController() {
        getClientRepository();
        getVisitScheduleRepository();
        getAnnouncementRepository();

    }


    public String getPropertyAddressByAnnouncementId(int id) {
        Announcement announcement = getAnnouncementRepository().getAnnouncementById(id);
        Property property = getPropertyByAnnouncement(announcement);
        return String.valueOf(property.getAddress());
    }



    /**
     * Get announcement sorted by default criteria array list.
     */
    public ArrayList<Announcement> getAllAnnouncementsSortedByDefualtCriteria() {
        AnnouncementRepository announcementRepository = getAnnouncementRepository();
        return announcementRepository.getAllAnnouncementsSortedByDefualtCriteria();
    }


    /**
     * Get property by announcement.
     * @param announcement
     * @return property
     */
    public Property getPropertyByAnnouncement(Announcement announcement){
        return getAnnouncementRepository().getPropertyByAnnouncement(announcement);
    }


    /**
     * Get client repository
     *
     * @return clientRepository
     */
    private ClientRepository getClientRepository() {
        if (clientRepository == null) {
            Repositories repositories = Repositories.getInstance();
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;
    }

    /**
     * Get visit schedule repository
     *
     * @return visitScheduleRepository
     */
    private VisitScheduleRepository getVisitScheduleRepository() {
        if (visitScheduleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            visitScheduleRepository = repositories.getVisitScheduleRepository();
        }
        return visitScheduleRepository;
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
    public long getCurrentUserPhone() {
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
    public boolean isOverlappingWithExistingSchedules(long userPhone, LocalDate visitDate, LocalTime startTime, LocalTime endTime) {
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
    public void saveVisitSchedule(int announcementID , String name, long telephoneNumber, LocalDate date, LocalTime startTime, LocalTime endTime, boolean approvedbyAgent, String adressOfProperty,String requesterEmail){
        String agentEmail = getAgentEmailByAnnouncementID(announcementID);
        VisitSchedule visitSchedule = new VisitSchedule(announcementID, name, telephoneNumber, date, startTime, endTime, approvedbyAgent, agentEmail, adressOfProperty, requesterEmail);
        this.visitScheduleRepository.addVisitSchedule(visitSchedule);
        sendEmailToAgent(announcementID, name, telephoneNumber, date, startTime, endTime, agentEmail, adressOfProperty);
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
    private void sendEmailToAgent(int announcementID, String name, long telephoneNumber, LocalDate date, LocalTime startTime, LocalTime endTime, String agentEmail, String adressOfProperty) {
        emailSender.sendEmailToAgent(announcementID, name, telephoneNumber, date, startTime, endTime, agentEmail, adressOfProperty);
    }


    /**
     * Approve visit by agent
     * @param visitSchedule
     */
    public void approveVisit(VisitSchedule visitSchedule) {
        visitSchedule.setApprovedByAgent(true);
    }

    /**
     * Disapprove visit by agent
     * @param visitSchedule
     */
    public void disapproveVisit(VisitSchedule visitSchedule) {
        visitSchedule.setApprovedByAgent(false);
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
    public List<VisitSchedule> getPendingVisitsByAgentEmail(String agentEmail) {
        return this.visitScheduleRepository.getPendingVisitsByAgentEmail(agentEmail);
    }

    /**
     * Get filtered visits by agent email
     * @param agentEmail agent email
     * @param startDate start date
     * @param endDate end date
     * @return filtered visits
     */
    public List<VisitSchedule> getFilteredVisitsByAgentEmail(String agentEmail, LocalDate startDate, LocalDate endDate) {
        return this.visitScheduleRepository.getFilteredVisitsByAgentEmail(agentEmail, startDate, endDate);
    }

    /**
     * Get sorted visits by agent email
     * @return sorted visits
     */
    public SortStrategy getSortStrategyFromConfig() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            String filename = "sortAlgoritm.properties";
            input = VisitScheduleRequestsWindow.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return null;
            }

            prop.load(input);

            String strategyClass = prop.getProperty("sorting.strategy");

            SortStrategy sortStrategy = (SortStrategy) Class.forName(strategyClass).getDeclaredConstructor().newInstance();

            return sortStrategy;

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ReflectiveOperationException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * Get employee repository
     */
    private EmployeeRepository getEmployeeRepository(){
        if (employeeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

    /**
     * Get employee name by email
     * @param email
     * @return employee name
     */
    public String getEmployeeNameByEmail(String email) {
        Employee employee = getEmployeeRepository().getEmployeeByEmail(email);
        if (employee != null) {
            return employee.getName();
        }
        return null;
    }

    /**
     * Get employee phone number by email
     * @param email
     * @return employee phone number
     */
    public Long getEmployeePhoneNumberByEmail(String email) {
        Employee employee = getEmployeeRepository().getEmployeeByEmail(email);
        if (employee != null) {
            return employee.getTelephoneNumber();
        }
        return null;
    }

    /**
     * Respond to booking request by email
     * @param visitSchedule
     * @param reason
     */
    public void respondToBookingRequestByEmail(VisitSchedule visitSchedule, String reason) {
        String requesterEmail = visitSchedule.getRequesterEmail();
        int propertyId = visitSchedule.getPropertyID();
        String propertyLocation = visitSchedule.getAdressOfProperty();
        LocalDate visitDate = visitSchedule.getDate();
        LocalTime startTime = visitSchedule.getStartTime();
        LocalTime endTime = visitSchedule.getEndTime();
        boolean isAccepted = visitSchedule.isApprovedByAgent();
        String agentName = getEmployeeNameByEmail(visitSchedule.getAgentEmail());
        long agentPhone = getEmployeePhoneNumberByEmail(visitSchedule.getAgentEmail());

        emailSender.respondToBookingRequestEmail(requesterEmail, propertyId, propertyLocation, visitDate,
                startTime, endTime, isAccepted, agentName, agentPhone, reason);
    }

}

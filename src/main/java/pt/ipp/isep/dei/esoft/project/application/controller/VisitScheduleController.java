package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.VisitSchedule;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class VisitScheduleController {
    private AuthenticationRepository authenticationRepository = null;
    private VisitScheduleRepository visitScheduleRepository = null;
    private AnnouncementRepository announcementRepository = null;
    private PropertyRepository propertyRepository = null;
    private ClientRepository clientRepository = null;
    private AuthenticationController authController = new AuthenticationController();

    public VisitScheduleController() {
        Repositories repositories = Repositories.getInstance();
        this.clientRepository = repositories.getClientRepository();
        this.visitScheduleRepository = repositories.getVisitScheduleRepository(); //inicializando visitScheduleRepository
        getAnnouncementRepository();
    }


    private AnnouncementRepository getAnnouncementRepository(){
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }

    public boolean verifyAnnouncementID(int id) {
        Announcement announcement = getAnnouncementRepository().getAnnouncementById(id);
        return announcement != null;
    }

    public String getCurrentUserEmail() {
        return authController.getUserEmail();
    }

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


    public boolean isOverlappingWithExistingSchedules(int userPhone, LocalDate visitDate, String startTime, String endTime) {
        // Convert string times to LocalTime objects
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

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




    private boolean isOverlapping(String existingStart, String existingEnd, String newStart, String newEnd) {
        LocalTime existingStartTime = LocalTime.parse(existingStart);
        LocalTime existingEndTime = LocalTime.parse(existingEnd);
        LocalTime newStartTime = LocalTime.parse(newStart);
        LocalTime newEndTime = LocalTime.parse(newEnd);

        return newStartTime.isBefore(existingEndTime) && newEndTime.isAfter(existingStartTime);
    }

    public void saveVisitSchedule(int announcementID , String name, int telephoneNumber, LocalDate date, String startTime, String endTime, boolean approvedbyAgent) {
        VisitSchedule visitSchedule = new VisitSchedule(announcementID, name, telephoneNumber, date, startTime, endTime, approvedbyAgent);
        this.visitScheduleRepository.addVisitSchedule(visitSchedule);
    }


}

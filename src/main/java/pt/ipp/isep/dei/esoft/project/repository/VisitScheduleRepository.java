package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.VisitSchedule;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Luis Leal 1100253@isep.ipp.pt
 */

/**
 * The Visit schedule repository.
 */
public class VisitScheduleRepository {

    /**
     * Instantiates a new Visit schedule repository.
     */
    private ArrayList<VisitSchedule> visitSchedules = new ArrayList<>();


    /**
     * Add visit schedule.
     * @param visitSchedule the visit schedule
     */
    public void addVisitSchedule(VisitSchedule visitSchedule) {
        visitSchedules.add(visitSchedule);
    }

    /**
     * Get visit schedules array list.
     * @return the array list of visit schedules
     */
    public ArrayList<VisitSchedule> getVisitSchedules() {
        return visitSchedules;
    }

    /**
     * Get visit schedules by user phone and date array list.
     * @param userPhone the user phone
     * @param visitDate the visit date
     * @return the array list of visit schedules
     */
    public ArrayList<VisitSchedule> getVisitSchedulesByUserPhoneAndDate(long userPhone, LocalDate visitDate) {
        ArrayList<VisitSchedule> userSchedules = new ArrayList<>();
        for (VisitSchedule visit : visitSchedules) {
            if (visit.getTelephoneNumber() == userPhone && visit.getDate().equals(visitDate)) {
                userSchedules.add(visit);
            }
        }
        return userSchedules;
    }


    /**
     * Remove visit schedule from array list.
     * @param visitSchedule the visit schedule
     */
    public void removeVisitSchedule(VisitSchedule visitSchedule) {
        visitSchedules.remove(visitSchedule);
    }



}

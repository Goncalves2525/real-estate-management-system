package pt.ipp.isep.dei.esoft.project.repository;
import java.time.LocalDate;
import java.util.ArrayList;
import pt.ipp.isep.dei.esoft.project.domain.VisitSchedule;

public class VisitScheduleRepository {
    private ArrayList<VisitSchedule> visitSchedules = new ArrayList<>();

    public void addVisitSchedule(VisitSchedule visitSchedule) {
        visitSchedules.add(visitSchedule);
    }

    public ArrayList<VisitSchedule> getVisitSchedules() {
        return visitSchedules;
    }

    public ArrayList<VisitSchedule> getVisitSchedulesByUserPhoneAndDate(int userPhone, LocalDate visitDate) {
        ArrayList<VisitSchedule> userSchedules = new ArrayList<>();
        for (VisitSchedule visit : visitSchedules) {
            if (visit.getTelephoneNumber() == userPhone && visit.getDate().equals(visitDate)) {
                userSchedules.add(visit);
            }
        }
        return userSchedules;
    }

    public void removeVisitSchedule(VisitSchedule visitSchedule) {
        visitSchedules.remove(visitSchedule);
    }


}

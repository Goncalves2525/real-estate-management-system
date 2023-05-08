package pt.ipp.isep.dei.esoft.project.repository;
import java.util.ArrayList;
import pt.ipp.isep.dei.esoft.project.domain.VisitSchedule;

public class VisitScheduleRepository {
    private ArrayList<VisitSchedule> visitSchedules = new ArrayList<>();

    public void addVisitSchedule(VisitSchedule visitSchedule) {
        visitSchedules.add(visitSchedule);
    }





}

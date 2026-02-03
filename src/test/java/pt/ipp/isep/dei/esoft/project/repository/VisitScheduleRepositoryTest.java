package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.VisitSchedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class VisitScheduleRepositoryTest {
    private VisitScheduleRepository repository;
    private VisitSchedule visitSchedule1;
    private VisitSchedule visitSchedule2;

    @BeforeEach
    void setUp() {
        repository = new VisitScheduleRepository();

        visitSchedule1 = new VisitSchedule(25,"Joao", 2323232323L, LocalDate.of(2023, 5, 28), LocalTime.of(10, 30), LocalTime.of(11, 30),false,"teste@this.app","Rua 1, Braga","requester1@this.app");
        visitSchedule2 = new VisitSchedule(26,"Manel", 2323232324L, LocalDate.of(2023, 5, 20), LocalTime.of(11, 30), LocalTime.of(12, 30),false,"teste2@this.app","Rua 2, Porto","requester2@this.app");

        repository.addVisitSchedule(visitSchedule1);
        repository.addVisitSchedule(visitSchedule2);
    }

//    @Test
//    void addVisitSchedule() {
//        VisitSchedule visitSchedule3 = new VisitSchedule(27,"Joaquim", 2323232324L, LocalDate.of(2023, 5, 20), LocalTime.of(11, 30), LocalTime.of(12, 30),false,"teste2@this.app");
//        repository.addVisitSchedule(visitSchedule3);
//        ArrayList<VisitSchedule> visitSchedules = repository.getVisitSchedules();
//        assertTrue(visitSchedules.contains(visitSchedule3));
//    }

    @Test
    void getVisitSchedules() {
        ArrayList<VisitSchedule> visitSchedules = repository.getVisitSchedules();

        assertTrue(visitSchedules.contains(visitSchedule1));
        assertTrue(visitSchedules.contains(visitSchedule2));
    }

    @Test
    void getVisitSchedulesByUserPhoneAndDate() {
        ArrayList<VisitSchedule> visitSchedules = repository.getVisitSchedulesByUserPhoneAndDate(2323232323L, LocalDate.of(2023, 5, 28));

        assertTrue(visitSchedules.contains(visitSchedule1));
    }

}

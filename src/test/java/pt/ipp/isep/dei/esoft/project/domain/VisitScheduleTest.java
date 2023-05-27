package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class VisitScheduleTest {

    /**
     * Test of the method isOverlapping, of class VisitSchedule and returns true.
     */
    @Test
    void isOverlappingTrue() {
        VisitSchedule visitSchedule = new VisitSchedule(1, "Test", 123456789, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), false, "test@test.com");

        boolean result = visitSchedule.isOverlapping(LocalTime.of(11, 0), LocalTime.of(13, 0));

        assertTrue(result, "Expected true as the given times overlap with the visit schedule.");
    }

    /**
     * Test of the method isOverlapping, of class VisitSchedule and returns false.
     */
    @Test
    void isOverlappingFalse() {
        VisitSchedule visitSchedule = new VisitSchedule(1, "Test", 123456789, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), false, "test@test.com");

        boolean result = visitSchedule.isOverlapping(LocalTime.of(13, 0), LocalTime.of(14, 0));

        assertFalse(result, "Expected false as the given times do not overlap with the visit schedule.");
    }

}
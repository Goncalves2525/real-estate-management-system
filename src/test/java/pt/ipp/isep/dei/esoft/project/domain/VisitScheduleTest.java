package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;

class VisitScheduleTest {

    private VisitSchedule visitSchedule;

    @BeforeEach
    void setUp() {
        visitSchedule = new VisitSchedule(1, "João", 1234567899, LocalDate.now(), LocalTime.of(10,0), LocalTime.of(12,0), false, "agent@gmail.com", "Street X", "requester@gmail.com");
    }

    @Test
    void getPropertyID() {
        assertEquals(1, visitSchedule.getPropertyID());
    }

    @Test
    void setPropertyID() {
        visitSchedule.setPropertyID(2);
        assertEquals(2, visitSchedule.getPropertyID());
    }

    @Test
    void getNameOfClient() {
        assertEquals("João", visitSchedule.getNameOfClient());
    }

    @Test
    void setNameOfClient() {
        visitSchedule.setNameOfClient("Maria");
        assertEquals("Maria", visitSchedule.getNameOfClient());
    }

    @Test
    void getTelephoneNumberOfClient() {
        assertEquals(1234567899, visitSchedule.getTelephoneNumberOfClient());
    }

    @Test
    void setTelephoneNumberOfClient() {
        visitSchedule.setTelephoneNumberOfClient(987654321);
        assertEquals(987654321, visitSchedule.getTelephoneNumberOfClient());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.now(), visitSchedule.getDate());
    }

    @Test
    void getStartTime() {
        assertEquals(LocalTime.of(10, 0), visitSchedule.getStartTime());
    }

    @Test
    void setStartTime() {
        LocalTime newStartTime = LocalTime.of(9, 0);
        visitSchedule.setStartTime(newStartTime);
        assertEquals(newStartTime, visitSchedule.getStartTime());
    }

    @Test
    void getEndTime() {
        assertEquals(LocalTime.of(12, 0), visitSchedule.getEndTime());
    }

    @Test
    void setEndTime() {
        LocalTime newEndTime = LocalTime.of(11, 0);
        visitSchedule.setEndTime(newEndTime);
        assertEquals(newEndTime, visitSchedule.getEndTime());
    }

    @Test
    void isOverlappingTrue() {
        assertTrue(visitSchedule.isOverlapping(LocalTime.of(11, 0), LocalTime.of(13, 0)));
    }

    @Test
    void isOverlappingFalse() {
        assertFalse(visitSchedule.isOverlapping(LocalTime.of(12, 30), LocalTime.of(13, 30)));
    }

    @Test
    void isApprovedByAgent() {
        assertFalse(visitSchedule.isApprovedByAgent());
    }

    @Test
    void setApprovedByAgent() {
        visitSchedule.setApprovedByAgent(true);
        assertTrue(visitSchedule.isApprovedByAgent());
    }

    @Test
    void setDate() {
        LocalDate newDate = LocalDate.now().plusDays(1);
        visitSchedule.setDate(newDate);
        assertEquals(newDate, visitSchedule.getDate());
    }

    @Test
    void getAdressOfProperty() {
        assertEquals("Street X", visitSchedule.getAdressOfProperty());
    }

    @Test
    void setAdressOfProperty() {
        visitSchedule.setAdressOfProperty("Street Y");
        assertEquals("Street Y", visitSchedule.getAdressOfProperty());
    }

    @Test
    void getAgentEmail() {
        assertEquals("agent@gmail.com", visitSchedule.getAgentEmail());
    }

    @Test
    void setAgentEmail() {
        visitSchedule.setAgentEmail("newagent@gmail.com");
        assertEquals("newagent@gmail.com", visitSchedule.getAgentEmail());
    }

    @Test
    void getRequesterEmail() {
        assertEquals("requester@gmail.com", visitSchedule.getRequesterEmail());
    }

    @Test
    void setRequesterEmail() {
        visitSchedule.setRequesterEmail("newrequester@gmail.com");
        assertEquals("newrequester@gmail.com", visitSchedule.getRequesterEmail());
    }
}

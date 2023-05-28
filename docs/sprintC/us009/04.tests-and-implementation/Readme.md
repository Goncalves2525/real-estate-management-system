# US 009 - As a client, I want to leave a message to the agent to schedule a visit to a property of my interest

# 4. Tests 

**Test 1:** Check if the given times overlap with the visit schedule

	@Test(expected True)
    void isOverlappingTrue() {
        VisitSchedule visitSchedule = new VisitSchedule(1, "Test", 123456789, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), false, "test@test.com");

        boolean result = visitSchedule.isOverlapping(LocalTime.of(11, 0), LocalTime.of(13, 0));

        assertTrue(result, "Expected true as the given times overlap with the visit schedule.");
    }
	

**Test 2:** Check if the given times overlap with the visit schedule

	@Test (expected False)
    void isOverlappingFalse() {
        VisitSchedule visitSchedule = new VisitSchedule(1, "Test", 123456789, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), false, "test@test.com");

        boolean result = visitSchedule.isOverlapping(LocalTime.of(13, 0), LocalTime.of(14, 0));

        assertFalse(result, "Expected false as the given times do not overlap with the visit schedule.");
    }

**Test 3:** Test if the visit schedule is added to the repository

    @Test
    void addVisitSchedule() {
    VisitSchedule visitSchedule3 = new VisitSchedule(27,"Joaquim", 2323232324L, LocalDate.of(2023, 5, 20), LocalTime.of(11, 30), LocalTime.of(12, 30),false,"teste2@this.app");
    repository.addVisitSchedule(visitSchedule3);
    ArrayList<VisitSchedule> visitSchedules = repository.getVisitSchedules();
    assertTrue(visitSchedules.contains(visitSchedule3));
    }

**Test 4:** Test if the visit schedules are returned

    @Test
    void getVisitSchedules() {
        ArrayList<VisitSchedule> visitSchedules = repository.getVisitSchedules();

        assertTrue(visitSchedules.contains(visitSchedule1));
        assertTrue(visitSchedules.contains(visitSchedule2));
    }

**Test 5:** Test if the visit schedules are returned by the user phone number

    @Test
    void getVisitSchedulesByUserPhoneAndDate() {
        ArrayList<VisitSchedule> visitSchedules = repository.getVisitSchedulesByUserPhoneAndDate(2323232323L, LocalDate.of(2023, 5, 28));

        assertTrue(visitSchedules.contains(visitSchedule1));
    }


# 5. Construction (Implementation)


## Class VisitScheduleController

```java
    public void saveVisitSchedule(int announcementID , String name, int telephoneNumber, LocalDate date, LocalTime startTime, LocalTime endTime, boolean approvedbyAgent){
        String agentEmail = getAgentEmailByAnnouncementID(announcementID);
        VisitSchedule visitSchedule = new VisitSchedule(announcementID, name, telephoneNumber, date, startTime, endTime, approvedbyAgent, agentEmail);
        this.visitScheduleRepository.addVisitSchedule(visitSchedule);
        }
public void saveVisitSchedule(int announcementID , String name, int telephoneNumber, LocalDate date, LocalTime startTime, LocalTime endTime, boolean approvedbyAgent){
        String agentEmail = getAgentEmailByAnnouncementID(announcementID);
        VisitSchedule visitSchedule = new VisitSchedule(announcementID, name, telephoneNumber, date, startTime, endTime, approvedbyAgent, agentEmail);
        this.visitScheduleRepository.addVisitSchedule(visitSchedule);
        }

```


## Class VisitScheduleRepository

```java
public class VisitScheduleRepository {
    private ArrayList<VisitSchedule> visitSchedules = new ArrayList<>();

    public void addVisitSchedule(VisitSchedule visitSchedule) {
        visitSchedules.add(visitSchedule);
    }
```

# 6. Integration and Demo 

* A new option on the Client menu options was added.

* There is no demo purposes for tasks because the client starts the process and the agent finishes it.


# 7. Observations

The process of scheduling a visit is not yet complete. The client can schedule a visit but the agent cannot approve it.






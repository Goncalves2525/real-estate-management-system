# US016 - As an agent, when viewing a booking request, I want to respond to the user that scheduled the visit.

# 4. Tests 
    @BeforeEach
    void setUp() {
    visitSchedule = new VisitSchedule(1, "João", 1234567899, LocalDate.now(), LocalTime.of(10,0), LocalTime.of(12,0), false, "agent@gmail.com", "Street X", "requester@gmail.com");
    }

**Test 1:** Test if the property ID is correctly returned.

    @Test
    void getPropertyID() {
        assertEquals(1, visitSchedule.getPropertyID());
    }

**Test 2:** Test if the property ID is correctly set.

    @Test
    void setPropertyID() {
        visitSchedule.setPropertyID(2);
        assertEquals(2, visitSchedule.getPropertyID());
    }

**Test 3:** Test if the name of the client is correctly returned.

    @Test
    void getNameOfClient() {
        assertEquals("João", visitSchedule.getNameOfClient());
    }

**Test 4:** Test if the name of the client is correctly set.

    @Test
    void setNameOfClient() {
        visitSchedule.setNameOfClient("Maria");
        assertEquals("Maria", visitSchedule.getNameOfClient());
    }

**Test 5:** Test if the telephone number of the client is correctly returned.

    @Test
    void getTelephoneNumberOfClient() {
        assertEquals(1234567899, visitSchedule.getTelephoneNumberOfClient());
    }

**Test 6:** Test if the telephone number of the client is correctly set.

    @Test
    void setTelephoneNumberOfClient() {
        visitSchedule.setTelephoneNumberOfClient(987654321);
        assertEquals(987654321, visitSchedule.getTelephoneNumberOfClient());
    }

**Test 7:** Test if the date of the visit is correctly returned.

    @Test
    void getDate() {
        assertEquals(LocalDate.now(), visitSchedule.getDate());
    }

**Test 8:** Test if the date of the visit is correctly set.

    @Test
    void getStartTime() {
        assertEquals(LocalTime.of(10, 0), visitSchedule.getStartTime());
    }

**Test 9:** Test if the start time of the visit is correctly set.

    @Test
    void setStartTime() {
        LocalTime newStartTime = LocalTime.of(9, 0);
        visitSchedule.setStartTime(newStartTime);
        assertEquals(newStartTime, visitSchedule.getStartTime());
    }

**Test 10:** Test if the end time of the visit is correctly returned.

    @Test
    void getEndTime() {
        assertEquals(LocalTime.of(12, 0), visitSchedule.getEndTime());
    }

**Test 11:** Test if the end time of the visit is correctly set.

    @Test
    void setEndTime() {
        LocalTime newEndTime = LocalTime.of(11, 0);
        visitSchedule.setEndTime(newEndTime);
        assertEquals(newEndTime, visitSchedule.getEndTime());
    }

**Test 12:** Test if the visit is overlapping.

    @Test
    void isOverlappingTrue() {
        assertTrue(visitSchedule.isOverlapping(LocalTime.of(11, 0), LocalTime.of(13, 0)));
    }

**Test 13:** Test if the visit is not overlapping.

    @Test
    void isOverlappingFalse() {
        assertFalse(visitSchedule.isOverlapping(LocalTime.of(12, 30), LocalTime.of(13, 30)));
    }

**Test 14:** Test if the visit is not approved by the agent.

    @Test
    void isApprovedByAgent() {
        assertFalse(visitSchedule.isApprovedByAgent());
    }

**Test 15:** Test if the visit is approved by the agent.

    @Test
    void setApprovedByAgent() {
        visitSchedule.setApprovedByAgent(true);
        assertTrue(visitSchedule.isApprovedByAgent());
    }

**Test 16:** Test if the date of the visit is correctly set.

    @Test
    void setDate() {
        LocalDate newDate = LocalDate.now().plusDays(1);
        visitSchedule.setDate(newDate);
        assertEquals(newDate, visitSchedule.getDate());
    }

**Test 17:** Test if the address of the property is correctly returned.

    @Test
    void getAdressOfProperty() {
        assertEquals("Street X", visitSchedule.getAdressOfProperty());
    }

**Test 18:** Test if the address of the property is correctly set.

    @Test
    void setAdressOfProperty() {
        visitSchedule.setAdressOfProperty("Street Y");
        assertEquals("Street Y", visitSchedule.getAdressOfProperty());
    }

**Test 19:** Test if the agent email is correctly returned.

    @Test
    void getAgentEmail() {
        assertEquals("agent@gmail.com", visitSchedule.getAgentEmail());
    }

**Test 20:** Test if the agent email is correctly set.

    @Test
    void setAgentEmail() {
        visitSchedule.setAgentEmail("newagent@gmail.com");
        assertEquals("newagent@gmail.com", visitSchedule.getAgentEmail());
    }

**Test 21:** Test if the requester email is correctly returned.

    @Test
    void getRequesterEmail() {
        assertEquals("requester@gmail.com", visitSchedule.getRequesterEmail());
    }

**Test 22:** Test if the requester email is correctly set.

    @Test
    void setRequesterEmail() {
        visitSchedule.setRequesterEmail("newrequester@gmail.com");
        assertEquals("newrequester@gmail.com", visitSchedule.getRequesterEmail());
    }
    

*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)

## Class VisitSchedule

```java
    public VisitSchedule(int propertyID, String nameOfClient, long telephoneNumberOfClient, LocalDate date, LocalTime startTime, LocalTime endTime, boolean approvedByAgent, String agentEmail, String adressOfProperty, String requesterEmail) {
        this.propertyID = propertyID;
        this.nameOfClient = nameOfClient;
        this.telephoneNumberOfClient = telephoneNumberOfClient;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.approvedByAgent = approvedByAgent;
        this.agentEmail = agentEmail;
        this.adressOfProperty = adressOfProperty;
        this.requesterEmail = requesterEmail;
        }

```     



# 6. Integration and Demo 
* This class will be used when the client wants to schedule a visit to a property.
* The client will have to provide the property ID, the date, the start time and the end time of the visit.
* The client will be able to schedule a visit to a property if the visit is not overlapping with another visit and if the agent approves the visit.
* The agent will be able to see the visits requested by the clients.
* The agent will also be able to approve or reject a visit.
* The requester will receive an email with the confirmation of the visit if the visit is approved by the agent.

# 7. Observations
This class is used in 3 Users Stories:
* US9: As a client, I want to leave a message to the agent to schedule a visit to a property of my interest.
* US15: As an agent, I intend to list all booking requests for properties managed by me.
* US16: As an agent, when viewing a booking request, I want to respond to the user that scheduled the visit.





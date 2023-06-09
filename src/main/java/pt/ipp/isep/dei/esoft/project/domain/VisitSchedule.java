package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * The Visit schedule
 */
public class VisitSchedule implements Serializable {
    private int propertyID;
    private String nameOfClient;
    private long telephoneNumberOfClient;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean approvedByAgent;
    private String agentEmail;
    private String adressOfProperty;
    private String requesterEmail;

    /**
     * Instantiates a new Visit schedule.
     * @param propertyID the property id
     * @param nameOfClient the name
     * @param telephoneNumberOfClient the telephone number
     * @param date the date
     * @param startTime the start time
     * @param endTime the end time
     * @param approvedByAgent the approved by agent
     * @param agentEmail the agent email
     * @param adressOfProperty the adress of property
     * @param requesterEmail the requester email
     */

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


    /**
     * Gets property id.
     */
    public int getPropertyID() {
        return propertyID;
    }

    /**
     * Set property id.
     */
    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    /**
     * Gets name.
     */
    public String getNameOfClient() {
        return nameOfClient;
    }

    /**
     * Sets name.
     */
    public void setNameOfClient(String nameOfClient) {
        this.nameOfClient = nameOfClient;
    }

    /**
     * Gets telephone number.
     */
    public long getTelephoneNumberOfClient() {
        return telephoneNumberOfClient;
    }

    /**
     * Sets telephone number.
     */
    public void setTelephoneNumberOfClient(long telephoneNumberOfClient) {
        this.telephoneNumberOfClient = telephoneNumberOfClient;
    }

    /**
     * Gets date.
     */
    public LocalDate getDate() {
        return date;
    }


    /**
     * Gets start time.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Is overlapping boolean.
     * @param startTime the start time
     * @param endTime the end time
     * @return the boolean
     */
    public boolean isOverlapping(LocalTime startTime, LocalTime endTime) {
        // Check if the start time is within the VisitSchedule's time range
        if ((startTime.equals(this.startTime) || startTime.isAfter(this.startTime)) && startTime.isBefore(this.endTime)) {
            return true;
        }

        // Check if the end time is within the VisitSchedule's time range
        if (endTime.isAfter(this.startTime) && (endTime.isBefore(this.endTime) || endTime.equals(this.endTime))) {
            return true;
        }

        // Check if the provided time range completely covers the VisitSchedule's time range
        if (startTime.isBefore(this.startTime) && endTime.isAfter(this.endTime)) {
            return true;
        }

        return false;
    }


    /**
     * Is approved by agent boolean.
     * @return the boolean
     */
    public boolean isApprovedByAgent() {
        return this.approvedByAgent;
    }

    /**
     * Sets approved by agent.
     * @param approvedByAgent the approved by agent
     */
    public void setApprovedByAgent(boolean approvedByAgent) {
        this.approvedByAgent = approvedByAgent;
    }

    /**
     * Sets date.
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAdressOfProperty() {
        return adressOfProperty;
    }

    public void setAdressOfProperty(String adressOfProperty) {
        this.adressOfProperty = adressOfProperty;
    }

    /**
     * Gets agent email.
     * @return the agent email
     */
    public String getAgentEmail() {
        return agentEmail;
    }

    /**
     * Sets agent email.
     * @param agentEmail the agent email
     */
    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public String getRequesterEmail() {
        return requesterEmail;
    }

    public void setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }

    /**
     * To string string.
     * @return the string
     */
    @Override
    public String toString() {
        return "VisitSchedule{" +
                "propertyID=" + propertyID +
                ", name='" + nameOfClient + '\'' +
                ", telephoneNumber=" + telephoneNumberOfClient +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}

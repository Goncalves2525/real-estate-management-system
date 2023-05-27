package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Luis Leal 1100253@isep.ipp.pt
 */


/**
 * The Visit schedule
 */
public class VisitSchedule {
    private int propertyID;
    private String name;
    private int telephoneNumber;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean approvedByAgent;
    private String agentEmail;

    /**
     * Instantiates a new Visit schedule.
     * @param propertyID the property id
     * @param name the name
     * @param telephoneNumber the telephone number
     * @param date the date
     * @param startTime the start time
     * @param endTime the end time
     * @param approvedByAgent the approved by agent
     * @param agentEmail the agent email
     */

    public VisitSchedule(int propertyID, String name, int telephoneNumber, LocalDate date, LocalTime startTime, LocalTime endTime, boolean approvedByAgent, String agentEmail){
        this.propertyID = propertyID;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.approvedByAgent = approvedByAgent;
        this.agentEmail = agentEmail;
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
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets telephone number.
     */
    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets telephone number.
     */
    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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
        if (startTime.isAfter(this.startTime) && startTime.isBefore(this.endTime)) {
            return true;
        }

        // Check if the end time is within the VisitSchedule's time range
        if (endTime.isAfter(this.startTime) && endTime.isBefore(this.endTime)) {
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

    /**
     * To string string.
     * @return the string
     */
    @Override
    public String toString() {
        return "VisitSchedule{" +
                "propertyID=" + propertyID +
                ", name='" + name + '\'' +
                ", telephoneNumber=" + telephoneNumber +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}

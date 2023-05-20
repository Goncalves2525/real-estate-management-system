package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisitSchedule {
    private int propertyID;
    private String name;
    private int telephoneNumber;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean approvedByAgent;
    private String agentEmail;

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


    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public LocalDate getDate() {
        return date;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

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

    public boolean isApprovedByAgent() {
        return this.approvedByAgent;
    }

    public void setApprovedByAgent(boolean approvedByAgent) {
        this.approvedByAgent = approvedByAgent;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

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

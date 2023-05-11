package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisitSchedule {
    private int propertyID;
    private String name;
    private int telephoneNumber;
    private LocalDate date;
    private String startTime;
    private String endTime;
    private boolean aprovatedByAgent;

    public VisitSchedule(int propertyID, String name, int telephoneNumber, LocalDate date, String startTime, String endTime, boolean aprovatedByAgent){
        this.propertyID = propertyID;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.aprovatedByAgent = aprovatedByAgent;
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


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isOverlapping(LocalTime startTime, LocalTime endTime) {
        // Check if the start time is within the VisitSchedule's time range
        if (startTime.isAfter(LocalTime.parse(this.startTime)) && startTime.isBefore(LocalTime.parse(this.endTime))) {
            return true;
        }

        // Check if the end time is within the VisitSchedule's time range
        if (endTime.isAfter(LocalTime.parse(this.startTime)) && endTime.isBefore(LocalTime.parse(this.endTime))) {
            return true;
        }

        // Check if the provided time range completely covers the VisitSchedule's time range
        if (startTime.isBefore(LocalTime.parse(this.startTime)) && endTime.isAfter(LocalTime.parse(this.endTime))) {
            return true;
        }

        return false;
    }

    public boolean isAprovatedByAgent() {
        return this.aprovatedByAgent;
    }

    public void setAprovatedByAgent(boolean aprovatedByAgent) {
        this.aprovatedByAgent = aprovatedByAgent;
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

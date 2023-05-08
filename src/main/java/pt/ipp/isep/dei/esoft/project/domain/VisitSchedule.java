package pt.ipp.isep.dei.esoft.project.domain;

public class VisitSchedule {
    private int propertyID;
    private String name;
    private int telephoneNumber;
    private String date;
    private String startTime;
    private String endTime;

    public VisitSchedule(int propertyID, String name, int telephoneNumber, String date, String startTime, String endTime) {
        this.propertyID = propertyID;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Date;

public class Announcement {
    private Property property;
    private TypeOfProperty typeOfProperty;
    private TransactionType transactionType;
    private Date publishDate;
    private Comission comission;
    private ArrayList<Photo> photos = new ArrayList<>();

    private House house;

    private Land land;

    private Apartment apartment;

    private Agent agent;
    private final int id;
    private boolean isPublished;
    public static int idCounter = 0;

    public Announcement(Employee agent, Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Comission comission, ArrayList<Photo> photos) {
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.comission = comission;
        this.photos = photos;
        id = idCounter++;
    }

    public Announcement(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Comission comission, ArrayList<Photo> photos, boolean isPublished) {
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.comission = comission;
        this.photos = photos;
        this.isPublished = isPublished;
        id = idCounter++;
    }

    public Announcement(Agent agent,Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Comission comission, ArrayList<Photo> photos, boolean isPublished) {
        this.agent = agent;
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.comission = comission;
        this.photos = photos;
        this.isPublished = isPublished;
        id = idCounter++;
    }

    public Announcement(Employee employee,Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Comission comission, ArrayList<Photo> photos, boolean isPublished) {
        //this.agent = employee;
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.comission = comission;
        this.photos = photos;
        this.isPublished = isPublished;
        id = idCounter++;
    }

    public Announcement(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate,  ArrayList<Photo> photos) {
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.photos = photos;
        id = idCounter++;
    }

    public Announcement(Agent agent,Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Comission comission,  ArrayList<Photo> photos, House house) {
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.photos = photos;
        this.house = house;
        this.comission = comission;
        id = idCounter++;
    }

    public Announcement(Employee agent, Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Comission comission, ArrayList<Photo> photos, Land land) {
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.photos = photos;
        this.land = land;
        this.comission = comission;
        id = idCounter++;
    }

    public Announcement(Employee agent, Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Comission comission, ArrayList<Photo> photos, Apartment apartment) {
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.photos = photos;
        this.apartment = apartment;
        this.comission = comission;
        id = idCounter++;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Property getProperty() {
        return property;
    }

    public TypeOfProperty getTypeOfProperty() {
        return typeOfProperty;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setTypeOfProperty(TypeOfProperty typeOfProperty) {
        this.typeOfProperty = typeOfProperty;
    }

    public Comission getComission() {
        return comission;
    }

    public boolean isPublished() {
        return this.isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public void setComission(Comission comission) {
        this.comission = comission;
    }

    public int getId() {
        try{
            return id;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public boolean hasProperty(Property property) {
        return this.property.equals(property);
    }

    public boolean hasTypeOfProperty(TypeOfProperty typeOfProperty) {
        return this.typeOfProperty.equals(typeOfProperty);
    }

    /**
     * This method returns the Agent's name.
     *
     * @return name
     */
    public String getAgentEmail() {
        try{
            return this.agent.getEmail();
        } catch (NullPointerException e) {
            return "";
        }
    }

    public boolean hasPublishDate(Date publishDate) {
        return this.publishDate.equals(publishDate);
    }

    public boolean hasCommission(Comission comission) {
        return this.comission.equals(comission);
    }

    @Override
    public String toString() {
        return String.format("-ID: %d \n-Property Information: %s \n-Type of Property: %s \n-Transaction Type: %s \n-Publish Date: %s \n-Comission: %s \n-Photos: %s \n-IsPublished: %b",id, property, typeOfProperty.toString(), transactionType.toString(), publishDate, comission, photos, isPublished);
    }

    public void setIsPublished() {
        this.isPublished = true;
        this.publishDate = new Date();
    }
}

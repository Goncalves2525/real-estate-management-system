package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * Announcement class that contains all the information about an announcement.
 */

public class Announcement {
    private Property property;
    private TypeOfProperty typeOfProperty;
    private TransactionType transactionType;
    private Date publishDate;
    private Commission commission;
    private ArrayList<Photo> photos = new ArrayList<>();

    private House house;

    private Land land;

    private Apartment apartment;

    private Employee agent;
    private final int id;
    private boolean isPublished;
    public static int idCounter = 0;

    /**
     * This method is the constructor of Announcement.
     * @param agent Employee
     * @param property Property
     * @param typeOfProperty TypeOfProperty
     * @param transactionType TransactionType
     * @param publishDate Date
     * @param commission Commission
     * @param photos ArrayList<Photo>
     */
    public Announcement(Employee agent, Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Commission commission, ArrayList<Photo> photos) {
        this.agent = agent;
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.commission = commission;
        this.photos = photos;
        id = idCounter++;
    }

    /**
     * This method is the constructor of Announcement.
     * @param property Property
     * @param typeOfProperty TypeOfProperty
     * @param transactionType TransactionType
     * @param publishDate Date
     * @param commission Commission
     * @param photos ArrayList<Photo>
     */
    public Announcement(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Commission commission, ArrayList<Photo> photos, boolean isPublished) {
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.commission = commission;
        this.photos = photos;
        this.isPublished = isPublished;
        id = idCounter++;
    }

    /**
     * This method is the constructor of Announcement.
     * @param employee Employee
     * @param property Property
     * @param typeOfProperty TypeOfProperty
     * @param transactionType TransactionType
     * @param publishDate Date
     * @param commission Commission
     * @param photos ArrayList<Photo>
     */
    public Announcement(Employee employee, Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Commission commission, ArrayList<Photo> photos, boolean isPublished) {
        this.agent = employee;
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.commission = commission;
        this.photos = photos;
        this.isPublished = isPublished;
        id = idCounter++;
    }

    /**
     * This method is the constructor of Announcement.
     * @param property Property
     * @param typeOfProperty TypeOfProperty
     * @param transactionType TransactionType
     * @param publishDate Date
     * @param photos ArrayList<Photo>
     */
    public Announcement(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate,  ArrayList<Photo> photos) {
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.photos = photos;
        id = idCounter++;
    }

    /**
     * This method returns the Transaction Type.
     * @return transactionType
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * This method sets the Announcement TransactionType.
     */
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * This method returns the Announcement's Property.
     *
     * @return property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * This method returns the Announcement's TypeOfProperty.
     *
     * @return typeOfProperty
     */
    public TypeOfProperty getTypeOfProperty() {
        return typeOfProperty;
    }

    /**
     * This method returns the Announcement's PublishDate.
     *
     * @return publishDate
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * This method sets the Announcement's Property.
     *
     * @return property
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * This method sets the Announcement's TypeOfProperty.
     *
     * @return typeOfProperty
     */
    public void setTypeOfProperty(TypeOfProperty typeOfProperty) {
        this.typeOfProperty = typeOfProperty;
    }

    /**
     * This method returns the Announcement's Commission.
     *
     * @return commission
     */
    public Commission getComission() {
        return commission;
    }

    /**
     * This method returns the Announcement's published status.
     *
     * @return isPublished
     */
    public boolean isPublished() {
        return this.isPublished;
    }

    /**
     * This method sets the Announcement's published status.
     *
     */
    public void setPublished(boolean published) {
        isPublished = published;
    }

    /**
     * This method sets the Announcement's Commission.
     *
     * @return commission
     */
    public void setComission(Commission commission) {
        this.commission = commission;
    }

    /**
     * This method returns the Announcement's id, or 0 if it is null.
     *
     * @return id
     */
    public int getId() {
        try{
            return id;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    /**
     * This method returns the Announcement's idCounter.
     *
     * @return idCounter
     */
    public static int getIdCounter() {
        return idCounter;
    }

    /**
     * This method sets the Announcement's Publish Date.
     *
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * This method returns if the Announcement has a Property.
     *
     * @return property.equals(property)
     */
    public boolean hasProperty(Property property) {
        return this.property.equals(property);
    }

    /**
     * This method returns if the Announcement has a TypeOfProperty.
     *
     * @return typeOfProperty.equals(typeOfProperty)
     */
    public boolean hasTypeOfProperty(TypeOfProperty typeOfProperty) {
        return this.typeOfProperty.equals(typeOfProperty);
    }

    /**
     * This method returns the Agent's email, or empty if it's null.
     *
     * @return email
     */
    public String getAgentEmail() {
        try{
            return this.agent.getEmail();
        } catch (NullPointerException e) {
            return "";
        }
    }

    /**
     * This method returns if the Announcement has an equal Publish Date.
     *
     * @return publishDate.equals(publishDate)
     */
    public boolean hasPublishDate(Date publishDate) {
        return this.publishDate.equals(publishDate);
    }

    /**
     * This method returns if the Announcement has an equal Commission.
     *
     * @return commission.equals(commission)
     */
    public boolean hasCommission(Commission commission) {
        return this.commission.equals(commission);
    }


    /**
     * This method returns the overriden toString method.
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("-ID: %d \n-Property Information: %s \n-Type of Property: %s \n-Transaction Type: %s \n-Publish Date: %s \n-Comission: %s \n-Photos: %s \n-IsPublished: %b",id, property, typeOfProperty.toString(), transactionType.toString(), publishDate, commission, photos, isPublished);
    }


    /**
     * This method sets the Announcement's isPublished to true and the publishDate to the current date.
     *
     */
    public void setIsPublished() {
        this.isPublished = true;
        this.publishDate = new Date();
    }
}

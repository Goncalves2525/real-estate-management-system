package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * Announcement class that contains all the information about an announcement.
 */

public class Announcement {
    private int propertyID;
    private TypeOfProperty typeOfProperty;
    private TransactionType transactionType;
    private Date publishDate;
    private Commission commission;
    private ArrayList<Photo> photos = new ArrayList<>();
    private Employee agent;
    private final int id;
    private boolean isPublished;

    public static int idCounter = 0;

    private Agency agency;


    /**
     * This method is the constructor of Announcement.
     * @param propertyID int
     * @param agent Employee
     * @param typeOfProperty TypeOfProperty
     * @param transactionType TransactionType
     * @param publishDate Date
     * @param commission Commission
     * @param photos ArrayList<Photo>
     */
    public Announcement(Employee agent, int propertyID, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Commission commission, ArrayList<Photo> photos) {
        this.agent = agent;
        this.propertyID = propertyID;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.commission = commission;
        this.photos = photos;
        id = idCounter++;
    }

    /**
     * This method is the constructor of Announcement.
     * @param propertyID int
     * @param typeOfProperty TypeOfProperty
     * @param transactionType TransactionType
     * @param publishDate Date
     * @param commission Commission
     * @param photos ArrayList<Photo>
     */
    public Announcement(int propertyID, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Commission commission, ArrayList<Photo> photos, boolean isPublished) {
        this.propertyID = propertyID;
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
     * @param propertyID int
     * @param typeOfProperty TypeOfProperty
     * @param transactionType TransactionType
     * @param publishDate Date
     * @param commission Commission
     * @param photos ArrayList<Photo>
     */
    public Announcement(Employee employee, int propertyID, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Commission commission, ArrayList<Photo> photos, boolean isPublished) {
        this.agent = employee;
        this.propertyID = propertyID;
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
     * @param propertyID int
     * @param typeOfProperty TypeOfProperty
     * @param transactionType TransactionType
     * @param publishDate Date
     * @param photos ArrayList<Photo>
     */
    public Announcement(int propertyID, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate,  ArrayList<Photo> photos) {
        this.propertyID = propertyID;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.photos = photos;
        id = idCounter++;
    }

    /**
     * This method is the constructor of Announcement, with an existing ID.
     * @param sid int
     */
    public Announcement(int sid, String owner_name, int owner_passportNum, String owner_TIN, String owner_email, String owner_phone, String property_type, int property_area, String property_location, int property_distanceFromCenter,
                        String property_numberBedrooms, String property_numberBathrooms, String property_pnumParking, String property_centralHeating, String property_airconditioned, String property_basement, String property_loft, String property_sunExposure, int property_requested_sale_rent_price, int property_sale_rent_price, int commission, String contract_duration, Date property_dateAnnounceRequest, Date property_dateofSale, String type_business, int store_ID, String store_name, String store_location, String store_phonenumber, String store_emailAddress) {
        this.id = sid;
        this.transactionType = TransactionType.valueOf(type_business.toUpperCase());
        this.typeOfProperty = TypeOfProperty.valueOf(property_type.toUpperCase());
        this.publishDate = property_dateAnnounceRequest;
        this.commission = new Commission(commission);
        String[] storeAddressArray = store_location.trim().split(",");
        Address storeAddress = new Address(storeAddressArray[0],storeAddressArray[1],storeAddressArray[2],Integer.parseInt(storeAddressArray[storeAddressArray.length-1].trim()));
        this.agency = new Agency(store_ID,store_name,store_emailAddress,Long.parseLong(store_phonenumber.replace("-","")),storeAddress);
        String[] propertyAddressArray = property_location.trim().split(",");
        Address propertyAddress = new Address(propertyAddressArray[0],propertyAddressArray[1],propertyAddressArray[2],Integer.parseInt(propertyAddressArray[propertyAddressArray.length-1].trim()));
        Property propertyForAnnouncement = new Property(property_area,property_distanceFromCenter,property_sale_rent_price, propertyAddress);
        this.propertyID = propertyForAnnouncement.getId();
        //Order orderForAnnouncement = new Order()
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
    public int getPropertyID() {
        return propertyID;
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
    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
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
    public Commission getCommission() {
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
    public void setCommission(Commission commission) {
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
    public boolean hasProperty(int propertyID) {
        return this.propertyID == propertyID;
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
        return String.format("-ID: %d \n-Type of Property: %s \n-Transaction Type: %s \n-Publish Date: %s \n-Comission: %s \n-Photos: %s \n-IsPublished: %b",id, typeOfProperty.toString(), transactionType.toString(), publishDate, commission, photos, isPublished);
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

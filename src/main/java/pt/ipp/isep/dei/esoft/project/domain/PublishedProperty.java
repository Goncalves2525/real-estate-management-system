package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Date;

public class PublishedProperty {
    private Property property;
    private TypeOfProperty typeOfProperty;
    private TransactionType transactionType;
    private Date publishDate;
    private Comission comission;
    private ArrayList<Photo> photos = new ArrayList<>();
    private int id;
    public static int idCounter = 0;

    public PublishedProperty(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date publishDate, Comission comission, ArrayList<Photo> photos) {
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.transactionType = transactionType;
        this.publishDate = publishDate;
        this.comission = comission;
        this.photos = photos;
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

    public Comission getCommission() {
        return comission;
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

    public void setComission(Comission comission) {
        this.comission = comission;
    }

    public int getId() {
        return id;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setCommission(Comission comission) {
        this.comission = comission;
    }

    public boolean hasProperty(Property property) {
        return this.property.equals(property);
    }

    public boolean hasTypeOfProperty(TypeOfProperty typeOfProperty) {
        return this.typeOfProperty.equals(typeOfProperty);
    }

    public boolean hasPublishDate(Date publishDate) {
        return this.publishDate.equals(publishDate);
    }

    public boolean hasCommission(Comission comission) {
        return this.comission.equals(comission);
    }

    @Override
    public String toString() {
        return "PublishedProperty{" +
                "property=" + property +
                ", typeOfProperty=" + typeOfProperty +
                ", publishDate=" + publishDate +
                ", commission=" + comission +
                '}';
    }
}

package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

public class PublishedProperty {
    private Property property;
    private TypeOfProperty typeOfProperty;
    private Date publishDate;
    private Commission commission;

    public PublishedProperty(Property property, TypeOfProperty typeOfProperty, Date publishDate, Commission commission) {
        this.property = property;
        this.typeOfProperty = typeOfProperty;
        this.publishDate = publishDate;
        this.commission = commission;
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

    public Commission getCommission() {
        return commission;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setTypeOfProperty(TypeOfProperty typeOfProperty) {
        this.typeOfProperty = typeOfProperty;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
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

    public boolean hasCommission(Commission commission) {
        return this.commission.equals(commission);
    }


}

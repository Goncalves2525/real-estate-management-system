package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementTest {
    private Employee employee;
    private Property property;
    private TypeOfProperty typeOfProperty;
    private TransactionType transactionType;
    private Date publishDate;
    private Commission commission;
    private ArrayList<Photo> photos;

    @BeforeEach
    void setUp() {
        Address address = new Address("123 Main St", "Anytown", "Washington",123);
        List<Role> roles = new ArrayList<>();
        roles.add(Role.AGENT);
        employee = new Employee("John Doe", "jonh.doe@this.app",123456789,123,1234567890,address ,roles);
        property = new House(123,123,123,address,1,1,1,true,true,true,true,SunExposure.SOUTH);
        typeOfProperty = TypeOfProperty.HOUSE;
        transactionType = TransactionType.SALE;
        publishDate = new Date();
        commission = new Commission(0.05);
        photos = new ArrayList<>();
    }

    @Test
    void testConstructorWithAgent() {
        Announcement announcement = new Announcement(employee, property, typeOfProperty, transactionType, publishDate, commission, photos);
        assertEquals(employee.getEmail(), announcement.getAgentEmail());
        assertEquals(property, announcement.getProperty());
        assertEquals(typeOfProperty, announcement.getTypeOfProperty());
        assertEquals(transactionType, announcement.getTransactionType());
        assertEquals(publishDate, announcement.getPublishDate());
        assertEquals(commission, announcement.getComission());
        assertFalse(announcement.isPublished());
    }

    @Test
    void testConstructorWithoutAgent() {
        Announcement announcement = new Announcement(property, typeOfProperty, transactionType, publishDate, commission, photos, true);
        assertEquals(announcement.getAgentEmail(),"");
        assertEquals(property, announcement.getProperty());
        assertEquals(typeOfProperty, announcement.getTypeOfProperty());
        assertEquals(transactionType, announcement.getTransactionType());
        assertEquals(publishDate, announcement.getPublishDate());
        assertEquals(commission, announcement.getComission());
        assertTrue(announcement.isPublished());
    }

    @Test
    void testGettersAndSetters() {
        Announcement announcement = new Announcement(employee, property, typeOfProperty, transactionType, publishDate, commission, photos);
        assertEquals(transactionType, announcement.getTransactionType());
        announcement.setTransactionType(TransactionType.RENT);
        assertEquals(TransactionType.RENT, announcement.getTransactionType());

        assertEquals(property, announcement.getProperty());
        Property newProperty = new Apartment(123,123,123,new Address("123 Main St", "Anytown", "Washington",123),1,1);
        announcement.setProperty(newProperty);
        assertEquals(newProperty, announcement.getProperty());

        assertEquals(typeOfProperty, announcement.getTypeOfProperty());
        TypeOfProperty newTypeOfProperty = TypeOfProperty.APARTMENT;
        announcement.setTypeOfProperty(newTypeOfProperty);
        assertEquals(newTypeOfProperty, announcement.getTypeOfProperty());

        assertEquals(publishDate, announcement.getPublishDate());
        Date newPublishDate = new Date();
        announcement.setPublishDate(newPublishDate);
        assertEquals(newPublishDate, announcement.getPublishDate());

        assertEquals(commission, announcement.getComission());
        Commission newCommission = new Commission(0.1);
        announcement.setComission(newCommission);
        assertEquals(newCommission, announcement.getComission());

        assertFalse(announcement.isPublished());
        announcement.setPublished(true);
        assertTrue(announcement.isPublished());
    }
}

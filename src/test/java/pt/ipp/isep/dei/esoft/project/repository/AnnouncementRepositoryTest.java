package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnouncementRepositoryTest {
Repositories repositories = Repositories.getInstance();
AnnouncementRepository announcementRepository = repositories.getAnnouncementRepository();
PropertyRepository propertyRepository = repositories.getPropertyRepository();

    @BeforeEach
    void setUp() {
        announcementRepository.removeAllAnnouncements();
        propertyRepository.removeAllProperties();
        Commission c1 = new Commission();
        Date d1 = new Date(2019, 10, 10);
        Date d2 = new Date(2019, 10, 11);
        Date d3 = new Date(2019, 10, 12);
        TypeOfProperty typeHouse = TypeOfProperty.HOUSE;
        TypeOfProperty typeApartment = TypeOfProperty.APARTMENT;
        TypeOfProperty typeLand = TypeOfProperty.LAND;
        TransactionType typeRent = TransactionType.RENT;
        TransactionType typeSale = TransactionType.SALE;
        Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        Address a2 = new Address("Rua 2", "Porto", "Porto", "Porto", 54321);
        Address a3 = new Address("Rua 3", "Lisboa", "Lisboa", "Lisboa", 123111);
        Address a4 = new Address("Rua 4", "Faro", "Faro", "Algarve", 123222);
        Property.idCounter = 1;
        propertyRepository.addHouse(150, 30, 250000, a1, 1, 2, 1, true, true, false, false, SunExposure.EAST);
        propertyRepository.addApartment(150, 30, 100000, a2, 4, 2, 1, true, true);
        propertyRepository.addLand(150, 30, 50000, a3);
        propertyRepository.addHouse(150, 30, 3000000, a4, 1, 2, 1, true, true, false, false, SunExposure.EAST);
        Announcement.idCounter = 1;
        announcementRepository.addAnnouncement(0, typeHouse, typeSale, d1, c1, null, true);
        announcementRepository.addAnnouncement(1, typeApartment, typeRent, d2, c1, null, true);
        announcementRepository.addAnnouncement(2, typeLand, typeRent, d3, c1, null, true);
        announcementRepository.addAnnouncement(3, typeHouse, typeSale, d3, c1, null, true);
    }

    @Test
    void ensureGetAllAnnouncementsSortedByDefualtCriteria_ReturnsCorrectList() {

        ArrayList<Announcement> result = announcementRepository.getAllAnnouncementsSortedByDefualtCriteria();

        int expectedFirst = 3;
        int expectedLast = 0;
        int resultFirst = result.get(0).getId();
        int resultLast = result.get(3).getId();

        assertEquals(expectedFirst, resultFirst);
        assertEquals(expectedLast, resultLast);
    }

    @Test
    void ensureGetAllAnnouncementsSortedBySortCriteria_ReturnsCorrectList() {
        ArrayList<Announcement> result = announcementRepository.getAllAnnouncementsSortedBySortCriteria("price", "descending");

        double expectedFirst = 3000000;
        double expectedLast = 50000;
        double resultFirst = announcementRepository.getPropertyByAnnouncement(result.get(0)).getPrice();
        double resultLast = announcementRepository.getPropertyByAnnouncement(result.get(3)).getPrice();

        assertEquals(expectedFirst, resultFirst);
        assertEquals(expectedLast, resultLast);
    }

    @Test
    void ensureGetAllAnnouncementsSortedBySortCriteria_ReturnsEmptyWithWrongParameters() {
        ArrayList<Announcement> result = announcementRepository.getAllAnnouncementsSortedBySortCriteria("wrong", "parameter");

        String expected = "[]";
        String resultString = result.toString();

        assertEquals(expected, resultString);
    }

    @Test
    void ensureGetFilteredAnnouncements_FiltersCorrectly() {
        ArrayList<Announcement> result = announcementRepository.getFilteredAnnouncements(TypeOfProperty.HOUSE, TransactionType.SALE, 1);

        int expected = 2;
        int resultInt = result.size();
        TypeOfProperty resultType = result.get(0).getTypeOfProperty();
        TransactionType resultTransaction = result.get(0).getTransactionType();
        int resultRooms = ((House) announcementRepository.getPropertyByAnnouncement(result.get(0))).getNumberOfBedrooms();

        //asserts array size
        assertEquals(expected, resultInt);

        //asserts filters
        assertEquals(TypeOfProperty.HOUSE, resultType);
        assertEquals(TransactionType.SALE, resultTransaction);
        assertEquals(1, resultRooms);
    }

    @Test
    void ensureGetFilteredAnnouncements_ReturnsEmptyWithNoFilters() {
        ArrayList<Announcement> result = announcementRepository.getFilteredAnnouncements(null, null, -1);

        int expected = 0;
        int resultInt = result.size();
        String expectedString = "[]";
        String resultString = result.toString();

        assertEquals(expected, resultInt);
        assertEquals(expectedString, resultString);

    }

    @Test
    void ensureGetFilteredAndSortedAnnouncements_FiltersAndSortsCorrectly() {
        ArrayList<Announcement> result = announcementRepository.getFilteredAndSortedAnnouncements(TypeOfProperty.HOUSE, TransactionType.SALE, 1, "price", "descending");

        int expected = 2;
        int resultInt = result.size();
        double expectedFirst = 3000000;
        double expectedLast = 250000;
        double resultFirst = announcementRepository.getPropertyByAnnouncement(result.get(0)).getPrice();
        double resultLast = announcementRepository.getPropertyByAnnouncement(result.get(1)).getPrice();
        TypeOfProperty resultType = result.get(0).getTypeOfProperty();
        TransactionType resultTransaction = result.get(0).getTransactionType();
        int resultRooms = ((House) announcementRepository.getPropertyByAnnouncement(result.get(0))).getNumberOfBedrooms();

        //asserts array size
        assertEquals(expected, resultInt);

        //asserts sort order
        assertEquals(expectedFirst, resultFirst);
        assertEquals(expectedLast, resultLast);

        //asserts filters
        assertEquals(TypeOfProperty.HOUSE, resultType);
        assertEquals(TransactionType.SALE, resultTransaction);
        assertEquals(1, resultRooms);
    }

    @Test
    void ensureAddAnnouncement_Works() {
        Property property = new House(150, 30, 250000, new Address("Rua 1", "Braga", "Braga", "Minho", 123333), 1, 2, true, true);
        announcementRepository.addAnnouncement(property.getId(), TypeOfProperty.HOUSE, TransactionType.SALE, new Date(2019, 10, 10), new Commission(), null, true);

        int expected = 5;
        int result = announcementRepository.getAllAnnouncementsSortedByDefualtCriteria().size();

        assertEquals(expected, result);
    }

    @Test
    void getAnnouncementsByAgent() {
        ArrayList<Announcement> result = announcementRepository.getAnnouncementsByAgent("employee5@this.app");

        int expected = 0;
        int resultInt = result.size();

        assertEquals(expected, resultInt);
    }

    @Test
    void getAnnouncementsByAgent_ReturnsEmptyWithWrongEmail() {
        ArrayList<Announcement> result = announcementRepository.getAnnouncementsByAgent("wrongemail@this.app");

        int expected = 0;
        int resultInt = result.size();

        assertEquals(expected, resultInt);
    }

}
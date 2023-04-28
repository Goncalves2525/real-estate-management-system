package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementRepositoryTest {

AnnouncementRepository announcementRepository = new AnnouncementRepository();

    @BeforeEach
    void setUp() {

        Comission c1 = new Comission();
        Date d1 = new Date(2019, 10, 10);
        Date d2 = new Date(2019, 10, 11);
        Date d3 = new Date(2019, 10, 12);
        TypeOfProperty typeHouse = TypeOfProperty.HOUSE;
        TypeOfProperty typeApartment = TypeOfProperty.APARTMENT;
        TypeOfProperty typeLand = TypeOfProperty.LAND;
        TransactionType typeRent = TransactionType.RENT;
        TransactionType typeSale = TransactionType.SALE;
        Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        Property p1 = new House(150, 30, 250000, a1, 1, 2, true, true);
        Property p2 = new Apartment(150, 30, 100000, a1, 2, 2);
        Property p3 = new Land(150, 30, 50000, a1);
        Property p4 = new House(150, 30, 3000000, a1, 3, 2, true, true);
        announcementRepository.addAnnouncement(p1, typeHouse, typeSale, d1, c1, null, true);
        announcementRepository.addAnnouncement(p2, typeApartment, typeRent, d2, c1, null, true);
        announcementRepository.addAnnouncement(p3, typeLand, typeSale, d3, c1, null, true);
        announcementRepository.addAnnouncement(p4, typeHouse, typeRent, d3, c1, null, true);
    }

    @Test
    void ensureGetAllAnnouncementsSortedByDefualtCriteria_ReturnCorrectList() {
        ArrayList<Announcement> result = announcementRepository.getAllAnnouncementsSortedByDefualtCriteria();

        int expectedFirst = 3;
        int expectedLast = 0;
        int resultFirst = result.get(0).getId();
        int resultLast = result.get(3).getId();

        assertEquals(expectedFirst, resultFirst);
        assertEquals(expectedLast, resultLast);
    }

    @Test
    void ensureGetAllAnnouncementsSortedByDefualtCriteria_FailsWithWrongParameters() {

        ArrayList<Announcement> result = announcementRepository.getAllAnnouncementsSortedByDefualtCriteria();

        int expectedFirst = 3;
        int expectedLast = 0;
        int resultFirst = result.get(0).getId();
        int resultLast = result.get(3).getId();

        assertEquals(expectedFirst, resultFirst);
        assertEquals(expectedLast, resultLast);
    }

    @Test
    void getAllAnnouncementsSortedBySortCriteria() {
    }

    @Test
    void getFilteredAnnouncements() {
    }

    @Test
    void getFilteredAndSortedAnnouncements() {
    }

    @Test
    void addAnnouncement() {
    }

    @Test
    void addAnnouncementFromOwner() {
    }

    @Test
    void addAnnouncementByRequest() {
    }
}
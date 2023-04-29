# US 001 - Display Listed Properties 

# 4. Tests 

**Test 1:** Checks that when a user doesn't choose filters nor sort criteria, a list of all properties sorted by default criteria (most recently added) is returned - AC5.

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



**Test 2:** Checks that when a user only choses to sort, the list of properties is correctly ordered - AC2 and AC4. 

	@Test
    void ensureGetAllAnnouncementsSortedBySortCriteria_ReturnsCorrectList() {
        ArrayList<Announcement> result = announcementRepository.getAllAnnouncementsSortedBySortCriteria("price", "descending");

        double expectedFirst = 3000000;
        double expectedLast = 50000;
        double resultFirst = result.get(0).getProperty().getPrice();
        double resultLast = result.get(3).getProperty().getPrice();

        assertEquals(expectedFirst, resultFirst);
        assertEquals(expectedLast, resultLast);
    }

**Test 3:** Checks that Test 2 method returns an empty list with incorrect parameters  
    
    @Test
    void ensureGetAllAnnouncementsSortedBySortCriteria_ReturnsEmptyWithWrongParameters() {
        ArrayList<Announcement> result = announcementRepository.getAllAnnouncementsSortedBySortCriteria("wrong", "parameter");

        String expected = "[]";
        String resultString = result.toString();

        assertEquals(expected, resultString);
    }

**Test 4:** Checks that when a user only choses to filter, the list of properties is correctly filtered  - AC1 and AC3. 

    @Test
    void ensureGetFilteredAnnouncements_FiltersCorrectly() {
        ArrayList<Announcement> result = announcementRepository.getFilteredAnnouncements(TypeOfProperty.HOUSE, TransactionType.SALE, 1);

        int expected = 2;
        int resultInt = result.size();
        TypeOfProperty resultType = result.get(0).getTypeOfProperty();
        TransactionType resultTransaction = result.get(0).getTransactionType();
        int resultRooms = ((House) result.get(0).getProperty()).getNumberOfBedrooms();

        //asserts array size
        assertEquals(expected, resultInt);

        //asserts filters
        assertEquals(TypeOfProperty.HOUSE, resultType);
        assertEquals(TransactionType.SALE, resultTransaction);
        assertEquals(1, resultRooms);
    }

**Test 5:** Checks that Test 4 method returns an empty list with incorrect parameters

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

**Test 6:** Checks that when a user choses to filter and sort, the list of properties is correctly filtered and sorted - AC1, AC2 and AC4. 

    @Test
    void ensureGetFilteredAndSortedAnnouncements_FiltersAndSortsCorrectly() {
        ArrayList<Announcement> result = announcementRepository.getFilteredAndSortedAnnouncements(TypeOfProperty.HOUSE, TransactionType.SALE, 1, "price", "descending");

        int expected = 2;
        int resultInt = result.size();
        double expectedFirst = 3000000;
        double expectedLast = 250000;
        double resultFirst = result.get(0).getProperty().getPrice();
        double resultLast = result.get(1).getProperty().getPrice();
        TypeOfProperty resultType = result.get(0).getTypeOfProperty();
        TransactionType resultTransaction = result.get(0).getTransactionType();
        int resultRooms = ((House) result.get(0).getProperty()).getNumberOfBedrooms();

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


# 5. Construction (Implementation)


## Class ListAnnouncementsController 

```java
public ArrayList<Announcement> getAllAnnouncementsSortedByDefualtCriteria() {
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getAllAnnouncementsSortedByDefualtCriteria();
        }
```       
```java        
public ArrayList<Announcement> getAllAnnouncementsSortedBySortCriteria(String sortCriteria, String order){
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getAllAnnouncementsSortedBySortCriteria(sortCriteria, order);
        }        
```
```java
public ArrayList<Announcement> getFilteredAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms) {
    AnnouncementRepository announcementRepository = getAnnoucementRepository();
    return announcementRepository.getFilteredAnnouncements(typeOfProperty, transactionType, numberOfRooms);
}
```
```java
 public ArrayList<Announcement> getFilteredAndSortedAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order){
        AnnouncementRepository announcementRepository = getAnnoucementRepository();
        return announcementRepository.getFilteredAndSortedAnnouncements(typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
    }
```    

# 6. Integration and Demo

* Some Announcements are bootstrapped while system starts.


# 7. Observations








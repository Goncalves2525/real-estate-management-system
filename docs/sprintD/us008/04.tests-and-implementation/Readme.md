# US 008 - Post an Announcement

# 4. Tests 

**Test 1:** Checks that when an invalid announcement id is given, the system returns null.

    @Test
    void getAnnouncementById_InvalidId_ReturnNull() {
        // Arrange
        int id = -1;
        if(announcementRepository.getAnnouncementById(id) == null){
            throw new IllegalArgumentException();
        }
        // Actual announcement
        Announcement actualAnnouncement = controller.getAnnouncementById(id);

        // Assert
        assertNull(actualAnnouncement);
    }


# 5. Construction (Implementation)


## Class PublishAnnouncementController 

```java
public Announcement getAnnouncementById(int id){
    try {
        return announcementRepository.getAnnouncementById(id);
    } catch (IllegalArgumentException e) {
        return null;
    }
}
```

# 6. Integration and Demo

* Some Announcements are bootstrapped when the system starts, others are created by the user and some can be imported.


# 7. Observations








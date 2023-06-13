# US 017 - As a network manager, I want to list all deals made.

# 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Announcement class with null values. 

    @Test(expected = IllegalArgumentException.class)
        Announcement announcement = new Announcement(employee, propertyID, typeOfProperty, transactionType, publishDate, commission, photos);
        assertThrows(IllegalArgumentException.class, () -> announcement.setTransactionType(null));
        assertThrows(IllegalArgumentException.class, () -> announcement.setTypeOfProperty(null));
        assertThrows(IllegalArgumentException.class, () -> announcement.setPublishDate(null));
        assertThrows(IllegalArgumentException.class, () -> announcement.setCommission(null));
    }

*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)

## Class ImportController

```java
public String importData(ArrayList<String[]> dataToImport) {
    String importResult = "";
    int totalImported = 0;
    int totalItemsToImport = dataToImport.size();
    try {

        for (String[] data : dataToImport) {
            try {
                announcementRepository.addAnnouncementFromImportedFile(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2].trim()), data[3], data[4], data[5], data[6], Integer.parseInt(data[7].trim()), data[8], Integer.parseInt(data[9].trim()), data[10], data[11], data[12], data[13], data[14], data[15], data[16], data[17], Integer.parseInt(data[18].trim()), Integer.parseInt(data[19].trim()), Integer.parseInt(data[20].trim()), data[21], parseDate(data[22]), parseDate(data[23]), data[24],Integer.parseInt(data[25].trim()), data[26], data[27], data[28], data[29]);
                totalImported++;
                importResult += "\nAnnouncement imported: " + data[0];
            } catch (Exception e) {
                importResult += "\nError importing Announcement: " + data[0] + " - " + e.getMessage();
            }
        }
    } catch (Exception e) {
        importResult += "Error importing data";
    }
    return importResult;
}
```     



# 6. Integration and Demo 

* A new option on the Administrator menu options was added.


# 7. Observations







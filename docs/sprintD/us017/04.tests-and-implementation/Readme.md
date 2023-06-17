# US 017 - As a network manager, I want to list all deals made.

# 4. Tests 

**Test 1:** Check if the properties are sorted by area in descending/descending order.
        
        // Create a list of properties for testing
        ArrayList<Property> properties = new ArrayList<>();
        properties.add(new Property("A", 100));
        properties.add(new Property("B", 200));
        properties.add(new Property("C", 150));
        properties.add(new Property("D", 50));
        properties.add(new Property("E", 300));

        // Test sortPropertiesInsertionSortByAreaDescending
        ArrayList<Property> sortedDesc = sortPropertiesInsertionSortByAreaDescending(properties);
        System.out.println("Descending Order (Insertion Sort):");
        for (Property p : sortedDesc) {
            System.out.println(p.getName() + " - " + p.getArea());
        }
        System.out.println();

        // Test sortPropertiesSelectionSortByAreaDescending
        ArrayList<Property> sortedDesc2 = sortPropertiesSelectionSortByAreaDescending(properties);
        System.out.println("Descending Order (Selection Sort):");
        for (Property p : sortedDesc2) {
            System.out.println(p.getName() + " - " + p.getArea());
        }
        System.out.println();

        // Test sortPropertiesSelectionSortByAreaAscending
        ArrayList<Property> sortedAsc = sortPropertiesSelectionSortByAreaAscending(properties);
        System.out.println("Ascending Order (Selection Sort):");
        for (Property p : sortedAsc) {
            System.out.println(p.getName() + " - " + p.getArea());
        }
        System.out.println();

        // Test sortPropertiesInsertionSortByAreaAscending
        ArrayList<Property> sortedAsc2 = sortPropertiesInsertionSortByAreaAscending(properties);
        System.out.println("Ascending Order (Insertion Sort):");
        for (Property p : sortedAsc2) {
            System.out.println(p.getName() + " - " + p.getArea());
        }
        System.out.println();
    }


*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)

```java

public ArrayList<Property> getPropertiesInsertionSortByAreaDescending() {
         return propertyDealRepository.getPropertiesInsertionSortByAreaDescending();
     }
public ArrayList<Property> getPropertiesInsertionSortByAreaAscending() {
         return propertyDealRepository.getPropertiesInsertionSortByAreaAscending();
     }
public ArrayList<Property> getPropertiesSelectionSortByAreaAscending() {
        return propertyDealRepository.getPropertiesSelectionSortByAreaAscending();
    }
public ArrayList<Property> getPropertiesSelectionSortByAreaDescending() {
        return propertyDealRepository.getPropertiesSelectionSortByAreaDescending();
    }

public ArrayList<Property> getPropertiesSelectionSortByID() {
        return propertyDealRepository.getPropertiesSelectionSortByID();
    }
```    
    

# 6. Integration and Demo 

* A new option on the Administrator menu options was added.


# 7. Observations







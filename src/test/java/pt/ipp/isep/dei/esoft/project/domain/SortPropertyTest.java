package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.SortProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortPropertyTest {

    @Test
    public void testSortPropertiesInsertionSortByAreaDescending() {
        // Create properties
        Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        Property property1 = new Property(150, 112, 192, a1, 1, "Store 1");
        property1.setArea(100);
        Property property2 = new Property(122, 162, 132, a1, 1, "Store 2");
        property2.setArea(200);
        Property property3 = new Property(142, 172, 152, a1, 1, "Store 3");
        property3.setArea(50);

        // Create the input list
        List<Property> inputList = Arrays.asList(property1, property2, property3);

        // Call the method
        ArrayList<Property> result = SortProperty.sortPropertiesInsertionSortByAreaDescending(new ArrayList<>(inputList));

        // Create the expected sorted list
        List<Property> expectedList = Arrays.asList(property2, property1, property3);

        // Assert the result
        assertEquals(expectedList, result);
    }

    @Test
    public void testSortPropertiesSelectionSortByAreaDescending() {
        // Create properties
        Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        Property property1 = new Property(142, 172, 152, a1, 1, "Store 3");
        property1.setArea(100);
        Property property2 = new Property(122, 162, 132, a1, 1, "Store 2");
        property2.setArea(200);
        Property property3 = new Property(150, 112, 192, a1, 1, "Store 1");
        property3.setArea(50);

        // Create the input list
        List<Property> inputList = Arrays.asList(property1, property2, property3);

        // Call the method
        ArrayList<Property> result = SortProperty.sortPropertiesSelectionSortByAreaDescending(new ArrayList<>(inputList));

        // Create the expected sorted list
        List<Property> expectedList = Arrays.asList(property2, property1, property3);

        // Assert the result
        assertEquals(expectedList, result);
    }

    @Test
    public void testSortPropertiesSelectionSortByAreaAscending() {
        // Create properties
        Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        Property property1 = new Property(142, 172, 152, a1, 1, "Store 3");
        property1.setArea(100);
        Property property2 = new Property(122, 162, 132, a1, 1, "Store 2");
        property2.setArea(200);
        Property property3 = new Property(150, 112, 192, a1, 1, "Store 1");
        property3.setArea(50);

        // Create the input list
        List<Property> inputList = Arrays.asList(property1, property2, property3);

        // Call the method
        ArrayList<Property> result = SortProperty.sortPropertiesSelectionSortByAreaAscending(new ArrayList<>(inputList));

        // Create the expected sorted list
        List<Property> expectedList = Arrays.asList(property3, property1, property2);

        // Assert the result
        assertEquals(expectedList, result);
    }

    @Test
    public void testSortPropertiesInsertionSortByAreaAscending() {
        // Create properties
        Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        Property property1 = new Property(142, 172, 152, a1, 1, "Store 3");
        property1.setArea(100);
        Property property2 = new Property(122, 162, 132, a1, 1, "Store 2");
        property2.setArea(200);
        Property property3 = new Property(150, 112, 192, a1, 1, "Store 1");
        property3.setArea(50);

        // Create the input list
        List<Property> inputList = Arrays.asList(property1, property2, property3);

        // Call the method
        ArrayList<Property> result = SortProperty.sortPropertiesInsertionSortByAreaAscending(new ArrayList<>(inputList));

        // Create the expected sorted list
        List<Property> expectedList = Arrays.asList(property3, property1, property2);

        // Assert the result
        assertEquals(expectedList, result);
    }
}


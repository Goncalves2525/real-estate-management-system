package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PropertyTest {

    private Property property;
    private Address address;

    @BeforeEach
    public void setUp() {
        address = new Address("Rua das Flores", "Porto", "4000-000", 50);
        property = new Property(100, 5, 100000, address);
    }

    @Test
    public void testGetters() {
        Assertions.assertEquals(100, property.getArea());
        Assertions.assertEquals(5, property.getDistanceFromCenter());
        Assertions.assertEquals(100000, property.getPrice());
        Assertions.assertEquals(address, property.getAddress());
    }

    @Test
    public void testSetters() {
        property.setArea(150);
        property.setDistanceFromCenter(10);
        property.setPrice(150000);
        Address newAddress = new Address("Avenida da Boavista", "Porto", "4100-000", 100);
        property.setAddress(newAddress);
        Assertions.assertEquals(150, property.getArea());
        Assertions.assertEquals(10, property.getDistanceFromCenter());
        Assertions.assertEquals(150000, property.getPrice());
        Assertions.assertEquals(newAddress, property.getAddress());
    }

    @Test
    public void testToString() {
        String expectedString = String.format("\n-Area: %.2f\n-Distance from center: %.2f\n-Price: %.2f\n-Address: %s", 100.0, 5.0, 100000.0, address.toString());
        Assertions.assertEquals(expectedString, property.toString());
    }
}


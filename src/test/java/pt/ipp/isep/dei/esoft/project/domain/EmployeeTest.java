package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        Address address = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        List<Role> roles = Arrays.asList(Role.AGENT);
        employee = new Employee("John Doe", "john.doe@example.com", 12345, 67890, 123456789, address, roles);
    }

    @Test
    void addRole() {
        employee.addRole(Role.STORE_NETWORK_MANAGER);

        assertTrue(employee.hasRole(Role.STORE_NETWORK_MANAGER));
    }

    @Test
    void hasRole() {
        assertTrue(employee.hasRole(Role.AGENT));
        assertFalse(employee.hasRole(Role.STORE_NETWORK_MANAGER));
    }

    @Test
    void testClone() {
        Employee clonedEmployee = employee.clone();

        assertNotSame(employee, clonedEmployee);
        assertEquals(employee, clonedEmployee);
    }
}

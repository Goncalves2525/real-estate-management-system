package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;
    private Address address;
    private Agency agency;
    private Set<Role> roles;

    @BeforeEach
    void setUp() {
        address = new Address("Street", "City", "District", "State", 12345);
        agency = new Agency(1,"Agency2","2@",123,address,agency.getAgents());
        roles = new HashSet<>();
        roles.getClass();

        employee = new Employee("John Doe", "johndoe@example.com", 123456, 123456789, 987654321, address, agency, roles);
    }

    @Test
    void addRole() {
        Role newRole = null;
        employee.addRole(newRole);

        assertTrue(employee.hasRole(newRole), "Employee should have the new role");
    }

    @Test
    void removeRole() {
        Role roleToRemove = null;
        employee.removeRole(roleToRemove);

        assertFalse(employee.hasRole(roleToRemove), "Employee should not have the removed role");
    }

    @Test
    void hasRole() {
        Role existingRole = null;
        assertTrue(employee.hasRole(existingRole), "Employee should have the existing role");

        Role nonExistingRole = null;
        assertFalse(employee.hasRole(nonExistingRole), "Employee should not have the non-existing role");
    }

    @Test
    void hasEmail() {
        String existingEmail = "johndoe@example.com";
        assertTrue(employee.hasEmail(existingEmail), "Employee should have the existing email");

        String nonExistingEmail = "johnsmith@example.com";
        assertFalse(employee.hasEmail(nonExistingEmail), "Employee should not have the non-existing email");
    }

    @Test
    void cloneEmployee() {
        Employee clonedEmployee = employee.clone();

        assertEquals(employee, clonedEmployee, "Cloned employee should be equal to the original employee");
        assertNotSame(employee, clonedEmployee, "Cloned employee should not be the same instance as the original employee");
    }

    @Test
    void getNumberOfRoles() {
        assertEquals(1, employee.getNumberOfRoles(), "Employee should have 1 role");
    }
}

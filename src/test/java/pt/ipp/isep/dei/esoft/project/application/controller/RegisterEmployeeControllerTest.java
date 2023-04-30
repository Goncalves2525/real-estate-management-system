package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Role;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterEmployeeControllerTest {

    private RegisterEmployeeController controller;

    @BeforeEach
    void setUp() {
        controller = new RegisterEmployeeController();
    }

    @Test
    void getRolesToCreate() {
        List<Role> roles = controller.getRolesToCreate();

        assertFalse(roles.isEmpty(), "Roles list should not be empty");
        // Add more assertions based on the expected roles in the list.
    }

    @Test
    void getRoleByVale() {
        int value = 1; // Change this value based on the roles in your system.
        Role role = controller.getRoleByVale(value);

        assertNotNull(role, "Role should not be null");
        // Add more assertions based on the expected role.
    }

    @Test
    void registerEmployee() {
        String name = "John Doe";
        String email = "johndoe@example.com";
        int passportCardNumber = 123456;
        int taxNumber = 123456789;
        int telephoneNumber = 987654321;
        Address address = new Address("Street", "City", "District", "State", 12345);
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyListRepository();
        List<Employee> agents = new ArrayList<>();
        Agency agency = new Agency(1, "Agency1", "agency1@this.app", 934875844, new Address("street1", "City1", "District1", "State1", 1234), agents);


        Role role = null;
        Set<Role> roles = Set.of(role);


        boolean result = controller.registerEmployee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, roles);

        assertTrue(result, "Employee should be registered successfully");

        // You can add more test cases for different input combinations and edge cases.
    }

    @Test
    void generatePassword() {
        String password1 = controller.generatePassword();
        String password2 = controller.generatePassword();

        assertNotNull(password1, "Generated password should not be null");
        assertNotNull(password2, "Generated password should not be null");

        assertNotEquals(password1, password2, "Generated passwords should be different");

        // Add more assertions based on the complexity criteria of the password.
    }
}

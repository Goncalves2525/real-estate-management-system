package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegisterEmployeeControllerTest {

    private RegisterEmployeeController controller;

    @BeforeEach
    void setUp() {
        Repositories repositories = Repositories.getInstance();
        Repositories.getInstance();

        controller = new RegisterEmployeeController();
    }


    @Test
    void getRoleByVale() {
        Role role = Role.ADMIN;
        Role result = controller.getRoleByVale(role.getValue());

        assertEquals(role, result);
    }

    @Test
    void registerEmployee() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        int passportCardNumber = 123456;
        int taxNumber = 654321;
        int telephoneNumber = 987654;
        int agencyID = 3;
        Address address = new Address("happy","new","dist","big",4525);
        ArrayList<Employee> agenteTest = new ArrayList<>();
        Agency agency = new Agency("agency1", "email@mail.com", 1234567890,address, agenteTest);
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(Role.ADMIN);
        roles.add(Role.AGENT);

        Employee employee = new Employee(name, email, passportCardNumber, taxNumber, telephoneNumber, address,roles, agencyID);

//        boolean result = controller.registerEmployee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agencyID, roles);
//
//        assertTrue(result);
    }

    @Test
    void generatePassword() {
        String generatedPassword = controller.generatePassword();
        assertNotNull(generatedPassword);
        assertTrue(generatedPassword.length() == 7);

    }


}

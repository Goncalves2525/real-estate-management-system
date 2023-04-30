package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;
    private Employee employee1;
    private Employee employee2;
    private Agency agency1;
    private Address address1;

    @BeforeEach
    void setUp() {
        employeeRepository = new EmployeeRepository();
        address1 = new Address("Street", "City", "district", "state", 1234);
        agency1 = new Agency(1, "Agency1", "agency1@example.com", 123456789, address1, new ArrayList<>());
        employee1 = new Employee("John Doe", "john.doe@example.com", 11111, 22222, 33333, address1, agency1, Arrays.asList(Role.AGENT));
        employee2 = new Employee("Jane Doe", "jane.doe@example.com", 11112, 22223, 33334, address1, agency1, Arrays.asList(Role.AGENT));
    }

    @Test
    void addEmployee() {
        assertTrue(employeeRepository.addEmployee(employee1));
        assertFalse(employeeRepository.addEmployee(employee1));
        assertTrue(employeeRepository.addEmployee(employee2));
    }

    @Test
    void getEmployeeByEmail() {
        employeeRepository.addEmployee(employee1);
        employeeRepository.addEmployee(employee2);
        assertEquals(employee1, employeeRepository.getEmployeeByEmail("john.doe@example.com"));
        assertNull(employeeRepository.getEmployeeByEmail("nonexistent@example.com"));
    }

    @Test
    void getAgentsByAgencyId() {
        employeeRepository.addEmployee(employee1);
        employeeRepository.addEmployee(employee2);
        ArrayList<Employee> agents = employeeRepository.getAgentsByAgencyId(1);
        assertEquals(2, agents.size());
        assertTrue(agents.contains(employee1));
        assertTrue(agents.contains(employee2));
    }

    @Test
    void employeeExists() {
        assertFalse(employeeRepository.employeeExists("john.doe@example.com"));
        employeeRepository.addEmployee(employee1);
        assertTrue(employeeRepository.employeeExists("john.doe@example.com"));
    }

    @Test
    void getEmployeeList() {
        employeeRepository.addEmployee(employee1);
        employeeRepository.addEmployee(employee2);
        ArrayList<Employee> employees = employeeRepository.getEmployeeList();
        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
    }

    @Test
    void add() {
        employeeRepository.add(employee1);
        assertTrue(employeeRepository.getEmployeeList().contains(employee1));
    }
}

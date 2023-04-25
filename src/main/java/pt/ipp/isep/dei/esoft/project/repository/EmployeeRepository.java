package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    private final ArrayList<Employee> employeeList = new ArrayList<>();

    public boolean addEmployee(Employee employee) {
        if (!employeeExists(employee.getEmail())) {
            return employeeList.add(employee);
        }
        return false;
    }

    public boolean employeeExists(String email) {
        return employeeList.stream().anyMatch(employee -> employee.hasEmail(email));
    }



}

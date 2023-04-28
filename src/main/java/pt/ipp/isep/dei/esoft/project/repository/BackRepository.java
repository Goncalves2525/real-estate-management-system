package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.util.ArrayList;

public class BackRepository {

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

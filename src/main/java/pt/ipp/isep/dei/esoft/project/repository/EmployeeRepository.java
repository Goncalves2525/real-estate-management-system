package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    private ArrayList<Employee> employeeList = new ArrayList<>();

    public boolean addEmployee(Employee employee) {
        if (!employeeExists(employee.getEmail())) {
            return employeeList.add(employee);
        }
        return false;
    }

    public Employee getEmployeeByEmail(String employeeEmail) {
        for (Employee employee : employeeList) {
            if (employee.getEmail().equals(employeeEmail)) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> getAgentsByAgencyId(int agencyId) {
        List<Employee> allAgents = getEmployeeList();
        List<Employee> agentsByAgency = new ArrayList<>();
        for (Employee agent : allAgents) {
            if (agent.getAgency() != null && agent.getAgency().getId() == agencyId) {
                agentsByAgency.add(agent);
            }
        }
        return agentsByAgency;
    }

    public boolean employeeExists(String email) {
        return employeeList.stream().anyMatch(employee -> employee.hasEmail(email));
    }

    //returns the list of employees
    public List<Employee> getEmployeeList() {
        return this.employeeList;
    }

    public void add(Employee employee) {
        employeeList.add(employee);
    }
}

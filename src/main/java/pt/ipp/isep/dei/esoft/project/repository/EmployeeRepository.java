package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Employee Repository.
 * <p>
 * This class will be responsible for managing a list of employees.
 */
public class EmployeeRepository implements Serializable {

    /**
     * List of employees.
     */
    private ArrayList<Employee> employeeList = new ArrayList<>();

    /**
     * Instantiates a new Employee repository.
     */
    public boolean addEmployee(Employee employee) {
        if (!employeeExists(employee.getEmail())) {
            return employeeList.add(employee);
        }
        return false;
    }

    /**
     * Gets employee by email.
     *
     * @param employeeEmail the employee email
     * @return the employee by email
     */
    public Employee getEmployeeByEmail(String employeeEmail) {
        for (Employee employee : employeeList) {
            if (employee.getEmail().equals(employeeEmail)) {
                return employee;
            }
        }
        return null;
    }


    /**
     * Gets employee by id.
     *
     * @param agencyId The agency id
     * @return
     */
    public ArrayList<Employee> getAgentsByAgencyId(int agencyId) {
        ArrayList<Employee> allAgents = getEmployeeList();
        ArrayList<Employee> agentsByAgency = new ArrayList<>();
        for (Employee agent : allAgents) {
            if (agent.getAgencyID() == agencyId) {
                agentsByAgency.add(agent);
            }
        }
        return agentsByAgency;
    }

    /**
     * Employee exists boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean employeeExists(String email) {
        return employeeList.stream().anyMatch(employee -> employee.hasEmail(email));
    }

    /**
     * Gets employee list.
     *
     * @return the employee list
     */
    public ArrayList<Employee> getEmployeeList() {
        return this.employeeList;
    }

    /**
     * Add employee.
     *
     * @param employee the employee
     */
    public void add(Employee employee) {
        employeeList.add(employee);
    }


}

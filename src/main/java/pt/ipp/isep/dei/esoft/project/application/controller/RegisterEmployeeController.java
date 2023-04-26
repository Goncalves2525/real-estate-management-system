package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class RegisterEmployeeController {

    private EmployeeRepository employeeRepository = null;

    //Repository instances are obtained from the Repositories class
    public RegisterEmployeeController() {
        getEmployeeRepository();
    }

    private void getEmployeeRepository() {
        this.employeeRepository = Repositories.getInstance().getEmployeeRepository();
    }

    public boolean registerEmployee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, Role role) {
        Employee employee = new Employee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, role);
        return employeeRepository.addEmployee(employee);
    }


}

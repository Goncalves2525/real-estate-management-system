package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedPropertyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Date;

public class RegisterEmployeeController {

    private EmployeeRepository employeeRepository = null;

    //Repository instances are obtained from the Repositories class
    public RegisterEmployeeController() {
        getEmployeeRepository();
    }

    private void getEmployeeRepository() {
        this.employeeRepository = Repositories.getInstance().getEmployeeRepository();
    }

    public boolean registerEmployee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Role role) {
        Employee employee = new Employee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, role);
        return employeeRepository.addEmployee(employee);
    }


}

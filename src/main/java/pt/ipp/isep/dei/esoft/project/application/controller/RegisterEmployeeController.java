package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.RoleRepository;



import java.util.List;
import java.util.Set;

public class RegisterEmployeeController {

    private EmployeeRepository employeeRepository = null;
    private RoleRepository roleRepository = null;
    private PasswordGenerator passwordGenerator;
    public RegisterEmployeeController() {
        getEmployeeRepository();
        passwordGenerator = new PasswordGenerator();
    }

    public List<Role> getRolesToCreate() {
        this.roleRepository = Repositories.getInstance().getRoleRepository();
        return this.roleRepository.getRolesToCreate();
    }

    public Role getRoleByVale(int value) {
        this.roleRepository = Repositories.getInstance().getRoleRepository();
        return this.roleRepository.getRoleByValue(value);
    }

    private void getEmployeeRepository() {
        this.employeeRepository = Repositories.getInstance().getEmployeeRepository();
    }

    public boolean registerEmployee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, Set<Role> roles) {
        Employee employee = new Employee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, roles);
        return employeeRepository.addEmployee(employee);
    }


    public String generatePassword() {
        return passwordGenerator.generatePassword();
    }

}

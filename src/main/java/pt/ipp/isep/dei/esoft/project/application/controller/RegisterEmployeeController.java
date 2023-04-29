package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.RoleRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;

import java.util.List;
import java.util.Set;

/**
 * Register Employee Controller.
 * <p>
 * This class represents the Register Employee Controller.
 * </p>
 */

public class RegisterEmployeeController {
    /**
     * Instance variables.
     */
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private PasswordGenerator passwordGenerator;

    /**
     * Instantiates a new Register Employee Controller.
     */
    public RegisterEmployeeController() {
        employeeRepository = Repositories.getInstance().getEmployeeRepository();
        passwordGenerator = new PasswordGenerator();
    }

    /**
     * Gets the list of Roles to create.
     *
     * @return list of Roles to create
     */
    public List<Role> getRolesToCreate() {
        this.roleRepository = Repositories.getInstance().getRoleRepository();
        return this.roleRepository.getRolesToCreate();
    }

    /**
     * Gets the list of Roles to create.
     *
     * @return list of Roles to create
     */
    public Role getRoleByVale(int value) {
        this.roleRepository = Repositories.getInstance().getRoleRepository();
        return this.roleRepository.getRoleByValue(value);
    }

    public boolean registerEmployee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, List<Role> roles) {
        Employee employee = new Employee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, roles);
        return employeeRepository.addEmployee(employee);
    }

    public String generatePassword() {
        return passwordGenerator.generatePassword();
    }
}

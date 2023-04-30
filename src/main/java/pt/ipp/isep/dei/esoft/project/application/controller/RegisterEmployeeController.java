package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.RoleRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;

import java.util.List;


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
    private AuthenticationRepository authenticationRepository;

    /**
     * Instantiates a new Register Employee Controller.
     */
    public RegisterEmployeeController() {
        employeeRepository = Repositories.getInstance().getEmployeeRepository();
        passwordGenerator = new PasswordGenerator();
        authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
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


    /**
     * @param name
     * @param email
     * @param passportCardNumber
     * @param taxNumber
     * @param telephoneNumber
     * @param address
     * @param agency
     * @param roles
     * @return
     */
    public boolean registerEmployee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, List<Role> roles) {
        Employee employee = new Employee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, roles);
        return employeeRepository.addEmployee(employee);
    }

    public String generatePassword() {
        return passwordGenerator.generatePassword();
    }

    public void createUser(String name, String email, String password, String role) {
        authenticationRepository.addUserWithRole(name, email, password, role);
    }
}

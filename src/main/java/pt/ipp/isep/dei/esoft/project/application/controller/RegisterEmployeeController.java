package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

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
    private AgencyRepository agencyRepository;
    private RoleRepository roleRepository;
    private PasswordGenerator passwordGenerator;
    private AuthenticationRepository authenticationRepository;

    /**
     * Instantiates a new Register Employee Controller.
     */
    public RegisterEmployeeController() {
        employeeRepository = Repositories.getInstance().getEmployeeRepository();
        agencyRepository = Repositories.getInstance().getAgencyRepository();
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
     * @param agencyID
     * @param roles
     * @return
     */
    public boolean registerEmployee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, int agencyID, List<Role> roles) {
        Employee employee = new Employee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, roles, agencyID);
        return employeeRepository.addEmployee(employee);
    }

    public Agency getAgencyByID(int agencyID) {
        return agencyRepository.getAgencyById(agencyID);
    }

    /**
     * Generates a password.
     *
     * @return password
     */
    public String generatePassword() {
        return passwordGenerator.generatePassword();
    }

    /** Creates a user for the authentication repository.
     *
     * @param name    name
     * @param email  email
     * @param password password
     * @param role role
     */
    public void createUser(String name, String email, String password, String role) {
        authenticationRepository.addUserWithRole(name, email, password, role);
    }
}

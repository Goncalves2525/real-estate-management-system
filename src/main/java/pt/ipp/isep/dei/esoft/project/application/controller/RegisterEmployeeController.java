package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



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
    private EmailSender emailSender = new EmailSender();


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
    public ArrayList<Role> getRolesToCreate() {
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
     * Gets the list of Agencies.
     *
     * @return list of Agencies
     */
    public ArrayList<Agency> listAgencies() {
        return agencyRepository.getAgencies();
    }

    /**
     * Gets the total number of Agencies.
     *
     * @return total number of Agencies
     */
    public int getTotalAgencies() {
        return agencyRepository.getAgencies().size();
    }

    /** Register Employee.
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
    public boolean registerEmployee(String name, String email, int passportCardNumber, int taxNumber, long telephoneNumber, Address address, int agencyID, ArrayList<Role> roles) {
        Employee employee = new Employee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, roles, agencyID);
        return employeeRepository.addEmployee(employee);
    }

    /** Get Agency by ID.
     *
     * @param agencyID
     * @return
     */
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

    public void createUserWithRoles(String name, String email, String password, String[] role) {
        authenticationRepository.addUserWithRoles(name, email, password, role);
    }

    /**
     * Writes the register email to employee.
     *
     * @param name     name
     * @param email    email
     * @param password password
     * @param fileName file name
     */
    public void writeRegisterEmailToEmployee(String name, String email, String password, String fileName) {
        emailSender.writeRegisterEmailToEmployee(name, email, password, fileName);
    }

}

package pt.ipp.isep.dei.esoft.project.domain;


import java.util.ArrayList;
import java.util.List;

/**
 * Employee.
 * <p>
 * This class represents an employee.
 * </p>
 */
public class Employee extends Person {

    private int agencyID;

    /**
     * @param name Name of the employee
     * @param email Email of the employee
     * @param passportCardNumber Passport card number of the employee
     * @param taxNumber Tax number of the employee
     * @param telephoneNumber Telephone number of the employee
     * @param address Address of the employee
     * @param roles Roles of the employee
     * @param agencyID Agency ID of the employee
     */
    public Employee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, List<Role> roles, int agencyID) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address, roles);
        this.agencyID = agencyID;
    }

    /**
     * @param name Name of the employee
     * @param email Email of the employee
     * @param passportCardNumber Passport card number of the employee
     * @param taxNumber Tax number of the employee
     * @param telephoneNumber Telephone number of the employee
     * @param agencyID Agency ID of the employee
     */
    public Employee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, List<Role> roles, int agencyID) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, roles);
        this.agencyID = agencyID;
    }
    /**
     * @param email Email of the employee
     */
    public Employee(String email) {
        super(email);
    }

    /**
     * @param email Email of the employee
     * @param agencyID Agency ID of the employee
     */
    public Employee(String email, int agencyID){
        super(email);
        this.agencyID = agencyID;
    }


     /**
      * @param role Add Role of the employee
      */
    public void addRole(Role role) {
        List<Role> roles = new ArrayList<>(getRoles());
        roles.add(role);
        setRoles(roles);
    }

    /**
     * @param role Remove Role of the employee
     */
    public void removeRole(Role role) {
        List<Role> roles = new ArrayList<>(getRoles());
        roles.remove(role);
        setRoles(roles);
    }

    /**
     * @param role Role of the employee
     * @return true if has role, false otherwise
     */
    public boolean hasRole(Role role) {
        return getRoles().contains(role);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return getEmail().equals(employee.getEmail()) &&
                getPassportCardNumber() == employee.getPassportCardNumber() &&
                getTaxNumber() == employee.getTaxNumber() &&
                getTelephoneNumber() == employee.getTelephoneNumber() &&
                getAddress().equals(employee.getAddress()) &&
                getRoles().equals(employee.getRoles());
    }

    /**
     * Hash code.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = getEmail().hashCode();
        result = 31 * result + getPassportCardNumber();
        result = 31 * result + getTaxNumber();
        result = 31 * result + getTelephoneNumber();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getRoles().hashCode();
        return result;
    }

    /**
     * Checks if has email.
     *
     * @param email email
     * @return true if has email, false otherwise
     */
    public boolean hasEmail(String email) {
        return getEmail().equals(email);
    }


    /**
     * @return clone of employee
     */
    public Employee clone() {
        return new Employee(getName(), getEmail(), getPassportCardNumber(), getTaxNumber(), getTelephoneNumber(), getAddress(), getRoles(), getAgencyID());
    }

    public int getAgencyID() {
        return agencyID;
    }


    /**
     * @return number of roles
     */
    public int getNumberOfRoles() {
        return getRoles().size();
    }

    /**
     * @return string of employee
     */
    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Passport Card Number: " + getPassportCardNumber() + "\n" +
                "Tax Number: " + getTaxNumber() + "\n" +
                "Telephone Number: " + getTelephoneNumber() + "\n" +
                "Address: " + getAddress() + "\n" +
                "Role: " + getRoles();
    }

}

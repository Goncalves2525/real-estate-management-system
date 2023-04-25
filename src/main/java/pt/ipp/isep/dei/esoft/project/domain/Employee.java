package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Employee extends User{
   private Role role;

    /**
     * Instantiates a new Employee.
     *
     * @param name               the name
     * @param email              the email
     * @param passportCardNumber the passport card number
     * @param taxNumber          the tax number
     * @param telephoneNumber    the telephone number
     * @param address            the address
     * @param agency             the agency
     */
    public Employee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency);
        this.role = Role.EMPLOYEE;
    }

    public Employee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Role role) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address, role);
        this.role = Role.EMPLOYEE;
    }

    public Employee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, Role role) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, role);
        this.role = role;
    }


    //address is optional
    /**
     * Instantiates a new Employee.
     *
     * @param name               the name
     * @param email              the email
     * @param passportCardNumber the passport card number
     * @param taxNumber          the tax number
     * @param telephoneNumber    the telephone number
     * @param role               the role
     */
    public Employee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Role role) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, role);
    }

    //para não dar problema com o outro
    /**
     * Instantiates a new Employee.
     *
     * @param email the email
     */
    public Employee(String email) {
        super(email);
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    @Override
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    @Override
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
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
                Objects.equals(getAddress(), employee.getAddress()) &&
                Objects.equals(role, employee.role);
    }

    //resolver isto
    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassportCardNumber(), getTaxNumber(), getTelephoneNumber(), getAddress(), role);
    }

    /**
     * Returns email string.
     *
     * @return the string
     */
    public boolean hasEmail(String email) {
        return getEmail().equals(email);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    //resolver isto
    public Employee clone() {
        return new Employee("nada");
    }
}

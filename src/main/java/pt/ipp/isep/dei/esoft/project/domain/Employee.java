package pt.ipp.isep.dei.esoft.project.domain;

import java.util.HashSet;
import java.util.Set;

public class Employee extends User {

    public Employee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, Set<Role> roles) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, roles);
    }

    public Employee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Set<Role> roles) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address, roles);
    }

    public Employee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Set<Role> roles) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, roles);
    }

    public Employee(String email) {
        super(email);
    }

    public Employee(String email, Agency agency){
        super(email,agency);
    }

    public void addRole(Role role) {
        Set<Role> roles = getRoles();
        roles.add(role);
        setRoles(roles);
    }

    public void removeRole(Role role) {
        Set<Role> roles = getRoles();
        roles.remove(role);
        setRoles(roles);
    }

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

    public boolean hasEmail(String email) {
        return getEmail().equals(email);
    }

    public Employee clone() {
        return new Employee(getName(), getEmail(), getPassportCardNumber(), getTaxNumber(), getTelephoneNumber(), getAddress(), getAgency(), new HashSet<>(getRoles()));
    }
}

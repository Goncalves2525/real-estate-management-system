package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class User {
    private String name;
    private String email;
    private int passportCardNumber;
    private int taxNumber;
    private int telephoneNumber;
    private Address address;
    private List<Role> roles;
    private Agency agency;

    public User(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.agency = agency;
    }

    public User(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.agency = agency;
        this.roles = roles;
    }

    public User(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.roles = roles;
    }

    public User(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        this.roles = roles;
    }

    public User(String email) {
        this.email = email;
    }
    public User(String email, Agency agency) {
        this.email = email;
        this.agency = agency;
    }

    public User(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        List<Role> roles = new ArrayList<>();
        roles.add(Role.EMPLOYEE);
        this.setRoles(roles);
    }

    // ... outros métodos ...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public int getPassportCardNumber() {
        return passportCardNumber;
    }

    public int getTaxNumber() {
        return taxNumber;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return String.format("Name: %s%nEmail: %s%nPassport Card Number: %d%nTax Number: %d%nTelephone Number: %d%nAddress: %s%nRole: %s%%n ", this.name, this.email, this.passportCardNumber, this.taxNumber, this.telephoneNumber, this.address == null ? "" : this.address.toString(), this.roles == null ? "" : this.roles.toString());
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if (roles == null) {
        }
        roles.add(role);
    }

    public boolean hasRole(Role role) {
        return roles != null && roles.contains(role);
    }
}
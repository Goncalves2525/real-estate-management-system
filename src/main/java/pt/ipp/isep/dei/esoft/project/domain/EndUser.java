package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a User and its characteristics.
 */
public abstract class EndUser {
    private String name;
    private String email;
    private int passportCardNumber;
    private int taxNumber;
    private int telephoneNumber;
    private Address address;
    private List<Role> roles;
    private Agency agency;

    /**
     * Instantiates a new User.
     *
     * @param name                the name
     * @param email               the email
     * @param passportCardNumber  the passport card number
     * @param taxNumber           the tax number
     * @param telephoneNumber     the telephone number
     * @param address             the address
     * @param agency              the agency
     */

    public EndUser(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.agency = agency;
    }

    /**
     * Instantiates a new User.
     *
     * @param name                the name
     * @param email               the email
     * @param passportCardNumber  the passport card number
     * @param taxNumber           the tax number
     * @param telephoneNumber     the telephone number
     * @param address             the address
     * @param agency              the agency
     * @param roles               the roles
     */

    public EndUser(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.agency = agency;
        this.roles = roles;
    }
    /**
     * Instantiates a new User.
     *
     * @param name                the name
     * @param email               the email
     * @param passportCardNumber  the passport card number
     * @param taxNumber           the tax number
     * @param telephoneNumber     the telephone number
     * @param address             the address
     * @param roles               the roles
     */

    public EndUser(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.roles = roles;
    }
    /**
     * Instantiates a new User.
     *
     * @param name                the name
     * @param email               the email
     * @param passportCardNumber  the passport card number
     * @param taxNumber           the tax number
     * @param telephoneNumber     the telephone number
     * @param roles               the roles
     */

    public EndUser(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        this.roles = roles;
    }

    /**
     * Instantiates a new User.

     * @param email               the email
     */

    public EndUser(String email) {
        this.email = email;
    }
    /**
     * Instantiates a new User.
     *
     * @param email               the email
     * @param agency              the agency
     */
    public EndUser(String email, Agency agency) {
        this.email = email;
        this.agency = agency;
    }
    /**
     * Instantiates a new User.
     *
     * @param name                the name
     * @param email               the email
     * @param passportCardNumber  the passport card number
     * @param taxNumber           the tax number
     * @param telephoneNumber     the telephone number
     */
    public EndUser(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        List<Role> roles = new ArrayList<>();
        roles.add(Role.AGENT);
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
        return this.roles;
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
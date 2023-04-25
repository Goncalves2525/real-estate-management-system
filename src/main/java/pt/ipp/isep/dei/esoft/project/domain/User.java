package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public abstract class User {
    private String name;
    private String email;
    private int passportCardNumber;
    private int taxNumber;
    private int telephoneNumber;
    private Address address;
    private Role role;

    public User(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
    }

    public User(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Role role) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        this.role = role;
    }

    //para não dar problema com o outro
    public User(String email) {
        this.email = email;
    }

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

    /**
     * Sets the telephone number.
     *
     * @param telephoneNumber telephone number
     */
    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("Name: %s%nEmail: %s%nPassport Card Number: %d%nTax Number: %d%nTelephone Number: %d%nAddress: %s%nRole: %s%%n ", this.name, this.email, this.passportCardNumber, this.taxNumber, this.telephoneNumber, this.address==null?"":this.address.toString(),this.role.toString());
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean hasRole(Role role) {
        return this.role == role;
    }
}

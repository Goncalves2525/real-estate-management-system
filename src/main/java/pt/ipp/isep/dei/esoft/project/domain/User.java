package pt.ipp.isep.dei.esoft.project.domain;

public abstract class User {
    private String name;
    private String email;
    private int passportCardNumber;
    private int taxNumber;
    private int telephoneNumber;
    private Address address;


    public User(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
    }

    public User(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber) {
        this.name = name;
        this.email = email;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.telephoneNumber = telephoneNumber;
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

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("Name: %s%nEmail: %s%nPassport Card Number: %d%nTax Number: %d%nTelephone Number: %d%nAddress: %s%n", this.name, this.email, this.passportCardNumber, this.taxNumber, this.telephoneNumber, this.address==null?"":this.address.toString());
    }
}

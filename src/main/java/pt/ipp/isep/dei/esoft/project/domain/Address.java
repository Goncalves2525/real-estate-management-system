package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Address class that represents an address.
 * <p>
 * An address is characterized by a street, city, district, state and zipcode.
 */
public class Address {
    private String street, city, district, state;
    private int zipcode;

    /**
     * Address Full Constructor
     * @param street street
     * @param city city
     * @param district district
     * @param state state
     * @param zipcode zipcode
     */
    public Address(String street, String city, String district, String state, int zipcode) {
        this.street = street;
        this.city = city;
        this.district = district;
        this.state = state;
        this.zipcode = zipcode;
    }

    /**
     * Address Constructor without district
     * @param street street
     * @param city city
     * @param state state
     * @param zipcode zipcode
     */
    public Address(String street, String city, String state, int zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString(){
        return String.format("%s, %s, %s, %s, %d", street, city, district, state, zipcode);
    }
}

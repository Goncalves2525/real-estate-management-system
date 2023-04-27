package pt.ipp.isep.dei.esoft.project.domain;

public class Address {
    private String street, city, district, state;
    private int zipcode;

    public Address(String street, String city, String district, String state, int zipcode) {
        this.street = street;
        this.city = city;
        this.district = district;
        this.state = state;
        this.zipcode = zipcode;
    }

    //district is optional
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

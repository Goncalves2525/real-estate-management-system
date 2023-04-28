package pt.ipp.isep.dei.esoft.project.domain;


public class Property {
    private double area;
    private double distanceFromCenter;
    private double price;
    private Address address;

    public Property(double area, double distanceFromCenter, double price, Address address) {
        this.area = area;
        this.distanceFromCenter = distanceFromCenter;
        this.price = price;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getDistanceFromCenter() {
        return distanceFromCenter;
    }

    public void setDistanceFromCenter(double distanceFromCenter) {
        this.distanceFromCenter = distanceFromCenter;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString(){
        return String.format("\n-Area: %.2f\n-Distance from center: %.2f\n-Price: %.2f\n-Address: %s", area, distanceFromCenter, price, address.toString());
    }
}
package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * Property.
 * <p>
 * This class represents a Property.
 * A Property is characterized by an area, a distance from the center, a price and an address.
 * <p>
 * It should be noted that this class's attributes are immutable.
 */

public class Property implements Serializable {

    private final int id;
    private double area;
    private double distanceFromCenter;
    private double price;
    private Address address;

    public static int idCounter = 0;

    public int agencyID;
    public String agencyName;

    /**
     * Instantiates a new Property.
     *
     * @param area              the area
     * @param distanceFromCenter the distance from center
     * @param price             the price
     * @param address           the address
     */
    public Property(double area, double distanceFromCenter, double price, Address address) {
        this.area = area;
        this.distanceFromCenter = distanceFromCenter;
        this.price = price;
        this.address = address;
        id = idCounter++;
    }


    public Property(double area, double distanceFromCenter, double price, Address address, int storeID, String storeName) {
        this.area = area;
        this.distanceFromCenter = distanceFromCenter;
        this.price = price;
        this.address = address;
        this.agencyName = storeName;
        this.agencyID = storeID;
        id = idCounter++;
    }

    /**
     * Gets address.
     *
     * @return the address
     */

    public Address getAddress() {
        return address;
    }
    /**
     * Sets address.
     *
     * @param address the address
     */

    public void setAddress(Address address) {
        this.address = address;
    }
    /**
     * Gets area.
     *
     * @return the area
     */

    public double getArea() {
        return area;
    }

    public int getId() {
        return id;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */

    public void setArea(double area) {
        this.area = area;
    }
    /**
     * Gets distance from center.
     *
     * @return the distance from center
     */

    public double getDistanceFromCenter() {
        return distanceFromCenter;
    }
    /**
     * Sets distance from center.
     *
     * @param distanceFromCenter the distance from center
     */

    public void setDistanceFromCenter(double distanceFromCenter) {
        this.distanceFromCenter = distanceFromCenter;
    }
    /**
     * Gets price.
     *
     * @return the price
     */

    public double getPrice() {
        return price;
    }
    /**
     * Sets price.
     *
     * @param price the price
     */

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * To string string.
     * @return the string of the property
     */
    @Override
    public String toString(){
        return String.format("\n-Area: %.2f\n-Distance from center: %.2f\n-Price: %.2f$\n-Address: %s", area, distanceFromCenter, price, address.toString());
    }

    public int getAgencyID() {
        return agencyID;
    }
    public String getAgencyName(){
        return agencyName;
    }
}
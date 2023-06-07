package pt.ipp.isep.dei.esoft.project.domain;


/**
 * This class represents a Land.
 */
public class Land extends Property {

    /**
     * Instantiates a new Land.
     *
     * @param area              the area
     * @param distanceFromCenter the distance from center
     * @param price             the price
     * @param address           the address
     */

    public Land(double area, double distanceFromCenter, double price, Address address) {
        super(area, distanceFromCenter, price, address);
    }

    public Land(double area, double distanceFromCenter, double price, Address address, int storeID, String storeName) {
        super(area, distanceFromCenter, price, address);
    }

    /**
     * To string Land.
     * @return
     */
    @Override
    public String toString(){
        return String.format(super.toString());
    }
}

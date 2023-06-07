package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Apartment class.
 * <p>
 * This class represents an Apartment.
 * An Apartment is a type of residency.
 * It is characterized by the number of bedrooms, bathrooms and parking spaces, and if it has central heating and air conditioning.
 * It also has a list of equipments.
 * <p>
 * It should also be noted that the number of bathrooms and equipment are not mandatory.
 */
public class Apartment extends Residency{
    /**
     * Builds an instance of Apartment receiving all attributes as parameters.
     *
     * @param area                area
     * @param distanceFromCenter  distance from center
     * @param price               price
     * @param address             address
     * @param numberOfBedrooms    number of bedrooms
     * @param numberOfBathrooms   number of bathrooms
     * @param numberOfParkingSpaces number of parking spaces
     * @param hasCentralHeating   has central heating
     * @param hasAirConditioning  has air conditioning
     */
    public Apartment(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning) {
        super(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning);
    }

    public Apartment(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning, int storeID, String storeName) {
        super(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning, storeID, storeName);
    }

    /**
     * Builds an instance of Apartment receiving all attributes as parameters.
     *
     * @param area                area
     * @param distanceFromCenter  distance from center
     * @param price               price
     * @param address             address
     * @param numberOfBedrooms    number of bedrooms
     * @param numberOfParkingSpaces number of parking spaces
     */
    //number of bathrooms and equipment are not mandatory
    public Apartment(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfParkingSpaces) {
        super(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfParkingSpaces);
    }

    /**
     * Returns a string representation of the Apartment.
     * @return
     */
    @Override
    public String toString(){
        return String.format(super.toString());
    }

    public int getStoreID(){
        return super.getAgencyID();
    }

    public String getStoreName(){
        return super.getAgencyName();
    }
}

package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Residency class.
 * <p>
 * This class represents a residency.
 * A residency is a property with a number of bedrooms, bathrooms, parking spaces, central heating and air conditioning.
 * <p>
 * It is characterized by the following information:
 * -Number of bedrooms
 * -Number of bathrooms
 * -Number of parking spaces
 * -Has central heating
 * -Has air conditioning
 */
public abstract class Residency extends Property{

    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private int numberOfParkingSpaces;
    private boolean hasCentralHeating;
    private boolean hasAirConditioning;

    /**
     * Instantiates a new Residency.
     *
     * @param area
     * @param distanceFromCenter
     * @param price
     * @param address
     * @param numberOfBedrooms
     * @param numberOfBathrooms
     * @param numberOfParkingSpaces
     * @param hasCentralHeating
     * @param hasAirConditioning
     */

    public Residency(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning) {
        super(area, distanceFromCenter, price, address);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfParkingSpaces = numberOfParkingSpaces;
        this.hasCentralHeating = hasCentralHeating;
        this.hasAirConditioning = hasAirConditioning;
    }

    /**
     * Instantiates a new Residency.
     *
     * @param area
     * @param distanceFromCenter
     * @param price
     * @param address
     * @param numberOfBedrooms
     * @param numberOfParkingSpaces
     */

    //number of bathrooms and equipment are not mandatory
    public Residency(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfParkingSpaces) {
        super(area, distanceFromCenter, price, address);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfParkingSpaces = numberOfParkingSpaces;
    }

    /**
     * Gets has central heating.
     * @return
     */
    public boolean getHasCentralHeating() {
        return hasCentralHeating;
    }
    /**
     * Sets has central heating.
     * @param hasCentralHeating
     */

    public void setHasCentralHeating(boolean hasCentralHeating) {
        this.hasCentralHeating = hasCentralHeating;
    }
    /**
     * Gets has air conditioning.
     * @return
     */

    public boolean getHasAirConditioning() {
        return hasAirConditioning;
    }
    /**
     * Sets has air conditioning.
     * @param hasAirConditioning
     */


    public void setHasAirConditioning(boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }
    /**
     * Gets number of bedrooms.
     * @return
     */

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }
    /**
     * Sets number of bedrooms.
     * @param numberOfBedrooms
     */

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }
    /**
     * Gets number of bathrooms.
     * @return
     */

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }
    /**
     * Sets number of bathrooms.
     * @param numberOfBathrooms
     */

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }
    /**
     * Gets number of parking spaces.
     * @return
     */

    public int getNumberOfParkingSpaces() {
        return numberOfParkingSpaces;
    }
    /**
     * Sets number of parking spaces.
     * @param numberOfParkingSpaces
     */

    public void setNumberOfParkingSpaces(int numberOfParkingSpaces) {
        this.numberOfParkingSpaces = numberOfParkingSpaces;
    }

    /**
     * Returns a string representation of the object residency.
     */

    @Override
    public String toString(){
        return String.format(super.toString() + "\n-Number of bedrooms: %d\n-Number of bathrooms: %d\n-Number of parking spaces: %d\n-Has central heating: %b\n-Has air conditioning: %b", numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning);
    }


}

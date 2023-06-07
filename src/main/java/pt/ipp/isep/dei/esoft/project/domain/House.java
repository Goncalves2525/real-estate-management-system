package pt.ipp.isep.dei.esoft.project.domain;

/**
 * This class represents a House extends Residency.
 */
public class House extends Residency{
    private boolean hasBasement;
    private boolean hasInhabitableLoft;
    private SunExposure sunExposure;

    /**
     * Instantiates a new House.
     *
     * @param area              the area
     * @param distanceFromCenter the distance from center
     * @param price             the price
     * @param address           the address
     * @param numberOfBedrooms  the number of bedrooms
     * @param numberOfBathrooms the number of bathrooms
     * @param numberOfParkingSpaces the number of parking spaces
     * @param hasCentralHeating the has central heating
     * @param hasAirConditioning the has air conditioning
     * @param hasBasement the has basement
     * @param hasInhabitableLoft the has inhabitable loft
     * @param sunExposure the sun exposure
     */
    public House (double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning, boolean hasBasement, boolean hasInhabitableLoft, SunExposure sunExposure) {
        super(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning);
        this.hasBasement = hasBasement;
        this.hasInhabitableLoft = hasInhabitableLoft;
        this.sunExposure = sunExposure;
    }

    public House (double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning, boolean hasBasement, boolean hasInhabitableLoft, SunExposure sunExposure, int storeID, String storeName) {
        super(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning, storeID, storeName);
        this.hasBasement = hasBasement;
        this.hasInhabitableLoft = hasInhabitableLoft;
        this.sunExposure = sunExposure;
    }

    /**
     * Instantiates a new House.
     *
     * @param area              the area
     * @param distanceFromCenter the distance from center
     * @param price             the price
     * @param address           the address
     * @param numberOfBedrooms  the number of bedrooms
     * @param numberOfParkingSpaces the number of parking spaces
     * @param hasBasement the has basement
     * @param hasInhabitableLoft the has inhabitable loft
     */

    //bathrooms, equipment and sunExposure are not mandatory
    public House(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfParkingSpaces, boolean hasBasement, boolean hasInhabitableLoft) {
        super(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfParkingSpaces);
        this.hasBasement = hasBasement;
        this.hasInhabitableLoft = hasInhabitableLoft;
    }

    public boolean isHasBasement() {
        return hasBasement;
    }

    public void setHasBasement(boolean hasBasement) {
        this.hasBasement = hasBasement;
    }

    public boolean getHasInhabitableLoft() {
        return hasInhabitableLoft;
    }

    public void setHasInhabitableLoft(boolean hasInhabitableLoft) {
        this.hasInhabitableLoft = hasInhabitableLoft;
    }

    public SunExposure getSunExposure() {
        return sunExposure;
    }

    public void setSunExposure(SunExposure sunExposure) {
        this.sunExposure = sunExposure;
    }

    public boolean getHasBasement() {
        return hasBasement;
    }

    public void setBasement(boolean basement) {
        this.hasBasement = basement;
    }

    public boolean getInhabitableLoft() {
        return hasInhabitableLoft;
    }

    public void setInhabitableLoft(boolean inhabitableLoft) {
        this.hasInhabitableLoft = inhabitableLoft;
    }
    @Override
    public String toString(){
        return String.format(super.toString() + "\n-Has basement: %b\n-Has inhabitable loft: %b", hasBasement, hasInhabitableLoft);
    }
}

package pt.ipp.isep.dei.esoft.project.domain;

public abstract class Residency extends Property{

    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private int numberOfParkingSpaces;
    private boolean hasCentralHeating;
    private boolean hasAirConditioning;

    public Residency(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning) {
        super(area, distanceFromCenter, price, address);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfParkingSpaces = numberOfParkingSpaces;
        this.hasCentralHeating = hasCentralHeating;
        this.hasAirConditioning = hasAirConditioning;
    }

    //number of bathrooms and equipment are not mandatory
    public Residency(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfParkingSpaces) {
        super(area, distanceFromCenter, price, address);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfParkingSpaces = numberOfParkingSpaces;
    }


    public boolean getHasCentralHeating() {
        return hasCentralHeating;
    }

    public void setHasCentralHeating(boolean hasCentralHeating) {
        this.hasCentralHeating = hasCentralHeating;
    }

    public boolean getHasAirConditioning() {
        return hasAirConditioning;
    }

    public void setHasAirConditioning(boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public int getNumberOfParkingSpaces() {
        return numberOfParkingSpaces;
    }

    public void setNumberOfParkingSpaces(int numberOfParkingSpaces) {
        this.numberOfParkingSpaces = numberOfParkingSpaces;
    }

    @Override
    public String toString(){
        return String.format(super.toString() + "\n-Number of bedrooms: %d\n-Number of bathrooms: %d\n-Number of parking spaces: %d\n-Has central heating: %b\n-Has air conditioning: %b", numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning);
    }


}

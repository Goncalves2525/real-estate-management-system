package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Apartment class.
 * <p>
 * This class represents an Apartment.
 * A Apartment is a type of residency.
 * It is characterized by the number of bedrooms, bathrooms and parking spaces, and if it has central heating and air conditioning.
 * It also has a list of equipments.
 * <p>
 * It should also be noted that the number of bathrooms and equipment are not mandatory.
 */
public class Apartment extends Residency{

    public Apartment(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning) {
        super(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning);
    }

    //number of bathrooms and equipment are not mandatory
    public Apartment(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfParkingSpaces) {
        super(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfParkingSpaces);
    }

    @Override
    public String toString(){
        return String.format(super.toString());
    }
}

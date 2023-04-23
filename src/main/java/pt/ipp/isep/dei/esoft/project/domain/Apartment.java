package pt.ipp.isep.dei.esoft.project.domain;

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
        return String.format(super.toString() + "\n-This is an Apartment");
    }
}

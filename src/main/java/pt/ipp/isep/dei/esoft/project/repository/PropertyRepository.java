package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;

public class PropertyRepository {
    private ArrayList<Property> properties = new ArrayList<>();


    public int addHouse(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning, boolean hasBasement, boolean hasInhabitableLoft, SunExposure sunExposure) {
        House house = new House(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning, hasBasement, hasInhabitableLoft, sunExposure);
        properties.add(house);
        return house.getId();
    }

    public int addApartment(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning){
        Apartment apartment = new Apartment(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning);
        properties.add(apartment);
        return apartment.getId();
    }

    public int addLand(double area, double distanceFromCenter, double price, Address address){
        Land land = new Land(area, distanceFromCenter, price, address);
        properties.add(land);
        return land.getId();
    }


    public boolean verifyIfPropertyIsEqual(Property property) {
        for (Property p : properties) {
            if (p.equals(property)) {
                return true;
            }
        }
        return false;
    }


    public Property getPropertyByID(int id) {
        for (Property p : properties) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    public void removeProperty(Property property) {
        properties.remove(property);
    }

    public ArrayList<Property> getAllProperties() {
        return properties;
    }







}

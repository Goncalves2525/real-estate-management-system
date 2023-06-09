package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.*;
import java.util.ArrayList;

public class PropertyRepository implements Serializable{
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

    public int addHouseWithAgency(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning, boolean hasBasement, boolean hasInhabitableLoft, SunExposure sunExposure, int storeID, String storeName) {
        House house = new House(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning, hasBasement, hasInhabitableLoft, sunExposure, storeID, storeName);
        properties.add(house);
        return house.getId();
    }

    public int addApartmentWithAgency(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning, int storeID, String storeName){
        Apartment apartment = new Apartment(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning, storeID, storeName);
        properties.add(apartment);
        return apartment.getId();
    }

    public int addLandWithAgency(double area, double distanceFromCenter, double price, Address address, int storeID, String storeName){
        Land land = new Land(area, distanceFromCenter, price, address, storeID, storeName);
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

    public void removeAllProperties() {
        properties.clear();
    }

    public ArrayList<Property> getAllProperties() {
        return properties;
    }


    public ArrayList<Property> getPropertiesInsertionSortByAreaAscending() {
        return SortProperty.sortPropertiesInsertionSortByAreaAscending(properties);
    }
    public ArrayList<Property> getPropertiesInsertionSortByAreaDescending() {
        return SortProperty.sortPropertiesInsertionSortByAreaDescending(properties);
    }

    public ArrayList<Property> getPropertiesSelectionSortByAreaDescending() {
        return SortProperty.sortPropertiesSelectionSortByAreaDescending(properties);
    }
    public ArrayList<Property> getPropertiesSelectionSortByAreaAscending() {
        return SortProperty.sortPropertiesSelectionSortByAreaAscending(properties);
    }

    public ArrayList<Property> getPropertiesSelectionSortByID() {
        ArrayList<Property> propertiesSorted = new ArrayList<>();
        for (Property p : properties) {
            propertiesSorted.add(p);
        }
        for (int i = 0; i < propertiesSorted.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < propertiesSorted.size(); j++) {
                if (propertiesSorted.get(j).getId() > propertiesSorted.get(index).getId()) {
                    index = j;
                }
            }
            Property smallerNumber = propertiesSorted.get(index);
            propertiesSorted.set(index, propertiesSorted.get(i));
            propertiesSorted.set(i, smallerNumber);
        }
        return propertiesSorted;
    }



}

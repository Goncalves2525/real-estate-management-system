package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.PublishedProperty;

import java.util.ArrayList;
import java.util.Comparator;

public class PublishedPropertyRepository {

    ArrayList<PublishedProperty> publishedProperties = new ArrayList<>();

    public ArrayList<PublishedProperty> getPublishedProperties(String propertyType, String businessType, int numberOfRooms, String sortCriteria, String order) {
        ArrayList<PublishedProperty> resultProperties = new ArrayList<>();
        String inputType = checkInputType(propertyType, businessType, numberOfRooms, sortCriteria);
        if (inputType.equals("No filter and no sort")){
            resultProperties = getAllPublishedPropertiesByDefaultCriteria(publishedProperties);
        } else if (inputType.equals("Only sort")) {

        } else if (inputType.equals("Only filter")) {

        } else if (inputType.equals("Filter and sort")

        }

        return resultProperties;
    }

    private String checkInputType(String propertyType, String businessType, int numberOfRooms, String sortCriteria){
        String inputType;

        if (propertyType == null && businessType == null && numberOfRooms == -1 && sortCriteria == null) {
            inputType = "No filter and no sort";
        } else if (propertyType == null && businessType == null && numberOfRooms == -1) {
            inputType = "Only sort";
        } else if (sortCriteria == null) {
            inputType = "Only filter";
        } else {
            inputType = "Filter and sort";
        }

        return inputType;
    }

    private String checkTypeOfSort(){

    }

    private ArrayList<PublishedProperty> copyPublishedProperties(ArrayList<PublishedProperty> publishedProperties){
        ArrayList<PublishedProperty> copyPublishedProperties = new ArrayList<>();
        for (PublishedProperty publishedProperty : publishedProperties) {
            copyPublishedProperties.add(publishedProperty);
        }
        return copyPublishedProperties;
    }

    private ArrayList<PublishedProperty> getAllPublishedPropertiesByDefaultCriteria(ArrayList<PublishedProperty> publishedProperties){
        ArrayList<PublishedProperty> resultProperties = copyPublishedProperties(publishedProperties);
        resultProperties.sort(defaultCriteria);
        return resultProperties;
    }

    private ArrayList<PublishedProperty> getAllPublishedPropertiesByAscendingPriceCriteria(ArrayList<PublishedProperty> publishedProperties){
        ArrayList<PublishedProperty> resultProperties = copyPublishedProperties(publishedProperties);
        resultProperties.sort(ascendingPriceCriteria);
        return resultProperties;
    }

    private ArrayList<PublishedProperty> getAllPublishedPropertiesByDescendingPriceCriteria(ArrayList<PublishedProperty> publishedProperties){
        ArrayList<PublishedProperty> resultProperties = copyPublishedProperties(publishedProperties);
        resultProperties.sort(descendingPriceCriteria);
        return resultProperties;
    }

    private ArrayList<PublishedProperty> getAllPublishedPropertiesByAscendingStateCriteria(ArrayList<PublishedProperty> publishedProperties){
        ArrayList<PublishedProperty> resultProperties = copyPublishedProperties(publishedProperties);
        resultProperties.sort(ascendingStateCriteria);
        return resultProperties;
    }

    private ArrayList<PublishedProperty> getAllPublishedPropertiesByDescendingStateCriteria(ArrayList<PublishedProperty> publishedProperties){
        ArrayList<PublishedProperty> resultProperties = copyPublishedProperties(publishedProperties);
        resultProperties.sort(descendingStateCriteria);
        return resultProperties;
    }

    Comparator<PublishedProperty> ascendingPriceCriteria = new Comparator<PublishedProperty>() {
        @Override
        public int compare(PublishedProperty p1, PublishedProperty p2) {
            double price1 = p1.getProperty().getPrice();
            double price2 = p2.getProperty().getPrice();

            if (price1 < price2){
                return -1;
            } else if (price1 > price2) {
                return 1;
            }
            else return 0;
        }
    };

    Comparator<PublishedProperty> descendingPriceCriteria = new Comparator<PublishedProperty>() {
        @Override
        public int compare(PublishedProperty p1, PublishedProperty p2) {
            double price1 = p1.getProperty().getPrice();
            double price2 = p2.getProperty().getPrice();

            if (price1 > price2){
                return -1;
            } else if (price1 < price2) {
                return 1;
            }
            else return 0;
        }
    };

    Comparator<PublishedProperty> ascendingStateCriteria = new Comparator<PublishedProperty>() {
        @Override
        public int compare(PublishedProperty p1, PublishedProperty p2) {
            Address address1 = p1.getProperty().getAddress();
            Address address2 = p2.getProperty().getAddress();

            if (address1 < address2){
                return -1;
            } else if (address1 > address2) {
                return 1;
            }
            else return 0;
        }
    };

    Comparator<PublishedProperty> descendingStateCriteria = new Comparator<PublishedProperty>() {
        @Override
        public int compare(PublishedProperty p1, PublishedProperty p2) {
            Address address1 = p1.getProperty().getAddress();
            Address address2 = p2.getProperty().getAddress();

            if (address1 > address2){
                return -1;
            } else if (address1 < address2) {
                return 1;
            }
            else return 0;
        }
    };

    Comparator<PublishedProperty> defaultCriteria = new Comparator<PublishedProperty>() {
        @Override
        public int compare(PublishedProperty p1, PublishedProperty p2) {
            int id1 = p1.getId();
            int id2 = p2.getId();

            if (id1 > id2){
                return -1;
            } else if (id1 < id2) {
                return 1;
            }
            else return 0;
        }
    };





}

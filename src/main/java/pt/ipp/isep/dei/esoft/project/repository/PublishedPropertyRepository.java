package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class PublishedPropertyRepository {

    private final ArrayList<PublishedProperty> publishedProperties = new ArrayList<>();
    public ArrayList<PublishedProperty> getPublishedProperties(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        ArrayList<PublishedProperty> resultProperties = new ArrayList<>();
        String inputType = checkInputType(typeOfProperty, transactionType, numberOfRooms, sortCriteria);
        if (inputType.equals("No filter and no sort")) {
            resultProperties = getAllPublishedPropertiesByDefaultCriteria(publishedProperties);
        } else if (inputType.equals("Only sort")) {
            resultProperties = getAllPublishedPropertiesBySortCriteria(publishedProperties, sortCriteria, order);
        } else if (inputType.equals("Only filter")) {
            resultProperties = filterPublishedProperties(publishedProperties, typeOfProperty, transactionType, numberOfRooms);
        } else {
            resultProperties = getPublishedPropertiesByFilterAndSortCriteria(publishedProperties, typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
        }
        return resultProperties;
    }

    private String checkInputType(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria) {
        String inputType;

        if (typeOfProperty == null && transactionType == null && numberOfRooms == -1 && sortCriteria == null) {
            inputType = "No filter and no sort";
        } else if (typeOfProperty == null && transactionType == null && numberOfRooms == -1) {
            inputType = "Only sort";
        } else if (sortCriteria == null) {
            inputType = "Only filter";
        } else {
            inputType = "Filter and sort";
        }

        return inputType;
    }

    private ArrayList<PublishedProperty> copyPublishedProperties(ArrayList<PublishedProperty> publishedProperties) {
        ArrayList<PublishedProperty> copiedPublishedProperties = new ArrayList<>();
        copiedPublishedProperties.addAll(publishedProperties);
        return copiedPublishedProperties;
    }

    private ArrayList<PublishedProperty> getPublishedPropertiesByFilterAndSortCriteria(ArrayList<PublishedProperty> publishedProperties, TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        ArrayList<PublishedProperty> resultProperties = new ArrayList<>();
        resultProperties = filterPublishedProperties(publishedProperties, typeOfProperty, transactionType, numberOfRooms);
        resultProperties = sortPublishedProperties(resultProperties, sortCriteria, order);
        return resultProperties;
    }

    private ArrayList<PublishedProperty> getAllPublishedPropertiesBySortCriteria(ArrayList<PublishedProperty> publishedProperties, String sortCriteria, String order) {
        if (sortCriteria.equals("price")) {
            publishedProperties = sortPublishedPropertiesByPriceCriteria(publishedProperties, order);
        } else {
            publishedProperties = sortPublishedPropertiesByStateCriteria(publishedProperties, order);
        }
        return publishedProperties;
    }

    private ArrayList<PublishedProperty> getAllPublishedPropertiesByDefaultCriteria(ArrayList<PublishedProperty> publishedProperties) {
        publishedProperties.sort(defaultCriteria);
        return publishedProperties;
    }

    private ArrayList<PublishedProperty> sortPublishedProperties(ArrayList<PublishedProperty> publishedProperties, String sortCriteria, String order){
        if (sortCriteria.equals("price")) {
            publishedProperties = sortPublishedPropertiesByPriceCriteria(publishedProperties, order);
        } else {
            publishedProperties = sortPublishedPropertiesByStateCriteria(publishedProperties, order);
        }
        return publishedProperties;
    }

    private ArrayList<PublishedProperty> sortPublishedPropertiesByPriceCriteria(ArrayList<PublishedProperty> publishedProperties, String order) {
        if (order.equals("ascending")) {
            publishedProperties.sort(ascendingPriceCriteria);
        } else {
            publishedProperties.sort(descendingPriceCriteria);
        }

        return publishedProperties;
    }

    private ArrayList<PublishedProperty> sortPublishedPropertiesByStateCriteria(ArrayList<PublishedProperty> publishedProperties, String order) {
        if (order.equals("ascending")) {
            publishedProperties.sort(ascendingStateCriteria);
        } else {
            publishedProperties.sort(descendingStateCriteria);
        }
        return publishedProperties;
    }

    private ArrayList<PublishedProperty> sortPublishedPropertiesByDefaultCriteria(ArrayList<PublishedProperty> publishedProperties) {
        publishedProperties.sort(defaultCriteria);
        return publishedProperties;
    }

    private ArrayList<PublishedProperty> filterPublishedProperties(ArrayList<PublishedProperty> publishedProperties, TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms) {
        ArrayList<PublishedProperty> resultProperties = new ArrayList<>();
        for (PublishedProperty publishedProperty : publishedProperties) {
            Property propertyInstance = verifyPropertyInstance(publishedProperty.getProperty());
            if (propertyInstance instanceof House){
                if (publishedProperty.getTypeOfProperty().equals(typeOfProperty) && publishedProperty.getTransactionType().equals(transactionType) && ((House) propertyInstance).getNumberOfBedrooms() == numberOfRooms) {
                    resultProperties.add(publishedProperty);
                }
            } else if (propertyInstance instanceof Apartment){
                if (publishedProperty.getTypeOfProperty().equals(typeOfProperty) && publishedProperty.getTransactionType().equals(transactionType) && ((Apartment) propertyInstance).getNumberOfBedrooms() == numberOfRooms) {
                    resultProperties.add(publishedProperty);
                }
            } else if (propertyInstance instanceof Land){
                if (publishedProperty.getTypeOfProperty().equals(typeOfProperty) && publishedProperty.getTransactionType().equals(transactionType)) {
                    resultProperties.add(publishedProperty);
                }
            }

        }

        resultProperties = sortPublishedPropertiesByDefaultCriteria(resultProperties);
        return resultProperties;
    }

    private Property verifyPropertyInstance(Property property){
        Property propertyInstance;

        if (property instanceof House){
            propertyInstance = (House) property;
        } else if (property instanceof Apartment){
            propertyInstance = (Apartment) property;
        } else {
            propertyInstance = (Land) property;
        }

        return propertyInstance;
    }

    public void addPublishedProperty(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, Comission comission, ArrayList<Photo> photos){
        PublishedProperty publishedProperty = new PublishedProperty(property, typeOfProperty, transactionType, date, comission, photos);
        publishedProperties.add(publishedProperty);
    }

    Comparator<PublishedProperty> ascendingPriceCriteria = new Comparator<PublishedProperty>() {
        @Override
        public int compare(PublishedProperty p1, PublishedProperty p2) {
            double price1 = p1.getProperty().getPrice();
            double price2 = p2.getProperty().getPrice();

            if (price1 < price2) {
                return -1;
            } else if (price1 > price2) {
                return 1;
            } else return 0;
        }
    };

    Comparator<PublishedProperty> descendingPriceCriteria = new Comparator<PublishedProperty>() {
        @Override
        public int compare(PublishedProperty p1, PublishedProperty p2) {
            double price1 = p1.getProperty().getPrice();
            double price2 = p2.getProperty().getPrice();

            if (price1 > price2) {
                return -1;
            } else if (price1 < price2) {
                return 1;
            } else return 0;
        }
    };

    Comparator<PublishedProperty> ascendingStateCriteria = new Comparator<PublishedProperty>() {
        @Override
        public int compare(PublishedProperty s1, PublishedProperty s2) {
            String state1 = s1.getProperty().getAddress().getState();
            String state2 = s2.getProperty().getAddress().getState();

            if (state1.compareTo(state2) > 0) {
                return 1;
            } else if (state1.compareTo(state2) < 0) {
                return -1;
            } else return 0;
        }
    };

    Comparator<PublishedProperty> descendingStateCriteria = new Comparator<PublishedProperty>() {
        @Override
        public int compare(PublishedProperty s1, PublishedProperty s2) {
            String state1 = s1.getProperty().getAddress().getState();
            String state2 = s2.getProperty().getAddress().getState();

            if (state1.compareTo(state2) < 0) {
                return 1;
            } else if (state1.compareTo(state2) > 0) {
                return -1;
            } else return 0;
        }
    };

    Comparator<PublishedProperty> defaultCriteria = new Comparator<PublishedProperty>() {
        @Override
        public int compare(PublishedProperty p1, PublishedProperty p2) {
            int id1 = p1.getId();
            int id2 = p2.getId();

            if (id1 > id2) {
                return -1;
            } else if (id1 < id2) {
                return 1;
            } else return 0;
        }
    };


}

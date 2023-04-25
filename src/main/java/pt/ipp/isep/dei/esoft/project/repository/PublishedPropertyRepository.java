package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public class PublishedPropertyRepository {

    private final ArrayList<PublishedProperty> publishedProperties = new ArrayList<>();

    public ArrayList<PublishedProperty> getPublishedProperties(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        ArrayList<PublishedProperty> resultPublishedProperties = copyPublishedProperties(publishedProperties);
        String inputType = checkInputType(typeOfProperty, transactionType, numberOfRooms, sortCriteria);
        switch (inputType) {
            case "No filter and no sort":
                sortAllPublishedPropertiesByDefaultCriteria(resultPublishedProperties);
                break;
            case "Only sort":
                sortAllPublishedPropertiesBySortCriteria(resultPublishedProperties, sortCriteria, order);
                break;
            case "Only filter":
                filterPublishedProperties(resultPublishedProperties, typeOfProperty, transactionType, numberOfRooms);
                break;
            default:
                filterAndSortPublishedProperties(resultPublishedProperties, typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
                break;
        }
        return resultPublishedProperties;
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
        return new ArrayList<>(publishedProperties);
    }

    private void sortAllPublishedPropertiesByDefaultCriteria(ArrayList<PublishedProperty> resultPublishedProperties) {
        resultPublishedProperties.sort(defaultCriteria);
    }

    private void sortAllPublishedPropertiesBySortCriteria(ArrayList<PublishedProperty> resultPublishedProperties, String sortCriteria, String order) {
        if (sortCriteria.equals("price")) {
            sortPublishedPropertiesByPriceCriteria(resultPublishedProperties, order);
        } else {
            sortPublishedPropertiesByStateCriteria(resultPublishedProperties, order);
        }
    }

    private void sortPublishedPropertiesByPriceCriteria(ArrayList<PublishedProperty> resultPublishedProperties, String order) {
        if (order.equals("ascending")) {
            resultPublishedProperties.sort(ascendingPriceCriteria);
        } else {
            resultPublishedProperties.sort(descendingPriceCriteria);
        }
    }

    private void sortPublishedPropertiesByStateCriteria(ArrayList<PublishedProperty> resultPublishedProperties, String order) {
        if (order.equals("ascending")) {
            resultPublishedProperties.sort(ascendingStateCriteria);
        } else {
            resultPublishedProperties.sort(descendingStateCriteria);
        }
    }

    private void filterPublishedProperties(ArrayList<PublishedProperty> resultPublishedProperties, TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms) {
        Iterator<PublishedProperty> iterator = resultPublishedProperties.iterator();
        while (iterator.hasNext()) {
            PublishedProperty publishedProperty = iterator.next();
            if (publishedProperty.getProperty() instanceof House) {
                if (publishedProperty.getTypeOfProperty() != typeOfProperty || publishedProperty.getTransactionType() != transactionType || ((House) publishedProperty.getProperty()).getNumberOfBedrooms() != numberOfRooms) {
                    iterator.remove();
                }
            } else if (publishedProperty.getProperty() instanceof Apartment) {
                if (publishedProperty.getTypeOfProperty() != typeOfProperty || publishedProperty.getTransactionType() != transactionType || ((Apartment) publishedProperty.getProperty()).getNumberOfBedrooms() != numberOfRooms) {
                    iterator.remove();
                }
            } else if (publishedProperty.getProperty() instanceof Land) {
                if (publishedProperty.getTypeOfProperty() != typeOfProperty || publishedProperty.getTransactionType() != transactionType) {
                    iterator.remove();
                }
            }
        }
        sortPublishedPropertiesByDefaultCriteria(resultPublishedProperties);
    }

    private void sortPublishedPropertiesByDefaultCriteria(ArrayList<PublishedProperty> resultPublishedProperties) {
        resultPublishedProperties.sort(defaultCriteria);
    }

    private void filterAndSortPublishedProperties(ArrayList<PublishedProperty> resultPublishedProperties, TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        filterPublishedProperties(resultPublishedProperties, typeOfProperty, transactionType, numberOfRooms);
        sortPublishedProperties(resultPublishedProperties, sortCriteria, order);
    }

    private void sortPublishedProperties(ArrayList<PublishedProperty> resultPublishedProperties, String sortCriteria, String order) {
        if (sortCriteria.equals("price")) {
            sortPublishedPropertiesByPriceCriteria(resultPublishedProperties, order);
        } else {
            sortPublishedPropertiesByStateCriteria(resultPublishedProperties, order);
        }
    }

    public void addPublishedProperty(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, Comission comission, ArrayList<Photo> photos) {
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

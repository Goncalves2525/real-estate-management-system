package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public class AnnouncementRepository {

    private final ArrayList<Announcement> publishedProperties = new ArrayList<>();

    public ArrayList<Announcement> getPublishedProperties(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        ArrayList<Announcement> resultPublishedProperties = copyPublishedProperties(publishedProperties);
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

    private ArrayList<Announcement> copyPublishedProperties(ArrayList<Announcement> publishedProperties) {
        return new ArrayList<>(publishedProperties);
    }

    private void sortAllPublishedPropertiesByDefaultCriteria(ArrayList<Announcement> resultPublishedProperties) {
        resultPublishedProperties.sort(defaultCriteria);
    }

    private void sortAllPublishedPropertiesBySortCriteria(ArrayList<Announcement> resultPublishedProperties, String sortCriteria, String order) {
        if (sortCriteria.equals("price")) {
            sortPublishedPropertiesByPriceCriteria(resultPublishedProperties, order);
        } else {
            sortPublishedPropertiesByStateCriteria(resultPublishedProperties, order);
        }
    }

    private void sortPublishedPropertiesByPriceCriteria(ArrayList<Announcement> resultPublishedProperties, String order) {
        if (order.equals("ascending")) {
            resultPublishedProperties.sort(ascendingPriceCriteria);
        } else {
            resultPublishedProperties.sort(descendingPriceCriteria);
        }
    }

    private void sortPublishedPropertiesByStateCriteria(ArrayList<Announcement> resultPublishedProperties, String order) {
        if (order.equals("ascending")) {
            resultPublishedProperties.sort(ascendingStateCriteria);
        } else {
            resultPublishedProperties.sort(descendingStateCriteria);
        }
    }

    private void filterPublishedProperties(ArrayList<Announcement> resultPublishedProperties, TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms) {
        Iterator<Announcement> iterator = resultPublishedProperties.iterator();
        while (iterator.hasNext()) {
            Announcement announcement = iterator.next();
            if (announcement.getProperty() instanceof House) {
                if (announcement.getTypeOfProperty() != typeOfProperty || announcement.getTransactionType() != transactionType || ((House) announcement.getProperty()).getNumberOfBedrooms() != numberOfRooms) {
                    iterator.remove();
                }
            } else if (announcement.getProperty() instanceof Apartment) {
                if (announcement.getTypeOfProperty() != typeOfProperty || announcement.getTransactionType() != transactionType || ((Apartment) announcement.getProperty()).getNumberOfBedrooms() != numberOfRooms) {
                    iterator.remove();
                }
            } else if (announcement.getProperty() instanceof Land) {
                if (announcement.getTypeOfProperty() != typeOfProperty || announcement.getTransactionType() != transactionType) {
                    iterator.remove();
                }
            }
        }
        sortPublishedPropertiesByDefaultCriteria(resultPublishedProperties);
    }

    private void sortPublishedPropertiesByDefaultCriteria(ArrayList<Announcement> resultPublishedProperties) {
        resultPublishedProperties.sort(defaultCriteria);
    }

    private void filterAndSortPublishedProperties(ArrayList<Announcement> resultPublishedProperties, TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        filterPublishedProperties(resultPublishedProperties, typeOfProperty, transactionType, numberOfRooms);
        sortPublishedProperties(resultPublishedProperties, sortCriteria, order);
    }

    private void sortPublishedProperties(ArrayList<Announcement> resultPublishedProperties, String sortCriteria, String order) {
        if (sortCriteria.equals("price")) {
            sortPublishedPropertiesByPriceCriteria(resultPublishedProperties, order);
        } else {
            sortPublishedPropertiesByStateCriteria(resultPublishedProperties, order);
        }
    }

    public void addPublishedProperty(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, Comission comission, ArrayList<Photo> photos) {
        Announcement announcement = new Announcement(property, typeOfProperty, transactionType, date, comission, photos);
        publishedProperties.add(announcement);
    }

    Comparator<Announcement> ascendingPriceCriteria = new Comparator<Announcement>() {
        @Override
        public int compare(Announcement p1, Announcement p2) {
            double price1 = p1.getProperty().getPrice();
            double price2 = p2.getProperty().getPrice();

            if (price1 < price2) {
                return -1;
            } else if (price1 > price2) {
                return 1;
            } else return 0;
        }
    };

    Comparator<Announcement> descendingPriceCriteria = new Comparator<Announcement>() {
        @Override
        public int compare(Announcement p1, Announcement p2) {
            double price1 = p1.getProperty().getPrice();
            double price2 = p2.getProperty().getPrice();

            if (price1 > price2) {
                return -1;
            } else if (price1 < price2) {
                return 1;
            } else return 0;
        }
    };

    Comparator<Announcement> ascendingStateCriteria = new Comparator<Announcement>() {
        @Override
        public int compare(Announcement s1, Announcement s2) {
            String state1 = s1.getProperty().getAddress().getState();
            String state2 = s2.getProperty().getAddress().getState();

            if (state1.compareTo(state2) > 0) {
                return 1;
            } else if (state1.compareTo(state2) < 0) {
                return -1;
            } else return 0;
        }
    };

    Comparator<Announcement> descendingStateCriteria = new Comparator<Announcement>() {
        @Override
        public int compare(Announcement s1, Announcement s2) {
            String state1 = s1.getProperty().getAddress().getState();
            String state2 = s2.getProperty().getAddress().getState();

            if (state1.compareTo(state2) < 0) {
                return 1;
            } else if (state1.compareTo(state2) > 0) {
                return -1;
            } else return 0;
        }
    };

    Comparator<Announcement> defaultCriteria = new Comparator<Announcement>() {
        @Override
        public int compare(Announcement p1, Announcement p2) {
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

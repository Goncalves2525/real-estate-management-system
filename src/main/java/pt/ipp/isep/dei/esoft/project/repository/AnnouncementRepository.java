package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

/**
 * The Announcement repository.
 *
 * This class saves the announcements in an ArrayList.
 *
 * This class ofers the methods to filter and sort the announcements.
 */
public class AnnouncementRepository {

    private final ArrayList<Announcement> announcements = new ArrayList<>();


    /**
     * This is the method used for when the user doesn't want to filter or sort the announcements
     * @return resultAnnouncements
     */
    public ArrayList<Announcement> getAllAnnouncementsSortedByDefualtCriteria() {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        resultAnnouncements.sort(defaultCriteria);
        removeNonPublishedAnnouncements(resultAnnouncements);
        return resultAnnouncements;
    }

    /**
     * This is the method used for when the user wants to only sort the announcements
     * @param sortCriteria - sort criteria
     * @param order - sort order
     * @return resultAnnouncements
     */
    public ArrayList<Announcement> getAllAnnouncementsSortedBySortCriteria(String sortCriteria, String order) {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        sortAnnouncements(resultAnnouncements, sortCriteria, order);
        removeNonPublishedAnnouncements(resultAnnouncements);
        return resultAnnouncements;
    }

    /**
     * This is the method used for when the user wants to only filter the announcements
     * @param typeOfProperty - type of property
     * @param transactionType - transaction type
     * @param numberOfRooms - number of rooms
     * @return resultAnnouncements
     */
    public ArrayList<Announcement> getFilteredAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms) {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        Iterator<Announcement> iterator = resultAnnouncements.iterator();
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
        resultAnnouncements.sort(defaultCriteria);
        removeNonPublishedAnnouncements(resultAnnouncements);
        return resultAnnouncements;
    }

    /**
     * This is the method used for when the user wants to filter and sort the announcements
     * @param typeOfProperty - type of property
     * @param transactionType - transaction type
     * @param numberOfRooms - number of rooms
     * @param sortCriteria - sort criteria
     * @param order - sort order
     * @return resultAnnouncements
     */
    public ArrayList<Announcement> getFilteredAndSortedAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        ArrayList<Announcement> resultAnnouncements;
        resultAnnouncements = getFilteredAnnouncements(typeOfProperty, transactionType, numberOfRooms);
        sortAnnouncements(resultAnnouncements, sortCriteria, order);
        removeNonPublishedAnnouncements(resultAnnouncements);
        return resultAnnouncements;
    }

    /**
     * Adds an announcement to the repository
     * @param property - property
     * @param typeOfProperty - type of property
     * @param transactionType - transaction type
     * @param date - date
     * @param comission - comission
     * @param photos - photos
     * @param isPublished - is published
     */
    public void addAnnouncement(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, Comission comission, ArrayList<Photo> photos, boolean isPublished) {
        Announcement announcement = new Announcement(property, typeOfProperty, transactionType, date, comission, photos, isPublished);
        announcements.add(announcement);
    }

    public void addAnnouncementFromOwner(Announcement announcement) {
        announcements.add(announcement);
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write( announcement.toString());
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addAnnouncementByRequest(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, ArrayList<Photo> photos) {
        Announcement announcement = new Announcement(property, typeOfProperty, transactionType, date, photos);
        announcements.add(announcement);
    }


    /**
     * Sorts the announcements using the criteria chosen by the user
     * @param resultAnnouncements - result announcements
     * @param sortCriteria - sort criteria
     * @param order - sort order
     */
    private void sortAnnouncements(ArrayList<Announcement> resultAnnouncements, String sortCriteria, String order) {
        if (sortCriteria.equals("price")) {
            if (order.equals("ascending")) {
                resultAnnouncements.sort(ascendingPriceCriteria);
            } else {
                resultAnnouncements.sort(descendingPriceCriteria);
            }
        } else if (sortCriteria.equals("state")) {
            if (order.equals("ascending")) {
                resultAnnouncements.sort(ascendingStateCriteria);
            } else {
                resultAnnouncements.sort(descendingStateCriteria);
            }
        }
        else {
            if (order.equals("ascending")) {
                resultAnnouncements.sort(ascendingCityCriteria);
            } else {
                resultAnnouncements.sort(descendingCityCriteria);
            }
        }
    }


    /**
     * This method prevents the user from seeing an announcement that is not published
     * @param resultAnnouncements - resultannouncements
     */
    private void removeNonPublishedAnnouncements(ArrayList<Announcement> resultAnnouncements) {
        Iterator<Announcement> iterator = resultAnnouncements.iterator();
        while (iterator.hasNext()) {
            Announcement announcement = iterator.next();
            if (!announcement.isPublished()) {
                iterator.remove();
            }
        }
    }

    /**
     * This method copies the original list of announcements into a new list
     * @param announcements - announcements
     * @return copy of announcements
     */
    private ArrayList<Announcement> copyAnnouncements(ArrayList<Announcement> announcements) {
        return new ArrayList<>(announcements);
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

    Comparator<Announcement> ascendingCityCriteria = new Comparator<Announcement>() {
        @Override
        public int compare(Announcement s1, Announcement s2) {
            String city1 = s1.getProperty().getAddress().getCity();
            String city2 = s2.getProperty().getAddress().getCity();

            if (city1.compareTo(city2) > 0) {
                return 1;
            } else if (city1.compareTo(city2) < 0) {
                return -1;
            } else return 0;
        }
    };

    Comparator<Announcement> descendingCityCriteria = new Comparator<Announcement>() {
        @Override
        public int compare(Announcement s1, Announcement s2) {
            String city1 = s1.getProperty().getAddress().getCity();
            String city2 = s2.getProperty().getAddress().getCity();

            if (city1.compareTo(city2) < 0) {
                return 1;
            } else if (city1.compareTo(city2) > 0) {
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

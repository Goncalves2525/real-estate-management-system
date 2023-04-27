package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public class AnnouncementRepository {

    private final ArrayList<Announcement> announcements = new ArrayList<>();


    public ArrayList<Announcement> getAllAnnouncementsSortedByDefualtCriteria() {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        resultAnnouncements.sort(defaultCriteria);
        removeNonPublishedAnnouncements(resultAnnouncements);
        return resultAnnouncements;
    }

    public ArrayList<Announcement> getAllAnnouncementsSortedBySortCriteria(String sortCriteria, String order) {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        sortAnnouncements(resultAnnouncements, sortCriteria, order);
        removeNonPublishedAnnouncements(resultAnnouncements);
        return resultAnnouncements;
    }

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

    public ArrayList<Announcement> getFilteredAndSortedAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        ArrayList<Announcement> resultAnnouncements;
        resultAnnouncements = getFilteredAnnouncements(typeOfProperty, transactionType, numberOfRooms);
        sortAnnouncements(resultAnnouncements, sortCriteria, order);
        removeNonPublishedAnnouncements(resultAnnouncements);
        return resultAnnouncements;
    }

    public void addAnnouncement(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, Comission comission, ArrayList<Photo> photos, boolean isPublished) {
        Announcement announcement = new Announcement(property, typeOfProperty, transactionType, date, comission, photos, isPublished);
        announcements.add(announcement);
    }

    public void addAnnouncementByRequest(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, ArrayList<Photo> photos) {
        Announcement announcement = new Announcement(property, typeOfProperty, transactionType, date, photos);
        announcements.add(announcement);
    }







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



    private void removeNonPublishedAnnouncements(ArrayList<Announcement> resultAnnouncements) {
        Iterator<Announcement> iterator = resultAnnouncements.iterator();
        while (iterator.hasNext()) {
            Announcement announcement = iterator.next();
            if (!announcement.isPublished()) {
                iterator.remove();
            }
        }
    }

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

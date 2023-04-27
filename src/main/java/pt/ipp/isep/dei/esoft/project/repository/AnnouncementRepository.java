package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public class AnnouncementRepository {

    private final ArrayList<Announcement> announcements = new ArrayList<>();

    public ArrayList<Announcement> getAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        String inputType = checkInputType(typeOfProperty, transactionType, numberOfRooms, sortCriteria);
        switch (inputType) {
            case "No filter and no sort":
                sortAllAnnouncementsByDefaultCriteria(resultAnnouncements);
                break;
            case "Only sort":
                sortAllAnnouncementsBySortCriteria(resultAnnouncements, sortCriteria, order);
                break;
            case "Only filter":
                filterAnnouncements(resultAnnouncements, typeOfProperty, transactionType, numberOfRooms);
                break;
            default:
                filterAndSortAnnouncements(resultAnnouncements, typeOfProperty, transactionType, numberOfRooms, sortCriteria, order);
                break;
        }
        return resultAnnouncements;
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

    private ArrayList<Announcement> copyAnnouncements(ArrayList<Announcement> announcements) {
        return new ArrayList<>(announcements);
    }

    private void sortAllAnnouncementsByDefaultCriteria(ArrayList<Announcement> resultAnnouncements) {
        resultAnnouncements.sort(defaultCriteria);
    }

    private void sortAllAnnouncementsBySortCriteria(ArrayList<Announcement> resultAnnouncements, String sortCriteria, String order) {
        if (sortCriteria.equals("price")) {
            sortAnnouncementsByPriceCriteria(resultAnnouncements, order);
        } else {
            sortAnnouncementsByStateCriteria(resultAnnouncements, order);
        }
    }

    private void sortAnnouncementsByPriceCriteria(ArrayList<Announcement> resultAnnouncements, String order) {
        if (order.equals("ascending")) {
            resultAnnouncements.sort(ascendingPriceCriteria);
        } else {
            resultAnnouncements.sort(descendingPriceCriteria);
        }
    }

    private void sortAnnouncementsByStateCriteria(ArrayList<Announcement> resultAnnouncements, String order) {
        if (order.equals("ascending")) {
            resultAnnouncements.sort(ascendingStateCriteria);
        } else {
            resultAnnouncements.sort(descendingStateCriteria);
        }
    }

    private void filterAnnouncements(ArrayList<Announcement> resultAnnouncements, TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms) {
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
        sortAnnouncementsByDefaultCriteria(resultAnnouncements);
    }

    private void sortAnnouncementsByDefaultCriteria(ArrayList<Announcement> resultPublishedProperties) {
        resultPublishedProperties.sort(defaultCriteria);
    }

    private void filterAndSortAnnouncements(ArrayList<Announcement> resultPublishedProperties, TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        filterAnnouncements(resultPublishedProperties, typeOfProperty, transactionType, numberOfRooms);
        sortAnnouncements(resultPublishedProperties, sortCriteria, order);
    }

    private void sortAnnouncements(ArrayList<Announcement> resultPublishedProperties, String sortCriteria, String order) {
        if (sortCriteria.equals("price")) {
            sortAnnouncementsByPriceCriteria(resultPublishedProperties, order);
        } else {
            sortAnnouncementsByStateCriteria(resultPublishedProperties, order);
        }
    }

    public void addAnnouncement(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, Comission comission, ArrayList<Photo> photos) {
        Announcement announcement = new Announcement(property, typeOfProperty, transactionType, date, comission, photos);
        announcements.add(announcement);
    }

    public void addAnnouncementByRequest(Property property, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, ArrayList<Photo> photos) {
        Announcement announcement = new Announcement(property, typeOfProperty, transactionType, date, photos);
        announcements.add(announcement);
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

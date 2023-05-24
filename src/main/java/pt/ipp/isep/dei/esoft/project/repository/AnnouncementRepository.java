package pt.ipp.isep.dei.esoft.project.repository;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import pt.ipp.isep.dei.esoft.project.application.controller.ListAnnouncementsController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

/**
 * The Announcement repository.
 * <p>
 * This class saves the announcements in an ArrayList.
 * <p>
 * This class ofers the methods to filter and sort the announcements.
 */
public class AnnouncementRepository {

    private final ArrayList<Announcement> announcements = new ArrayList<>();


    /**
     * This is the method used for when the user doesn't want to filter or sort the announcements
     *
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
     *
     * @param sortCriteria - sort criteria
     * @param order        - sort order
     * @return resultAnnouncements
     */
    public ArrayList<Announcement> getAllAnnouncementsSortedBySortCriteria(String sortCriteria, String order) {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        if (sortCriteria.equals("price") || sortCriteria.equals("city") || sortCriteria.equals("state")) {
            sortAnnouncements(resultAnnouncements, sortCriteria, order);
            removeNonPublishedAnnouncements(resultAnnouncements);
            return resultAnnouncements;
        } else {
            removeAllAnnouncements(resultAnnouncements);
            return resultAnnouncements;
        }
    }

    /**
     * This is the method used for when the user wants to only filter the announcements
     *
     * @param typeOfProperty  - type of property
     * @param transactionType - transaction type
     * @param numberOfRooms   - number of rooms
     * @return resultAnnouncements
     */
    public ArrayList<Announcement> getFilteredAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms) {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        Iterator<Announcement> iterator = resultAnnouncements.iterator();
        while (iterator.hasNext()) {
            Announcement announcement = iterator.next();
            Property property = getPropertyByAnnouncement(announcement);
            if (property instanceof House) {
                if (announcement.getTypeOfProperty() != typeOfProperty || announcement.getTransactionType() != transactionType || ((House) property).getNumberOfBedrooms() != numberOfRooms) {
                    iterator.remove();
                }
            } else if (property instanceof Apartment) {
                if (announcement.getTypeOfProperty() != typeOfProperty || announcement.getTransactionType() != transactionType || ((Apartment) property).getNumberOfBedrooms() != numberOfRooms) {
                    iterator.remove();
                }
            } else if (property instanceof Land) {
                if (announcement.getTypeOfProperty() != typeOfProperty || announcement.getTransactionType() != transactionType) {
                    iterator.remove();
                }
            }
        }
        resultAnnouncements.sort(defaultCriteria);
        removeNonPublishedAnnouncements(resultAnnouncements);
        return resultAnnouncements;
    }

    public ArrayList<Announcement> getFilteredAnnouncementsForGUI(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms) {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        Iterator<Announcement> iterator = resultAnnouncements.iterator();
        while (iterator.hasNext()) {
            Announcement announcement = iterator.next();
            Property property = getPropertyByAnnouncement(announcement);
            if (property instanceof House) {
                if (announcement.getTypeOfProperty() != typeOfProperty && typeOfProperty != null) {
                    iterator.remove();
                }
                else if (announcement.getTransactionType() != transactionType && transactionType != null) {
                    iterator.remove();
                }
                else if (((House) property).getNumberOfBedrooms() != numberOfRooms && numberOfRooms != 0) {
                    iterator.remove();
                }
            } else if (property instanceof Apartment) {
                if (announcement.getTypeOfProperty() != typeOfProperty && typeOfProperty != null) {
                    iterator.remove();
                }
                else if (announcement.getTransactionType() != transactionType && transactionType != null) {
                    iterator.remove();
                }
                else if (((Apartment) property).getNumberOfBedrooms() != numberOfRooms && numberOfRooms != 0) {
                    iterator.remove();
                }
            } else if (property instanceof Land) {
                if (announcement.getTypeOfProperty() != typeOfProperty && typeOfProperty != null ) {
                    iterator.remove();
                }
                else if (announcement.getTransactionType() != transactionType && transactionType != null) {
                    iterator.remove();
                }
                else if (numberOfRooms > 0) {
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
     *
     * @param typeOfProperty  - type of property
     * @param transactionType - transaction type
     * @param numberOfRooms   - number of rooms
     * @param sortCriteria    - sort criteria
     * @param order           - sort order
     * @return resultAnnouncements
     */
    public ArrayList<Announcement> getFilteredAndSortedAnnouncements(TypeOfProperty typeOfProperty, TransactionType transactionType, int numberOfRooms, String sortCriteria, String order) {
        ArrayList<Announcement> resultAnnouncements;
        resultAnnouncements = getFilteredAnnouncements(typeOfProperty, transactionType, numberOfRooms);
        if (sortCriteria.equals("price") || sortCriteria.equals("city") || sortCriteria.equals("state")) {
            sortAnnouncements(resultAnnouncements, sortCriteria, order);
            removeNonPublishedAnnouncements(resultAnnouncements);
            return resultAnnouncements;
        } else {
            removeAllAnnouncements(resultAnnouncements);
            return resultAnnouncements;
        }
    }

    public double getPropertyPriceByAnnouncmentId(int id) {
        if (getAnnouncementById(id) != null) {
            Announcement announcement = getAnnouncementById(id);
            Property property = getPropertyByAnnouncement(announcement);
            return property.getPrice();
        }
        System.out.println("Announcement not found");
        return -1;
    }

    /**
     * Adds an announcement to the repository
     *
     * @param propertyID      - property id
     * @param typeOfProperty  - type of property
     * @param transactionType - transaction type
     * @param date            - date
     * @param commission      - comission
     * @param photos          - photos
     * @param isPublished     - is published
     */
    public void addAnnouncement(int propertyID, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, Commission commission, ArrayList<Photo> photos, boolean isPublished) {
        Announcement announcement = new Announcement(propertyID, typeOfProperty, transactionType, date, commission, photos, isPublished);
        announcements.add(announcement);
    }

    /**
     * Adds an announcement to the repository, with an agent associated
     *
     * @param agent           - agent
     * @param propertyID      - property id
     * @param typeOfProperty  - type of property
     * @param transactionType - transaction type
     * @param date            - date
     * @param commission      - commission
     * @param photos          - photos
     * @param isPublished     - is published
     */
    public void addAnnouncementWithAgent(Employee agent, int propertyID, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, Commission commission, ArrayList<Photo> photos, boolean isPublished) {
        Announcement announcement = new Announcement(agent, propertyID, typeOfProperty, transactionType, date, commission, photos, isPublished);
        announcements.add(announcement);
    }

    /**
     * Adds an Announcement to the repository, created by a Client
     *
     * @param announcement
     */
    public void addAnnouncementFromOwner(Announcement announcement) {
        announcements.add(announcement);
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write(announcement.toString());
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts the announcements using the criteria chosen by the user
     *
     * @param resultAnnouncements - result announcements
     * @param sortCriteria        - sort criteria
     * @param order               - sort order
     */
    private void sortAnnouncements(ArrayList<Announcement> resultAnnouncements, String sortCriteria, String order) {
        switch (sortCriteria) {
            case "price":

                resultAnnouncements.sort(new Comparator<Announcement>() {
                    @Override
                    public int compare(Announcement a1, Announcement a2) {
                        Property property1 = getPropertyByAnnouncement(a1);
                        Property property2 = getPropertyByAnnouncement(a2);
                        return (int) (property1.getPrice() - property2.getPrice());
                    }
                });
                break;
            case "city":
                resultAnnouncements.sort(new Comparator<Announcement>() {
                    @Override
                    public int compare(Announcement a1, Announcement a2) {
                        Property property1 = getPropertyByAnnouncement(a1);
                        Property property2 = getPropertyByAnnouncement(a2);
                        return property1.getAddress().getCity().compareTo(property2.getAddress().getCity());
                    }
                });
                break;

            case "state":
                resultAnnouncements.sort(new Comparator<Announcement>() {
                    @Override
                    public int compare(Announcement a1, Announcement a2) {
                        Property property1 = getPropertyByAnnouncement(a1);
                        Property property2 = getPropertyByAnnouncement(a2);
                        return property1.getAddress().getState().compareTo(property2.getAddress().getState());
                    }
                });
                break;
        }
        ;

        if (Objects.equals(order, "descending")) Collections.reverse(resultAnnouncements);

    }


    /**
     * This method prevents the user from seeing an announcement that is not published
     *
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
     * This is the method used to return all the announcements assigned to the logged in Agent, sorted by the default criteria
     *
     * @return resultAnnouncements
     */
    public ArrayList<Announcement> getAllAnnouncementsByAgent() {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        resultAnnouncements.sort(defaultCriteria);
        String agentName = getEmployee().getUserName();
        getAnnouncementsByAgent(agentName);
        removePublishedAnnouncements(resultAnnouncements);
        return resultAnnouncements;
    }

    /**
     * This is the method used to return the current user session
     *
     * @return resultAnnouncements
     */
    public UserSession getEmployee() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        return authenticationRepository.getCurrentUserSession();
    }

    /**
     * This method is used to return all the announcements assigned to the loggedIn Agent's email
     *
     * @param agentEmail - the loggedIn agent's email
     * @return resultAnnouncements
     */
    public ArrayList<Announcement> getAnnouncementsByAgent(String agentEmail) {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        Iterator<Announcement> iterator = resultAnnouncements.iterator();
        while (iterator.hasNext()) {
            Announcement announcement = iterator.next();
            if (!announcement.getAgentEmail().equals(agentEmail) || announcement.isPublished()) {
                iterator.remove();
            }
        }
        return resultAnnouncements;
    }


    /**
     * This method prevents the user from seeing an announcement that is published
     *
     * @param resultAnnouncements - resultannouncements
     */
    private void removePublishedAnnouncements(ArrayList<Announcement> resultAnnouncements) {
        Iterator<Announcement> iterator = resultAnnouncements.iterator();
        while (iterator.hasNext()) {
            Announcement announcement = iterator.next();
            if (announcement.isPublished()) {
                iterator.remove();
            }
        }
    }

    /**
     * This method copies the original list of announcements into a new list
     *
     * @param announcements - announcements
     * @return copy of announcements
     */
    private ArrayList<Announcement> copyAnnouncements(ArrayList<Announcement> announcements) {
        return new ArrayList<>(announcements);
    }

    /**
     * This method removes all the announcements from the list
     *
     * @param announcements - announcements
     * @return announcements
     */
    private void removeAllAnnouncements(ArrayList<Announcement> announcements) {
        announcements.removeAll(announcements);
    }

    public void unpublishAnnouncement(int IdAnnouncement) {
        Announcement announcement = getAnnouncementById(IdAnnouncement);
        announcement.setPublished(false);
    }

    public Property getPropertyByAnnouncement(Announcement announcement) {
        int propertyID = announcement.getPropertyID();
        return Repositories.getInstance().getPropertyRepository().getPropertyByID(propertyID);
    }


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


    public Announcement getAnnouncementById(int id) {
        for (Announcement announcement : announcements) {
            if (announcement.getId() == id) {
                return announcement;
            }
        }
        return null;
    }

    public void removeAllAnnouncements() {
        announcements.removeAll(announcements);
    }

    public ArrayList<Announcement> getAllAnnouncementsSortedBypropertyAndIdCriteria() {
        ArrayList<Announcement> resultAnnouncements = copyAnnouncements(announcements);
        resultAnnouncements.sort(propertyAndIdCriteria);
        removeNonPublishedAnnouncements(resultAnnouncements);
        return resultAnnouncements;
    }

    Comparator<Announcement> propertyAndIdCriteria = new Comparator<Announcement>() {
        @Override
        public int compare(Announcement p1, Announcement p2) {
            return p1.getTypeOfProperty().compareTo(p2.getTypeOfProperty());
        }
    };


}

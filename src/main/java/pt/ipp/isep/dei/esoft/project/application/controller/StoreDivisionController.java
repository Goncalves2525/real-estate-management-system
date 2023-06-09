package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class StoreDivisionController {
    private PropertyRepository propertyRepository = null;
    private PropertyRepository propertyDealRepository = null;
    private AnnouncementRepository announcementRepository;


    // List<Integer> L = List.of(2, 12, 9, 4);
    //        generatePartitions(L);

    public StoreDivisionController() {
        getPropertyRepository();
        getAnnouncementRepository();
        getPropertyDealRepository();
    }

    private void getPropertyDealRepository() {
        if (propertyDealRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyDealRepository = repositories.getPropertyDealRepository();
        }
    }

    private void getPropertyRepository() {
        if (propertyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyRepository = repositories.getPropertyRepository();
        }
    }

    public Property getPropertyByAnnouncement(Announcement announcement){
        return announcementRepository.getPropertyByAnnouncement(announcement);
    }
    public Announcement getAnnouncementById(int id){
        try {
            return announcementRepository.getAnnouncementById(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    private AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }

    public ArrayList<Announcement> getAllAnnouncements(){
        return getAnnouncementRepository().getAllAnnouncementsSortedByDefualtCriteria();
    }

    public ArrayList<Announcement> getAllAnnouncementsSortedBypropertyAndIdCriteria() {
        return getAnnouncementRepository().getAllAnnouncementsSortedBypropertyAndIdCriteria();
    }

    public static void generatePartitions(List<Integer> L) {
        int n = L.size();
        int numPartitions = (int) Math.pow(2, n);

        long startTime = System.nanoTime(); // Record start time

        for (int i = 0; i < numPartitions - 1; i++) {
            List<Integer> L1 = new ArrayList<>();
            List<Integer> L2 = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    L1.add(L.get(j));
                } else {
                    L2.add(L.get(j));
                }
            }

            System.out.println("Partition " + (i + 1) + ": " + L1 + ", " + L2);
        }

        long endTime = System.nanoTime(); // Record end time
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " nanoseconds");
    }


}


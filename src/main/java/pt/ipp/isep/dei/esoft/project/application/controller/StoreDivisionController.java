package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
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

    public static String dividePartitions(List<Integer> L) {
        String result = "";
        int n = L.size();
        int numPartitions = (int) Math.pow(2, n);

        long startTime = System.currentTimeMillis(); // Record start time

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
            result += "Partition " + (i + 1) + ": " + L1 + ", " + L2 + "\n";
            //System.out.println("Partition " + (i + 1) + ": " + L1 + ", " + L2);
        }

        long endTime = System.currentTimeMillis(); // Record end time
        long executionTime = endTime - startTime;
        //System.out.println("Execution time: " + executionTime + " milliseconds");
        result += "Execution time: " + executionTime + " milliseconds";
        return result;
    }

//    public static String partitionTest2(int testNumber){
//        String result = "";
//        //int[] inputSizes = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30}; // Input sizes to test
//        long executionTimes = 0; // Array to store execution times
//
//        //for (int i = 0; i < inputSizes.length; i++) {
//            int n = testNumber;
//
//            // Generate input list
//            List<Agency> stores = generateInputList(n);
//
//            // Measure execution time
//            long startTime = System.currentTimeMillis();
//            List<List<Agency>> balancedPartition = findBalancedPartition(stores);
//            long endTime = System.currentTimeMillis();
//            long executionTime = endTime - startTime;
//            executionTimes = executionTime;
//
//                result += "Input size: " + n + "\n";
//                result += "Input list: " + stores + "\n";
//                result += "Balanced partition: " + balancedPartition + "\n";
//                result += "Difference of sums: " + calculateDifferenceOfSums(balancedPartition) + "\n";
//
//
//        result += "Input size: " + n + ", Execution time: " + executionTimes + " milliseconds";
//        return result;
//
//    }

    public static String partitionTest2(int testNumber){
        String result = "";
        List<Agency> L = generateInputList(testNumber);
        //int n = L.size();
        int n = testNumber;
        int numPartitions = (int) Math.pow(2, n);

        long startTime = System.currentTimeMillis(); // Record start time

        for (int i = 0; i < numPartitions - 1; i++) {
            List<Integer> L1 = new ArrayList<>();
            List<Integer> L2 = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    L1.add(L.get(j).getNoOfProperties());
                } else {
                    L2.add(L.get(j).getNoOfProperties());
                }
            }
            result += "Partition " + (i + 1) + ": " + L1 + ", " + L2 + "\n";
            //System.out.println("Partition " + (i + 1) + ": " + L1 + ", " + L2);
        }

        long endTime = System.currentTimeMillis(); // Record end time
        long executionTime = endTime - startTime;
        //System.out.println("Execution time: " + executionTime + " milliseconds");
        result += "Execution time: " + executionTime + " milliseconds";
        return result;
    }

    public static List<Agency> generateInputList(int n) {
        List<Agency> stores = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            stores.add(new Agency(i, i * 10));
            //stores.add(new Agency(i,(int) Math.random() * 10));
        }
        return stores;
    }

    public static List<List<Agency>> findBalancedPartition(List<Agency> stores) {
        // Implementation of the findBalancedPartition algorithm goes here
        // ...

        // Returning a dummy balanced partition for demonstration purposes
        List<List<Agency>> balancedPartition = new ArrayList<>();
        balancedPartition.add(stores.subList(0, stores.size() / 2));
        balancedPartition.add(stores.subList(stores.size() / 2, stores.size()));
        return balancedPartition;
    }

    public static int calculateDifferenceOfSums(List<List<Agency>> balancedPartition) {
        int sum1 = 0;
        int sum2 = 0;

        for (Agency store : balancedPartition.get(0)) {
            sum1 += store.getNoOfProperties();
        }

        for (Agency store : balancedPartition.get(1)) {
            sum2 += store.getNoOfProperties();
        }

        return Math.abs(sum1 - sum2);
    }

    public ArrayList<Property> getProperties() {
        return propertyRepository.getAllProperties();
    }
}


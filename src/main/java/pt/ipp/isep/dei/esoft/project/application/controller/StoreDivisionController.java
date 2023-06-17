package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.Tuple;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreDivisionController {

    private Repositories repositories = Repositories.getInstance();

    public StoreDivisionController() {}

    /**
     * Gets the properties from the Tuple
     * @param inputStores List of tuples
     * @return List of properties
     */
    public List<Tuple<String, Integer>> prepareStoreList(List<Tuple<String, Integer>> inputStores) {
        //List<Agency> stores = new ArrayList<>();
        List<Tuple<String, Integer>> stores = new ArrayList<>();
        for (Tuple<String, Integer> agency : inputStores) {
            //stores.add(new Agency(agency.getId(), agency.getNoOfProperties()));
            stores.add(new Tuple<>(((Tuple<String, Integer>) agency).getFirst(), ((Tuple<String, Integer>) agency).getSecond()));
        }
        return stores;
    }

    /**
     * Calculates the difference of sums
     * @param inputStores List of tuples
     * @return HashMap
     */
    public Map<String, Object> partitionTest2(List<Tuple<String, Integer>> inputStores) {
        List<Tuple<String, Integer>> L = prepareStoreList(inputStores);

        long startTime = System.currentTimeMillis(); // Record start time
        List<List<Tuple<String, Integer>>> balancedPartition = findBalancedPartition(L);
        long endTime = System.currentTimeMillis(); // Record end time

        long executionTime = endTime - startTime;
        int differenceOfSums = calculateDifferenceOfSums(balancedPartition);

        Map<String, Object> result = new HashMap<>();
        result.put("executionTime", executionTime);
        result.put("differenceOfSums", differenceOfSums);
        result.put("group1", balancedPartition.get(0));
        result.put("group2", balancedPartition.get(1));

        return result;
    }

    /**
     * Finds the balanced partition
     * @param stores List of tuples
     * @return List of tuples
     */
    public static List<List<Tuple<String, Integer>>> findBalancedPartition(List<Tuple<String, Integer>> stores) {
        int n = stores.size();
        int numPartitions = (int) Math.pow(2, n-1);
        List<List<Tuple<String, Integer>>> bestPartition = null;
        int minDifference = Integer.MAX_VALUE;

        for (int i = 0; i < numPartitions; i++) {
            List<Tuple<String, Integer>> L1 = new ArrayList<>();
            List<Tuple<String, Integer>> L2 = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    L1.add(stores.get(j));
                } else {
                    L2.add(stores.get(j));
                }
            }

            List<List<Tuple<String, Integer>>> currentPartition = new ArrayList<>();
            currentPartition.add(L1);
            currentPartition.add(L2);

            int currentDifference = calculateDifferenceOfSums(currentPartition);

            if (currentDifference < minDifference) {
                minDifference = currentDifference;
                bestPartition = currentPartition;
            }
        }

        return bestPartition;
    }

    /**
     * Calculates the difference of sums
     * @param balancedPartition List of tuples
     * @return int difference of sums
     */
    public static int calculateDifferenceOfSums(List<List<Tuple<String, Integer>>> balancedPartition) {
        int sum1 = 0;
        int sum2 = 0;

        for (Tuple<String, Integer> store :  balancedPartition.get(0)) {
            sum1 += (store.getSecond());
            //sum1 += store.getNoOfProperties();
        }

        for (Tuple<String, Integer> store : balancedPartition.get(1)) {
            sum2 += (store.getSecond());
        }

        return Math.abs(sum1 - sum2);
    }

    /**
     * Gets the properties
     * @return ArrayList of properties
     */
    public ArrayList<Property> getProperties() {
        return repositories.getPropertyRepository().getAllProperties();
    }
}

package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class StoreDivisionController {

    private Repositories repositories = Repositories.getInstance();

    public StoreDivisionController() {}

    public List<Agency> prepareStoreList(List<Agency> inputStores) {
        List<Agency> stores = new ArrayList<>();
        for (Agency agency : inputStores) {
            stores.add(new Agency(agency.getId(), agency.getNoOfProperties()));
        }
        return stores;
    }

    public String partitionTest2(List<Agency> inputStores) {
        StringBuilder result = new StringBuilder();
        List<Agency> L = prepareStoreList(inputStores);

        long startTime = System.currentTimeMillis(); // Record start time
        List<List<Agency>> balancedPartition = findBalancedPartition(L);
        long endTime = System.currentTimeMillis(); // Record end time

        long executionTime = endTime - startTime;

        result.append("Input size: ").append(inputStores.size()).append("\n")
                .append("Execution time: ").append(executionTime).append(" milliseconds\n")
                .append("Balanced partition: ").append(balancedPartition).append("\n")
                .append("Difference of sums: ").append(calculateDifferenceOfSums(balancedPartition)).append("\n\n");

        return result.toString();
    }

    public static List<List<Agency>> findBalancedPartition(List<Agency> stores) {
        int n = stores.size();
        int numPartitions = (int) Math.pow(2, n);
        List<List<Agency>> bestPartition = null;
        int minDifference = Integer.MAX_VALUE;

        for (int i = 1; i < numPartitions; i++) {
            List<Agency> L1 = new ArrayList<>();
            List<Agency> L2 = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    L1.add(stores.get(j));
                } else {
                    L2.add(stores.get(j));
                }
            }

            List<List<Agency>> currentPartition = new ArrayList<>();
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
        return repositories.getPropertyRepository().getAllProperties();
    }
}

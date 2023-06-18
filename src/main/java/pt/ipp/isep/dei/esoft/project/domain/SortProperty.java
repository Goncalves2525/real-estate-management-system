package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class SortProperty implements Serializable {

    /**
     * Sorts a list of properties by area in ascending order using the insertion sort algorithm.
     *
     * @param properties list of properties to be sorted
     * @return sorted list of properties
     */

    public static ArrayList<Property> sortPropertiesInsertionSortByAreaDescending(ArrayList<Property> properties) {
        try {
            ArrayList<Property> propertiesSorted = new ArrayList<>();
            for (Property p : properties) {
                propertiesSorted.add(p);
            }
            for (int i = 1; i < propertiesSorted.size(); i++) {
                Property key = propertiesSorted.get(i);
                int j = i - 1;
                while (j >= 0 && propertiesSorted.get(j).getArea() < key.getArea()) {
                    propertiesSorted.set(j + 1, propertiesSorted.get(j));
                    j = j - 1;
                }
                propertiesSorted.set(j + 1, key);
            }
            return propertiesSorted;
        } catch (Exception e) {
            // Handle the exception or rethrow it
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    /**
     * Sorts a list of properties by area in ascending order using the selection sort algorithm.
     *
     * @param properties list of properties to be sorted
     * @return sorted list of properties
     */

    public static ArrayList<Property> sortPropertiesSelectionSortByAreaDescending(ArrayList<Property> properties) {
        try {
            ArrayList<Property> propertiesSorted = new ArrayList<>();
            for (Property p : properties) {
                propertiesSorted.add(p);
            }
            for (int i = 0; i < propertiesSorted.size() - 1; i++) {
                int index = i;
                for (int j = i + 1; j < propertiesSorted.size(); j++) {
                    if (propertiesSorted.get(j).getArea() > propertiesSorted.get(index).getArea()) {
                        index = j;
                    }
                }
                Property smallerNumber = propertiesSorted.get(index);
                propertiesSorted.set(index, propertiesSorted.get(i));
                propertiesSorted.set(i, smallerNumber);
            }
            return propertiesSorted;
        } catch (Exception e) {
            // Handle the exception or rethrow it
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    /**
     * Sorts a list of properties by area in ascending order using the insertion sort algorithm.
     *
     * @param properties list of properties to be sorted
     * @return sorted list of properties
     */

    public static ArrayList<Property> sortPropertiesSelectionSortByAreaAscending(ArrayList<Property> properties) {
        try {
            ArrayList<Property> propertiesSorted = new ArrayList<>();
            for (Property p : properties) {
                propertiesSorted.add(p);
            }
            for (int i = 0; i < propertiesSorted.size() - 1; i++) {
                int index = i;
                for (int j = i + 1; j < propertiesSorted.size(); j++) {
                    if (propertiesSorted.get(j).getArea() < propertiesSorted.get(index).getArea()) {
                        index = j;
                    }
                }
                Property smallerNumber = propertiesSorted.get(index);
                propertiesSorted.set(index, propertiesSorted.get(i));
                propertiesSorted.set(i, smallerNumber);
            }
            return propertiesSorted;
        } catch (Exception e) {
            // Handle the exception or rethrow it
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    /**
     * Sorts a list of properties by area in ascending order using the insertion sort algorithm.
     *
     * @param properties list of properties to be sorted
     * @return sorted list of properties
     */

    public static ArrayList<Property> sortPropertiesInsertionSortByAreaAscending(ArrayList<Property> properties) {
        try {
            ArrayList<Property> propertiesSorted = new ArrayList<>();
            for (Property p : properties) {
                propertiesSorted.add(p);
            }
            for (int i = 1; i < propertiesSorted.size(); i++) {
                Property key = propertiesSorted.get(i);
                int j = i - 1;
                while (j >= 0 && propertiesSorted.get(j).getArea() > key.getArea()) {
                    propertiesSorted.set(j + 1, propertiesSorted.get(j));
                    j = j - 1;
                }
                propertiesSorted.set(j + 1, key);
            }
            return propertiesSorted;
        } catch (Exception e) {
            // Handle the exception or rethrow it
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    /**
     * Sorts a list of properties by area in ascending order using the insertion sort algorithm.
     *
     * @param properties list of properties to be sorted
     * @return sorted list of properties
     */

    public static ArrayList<Property> sortPropertiesSelectionSortByID(ArrayList<Property> properties) {
        try {
            ArrayList<Property> propertiesSorted = new ArrayList<>();
            for (Property p : properties) {
                propertiesSorted.add(p);
            }
            for (int i = 0; i < propertiesSorted.size() - 1; i++) {
                int index = i;
                for (int j = i + 1; j < propertiesSorted.size(); j++) {
                    if (propertiesSorted.get(j).getId() < propertiesSorted.get(index).getId()) {
                        index = j;
                    }
                }
                Property smallerNumber = propertiesSorted.get(index);
                propertiesSorted.set(index, propertiesSorted.get(i));
                propertiesSorted.set(i, smallerNumber);
            }
            return propertiesSorted;
        } catch (Exception e) {
            // Handle the exception or rethrow it
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}



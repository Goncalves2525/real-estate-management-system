package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public class SortProperty {

    public static ArrayList<Property> sortPropertiesInsertionSortByAreaDescending(ArrayList<Property> properties) {
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
    }
    public static ArrayList<Property> sortPropertiesSelectionSortByAreaDescending(ArrayList<Property> properties) {
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
    }
    public static ArrayList<Property> sortPropertiesSelectionSortByAreaAscending(ArrayList<Property> properties) {
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
    }

    public static ArrayList<Property> sortPropertiesInsertionSortByAreaAscending(ArrayList<Property> properties) {
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
    }
}


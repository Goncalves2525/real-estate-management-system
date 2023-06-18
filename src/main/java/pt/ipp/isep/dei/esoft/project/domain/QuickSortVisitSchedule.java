package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class QuickSortVisitSchedule implements SortVisitSchedule, Serializable {
    @Override
    public List<VisitSchedule> sort(List<VisitSchedule> list) {
        if (list == null || list.size() == 0) {
            return list;
        }
        int low = 0;
        int high = list.size() - 1;
        quickSort(list, low, high);
        return list;
    }

    private void quickSort(List<VisitSchedule> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(List<VisitSchedule> list, int low, int high) {
        VisitSchedule pivot = list.get(high);
        if (pivot == null || pivot.getDate() == null) {
            throw new IllegalArgumentException("Cannot sort list with null elements or dates.");
        }
        LocalDate pivotDate = pivot.getDate();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            VisitSchedule current = list.get(j);
            if (current == null || current.getDate() == null) {
                throw new IllegalArgumentException("Cannot sort list with null elements or dates.");
            }
            if (current.getDate().isBefore(pivotDate)) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<VisitSchedule> list, int i, int j) {
        VisitSchedule temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

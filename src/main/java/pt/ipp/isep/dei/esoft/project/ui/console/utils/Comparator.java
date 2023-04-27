package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;

public class Comparator {

    

    java.util.Comparator<Announcement> descendingPriceCriteria = new java.util.Comparator<Announcement>() {
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

    java.util.Comparator<Announcement> ascendingStateCriteria = new java.util.Comparator<Announcement>() {
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

    java.util.Comparator<Announcement> descendingStateCriteria = new java.util.Comparator<Announcement>() {
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

    java.util.Comparator<Announcement> defaultCriteria = new java.util.Comparator<Announcement>() {
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

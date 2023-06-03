package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public interface SortStrategy {
    List<VisitSchedule> sort(List<VisitSchedule> list);
}

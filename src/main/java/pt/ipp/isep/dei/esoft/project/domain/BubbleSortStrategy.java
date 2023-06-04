package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public class BubbleSortStrategy implements SortStrategy{
    @Override
    public List<VisitSchedule> sort(List<VisitSchedule> list) {
        int n = list.size();
        VisitSchedule temp;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(list.get(j-1).getStartTime().isAfter(list.get(j).getStartTime())){
                    temp = list.get(j-1);
                    list.set(j-1, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        return list;
    }

}

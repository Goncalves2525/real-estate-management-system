package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Statistics;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

public class StatisticsController {
    private AnnouncementRepository dealRepository = null;
    private Statistics statistics = Statistics.getInstance();

    public StatisticsController(){
        getDealRepository();
    }

    private AnnouncementRepository getDealRepository() {
        if (dealRepository == null) {
            Repositories repositories = Repositories.getInstance();
            dealRepository = repositories.getDealRepository();
        }
        return dealRepository;
    }

    public ArrayList<Announcement> getDeals() {
        return dealRepository.getAllAnnouncementsSortedByDefualtCriteria();
    }

    public void calcSimpleRegressionArea(ArrayList<Announcement> deals) {
        statistics.setDeals(deals);
        statistics.calcSimpleRegressionArea();
    }
}

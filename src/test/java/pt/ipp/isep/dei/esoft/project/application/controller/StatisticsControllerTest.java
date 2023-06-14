package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Statistics;
import pt.ipp.isep.dei.esoft.project.dto.StatisticsDTO;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsControllerTest {

    StatisticsController statsController = new StatisticsController();

    ImportController importController = new ImportController();

    Repositories repos = Repositories.getInstance();

    AnnouncementRepository dealRepository = repos.getAnnouncementRepository();


    @BeforeEach
    void setUp() {
        if (dealRepository.getAllAnnouncementsSortedByDefaultCriteriaReverse().isEmpty()){
            importController.importData("legacyRealStateUSAMoodle_MATCP_MDISC.csv");
        }
    }

    @Test
    public void ensure_calcSimpleRegressionArea_calculatesPredictedInterval() {
        //Arrange
        ArrayList<Announcement> deals = dealRepository.getAllAnnouncementsSortedByDefaultCriteriaReverse();
        statsController.setConfidenceLevel(0.95);
        statsController.calcSimpleRegressionArea(deals);
        StatisticsDTO stats = statsController.getStats();
        double[][] interval = stats.getSimplePredictionIntervals();


        //Act
        double expectedLowerBoundFirstProperty = 40668.35428504599;
        double expectedUpperBoundFirstProperty = 244150.26498754427;
        double resultLowerBoundFirstProperty = interval[0][0];
        double resultUpperBoundFirstProperty = interval[0][1];

        //Assert
        assertEquals(expectedLowerBoundFirstProperty, resultLowerBoundFirstProperty);
        assertEquals(expectedUpperBoundFirstProperty, resultUpperBoundFirstProperty);
    }

    @Test
    public void ensure_calcSimpleRegressionDistance_calculatesPredictedInterval(){
        //Arrange
        ArrayList<Announcement> deals = dealRepository.getAllAnnouncementsSortedByDefaultCriteriaReverse();
        statsController.setConfidenceLevel(0.95);
        statsController.calcSimpleRegressionDistance(deals);
        StatisticsDTO stats = statsController.getStats();
        double[][] interval = stats.getSimplePredictionIntervals();


        //Act
        double expectedLowerBoundFirstProperty = 15856.86172869819;
        double expectedUpperBoundFirstProperty = 326615.72339186113;
        double resultLowerBoundFirstProperty = interval[0][0];
        double resultUpperBoundFirstProperty = interval[0][1];

        //Assert
        assertEquals(expectedLowerBoundFirstProperty, resultLowerBoundFirstProperty);
        assertEquals(expectedUpperBoundFirstProperty, resultUpperBoundFirstProperty);
    }

    @Test
    public void ensure_calcSimpleRegressionParkingSpaces_calculatesPredictedInterval(){
        //Arrange
        ArrayList<Announcement> deals = dealRepository.getAllAnnouncementsSortedByDefaultCriteriaReverse();
        statsController.setConfidenceLevel(0.95);
        statsController.calcSimpleRegressionParkingSpaces(deals);
        StatisticsDTO stats = statsController.getStats();
        double[][] interval = stats.getSimplePredictionIntervals();


        //Act
        double expectedLowerBoundFirstProperty = 84165.77567176575;
        double expectedUpperBoundFirstProperty = 306746.174165148;
        double resultLowerBoundFirstProperty = interval[0][0];
        double resultUpperBoundFirstProperty = interval[0][1];

        //Assert
        assertEquals(expectedLowerBoundFirstProperty, resultLowerBoundFirstProperty);
        assertEquals(expectedUpperBoundFirstProperty, resultUpperBoundFirstProperty);
    }

    @Test
    public void ensure_calcSimpleRegressionBedrooms_calculatesPredictedInterval(){
        //Arrange
        ArrayList<Announcement> deals = dealRepository.getAllAnnouncementsSortedByDefaultCriteriaReverse();
        statsController.setConfidenceLevel(0.95);
        statsController.calcSimpleRegressionBedrooms(deals);
        StatisticsDTO stats = statsController.getStats();
        double[][] interval = stats.getSimplePredictionIntervals();


        //Act
        double expectedLowerBoundFirstProperty = 9349.31795536817;
        double expectedUpperBoundFirstProperty = 315836.89794799103;
        double resultLowerBoundFirstProperty = interval[0][0];
        double resultUpperBoundFirstProperty = interval[0][1];

        //Assert
        assertEquals(expectedLowerBoundFirstProperty, resultLowerBoundFirstProperty);
        assertEquals(expectedUpperBoundFirstProperty, resultUpperBoundFirstProperty);
    }

    @Test
    public void ensure_calcSimpleRegressionBathrooms_calculatesPredictedInterval(){
        //Arrange
        ArrayList<Announcement> deals = dealRepository.getAllAnnouncementsSortedByDefaultCriteriaReverse();
        statsController.setConfidenceLevel(0.95);
        statsController.calcSimpleRegressionBathrooms(deals);
        StatisticsDTO stats = statsController.getStats();
        double[][] interval = stats.getSimplePredictionIntervals();


        //Act
        double expectedLowerBoundFirstProperty = -20189.71280160977;
        double expectedUpperBoundFirstProperty = 216885.2128490728;
        double resultLowerBoundFirstProperty = interval[0][0];
        double resultUpperBoundFirstProperty = interval[0][1];

        //Assert
        assertEquals(expectedLowerBoundFirstProperty, resultLowerBoundFirstProperty);
        assertEquals(expectedUpperBoundFirstProperty, resultUpperBoundFirstProperty);
    }

    @Test
    public void ensure_calcMultipleRegression_calculatesPredictedInterval(){
        //Arrange
        ArrayList<Announcement> deals = dealRepository.getAllAnnouncementsSortedByDefaultCriteriaReverse();
        statsController.setConfidenceLevel(0.95);
        statsController.calcMultipleRegression(deals);
        StatisticsDTO stats = statsController.getStats();


        //Act
        double expectedLowerBoundFirstProperty = 73606.48919102724;
        double expectedUpperBoundFirstProperty = 232499.25584401464;
        double resultLowerBoundFirstProperty = stats.getPredictionsLowerBound().get(0);
        double resultUpperBoundFirstProperty = stats.getPredictionsUpperBound().get(0);

        //Assert
        assertEquals(expectedLowerBoundFirstProperty, resultLowerBoundFirstProperty);
        assertEquals(expectedUpperBoundFirstProperty, resultUpperBoundFirstProperty);
    }


}
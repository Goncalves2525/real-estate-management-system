package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Statistics;
import pt.ipp.isep.dei.esoft.project.dto.StatisticsDTO;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

public class StatisticsController {
    private AnnouncementRepository dealRepository = null;
    private Statistics statistics = Statistics.getInstance();

    public StatisticsController() {
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

    public void calcSimpleRegressionDistance(ArrayList<Announcement> deals) {
        statistics.setDeals(deals);
        statistics.calcSimpleRegressionDistance();
    }

    public void calcSimpleRegressionParkingSpaces(ArrayList<Announcement> deals) {
        statistics.setDeals(deals);
        statistics.calcSimpleRegressionParkingSpaces();
    }

    public void calcSimpleRegressionBedrooms(ArrayList<Announcement> deals) {
        statistics.setDeals(deals);
        statistics.calcSimpleRegressionBedrooms();
    }

    public void calcSimpleRegressionBathrooms(ArrayList<Announcement> deals) {
        statistics.setDeals(deals);
        statistics.calcSimpleRegressionBathrooms();
    }

    public ArrayList<Double> getForecastedPrices() {
        return statistics.getForecastedPrices();
    }

    public double[] getCoefficients() {
        double[] coefficients = new double[3];
        coefficients[0] = statistics.getCorrelationCoefficient();
        coefficients[1] = statistics.getDeterminationCoefficient();
        coefficients[2] = statistics.getAdjustedDeterminationCoefficient();

        return coefficients;
    }

    public double[][] getConfidenceIntervals() {
        return statistics.getConfidenceIntervals();
    }

    public boolean[] getRejects() {
        return statistics.getRejects();
    }

    public double getAlfa() {
        return statistics.getAlfa();
    }

    public double getInterceptPValue() {
        return statistics.getInterceptPValue();
    }

    public double getSlopePValue() {
        return statistics.getSlopePValue();
    }

    public double getTotalSumOfSquares(){
        return statistics.getTotalSumOfSquares();
    }

    public double getRegressionSumOfSquares(){
        return statistics.getRegressionSumOfSquares();
    }
    public double getResidualSumOfSquares(){
        return statistics.getResidualSumOfSquares();
    }
    public double getPValue(){
        return statistics.getpValue();
    }

    public double getFValue(){
        return statistics.getfValue();
    }

    public int getTotalDegreesOfFreedom(){
        return statistics.getTotalDegreesOfFreedom();
    }
    public int getRegressionDegreesOfFreedom(){
        return statistics.getRegressionDegreesOfFreedom();
    }
    public int getResidualDegreesOfFreedom(){
        return statistics.getResidualDegreesOfFreedom();
    }

    public double getRegressionMeanSquare(){
        return statistics.getRegressionMeanSquare();
    }
    public double getResidualMeanSquare(){
        return statistics.getResidualMeanSquare();
    }

    public double getConfidenceLevel(){
        return statistics.getConfidenceLevel();
    }

    public void setConfidenceLevel(double confidenceLevel){
        statistics.setConfidenceLevel(confidenceLevel);
    }

    public StatisticsDTO getStats(){
        StatisticsDTO stats = new StatisticsDTO(statistics.getN(), statistics.getConfidenceLevel(), statistics.getIntercept(), statistics.getSlope());
        return stats;
    }
}

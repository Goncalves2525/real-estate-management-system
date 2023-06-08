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
        return dealRepository.getAllAnnouncementsSortedByDefaultCriteriaReverse();
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

    public void calcMultipleRegression(ArrayList<Announcement> deals) {
        statistics.setDeals(deals);
        statistics.calcMultipleRegression();
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

    public double getCriticalValue(){
        return statistics.getCriticalValue();
    }

    public boolean getIsSignificant(){
        return statistics.getIsSignificant();
    }

    public StatisticsDTO getStats(){
        StatisticsDTO statsDTO = new StatisticsDTO(statistics.getN(), statistics.getConfidenceLevel(), statistics.getIntercept(), statistics.getSlope());
        return statsDTO;
    }

    public double getInterceptTValue(){
        return statistics.getInterceptTValue();
    }

    public double getSlopeTValue(){
        return statistics.getSlopeTValue();
    }

    public void setIsMultipleRegression(boolean isMultipleRegression){
        statistics.setIsMultipleRegression(isMultipleRegression);
    }

    public boolean getIsMultipleRegression(){
        return statistics.getIsMultipleRegression();
    }

    public double[] getCoefficientLowerBounds(){
        return statistics.getCoefficientLowerBounds();
    }

    public double[] getCoefficientUpperBounds(){
        return statistics.getCoefficientUpperBounds();
    }

    public double getPredictionLowerBound(){
        return statistics.getPredictionLowerBound();
    }

    public double getPredictionUpperBound(){
        return statistics.getPredictionUpperBound();
    }

    public double[] getTValues(){
        return statistics.getTValues();
    }
    public double[] getPValues(){
        return statistics.getPValues();
    }

    public double[] getStandardErrors(){
        return statistics.getStandardErrors();
    }

    public double getMeanSquareResidual(){
        return statistics.getMeanSquareResidual();
    }

    public double getMeanSquareRegression(){
        return statistics.getMeanSquareRegression();
    }

    public double getInterceptCriticalValue(){
        return statistics.getInterceptCriticalValue();
    }

    public double getSlopeCriticalValue(){
        return statistics.getSlopeCriticalValue();
    }

}

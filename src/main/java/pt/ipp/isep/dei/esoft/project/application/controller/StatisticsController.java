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

    public StatisticsDTO getStats() {
        StatisticsDTO statsDTO = new StatisticsDTO(
                statistics.getN(),
                statistics.getConfidenceLevel(),
                statistics.getIntercept(),
                statistics.getSlope(),
                statistics.getIsMultipleRegression(),
                statistics.getDeals(),
                statistics.getForecastedPrices(),
                statistics.getInterceptPValue(),
                statistics.getSlopePValue(),
                statistics.getCorrelationCoefficient(),
                statistics.getDeterminationCoefficient(),
                statistics.getAdjustedDeterminationCoefficient(),
                statistics.getSlopeStandardError(),
                statistics.getInterceptStandardError(),
                statistics.getAlfa(),
                statistics.getConfidenceIntervals(),
                statistics.getRejects(),
                statistics.getTotalSumOfSquares(),
                statistics.getResidualSumOfSquares(),
                statistics.getRegressionSumOfSquares(),
                statistics.getTotalDegreesOfFreedom(),
                statistics.getRegressionMeanSquare(),
                statistics.getResidualMeanSquare(),
                statistics.getRegressionDegreesOfFreedom(),
                statistics.getResidualDegreesOfFreedom(),
                statistics.getfValue(),
                statistics.getpValue(),
                statistics.getCriticalValue(),
                statistics.getIsSignificant(),
                statistics.getInterceptTValue(),
                statistics.getSlopeTValue(),
                statistics.getCoefficientLowerBounds(),
                statistics.getCoefficientUpperBounds(),
                statistics.getPredictionLowerBound(),
                statistics.getPredictionUpperBound(),
                statistics.getStandardErrors(),
                statistics.getTValues(),
                statistics.getPValues(),
                statistics.getMeanSquareRegression(),
                statistics.getMeanSquareResidual(),
                statistics.getInterceptCriticalValue(),
                statistics.getSlopeCriticalValue(),
                statistics.getExplainedSumOfSquares(),
                statistics.getDegreesOfFreedomRSS(),
                statistics.getDegreesOfFreedomTSS(),
                statistics.getMeanSquaredError(),
                statistics.getMultipleCriticalValue(),
                statistics.getCovarianceMatrix(),
                statistics.getEstimatedCoefficient(),
                statistics.getPredictionsLowerBound(),
                statistics.getPredictionsUpperBound(),
                statistics.getStandardError(),
                statistics.getTestStatistics(),
                statistics.getMultipleTestCriticalValue(),
                statistics.getSimplePredictionIntervals()
        );
        return statsDTO;
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
    public double getPValue() {
        return statistics.getpValue();
    }
    public double getFValue() {
        return statistics.getfValue();
    }
    public double getConfidenceLevel() {
        return statistics.getConfidenceLevel();
    }
    public void setConfidenceLevel(double confidenceLevel) {
        statistics.setConfidenceLevel(confidenceLevel);
    }
    public boolean getIsSignificant() {
        return statistics.getIsSignificant();
    }
    public void setIsMultipleRegression(boolean isMultipleRegression) {
        statistics.setIsMultipleRegression(isMultipleRegression);
    }
    public boolean getIsMultipleRegression() {
        return statistics.getIsMultipleRegression();
    }

    public double getDeterminationCoefficient() {
        return statistics.getDeterminationCoefficient();
    }

    public double getAdjustedDeterminationCoefficient() {
        return statistics.getAdjustedDeterminationCoefficient();
    }

}

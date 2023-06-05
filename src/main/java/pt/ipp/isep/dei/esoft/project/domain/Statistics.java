package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;

public class Statistics {
    private static final Statistics instance = new Statistics();
    private ArrayList<Announcement> deals;
    private ArrayList<Double> forecastedPrices = new ArrayList<>();
    private double slope;
    private double intercept;
    private double correlationCoefficient;
    private double determinationCoefficient;
    private double adjustedDeterminationCoefficient;
    private double standardError;
    private double confidenceLevel;
    private double criticalValue;
    private double[] confidenceInterval;
    private double significance;
    private double interceptStandardError;

    private Statistics() {
    }

    public static Statistics getInstance() {
        return instance;
    }

    public void calcSimpleRegressionArea() {
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            regression.addData(deal.getProperty().getArea(), deal.getProperty().getPrice());
        }

        for(Announcement deal : deals){
            forecastedPrices.add(regression.predict(deal.getProperty().getArea()));
        }



    }

    public void calcSimpleRegressionDistance(double distanceFromCenter) {
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            regression.addData(deal.getProperty().getDistanceFromCenter(), deal.getProperty().getPrice());
        }

    }

    public void calcSimpleRegressionBedrooms(int numberOfBedrooms) {
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House) {
                regression.addData(((House) deal.getProperty()).getNumberOfBedrooms(), deal.getProperty().getPrice());
            } else if (deal.getProperty() instanceof Apartment) {
                regression.addData(((Apartment) deal.getProperty()).getNumberOfBedrooms(), deal.getProperty().getPrice());
            }

        }
    }

    public void calcSimpleRegressionBathrooms(int numberOfBathrooms) {
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House) {
                regression.addData(((House) deal.getProperty()).getNumberOfBathrooms(), deal.getProperty().getPrice());
            } else if (deal.getProperty() instanceof Apartment) {
                regression.addData(((Apartment) deal.getProperty()).getNumberOfBathrooms(), deal.getProperty().getPrice());
            }

        }
    }

    public void calcSimpleRegressionParkingSpaces(int numberOfParkingSpaces) {
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House) {
                regression.addData(((House) deal.getProperty()).getNumberOfParkingSpaces(), deal.getProperty().getPrice());
            } else if (deal.getProperty() instanceof Apartment) {
                regression.addData(((Apartment) deal.getProperty()).getNumberOfParkingSpaces(), deal.getProperty().getPrice());
            }
        }
    }

    public double calcMultipleRegression(double area, double distanceFromCenter, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces) {
        return -1;
    }

    public void setDeals(ArrayList<Announcement> deals) {
        this.deals = deals;
    }

    public ArrayList<Announcement> getDeals() {
        return deals;
    }

    public ArrayList<Double> getForecastedPrices() {
        return forecastedPrices;
    }

    public double getSlope() {
        return slope;
    }

    public double getIntercept() {
        return intercept;
    }

    public double getCorrelationCoefficient() {
        return correlationCoefficient;
    }

    public double getDeterminationCoefficient() {
        return determinationCoefficient;
    }

    public double getAdjustedDeterminationCoefficient() {
        return adjustedDeterminationCoefficient;
    }

    public double getStandardError() {
        return standardError;
    }

    public double getConfidenceLevel() {
        return confidenceLevel;
    }

    public double getCriticalValue() {
        return criticalValue;
    }

    public double[] getConfidenceInterval() {
        return confidenceInterval;
    }

    public double getSignificance() {
        return significance;
    }

    public double getInterceptStandardError() {
        return interceptStandardError;
    }
}

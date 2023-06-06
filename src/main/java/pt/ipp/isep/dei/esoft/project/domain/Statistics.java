package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;

public class Statistics {
    private static final Statistics instance = new Statistics();
    private ArrayList<Announcement> deals;
    private ArrayList<Double> forecastedPrices = new ArrayList<>();
    private double intercept;
    private double interceptPValue;
    private double slope;
    private double slopePValue;
    private double correlationCoefficient;
    private double determinationCoefficient;
    private double adjustedDeterminationCoefficient;
    private double slopeStandardError;
    private double interceptStandardError;
    private double alfa;
    private int n;
    private double confidenceLevel = 0.95;
    private double[][] confidenceIntervals = new double[3][2];
    private boolean[] rejects = new boolean[2];
    private double totalSumOfSquares;
    private double residualSumOfSquares;
    private double regressionSumOfSquares;
    private int totalDegreesOfFreedom;
    private double regressionMeanSquare;
    private double residualMeanSquare;
    private int regressionDegreesOfFreedom;
    private int residualDegreesOfFreedom;
    private double fValue;
    private double pValue;
    private SimpleRegression regression;

    private Statistics() {
    }

    public static Statistics getInstance() {
        return instance;
    }

    public void calcSimpleRegressionArea() {
        SimpleRegression regression = new SimpleRegression();
        //Forecast Prices
        for (Announcement deal : deals) {
            regression.addData(deal.getProperty().getArea(), deal.getProperty().getPrice());
        }

        for (Announcement deal : deals) {
            forecastedPrices.add(regression.predict(deal.getProperty().getArea()));
        }

        //R, R^2 and adjusted R^2
        correlationCoefficient = regression.getR();
        determinationCoefficient = regression.getRSquare();
        adjustedDeterminationCoefficient = 1 - (1 - determinationCoefficient) * (deals.size() - 1) / (deals.size() - 2 - 1);

        //Confidence Interval
        slopeStandardError = regression.getSlopeStdErr();
        slope = regression.getSlope();
        alfa = 1 - confidenceLevel;
        n = deals.size();
        residualDegreesOfFreedom = n - 2;
        TDistribution tDistribution = new TDistribution(residualDegreesOfFreedom);

        double meanConfidenceError = slopeStandardError * tDistribution.inverseCumulativeProbability(1 - alfa / 2);
        double meanLowerBound = regression.predict(deals.get(0).getProperty().getArea()) - (meanConfidenceError / 2);
        double meanUpperBound = regression.predict(deals.get(0).getProperty().getArea()) + (meanConfidenceError / 2);
        confidenceIntervals[0][0] = meanLowerBound;
        confidenceIntervals[0][1] = meanUpperBound;

        interceptStandardError = regression.getInterceptStdErr();
        double interceptLowerBound = regression.getIntercept() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        double interceptUpperBound = regression.getIntercept() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        confidenceIntervals[1][0] = interceptLowerBound;
        confidenceIntervals[1][1] = interceptUpperBound;

        double slopeLowerBound = regression.getSlope() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        double slopeUpperBound = regression.getSlope() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        confidenceIntervals[2][0] = slopeLowerBound;
        confidenceIntervals[2][1] = slopeUpperBound;


        //Hyphotesis Test
        double a0 = 0;
        double b0 = 0;
        boolean interceptReject;
        boolean slopeReject;
        intercept = regression.getIntercept();
        double interceptTValue = (intercept - a0) / interceptStandardError;
        interceptPValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(interceptTValue)));

        if (interceptPValue < alfa) {
            interceptReject = true;
        } else {
            interceptReject = false;
        }

        slope = regression.getSlope();
        double slopeTValue = (slope - b0) / slopeStandardError;
        slopePValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(slopeTValue)));

        if (slopePValue < alfa) {
            slopeReject = true;
        } else {
            slopeReject = false;
        }

        rejects[0] = interceptReject;
        rejects[1] = slopeReject;

        //Anova
        totalSumOfSquares = regression.getTotalSumSquares();
        regressionSumOfSquares = regression.getRegressionSumSquares();
        residualSumOfSquares = regression.getSumSquaredErrors();

        totalDegreesOfFreedom = n - 1;
        regressionDegreesOfFreedom = 1;
        residualDegreesOfFreedom = n - 2;

        regressionMeanSquare = regressionSumOfSquares / regressionDegreesOfFreedom;
        residualMeanSquare = residualSumOfSquares / residualDegreesOfFreedom;

        fValue = regressionMeanSquare / residualMeanSquare;
        FDistribution fDistribution = new FDistribution(regressionDegreesOfFreedom, residualDegreesOfFreedom);
        pValue = 1 - fDistribution.cumulativeProbability(fValue);

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


    public double getConfidenceLevel() {
        return confidenceLevel;
    }

    public double[][] getConfidenceIntervals() {
        return confidenceIntervals;
    }


    public double getInterceptStandardError() {
        return interceptStandardError;
    }

    public double getSlope() {
        return slope;
    }

    public double getSlopeStandardError() {
        return slopeStandardError;
    }

    public double getAlfa() {
        return alfa;
    }

    public int getN() {
        return n;
    }

    public boolean[] getRejects() {
        return rejects;
    }


    public double getInterceptPValue() {
        return interceptPValue;
    }

    public double getSlopePValue() {
        return slopePValue;
    }

    public double getTotalSumOfSquares() {
        return totalSumOfSquares;
    }

    public double getResidualSumOfSquares() {
        return residualSumOfSquares;
    }

    public double getRegressionSumOfSquares() {
        return regressionSumOfSquares;
    }

    public int getTotalDegreesOfFreedom() {
        return totalDegreesOfFreedom;
    }

    public int getRegressionDegreesOfFreedom() {
        return regressionDegreesOfFreedom;
    }

    public int getResidualDegreesOfFreedom() {
        return residualDegreesOfFreedom;
    }

    public double getfValue() {
        return fValue;
    }

    public double getpValue() {
        return pValue;
    }

    public double getRegressionMeanSquare() {
        return regressionMeanSquare;
    }

    public double getResidualMeanSquare() {
        return residualMeanSquare;
    }
}

package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.io.Serializable;
import java.util.ArrayList;

public class Statistics implements Serializable {
    private static final Statistics instance = new Statistics();
    private boolean isMultipleRegression = false;
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
    private double confidenceLevel;
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
    private double criticalValue;
    private boolean isSignificant;
    private double interceptTValue;
    private double slopeTValue;
    private double[] coefficientLowerBounds = new double[6];
    private double[] coefficientUpperBounds = new double[6];
    private double predictionLowerBound;
    private double predictionUpperBound;
    private double[] standardErrors;
    private double[] tValues;
    private double[] pValues;
    private double meanSquareRegression;
    private double meanSquareResidual;
    private double interceptCriticalValue;
    private double slopeCriticalValue;
    private double explainedSumOfSquares;
    private double degreesOfFreedomRSS;
    private double degreesOfFreedomTSS;
    private double meanSquaredError;
    private double multipleCriticalValue;
    private double[][] covarianceMatrix;
    private double[] estimatedCoefficient;
    private ArrayList<Double> predictionsLowerBound;
    private ArrayList<Double> predictionsUpperBound;
    private double standardError;
    private double[] testStatistics;
    private double multipleTestCriticalValue;
    private double[][] simplePredictionIntervals = new double[499][2];

    private Statistics() {
        alfa = 1 - confidenceLevel;
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

        if (!forecastedPrices.isEmpty()) {
            forecastedPrices.clear();
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

        interceptStandardError = regression.getInterceptStdErr();
        double interceptLowerBound = regression.getIntercept() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        double interceptUpperBound = regression.getIntercept() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        confidenceIntervals[1][0] = interceptLowerBound;
        confidenceIntervals[1][1] = interceptUpperBound;

        double slopeLowerBound = regression.getSlope() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        double slopeUpperBound = regression.getSlope() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        confidenceIntervals[2][0] = slopeLowerBound;
        confidenceIntervals[2][1] = slopeUpperBound;



        double residualsSumSquared = regression.getSumSquaredErrors();
        double[] independentVariables = new double[deals.size()];
        double[] dependentVariables = new double[deals.size()];
        double lowerBound;
        double upperBound;
        double xSum = 0.0;

        for (int i = 0; i < deals.size(); i++) {
            Announcement deal = deals.get(i);
            double parkingSpaces = 0.0; // Default value
            parkingSpaces = deal.getProperty().getArea();

            // Assign the number of parking spaces as the independent variable
            independentVariables[i] = parkingSpaces;

            // Assign the price as the dependent variable
            dependentVariables[i] = deal.getProperty().getPrice();
        }

        for (int i = 0; i < independentVariables.length; i++) {
            double xValue = independentVariables[i];
            double prediction = regression.predict(xValue);
            n = independentVariables.length;
            xSum = 0.0;
            residualsSumSquared = 0.0;
            double x;
            double y;

            for (int j = 0; j < n; j++) {
                x = independentVariables[j];
                y = dependentVariables[j];
                xSum += x;
                double yHat = regression.predict(x);
                residualsSumSquared += Math.pow(y - yHat, 2);
            }

            int degreesOfFreedom = n - 2;
            tDistribution = new TDistribution(degreesOfFreedom);
            double tCritical = tDistribution.inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2);
            double xMean = xSum / n;
            double sxx = calculateSumOfSquares(independentVariables, xMean);
            double s = Math.sqrt(1.0 / (n - 2) * residualsSumSquared);
            double standardError = s * Math.sqrt(1 + (1.0 / n) + Math.pow(xValue - xMean, 2) / sxx);
            lowerBound = prediction - tCritical * standardError;
            upperBound = prediction + tCritical * standardError;

            simplePredictionIntervals[i][0] = lowerBound;
            simplePredictionIntervals[i][1] = upperBound;
        }


        //Hypothesis Test
        double a0 = 0;
        double b0 = 0;
        boolean interceptReject;
        boolean slopeReject;
        intercept = regression.getIntercept();
        interceptTValue = (intercept - a0) / interceptStandardError;
        interceptPValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(interceptTValue)));
        interceptCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if (Math.abs(interceptTValue) > interceptCriticalValue) {
            interceptReject = true;
        } else {
            interceptReject = false;
        }

        slope = regression.getSlope();
        slopeTValue = (slope - b0) / slopeStandardError;
        slopePValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(slopeTValue)));
        slopeCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if (Math.abs(slopeTValue) > slopeCriticalValue) {
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
        criticalValue = fDistribution.inverseCumulativeProbability(1 - alfa);
        isSignificant = fValue > criticalValue;
    }

    public void calcSimpleRegressionDistance() {
        SimpleRegression regression = new SimpleRegression();
        //Forecast Prices
        for (Announcement deal : deals) {
            regression.addData(deal.getProperty().getDistanceFromCenter(), deal.getProperty().getPrice());
        }
        if (!forecastedPrices.isEmpty()) {
            forecastedPrices.clear();
        }
        for (Announcement deal : deals) {
            forecastedPrices.add(regression.predict(deal.getProperty().getDistanceFromCenter()));
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

        interceptStandardError = regression.getInterceptStdErr();
        double interceptLowerBound = regression.getIntercept() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        double interceptUpperBound = regression.getIntercept() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        confidenceIntervals[1][0] = interceptLowerBound;
        confidenceIntervals[1][1] = interceptUpperBound;

        double slopeLowerBound = regression.getSlope() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        double slopeUpperBound = regression.getSlope() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        confidenceIntervals[2][0] = slopeLowerBound;
        confidenceIntervals[2][1] = slopeUpperBound;




        double residualsSumSquared = regression.getSumSquaredErrors();
        double[] independentVariables = new double[deals.size()];
        double[] dependentVariables = new double[deals.size()];
        double lowerBound;
        double upperBound;
        double xSum = 0.0;

        for (int i = 0; i < deals.size(); i++) {
            Announcement deal = deals.get(i);
            double parkingSpaces = 0.0; // Default value
            parkingSpaces = deal.getProperty().getDistanceFromCenter();

            // Assign the number of parking spaces as the independent variable
            independentVariables[i] = parkingSpaces;

            // Assign the price as the dependent variable
            dependentVariables[i] = deal.getProperty().getPrice();
        }

        for (int i = 0; i < independentVariables.length; i++) {
            double xValue = independentVariables[i];
            double prediction = regression.predict(xValue);
            n = independentVariables.length;
            xSum = 0.0;
            residualsSumSquared = 0.0;
            double x;
            double y;

            for (int j = 0; j < n; j++) {
                x = independentVariables[j];
                y = dependentVariables[j];
                xSum += x;
                double yHat = regression.predict(x);
                residualsSumSquared += Math.pow(y - yHat, 2);
            }

            int degreesOfFreedom = n - 2;
            tDistribution = new TDistribution(degreesOfFreedom);
            double tCritical = tDistribution.inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2);
            double xMean = xSum / n;
            double sxx = calculateSumOfSquares(independentVariables, xMean);
            double s = Math.sqrt(1.0 / (n - 2) * residualsSumSquared);
            double standardError = s * Math.sqrt(1 + (1.0 / n) + Math.pow(xValue - xMean, 2) / sxx);
            lowerBound = prediction - tCritical * standardError;
            upperBound = prediction + tCritical * standardError;

            simplePredictionIntervals[i][0] = lowerBound;
            simplePredictionIntervals[i][1] = upperBound;
        }


        //Hyphotesis Test
        double a0 = 0;
        double b0 = 0;
        boolean interceptReject;
        boolean slopeReject;
        intercept = regression.getIntercept();
        double interceptTValue = (intercept - a0) / interceptStandardError;
        interceptPValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(interceptTValue)));
        interceptCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if (Math.abs(interceptTValue) > interceptCriticalValue) {
            interceptReject = true;
        } else {
            interceptReject = false;
        }

        slope = regression.getSlope();
        double slopeTValue = (slope - b0) / slopeStandardError;
        slopePValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(slopeTValue)));
        slopeCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if (Math.abs(slopeTValue) > slopeCriticalValue) {
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
        criticalValue = fDistribution.inverseCumulativeProbability(1 - alfa);
        isSignificant = fValue > criticalValue;
    }

    public void calcSimpleRegressionBedrooms() {
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House) {
                regression.addData(((House) deal.getProperty()).getNumberOfBedrooms(), deal.getProperty().getPrice());
            } else if (deal.getProperty() instanceof Apartment) {
                regression.addData(((Apartment) deal.getProperty()).getNumberOfBedrooms(), deal.getProperty().getPrice());
            }

        }
        if (!forecastedPrices.isEmpty()) {
            forecastedPrices.clear();
        }
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House) {
                forecastedPrices.add(regression.predict(((House) deal.getProperty()).getNumberOfBedrooms()));
            } else if (deal.getProperty() instanceof Apartment) {
                forecastedPrices.add(regression.predict(((Apartment) deal.getProperty()).getNumberOfBedrooms()));
            }

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

        interceptStandardError = regression.getInterceptStdErr();
        double interceptLowerBound = regression.getIntercept() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        double interceptUpperBound = regression.getIntercept() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        confidenceIntervals[1][0] = interceptLowerBound;
        confidenceIntervals[1][1] = interceptUpperBound;

        double slopeLowerBound = regression.getSlope() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        double slopeUpperBound = regression.getSlope() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        confidenceIntervals[2][0] = slopeLowerBound;
        confidenceIntervals[2][1] = slopeUpperBound;




        double residualsSumSquared = regression.getSumSquaredErrors();
        double[] independentVariables = new double[deals.size()];
        double[] dependentVariables = new double[deals.size()];
        double lowerBound;
        double upperBound;
        double xSum = 0.0;

        for (int i = 0; i < deals.size(); i++) {
            Announcement deal = deals.get(i);
            double parkingSpaces = 0.0; // Default value

            // Check if the property is an instance of House or Apartment
            if (deal.getProperty() instanceof House) {
                House house = (House) deal.getProperty();
                // Assign the number of parking spaces for a House
                parkingSpaces = house.getNumberOfBedrooms();
            } else if (deal.getProperty() instanceof Apartment) {
                Apartment apartment = (Apartment) deal.getProperty();
                // Assign the number of parking spaces for an Apartment
                parkingSpaces = apartment.getNumberOfBedrooms();
            }

            // Assign the number of parking spaces as the independent variable
            independentVariables[i] = parkingSpaces;

            // Assign the price as the dependent variable
            dependentVariables[i] = deal.getProperty().getPrice();
        }

        for (int i = 0; i < independentVariables.length; i++) {
            double xValue = independentVariables[i];
            double prediction = regression.predict(xValue);
            n = independentVariables.length;
            xSum = 0.0;
            residualsSumSquared = 0.0;
            double x;
            double y;

            for (int j = 0; j < n; j++) {
                x = independentVariables[j];
                y = dependentVariables[j];
                xSum += x;
                double yHat = regression.predict(x);
                residualsSumSquared += Math.pow(y - yHat, 2);
            }

            int degreesOfFreedom = n - 2;
            tDistribution = new TDistribution(degreesOfFreedom);
            double tCritical = tDistribution.inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2);
            double xMean = xSum / n;
            double sxx = calculateSumOfSquares(independentVariables, xMean);
            double s = Math.sqrt(1.0 / (n - 2) * residualsSumSquared);
            double standardError = s * Math.sqrt(1 + (1.0 / n) + Math.pow(xValue - xMean, 2) / sxx);
            lowerBound = prediction - tCritical * standardError;
            upperBound = prediction + tCritical * standardError;

            simplePredictionIntervals[i][0] = lowerBound;
            simplePredictionIntervals[i][1] = upperBound;
        }


        //Hyphotesis Test
        double a0 = 0;
        double b0 = 0;
        boolean interceptReject;
        boolean slopeReject;
        intercept = regression.getIntercept();
        double interceptTValue = (intercept - a0) / interceptStandardError;
        interceptPValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(interceptTValue)));
        interceptCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if (Math.abs(interceptTValue) > interceptCriticalValue) {
            interceptReject = true;
        } else {
            interceptReject = false;
        }

        slope = regression.getSlope();
        double slopeTValue = (slope - b0) / slopeStandardError;
        slopePValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(slopeTValue)));
        slopeCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if (Math.abs(slopeTValue) > slopeCriticalValue) {
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
        criticalValue = fDistribution.inverseCumulativeProbability(1 - alfa);
        isSignificant = fValue > criticalValue;
    }

    public void calcSimpleRegressionBathrooms() {
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House) {
                regression.addData(((House) deal.getProperty()).getNumberOfBathrooms(), deal.getProperty().getPrice());
            } else if (deal.getProperty() instanceof Apartment) {
                regression.addData(((Apartment) deal.getProperty()).getNumberOfBathrooms(), deal.getProperty().getPrice());
            }
        }
        if (!forecastedPrices.isEmpty()) {
            forecastedPrices.clear();
        }
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House) {
                forecastedPrices.add(regression.predict(((House) deal.getProperty()).getNumberOfBathrooms()));
            } else if (deal.getProperty() instanceof Apartment) {
                forecastedPrices.add(regression.predict(((Apartment) deal.getProperty()).getNumberOfBathrooms()));
            }
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

        interceptStandardError = regression.getInterceptStdErr();
        double interceptLowerBound = regression.getIntercept() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        double interceptUpperBound = regression.getIntercept() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        confidenceIntervals[1][0] = interceptLowerBound;
        confidenceIntervals[1][1] = interceptUpperBound;

        double slopeLowerBound = regression.getSlope() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        double slopeUpperBound = regression.getSlope() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        confidenceIntervals[2][0] = slopeLowerBound;
        confidenceIntervals[2][1] = slopeUpperBound;


        double residualsSumSquared = regression.getSumSquaredErrors();
        double[] independentVariables = new double[deals.size()];
        double[] dependentVariables = new double[deals.size()];
        double lowerBound;
        double upperBound;
        double xSum = 0.0;

        for (int i = 0; i < deals.size(); i++) {
            Announcement deal = deals.get(i);
            double parkingSpaces = 0.0; // Default value

            // Check if the property is an instance of House or Apartment
            if (deal.getProperty() instanceof House) {
                House house = (House) deal.getProperty();
                // Assign the number of parking spaces for a House
                parkingSpaces = house.getNumberOfBathrooms();
            } else if (deal.getProperty() instanceof Apartment) {
                Apartment apartment = (Apartment) deal.getProperty();
                // Assign the number of parking spaces for an Apartment
                parkingSpaces = apartment.getNumberOfBathrooms();
            }

            // Assign the number of parking spaces as the independent variable
            independentVariables[i] = parkingSpaces;

            // Assign the price as the dependent variable
            dependentVariables[i] = deal.getProperty().getPrice();
        }

        for (int i = 0; i < independentVariables.length; i++) {
            double xValue = independentVariables[i];
            double prediction = regression.predict(xValue);
            n = independentVariables.length;
            xSum = 0.0;
            residualsSumSquared = 0.0;
            double x;
            double y;

            for (int j = 0; j < n; j++) {
                x = independentVariables[j];
                y = dependentVariables[j];
                xSum += x;
                double yHat = regression.predict(x);
                residualsSumSquared += Math.pow(y - yHat, 2);
            }

            int degreesOfFreedom = n - 2;
            tDistribution = new TDistribution(degreesOfFreedom);
            double tCritical = tDistribution.inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2);
            double xMean = xSum / n;
            double sxx = calculateSumOfSquares(independentVariables, xMean);
            double s = Math.sqrt(1.0 / (n - 2) * residualsSumSquared);
            double standardError = s * Math.sqrt(1 + (1.0 / n) + Math.pow(xValue - xMean, 2) / sxx);
            lowerBound = prediction - tCritical * standardError;
            upperBound = prediction + tCritical * standardError;

            simplePredictionIntervals[i][0] = lowerBound;
            simplePredictionIntervals[i][1] = upperBound;
        }

        //Hyphotesis Test
        double a0 = 0;
        double b0 = 0;
        boolean interceptReject;
        boolean slopeReject;
        intercept = regression.getIntercept();
        double interceptTValue = (intercept - a0) / interceptStandardError;
        interceptPValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(interceptTValue)));
        interceptCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if (Math.abs(interceptTValue) > interceptCriticalValue) {
            interceptReject = true;
        } else {
            interceptReject = false;
        }

        slope = regression.getSlope();
        double slopeTValue = (slope - b0) / slopeStandardError;
        slopePValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(slopeTValue)));
        slopeCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if (Math.abs(slopeTValue) > slopeCriticalValue) {
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
        criticalValue = fDistribution.inverseCumulativeProbability(1 - alfa);
        isSignificant = fValue > criticalValue;
    }

    public void calcSimpleRegressionParkingSpaces() {
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House) {
                regression.addData(((House) deal.getProperty()).getNumberOfParkingSpaces(), deal.getProperty().getPrice());
            } else if (deal.getProperty() instanceof Apartment) {
                regression.addData(((Apartment) deal.getProperty()).getNumberOfParkingSpaces(), deal.getProperty().getPrice());
            }
        }
        if (!forecastedPrices.isEmpty()) {
            forecastedPrices.clear();
        }
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House) {
                forecastedPrices.add(regression.predict(((House) deal.getProperty()).getNumberOfParkingSpaces()));
            } else if (deal.getProperty() instanceof Apartment) {
                forecastedPrices.add(regression.predict(((Apartment) deal.getProperty()).getNumberOfParkingSpaces()));
            }
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

        interceptStandardError = regression.getInterceptStdErr();
        double interceptLowerBound = regression.getIntercept() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        double interceptUpperBound = regression.getIntercept() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * interceptStandardError;
        confidenceIntervals[1][0] = interceptLowerBound;
        confidenceIntervals[1][1] = interceptUpperBound;

        double slopeLowerBound = regression.getSlope() - tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        double slopeUpperBound = regression.getSlope() + tDistribution.inverseCumulativeProbability(1 - alfa / 2) * slopeStandardError;
        confidenceIntervals[2][0] = slopeLowerBound;
        confidenceIntervals[2][1] = slopeUpperBound;


        double residualsSumSquared = regression.getSumSquaredErrors();
        double[] independentVariables = new double[deals.size()];
        double[] dependentVariables = new double[deals.size()];
        double lowerBound;
        double upperBound;
        double xSum = 0.0;

        for (int i = 0; i < deals.size(); i++) {
            Announcement deal = deals.get(i);
            double parkingSpaces = 0.0; // Default value

            // Check if the property is an instance of House or Apartment
            if (deal.getProperty() instanceof House) {
                House house = (House) deal.getProperty();
                // Assign the number of parking spaces for a House
                parkingSpaces = house.getNumberOfParkingSpaces();
            } else if (deal.getProperty() instanceof Apartment) {
                Apartment apartment = (Apartment) deal.getProperty();
                // Assign the number of parking spaces for an Apartment
                parkingSpaces = apartment.getNumberOfParkingSpaces();
            }

            // Assign the number of parking spaces as the independent variable
            independentVariables[i] = parkingSpaces;

            // Assign the price as the dependent variable
            dependentVariables[i] = deal.getProperty().getPrice();
        }

        for (int i = 0; i < independentVariables.length; i++) {
            double xValue = independentVariables[i];
            double prediction = regression.predict(xValue);
            n = independentVariables.length;
            xSum = 0.0;
            residualsSumSquared = 0.0;
            double x;
            double y;

            for (int j = 0; j < n; j++) {
                x = independentVariables[j];
                y = dependentVariables[j];
                xSum += x;
                double yHat = regression.predict(x);
                residualsSumSquared += Math.pow(y - yHat, 2);
            }

            int degreesOfFreedom = n - 2;
            tDistribution = new TDistribution(degreesOfFreedom);
            double tCritical = tDistribution.inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2);
            double xMean = xSum / n;
            double sxx = calculateSumOfSquares(independentVariables, xMean);
            double s = Math.sqrt(1.0 / (n - 2) * residualsSumSquared);
            double standardError = s * Math.sqrt(1 + (1.0 / n) + Math.pow(xValue - xMean, 2) / sxx);
            lowerBound = prediction - tCritical * standardError;
            upperBound = prediction + tCritical * standardError;

            simplePredictionIntervals[i][0] = lowerBound;
            simplePredictionIntervals[i][1] = upperBound;

        }


        //Hyphotesis Test
        double a0 = 0;
        double b0 = 0;
        boolean interceptReject;
        boolean slopeReject;
        intercept = regression.getIntercept();
        double interceptTValue = (intercept - a0) / interceptStandardError;
        interceptPValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(interceptTValue)));
        interceptCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if (Math.abs(interceptTValue) > interceptCriticalValue) {
            interceptReject = true;
        } else {
            interceptReject = false;
        }

        slope = regression.getSlope();
        double slopeTValue = (slope - b0) / slopeStandardError;
        slopePValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(slopeTValue)));
        slopeCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if (Math.abs(slopeTValue) > slopeCriticalValue) {
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
        criticalValue = fDistribution.inverseCumulativeProbability(1 - alfa);
        isSignificant = fValue > criticalValue;
    }

    private double calculateSumOfSquares(double[] independentVariables, double xMean) {
        double sum = 0.0;
        for (int p = 0; p < n; p++) {
            sum += Math.pow(independentVariables[p] - xMean, 2);
        }
        return sum;
    }


    public void calcMultipleRegression() {

        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();

        n = deals.size();
        double[][] independentVariables = new double[deals.size()][5];
        double[] dependentVariable = new double[deals.size()];


        for (int i = 0; i < deals.size(); i++) {
            Announcement deal = deals.get(i);
            independentVariables[i][0] = deal.getProperty().getArea();
            independentVariables[i][1] = deal.getProperty().getDistanceFromCenter();
            if (deal.getProperty() instanceof House) {
                independentVariables[i][2] = ((House) deal.getProperty()).getNumberOfBedrooms();
                independentVariables[i][3] = ((House) deal.getProperty()).getNumberOfBathrooms();
                independentVariables[i][4] = ((House) deal.getProperty()).getNumberOfParkingSpaces();
            } else if (deal.getProperty() instanceof Apartment) {
                independentVariables[i][2] = ((Apartment) deal.getProperty()).getNumberOfBedrooms();
                independentVariables[i][3] = ((Apartment) deal.getProperty()).getNumberOfBathrooms();
                independentVariables[i][4] = ((Apartment) deal.getProperty()).getNumberOfParkingSpaces();
            }
            dependentVariable[i] = deal.getProperty().getPrice();
        }

        //show independent variables
        for (int i = 0; i < independentVariables.length; i++) {
            for (int j = 0; j < independentVariables[i].length; j++) {
                System.out.print(independentVariables[i][j] + " ");
            }
            System.out.println();
        }


        regression.newSampleData(dependentVariable, independentVariables);
        System.out.println(regression.calculateRSquared());


        // General Data
        double[] coefficients = regression.estimateRegressionParameters();
        determinationCoefficient = regression.calculateRSquared();
        adjustedDeterminationCoefficient = regression.calculateAdjustedRSquared();
        standardErrors = regression.estimateRegressionParametersStandardErrors();
        estimatedCoefficient = regression.estimateRegressionParameters();
        covarianceMatrix = regression.estimateRegressionParametersVariance();
        alfa = 1 - confidenceLevel;
        standardError = regression.estimateRegressionStandardError();
        int numIndependentVariables = 5;

        //Forecasted Prices
        if (!forecastedPrices.isEmpty()) {
            forecastedPrices.clear();
        }
        for (Announcement deal : deals) {
            Property property = deal.getProperty();
            double predictedSaleValue = coefficients[0] + coefficients[1] * property.getArea() + coefficients[2] * property.getDistanceFromCenter();

            if (property instanceof House) {
                House house = (House) property;
                predictedSaleValue += coefficients[3] * house.getNumberOfBedrooms() + coefficients[4] * house.getNumberOfBathrooms()
                        + coefficients[5] * house.getNumberOfParkingSpaces();
            } else if (property instanceof Apartment) {
                Apartment apartment = (Apartment) property;
                predictedSaleValue += coefficients[3] * apartment.getNumberOfBedrooms() + coefficients[4] * apartment.getNumberOfBathrooms()
                        + coefficients[5] * apartment.getNumberOfParkingSpaces();
            }

            forecastedPrices.add(predictedSaleValue);
        }


        // Coefficient Intervals
        int numPredictors = 5; // Number of independent variables
        int degreesOfFreedom = n - numPredictors - 1;
        TDistribution tDistribution = new TDistribution(degreesOfFreedom);
        criticalValue = tDistribution.inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2);

        double[] lowerBounds = new double[coefficients.length];
        double[] upperBounds = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            double coefficient = coefficients[i];
            double standardError = standardErrors[i];
            coefficientLowerBounds[i] = coefficient - criticalValue * standardError;
            coefficientUpperBounds[i] = coefficient + criticalValue * standardError;
        }

        // Prediction Intervals
        predictionsLowerBound = new ArrayList<>();
        predictionsUpperBound = new ArrayList<>();
        meanSquaredError = regression.calculateResidualSumOfSquares() / (n - numIndependentVariables - 1);
        double[][] x1 = new double[independentVariables.length][6];

        for (int i = 0; i < independentVariables.length; i++) {
            x1[i][0] = 1; // Set the first column to ones
            Announcement deal = deals.get(i);
            x1[i][1] = independentVariables[i][0];
            x1[i][2] = independentVariables[i][1];
            if (deal.getProperty() instanceof House) {
                x1[i][3] = independentVariables[i][2];
                x1[i][4] = independentVariables[i][3];
                x1[i][5] = independentVariables[i][4];
            } else if (deal.getProperty() instanceof Apartment) {
                x1[i][3] = independentVariables[i][2];
                x1[i][4] = independentVariables[i][3];
                x1[i][5] = independentVariables[i][4];
            }
        }

        for (Announcement deal : deals) {


            Property property = deal.getProperty();
            double predictedSaleValue = estimatedCoefficient[0] + estimatedCoefficient[1] * property.getArea() + estimatedCoefficient[2] * property.getDistanceFromCenter();
            if (property instanceof Apartment) {
                predictedSaleValue += estimatedCoefficient[3] * ((Apartment) property).getNumberOfBedrooms()
                        + estimatedCoefficient[4] * ((Apartment) property).getNumberOfBathrooms()
                        + estimatedCoefficient[5] * ((Apartment) property).getNumberOfParkingSpaces();
            } else if (property instanceof House) {
                predictedSaleValue += estimatedCoefficient[3] * ((House) property).getNumberOfBedrooms()
                        + estimatedCoefficient[4] * ((House) property).getNumberOfBathrooms()
                        + estimatedCoefficient[5] * ((House) property).getNumberOfParkingSpaces();
            }


            Property p0 = deals.get(0).getProperty();
            RealMatrix x0;

            if (p0 instanceof Apartment) {
                x0 = MatrixUtils.createRealMatrix(new double[][]{{1, p0.getArea(), p0.getDistanceFromCenter(),
                        ((Apartment) p0).getNumberOfBedrooms(), ((Apartment) p0).getNumberOfBathrooms(),
                        ((Apartment) p0).getNumberOfParkingSpaces()}});
            } else {
                x0 = MatrixUtils.createRealMatrix(new double[][]{{1, p0.getArea(), p0.getDistanceFromCenter(),
                        ((House) p0).getNumberOfBedrooms(), ((House) p0).getNumberOfBathrooms(),
                        ((House) p0).getNumberOfParkingSpaces()}});

            }

            RealMatrix x = MatrixUtils.createRealMatrix(x1);
            RealMatrix x_x = x.transpose().multiply(x);
            RealMatrix inverse_x_x = MatrixUtils.inverse(x_x);
            RealMatrix x0T_inverse_x_x = x0.multiply(inverse_x_x);
            RealMatrix x0T_inverse_x_x_x0 = x0T_inverse_x_x.multiply(x0.transpose());

            double delta = criticalValue * Math.sqrt(meanSquaredError * (1 + x0T_inverse_x_x_x0.getEntry(0, 0)));

            predictionsLowerBound.add(predictedSaleValue - delta);
            predictionsUpperBound.add(predictedSaleValue + delta);

        }

        //ANOVA
        residualSumOfSquares = regression.calculateResidualSumOfSquares();
        explainedSumOfSquares = regression.calculateTotalSumOfSquares() - residualSumOfSquares;
        totalSumOfSquares = regression.calculateTotalSumOfSquares();
        degreesOfFreedomRSS = n - numIndependentVariables - 1;
        degreesOfFreedomTSS = n - 1;
        meanSquareRegression = explainedSumOfSquares / numIndependentVariables;
        meanSquaredError = residualSumOfSquares / degreesOfFreedomRSS;
        fValue = meanSquareRegression / meanSquaredError;

        FDistribution fDistribution = new FDistribution(numIndependentVariables, n - numIndependentVariables - 1);
        multipleCriticalValue = fDistribution.inverseCumulativeProbability(1 - alfa);

        if (fValue > multipleCriticalValue) {
            isSignificant = true;
        } else {
            isSignificant = false;
        }

        //Hyphotesis Testing
        testStatistics = new double[estimatedCoefficient.length];
        tDistribution = new TDistribution(degreesOfFreedomRSS);
        multipleTestCriticalValue = tDistribution.inverseCumulativeProbability(1 - alfa / 2);
        for (int i = 1; i < estimatedCoefficient.length; i++) {
            testStatistics[i] = estimatedCoefficient[i] / Math.sqrt(meanSquaredError * covarianceMatrix[i][i]);
        }

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

    public void setConfidenceLevel(double confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }

    public double getCriticalValue() {
        return criticalValue;
    }

    public boolean getIsSignificant() {
        return isSignificant;
    }

    public double getInterceptTValue() {
        return interceptTValue;
    }

    public double getSlopeTValue() {
        return slopeTValue;
    }

    public void setIsMultipleRegression(boolean multipleRegression) {
        isMultipleRegression = multipleRegression;
    }

    public boolean getIsMultipleRegression() {
        return isMultipleRegression;
    }

    public double[] getCoefficientLowerBounds() {
        return coefficientLowerBounds;
    }

    public double[] getCoefficientUpperBounds() {
        return coefficientUpperBounds;
    }

    public double getPredictionLowerBound() {
        return predictionLowerBound;
    }

    public double getPredictionUpperBound() {
        return predictionUpperBound;
    }

    public double[] getStandardErrors() {
        return standardErrors;
    }

    public double[] getTValues() {
        return tValues;
    }

    public double[] getPValues() {
        return pValues;
    }

    public double getMeanSquareRegression() {
        return meanSquareRegression;
    }

    public double getMeanSquareResidual() {
        return meanSquareResidual;
    }

    public double getInterceptCriticalValue() {
        return interceptCriticalValue;
    }

    public double getSlopeCriticalValue() {
        return slopeCriticalValue;
    }

    public double getExplainedSumOfSquares() {
        return explainedSumOfSquares;
    }

    public double getDegreesOfFreedomRSS() {
        return degreesOfFreedomRSS;
    }

    public double getDegreesOfFreedomTSS() {
        return degreesOfFreedomTSS;
    }

    public double getMeanSquaredError() {
        return meanSquaredError;
    }

    public double getMultipleCriticalValue() {
        return multipleCriticalValue;
    }

    public double[][] getCovarianceMatrix() {
        return covarianceMatrix;
    }

    public double[] getEstimatedCoefficient() {
        return estimatedCoefficient;
    }

    public ArrayList<Double> getPredictionsLowerBound() {
        return predictionsLowerBound;
    }

    public ArrayList<Double> getPredictionsUpperBound() {
        return predictionsUpperBound;
    }

    public double getStandardError() {
        return standardError;
    }

    public double[] getTestStatistics() {
        return testStatistics;
    }

    public double getMultipleTestCriticalValue() {
        return multipleTestCriticalValue;
    }

    public double[][] getSimplePredictionIntervals() {
        return simplePredictionIntervals;
    }
}

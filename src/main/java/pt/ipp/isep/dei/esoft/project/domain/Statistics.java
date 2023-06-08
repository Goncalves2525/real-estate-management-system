package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;
import java.util.Arrays;

public class Statistics {
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





        //Hypothesis Test
        double a0 = 0;
        double b0 = 0;
        boolean interceptReject;
        boolean slopeReject;
        intercept = regression.getIntercept();
        interceptTValue = (intercept - a0) / interceptStandardError;
        interceptPValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(interceptTValue)));
        interceptCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if(Math.abs(interceptTValue) > interceptCriticalValue) {
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


        //Hyphotesis Test
        double a0 = 0;
        double b0 = 0;
        boolean interceptReject;
        boolean slopeReject;
        intercept = regression.getIntercept();
        double interceptTValue = (intercept - a0) / interceptStandardError;
        interceptPValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(interceptTValue)));
        interceptCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if(Math.abs(interceptTValue) > interceptCriticalValue) {
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


        //Hyphotesis Test
        double a0 = 0;
        double b0 = 0;
        boolean interceptReject;
        boolean slopeReject;
        intercept = regression.getIntercept();
        double interceptTValue = (intercept - a0) / interceptStandardError;
        interceptPValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(interceptTValue)));
        interceptCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if(Math.abs(interceptTValue) > interceptCriticalValue) {
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


        //Hyphotesis Test
        double a0 = 0;
        double b0 = 0;
        boolean interceptReject;
        boolean slopeReject;
        intercept = regression.getIntercept();
        double interceptTValue = (intercept - a0) / interceptStandardError;
        interceptPValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(interceptTValue)));
        interceptCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if(Math.abs(interceptTValue) > interceptCriticalValue) {
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


        //Hyphotesis Test
        double a0 = 0;
        double b0 = 0;
        boolean interceptReject;
        boolean slopeReject;
        intercept = regression.getIntercept();
        double interceptTValue = (intercept - a0) / interceptStandardError;
        interceptPValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(interceptTValue)));
        interceptCriticalValue = tDistribution.inverseCumulativeProbability(1 - (alfa / 2));

        if(Math.abs(interceptTValue) > interceptCriticalValue) {
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

        regression.newSampleData(dependentVariable, independentVariables);


        // Coefficients
        double[] coefficients = regression.estimateRegressionParameters();
        correlationCoefficient = regression.calculateRSquared();
        determinationCoefficient = regression.calculateAdjustedRSquared();
        adjustedDeterminationCoefficient = regression.calculateAdjustedRSquared();
        standardErrors = regression.estimateRegressionParametersStandardErrors();

        //Forecasted Prices
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
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2);

        double[] lowerBounds = new double[coefficients.length];
        double[] upperBounds = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            double coefficient = coefficients[i];
            double standardError = standardErrors[i];
            coefficientLowerBounds[i] = coefficient - criticalValue * standardError;
            coefficientUpperBounds[i] = coefficient + criticalValue * standardError;
        }


        // Prediction Intervals
        double sumSquaredErrors = regression.calculateResidualSumOfSquares();
        double standardError = Math.sqrt(sumSquaredErrors / degreesOfFreedom);
        double marginOfError = criticalValue * standardError;

        predictionLowerBound = forecastedPrices.get(0) - marginOfError;
        predictionUpperBound = forecastedPrices.get(0) + marginOfError;

        for (int i = 1; i < n; i++) {
            double prediction = forecastedPrices.get(i);
            if (prediction - marginOfError < predictionLowerBound) {
                predictionLowerBound = prediction - marginOfError;
            }
            if (prediction + marginOfError > predictionUpperBound) {
                predictionUpperBound = prediction + marginOfError;
            }
        }

        //ANOVA
        standardErrors = new double[coefficients.length];
        double[] residuals = regression.estimateResiduals();
        double meanSquareError = regression.calculateResidualSumOfSquares() / (independentVariables.length - coefficients.length);

        int n = independentVariables.length; // Number of observations
        int numIndependentVariables = independentVariables[0].length; // Number of independent variables

        for (int i = 0; i < coefficients.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                double predicted = coefficients[0]; // intercept
                for (int k = 0; k < numIndependentVariables; k++) {
                    predicted += coefficients[k + 1] * independentVariables[j][k];
                }
                double residual = residuals[j];
                sum += Math.pow(independentVariables[j][i % numIndependentVariables], 2) * (residual * residual) / (n - coefficients.length - 1);
            }
            standardErrors[i] = Math.sqrt(meanSquareError * sum);
        }

        tValues = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            tValues[i] = coefficients[i] / standardErrors[i];
        }


        pValues = new double[coefficients.length];
        tDistribution = new TDistribution(independentVariables.length - coefficients.length);
        for (int i = 0; i < coefficients.length; i++) {
            double tValue = tValues[i];
            pValues[i] = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(tValue)));
        }

        double SSR = regression.calculateTotalSumOfSquares() - regression.calculateResidualSumOfSquares();
        double SSE = regression.calculateResidualSumOfSquares();

        int dfR = regression.estimateRegressionParameters().length - 1;
        int dfE = independentVariables.length - dfR - 1;

        meanSquareRegression = SSR / dfR;
        meanSquareResidual = SSE / dfE;


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

    public boolean isMultipleRegression() {
        return isMultipleRegression;
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
}

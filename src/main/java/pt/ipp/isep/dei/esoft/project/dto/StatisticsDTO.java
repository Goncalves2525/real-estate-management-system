package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Statistics;

import java.util.ArrayList;

public class StatisticsDTO {
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

    /**
        * Instantiates a new Statistics dto.
     */
    public StatisticsDTO(int n, double confidenceLevel, double intercept, double slope, boolean isMultipleRegression, ArrayList<Announcement> deals, ArrayList<Double> forecastedPrices, double interceptPValue, double slopePValue, double correlationCoefficient, double determinationCoefficient, double adjustedDeterminationCoefficient, double slopeStandardError, double interceptStandardError, double alfa, double[][] confidenceIntervals, boolean[] rejects, double totalSumOfSquares, double residualSumOfSquares, double regressionSumOfSquares, int totalDegreesOfFreedom, double regressionMeanSquare, double residualMeanSquare, int regressionDegreesOfFreedom, int residualDegreesOfFreedom, double fValue, double pValue, double criticalValue, boolean isSignificant, double interceptTValue, double slopeTValue, double[] coefficientLowerBounds, double[] coefficientUpperBounds, double predictionLowerBound, double predictionUpperBound, double[] standardErrors, double[] tValues, double[] pValues, double meanSquareRegression, double meanSquareResidual, double interceptCriticalValue, double slopeCriticalValue, double explainedSumOfSquares, double degreesOfFreedomRSS, double degreesOfFreedomTSS, double meanSquaredError, double multipleCriticalValue, double[][] covarianceMatrix, double[] estimatedCoefficient, ArrayList<Double> predictionsLowerBound, ArrayList<Double> predictionsUpperBound, double standardError, double[] testStatistics, double multipleTestCriticalValue, double[][] simplePredictionIntervals) {
        this.n = n;
        this.confidenceLevel = confidenceLevel;
        this.alfa = 1 - confidenceLevel;
        this.intercept = intercept;
        this.slope = slope;
        this.isMultipleRegression = isMultipleRegression;
        this.deals = deals;
        this.forecastedPrices = forecastedPrices;
        this.interceptPValue = interceptPValue;
        this.slopePValue = slopePValue;
        this.correlationCoefficient = correlationCoefficient;
        this.determinationCoefficient = determinationCoefficient;
        this.adjustedDeterminationCoefficient = adjustedDeterminationCoefficient;
        this.slopeStandardError = slopeStandardError;
        this.interceptStandardError = interceptStandardError;
        this.alfa = alfa;
        this.confidenceIntervals = confidenceIntervals;
        this.rejects = rejects;
        this.totalSumOfSquares = totalSumOfSquares;
        this.residualSumOfSquares = residualSumOfSquares;
        this.regressionSumOfSquares = regressionSumOfSquares;
        this.totalDegreesOfFreedom = totalDegreesOfFreedom;
        this.regressionMeanSquare = regressionMeanSquare;
        this.residualMeanSquare = residualMeanSquare;
        this.regressionDegreesOfFreedom = regressionDegreesOfFreedom;
        this.residualDegreesOfFreedom = residualDegreesOfFreedom;
        this.fValue = fValue;
        this.pValue = pValue;
        this.criticalValue = criticalValue;
        this.isSignificant = isSignificant;
        this.interceptTValue = interceptTValue;
        this.slopeTValue = slopeTValue;
        this.coefficientLowerBounds = coefficientLowerBounds;
        this.coefficientUpperBounds = coefficientUpperBounds;
        this.predictionLowerBound = predictionLowerBound;
        this.predictionUpperBound = predictionUpperBound;
        this.standardErrors = standardErrors;
        this.tValues = tValues;
        this.pValues = pValues;
        this.meanSquareRegression = meanSquareRegression;
        this.meanSquareResidual = meanSquareResidual;
        this.interceptCriticalValue = interceptCriticalValue;
        this.slopeCriticalValue = slopeCriticalValue;
        this.explainedSumOfSquares = explainedSumOfSquares;
        this.degreesOfFreedomRSS = degreesOfFreedomRSS;
        this.degreesOfFreedomTSS = degreesOfFreedomTSS;
        this.meanSquaredError = meanSquaredError;
        this.multipleCriticalValue = multipleCriticalValue;
        this.covarianceMatrix = covarianceMatrix;
        this.estimatedCoefficient = estimatedCoefficient;
        this.predictionsLowerBound = predictionsLowerBound;
        this.predictionsUpperBound = predictionsUpperBound;
        this.standardError = standardError;
        this.testStatistics = testStatistics;
        this.multipleTestCriticalValue = multipleTestCriticalValue;
        this.simplePredictionIntervals = simplePredictionIntervals;
    }



    public boolean isMultipleRegression() {
        return isMultipleRegression;
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

    public double getInterceptPValue() {
        return interceptPValue;
    }

    public double getSlope() {
        return slope;
    }

    public double getSlopePValue() {
        return slopePValue;
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

    public double getSlopeStandardError() {
        return slopeStandardError;
    }

    public double getInterceptStandardError() {
        return interceptStandardError;
    }

    public double getAlfa() {
        return alfa;
    }

    public int getN() {
        return n;
    }

    public double getConfidenceLevel() {
        return confidenceLevel;
    }

    public double[][] getConfidenceIntervals() {
        return confidenceIntervals;
    }

    public boolean[] getRejects() {
        return rejects;
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

    public double getRegressionMeanSquare() {
        return regressionMeanSquare;
    }

    public double getResidualMeanSquare() {
        return residualMeanSquare;
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

    public double getCriticalValue() {
        return criticalValue;
    }

    public boolean isSignificant() {
        return isSignificant;
    }

    public double getInterceptTValue() {
        return interceptTValue;
    }

    public double getSlopeTValue() {
        return slopeTValue;
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

    public double[] gettValues() {
        return tValues;
    }

    public double[] getpValues() {
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

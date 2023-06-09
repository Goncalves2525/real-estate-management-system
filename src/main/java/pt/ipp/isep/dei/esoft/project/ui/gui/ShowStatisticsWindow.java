package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.StatisticsController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Statistics;
import pt.ipp.isep.dei.esoft.project.dto.StatisticsDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowStatisticsWindow implements Initializable {

    private final StatisticsController controller;
    private Stage showGraphicWindow;
    @FXML
    private TextArea txtArea;
    @FXML
    private Button btPrices;
    @FXML
    private Button btR;
    @FXML
    private Button btR2;
    @FXML
    private Button btTest;
    @FXML
    private Button btInterval;
    @FXML
    private Button btAnova;
    @FXML
    private Button btValues;
    @FXML
    private Button btReturn;


    public ShowStatisticsWindow() {
        controller = new StatisticsController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void onBtPrices(ActionEvent event) {
        ArrayList<Double> forecastedPrices = controller.getForecastedPrices();
        ArrayList<Announcement> deals = controller.getDeals();
        txtArea.clear();
        txtArea.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.NORMAL, FontPosture.REGULAR, Font.getDefault().getSize()));
        txtArea.setText("Prices:\n");
        for (int i = 0; i < forecastedPrices.size(); i++) {
            String string = String.format("Property %-10d Forecasted Price: %-30f Real Price: %f\n", i, forecastedPrices.get(i), deals.get(i).getProperty().getPrice());
            txtArea.appendText(string);
        }
    }

    @FXML
    private void onBtR(ActionEvent event) {
        double[] coefficients = controller.getCoefficients();
        double determinationCoefficient = controller.getDeterminationCoefficient();
        double adjustedDeterminationCoefficient = controller.getAdjustedDeterminationCoefficient();

        if (!controller.getIsMultipleRegression()) {
            txtArea.clear();
            txtArea.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.NORMAL, FontPosture.REGULAR, Font.getDefault().getSize()));
            txtArea.setText("Coefficients:\n");
            txtArea.appendText("R: " + coefficients[0] + "\n");
            txtArea.appendText("R2: " + coefficients[1] + "\n");
            txtArea.appendText("Adjusted R2: " + coefficients[2] + "\n");
        } else {
            txtArea.clear();
            txtArea.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.NORMAL, FontPosture.REGULAR, Font.getDefault().getSize()));
            txtArea.setText("Coefficients:\n");
            txtArea.appendText("\nMultiple R: " + Math.sqrt(coefficients[1]));
            txtArea.appendText("\nR2: " + determinationCoefficient + "\n");
            txtArea.appendText("Adjusted R2: " + adjustedDeterminationCoefficient + "\n");
        }


    }

    @FXML
    private void onBtTest(ActionEvent event) {

        double interceptTValue = controller.getInterceptTValue();
        double slopeTValue = controller.getSlopeTValue();
        double interceptCriticalValue = controller.getInterceptCriticalValue();
        double slopeCriticalValue = controller.getSlopeCriticalValue();
        boolean isInterceptSignificant = interceptTValue > interceptCriticalValue;
        double[][] covarianceMatrix = controller.getCovarianceMatrix();
        double[] estimatedCoefficient = controller.getEstimatedCoefficient();
        double[] testStatistics = controller.getTestStatistics();
        double meanSquaredError = controller.getMeanSquaredError();
        double multipleCriticalValue = controller.getMultipleCriticalValue();
        double multipleTestCriticalValue = controller.getMultipleTestCriticalValue();
        String interceptResult;
        String[] coefficientNames = {"Intercept", "Area", "Distance from center", "Number of bedrooms", "Number of bathrooms", "Number of parking spaces"};

        if (!controller.getIsMultipleRegression()) {
            if (isInterceptSignificant) {
                interceptResult = "The intercept T-Value is higher than the critical Value. There is significant evidence to reject the null hypothesis for the intercept.";
            } else {
                interceptResult = "The intercept T-Value is lower than the critical Value. There is not enough evidence to reject the null hypothesis for the intercept.";
            }

            boolean isSlopeSignificant = slopeTValue > slopeCriticalValue;
            String slopeResult;
            if (isSlopeSignificant) {
                slopeResult = "The slope T-Value is higher then the critical value. There is significant evidence to reject the null hypothesis for the slope.";
            } else {
                slopeResult = "The slope T-Value is lower then the critical value. There is not enough evidence to reject the null hypothesis for the slope.";
            }

            txtArea.clear();
            txtArea.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.NORMAL, FontPosture.REGULAR, Font.getDefault().getSize()));
            String explanation =
                    "-------------------------------------------------\n" +
                            "Intercept Hypothesis Test Result:\n" +
                            "-------------------------------------------------\n" +
                            "T-Value: " + interceptTValue + "\n" +
                            "Critical Value: " + interceptCriticalValue + "\n" +
                            interceptResult + "\n" +
                            "-------------------------------------------------\n" +
                            "Slope Hypothesis Test Result:\n" +
                            "-------------------------------------------------\n" +
                            "T-Value: " + slopeTValue + "\n" +
                            "Critical Value: " + slopeCriticalValue + "\n" +
                            slopeResult;

            txtArea.setText(explanation);
        } else {
            txtArea.clear();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < estimatedCoefficient.length; i++) {
                testStatistics[i] = estimatedCoefficient[i] / Math.sqrt(meanSquaredError * covarianceMatrix[i][i]);
                sb.append("Hypothesis Test Result for " + coefficientNames[i] + ":\n");
                sb.append("-------------------------------------------------\n");
                sb.append("T-Value: " + testStatistics[i] + "\n");
                sb.append("Critical Value: " + multipleTestCriticalValue + "\n");
                sb.append("-------------------------------------------------\n");
                if (testStatistics[i] > multipleTestCriticalValue) {
                    sb.append("Since Test Statistic > Critical Value, we reject the null hypothesis.\n");
                } else {
                    sb.append("Since Test Statistic < Critical Value, we do not reject the null hypothesis.\n");
                }
                sb.append("\n\n\n");
            }

            txtArea.appendText(sb.toString());

        }


    }

    @FXML
    private void onBtInterval(ActionEvent event) {
        double[][] interval = controller.getConfidenceIntervals();
        double[] coefficientLowerBound = controller.getCoefficientLowerBounds();
        double[] coefficientUpperBound = controller.getCoefficientUpperBounds();
        double[][] predictionsInterval = controller.getSimplePredictionInterval();

        ArrayList<Double> predictionsUpperBound = controller.getPredictionsUpperBound();
        ArrayList<Double> predictionsLowerBound = controller.getPredictionsLowerBound();
        ArrayList<Announcement> deals = controller.getDeals();

        if (!controller.getIsMultipleRegression()) {
            txtArea.clear();
            txtArea.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.NORMAL, FontPosture.REGULAR, Font.getDefault().getSize()));
            txtArea.setText("Confidence Intervals:\n");
            txtArea.appendText("Intercept Confidence Interval: ]" + interval[1][0] + " ; " + interval[1][1] + "[\n");
            txtArea.appendText("Slope Confidence Interval: ]" + interval[2][0] + " ; " + interval[2][1] + "[\n");

            StringBuilder sb = new StringBuilder();
            sb.append("\n\nPredicted Value Confidence Intervals:\n");
            for (int i = 0; i < predictionsInterval.length; i++) {
                double[] propertyInterval = predictionsInterval[i];
                sb.append("Property ").append(i).append(" Confidence Interval:");
                sb.append(String.format("]%.2f, %.2f[", propertyInterval[0], propertyInterval[1])).append("\n");
            }
            txtArea.appendText(sb.toString());


        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Coefficient Intervals:\n");
            for (int i = 0; i < coefficientLowerBound.length; i++) {
                double lowerBound = coefficientLowerBound[i];
                double upperBound = coefficientUpperBound[i];
                String coefficientInterval = "Coefficient " + i + ": " + String.format("]%.2f, %.2f[", lowerBound, upperBound) + "\n";
                sb2.append(coefficientInterval);
            }
            txtArea.clear();
            txtArea.appendText(sb2.toString());

            StringBuilder sb = new StringBuilder();
            sb.append("\n\n\nPredicted Value Confidence Intervals:\n");
            for (int i = 0; i < deals.size(); i++) {
                Announcement deal = deals.get(i);
                double lowerBound = predictionsLowerBound.get(i);
                double upperBound = predictionsUpperBound.get(i);
                sb.append("Property-").append(i)
                        .append(": ").append(String.format("]%.2f, %.2f[", lowerBound, upperBound))
                        .append("\n");
            }

            String result = sb.toString();
            txtArea.appendText(result);

        }


    }

    @FXML
    private void onBtAnova(ActionEvent event) {
        double regressionSumOfSquares = controller.getRegressionSumOfSquares();
        int regressionDegreesOfFreedom = controller.getRegressionDegreesOfFreedom();
        double regressionMeanSquare = controller.getRegressionMeanSquare();
        double residualSumOfSquares = controller.getResidualSumOfSquares();
        int residualDegreesOfFreedom = controller.getResidualDegreesOfFreedom();
        double residualMeanSquare = controller.getResidualMeanSquare();
        double totalSumOfSquares = controller.getTotalSumOfSquares();
        int totalDegreesOfFreedom = controller.getTotalDegreesOfFreedom();
        double fValue = controller.getFValue();
        double pValue = controller.getPValue();
        double criticalValue = controller.getCriticalValue();
        double multipleTestCriticalValue = controller.getMultipleTestCriticalValue();
        double[] correlationCoefficient = controller.getCoefficients();
        boolean isSignificant = fValue > criticalValue;
        double meanSquareRegression = controller.getMeanSquareRegression();
        double meanSquareResidual = controller.getMeanSquareResidual();
        double explainedSumOfSquares = controller.getExplainedSumOfSquares();
        double degreesOfFreedomRSS = controller.getDegreesOfFreedomRSS();
        double degreesOfFreedomTSS = controller.getDegreesOfFreedomTSS();
        double meanSquaredError = controller.getMeanSquaredError();
        double alfa = controller.getAlfa();
        double multipleCriticalValue = controller.getMultipleCriticalValue();
        double determinationCoefficient = controller.getDeterminationCoefficient();
        int numIndependentVariables = 5;


        if (!controller.getIsMultipleRegression()) {
            String analysisResult;
            isSignificant = controller.getIsSignificant();
            if (isSignificant) {
                analysisResult = "The F-value is greater than the critical value. There is significant evidence to reject the null hypothesis.";
            } else {
                analysisResult = "The F-value is not greater than the critical value. There is not enough evidence to reject the null hypothesis.";
            }

            txtArea.clear();
            String anovaTable = String.format("ANOVA Table\n" +
                            "----------------------------------------------------------------------------------------------------\n" +
                            "%-10s %-22s %-5s %-22s %-10s %-10s %-10s\n" +
                            "----------------------------------------------------------------------------------------------------\n" +
                            "%-10s %-22f %-5d %-22f %-10f %-10f %-10f\n" +
                            "%-10s %-22f %-5d %-22f\n" +
                            "%-10s %-22f %-5d\n" +
                            "----------------------------------------------------------------------------------------------------\n",
                    "Source", "SS", "DF", "MS", "F", "p-value", "critical value",
                    "Regression", regressionSumOfSquares, regressionDegreesOfFreedom, regressionMeanSquare, fValue, pValue, criticalValue,
                    "Residual", residualSumOfSquares, residualDegreesOfFreedom, residualMeanSquare,
                    "Total", totalSumOfSquares, totalDegreesOfFreedom);

            String explanation = anovaTable +

                    "\n\n\n-------------------------------------------------\n" +
                    "Correlation Coefficient:\n" +
                    "-------------------------------------------------\n" +
                    "The correlation coefficient measures the strength and direction of the linear relationship between two variables.\n" +
                    "In the context of regression analysis, it represents the degree of association between the independent variable(s) and the dependent variable.\n" +
                    "A correlation coefficient of 1 indicates a perfect positive linear relationship, -1 indicates a perfect negative linear relationship, and 0 indicates no linear relationship.\n" +
                    "The correlation coefficient does not provide information about causation or the strength of non-linear relationships.\n" +
                    "It's important to consider the correlation coefficient alongside other statistical measures and the context of the specific analysis.\n" +
                    "-------------------------------------------------\n" +
                    "Analysis Result:\n" +
                    "-------------------------------------------------\n" +
                    analysisResult;


            txtArea.setFont(Font.font("Monospaced"));

            txtArea.appendText(explanation);

        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("ANOVA Table:\n");
            sb.append(String.format("%-20s %20s %10s %10s %10s\n", "Source", "SS", "DF", "MS", "F"));
            sb.append(String.format("%-20s %20.2f %10d %10.2f %10.2f\n", "Regression", explainedSumOfSquares, numIndependentVariables, meanSquareRegression, fValue));
            sb.append(String.format("%-20s %20.2f %10f %10.2f\n", "Residual", residualSumOfSquares, degreesOfFreedomRSS, meanSquaredError));
            sb.append(String.format("%-20s %20.2f %10f\n", "Total", totalSumOfSquares, degreesOfFreedomTSS));
            sb.append("\n\n");


            sb.append("Coefficient of Determination (R-squared):\n");
            sb.append(String.format("R-squared: %.2f\n", determinationCoefficient));
            sb.append("The coefficient of determination (R-squared) represents the proportion of variance in the dependent variable that can be explained by the independent variables.\n");
            sb.append("An R-squared value of 1 indicates that the regression model explains all the variability in the dependent variable,\n");
            sb.append("while a value of 0 indicates that the model does not explain any of the variability.\n");
            sb.append("\n\n");


            sb.append("Significance of the Regression Model:\n");
            sb.append(String.format("Critical Value (α=%.2f): %.2f\n", alfa, multipleCriticalValue));
            sb.append(String.format("F-Value: %.2f\n", fValue));
            sb.append("Result: ");
            if (isSignificant) {
                sb.append("Reject Null Hypothesis because F-Value > Critical Value.\n");
                sb.append("The regression model is significant, indicating that the independent variables have a statistically significant impact on the dependent variable.\n");
            } else {
                sb.append("Do not reject Null Hypothesis because F-Value < Critical Value.\n");
                sb.append("The regression model is not significant, indicating that the independent variables do not have a statistically significant impact on the dependent variable.\n");
            }

            String result = sb.toString();
            txtArea.clear();
            txtArea.setFont(Font.font("Monospaced"));
            txtArea.appendText(result);


        }


    }

    @FXML
    private void onBtValues(ActionEvent event) {
        StatisticsDTO stats = controller.getStats();
        int n = stats.getN();
        double intercept = stats.getIntercept();
        double slope = stats.getSlope();
        double confidenceLevel = stats.getConfidenceLevel();
        double alfa = stats.getAlfa();
        double standardError = controller.getStandardError();
        double[] estimatedCoefficients = controller.getEstimatedCoefficient();
        String[] independentVariableNames = {"x1", "x2", "x3", "x4", "x5"};

        if (!controller.getIsMultipleRegression()) {
            txtArea.clear();
            txtArea.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.NORMAL, FontPosture.REGULAR, Font.getDefault().getSize()));
            txtArea.setText("Values:\n");
            txtArea.appendText("n: " + n + "\n");
            txtArea.appendText("Intercept: " + String.format("%.2f", intercept) + "\n");
            txtArea.appendText("Slope: " + String.format("%.2f", slope) + "\n");
            txtArea.appendText("Confidence Level: " + confidenceLevel + "\n");
            txtArea.appendText("Alfa: " + String.format("%.2f", alfa) + "\n");
            txtArea.appendText("\n");
            txtArea.appendText("Regression line: y = " + String.format("%.2f", slope) + "x + (" + String.format("%.2f", intercept) + ")");

        } else {
            txtArea.clear();
            txtArea.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.NORMAL, FontPosture.REGULAR, Font.getDefault().getSize()));
            txtArea.setText("Values:\n");
            txtArea.appendText("n: " + n + "\n");
            txtArea.appendText("Confidence Level: " + confidenceLevel + "\n");
            txtArea.appendText("Alfa: " + String.format("%.2f", alfa) + "\n");
            txtArea.appendText("\n\n");

            StringBuilder equationBuilder = new StringBuilder("Multiple Regression Equation:\n");
            equationBuilder.append("y = ");
            equationBuilder.append(String.format("%.2f", estimatedCoefficients[0]));

            for (int i = 1; i < estimatedCoefficients.length; i++) {
                equationBuilder.append(" + ");
                equationBuilder.append(String.format("%.2f", estimatedCoefficients[i]));
                equationBuilder.append(independentVariableNames[i - 1]);
            }

            String equation = equationBuilder.toString();
            txtArea.appendText(equation);

        }
    }


    @FXML
    private void onBtReturn(ActionEvent event) {
        Stage mainStage = getMainStage();
        FXMLLoader selectRegressionModelLoader = new FXMLLoader(getClass().getResource("/SelectRegressionModelScene.fxml"));
        Parent selectRegressionModelRoot = null;
        try {
            selectRegressionModelRoot = selectRegressionModelLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SelectRegressionModelWindow controller = selectRegressionModelLoader.getController();
        double confidenceLevel = this.controller.getConfidenceLevel();
        String confidence = Double.toString(confidenceLevel);
        confidence = String.format("Confidence = %s%%", confidence);
        controller.setLabel(confidence);

        Scene SelectRegressionModelScene = new Scene(selectRegressionModelRoot);
        mainStage.setScene(SelectRegressionModelScene);
        mainStage.setTitle("Select Regression Model");
        mainStage.show();
    }

    private Stage getMainStage() {
        return (Stage) this.btReturn.getScene().getWindow();
    }


}

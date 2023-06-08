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

        if (!controller.getIsMultipleRegression()){
            txtArea.clear();
            txtArea.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.NORMAL, FontPosture.REGULAR, Font.getDefault().getSize()));
            txtArea.setText("Coefficients:\n");
            txtArea.appendText("R: " + coefficients[0] + "\n");
            txtArea.appendText("R2: " + coefficients[1] + "\n");
            txtArea.appendText("Adjusted R2: " + coefficients[2] + "\n");
        }else{
            txtArea.clear();
            txtArea.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.NORMAL, FontPosture.REGULAR, Font.getDefault().getSize()));
            txtArea.setText("Coefficients:\n");
            txtArea.appendText("R2: " + coefficients[1] + "\n");
            txtArea.appendText("Adjusted R2: " + coefficients[2] + "\n");
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
        double testStatistic;
        double meanSquaredError = controller.getMeanSquaredError();
        double multipleCriticalValue = controller.getMultipleCriticalValue();
        String interceptResult;
        String[] coefficientNames = {"Intercept","Area", "Distance from center", "Number of bedrooms", "Number of bathrooms", "Number of parking spaces"};

        if (!controller.getIsMultipleRegression()){
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
            String explanation = "Explanation of Hypothesis Test Results using Critical Value:\n" +
                    "-------------------------------------------------\n" +
                    "The hypothesis tests are conducted to evaluate the statistical significance of the intercept and slope coefficients in the regression model.\n" +
                    "For each hypothesis test, the test statistic is calculated and compared with the critical value to make a decision.\n" +
                    "The critical value is determined based on the chosen significance level (alpha), which represents the threshold for accepting or rejecting the null hypothesis.\n" +
                    "If the test statistic exceeds the critical value, it indicates significant evidence to reject the null hypothesis.\n" +
                    "Conversely, if the test statistic is below the critical value, there is not enough evidence to reject the null hypothesis.\n" +
                    "The interpretation of the hypothesis test results depends on the specific context and research question.\n\n\n" +
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
        }else {
            txtArea.clear();
            for (int i = 1; i < estimatedCoefficient.length; i++) {
                testStatistic = estimatedCoefficient[i] / Math.sqrt(meanSquaredError * covarianceMatrix[i][i]);
                if (testStatistic > multipleCriticalValue) {
                    txtArea.appendText("Since Test Statistic > Critical Value, we reject the null hypothesis for the " + coefficientNames[i] + "\n");
                } else {
                    txtArea.appendText("Since Test Statistic < Critical Value, we do not reject the null hypothesis for the " + coefficientNames[i] + "\n");
                }
            }
        }



    }

    @FXML
    private void onBtInterval(ActionEvent event) {
        double[][] interval = controller.getConfidenceIntervals();
        double[] coefficientLowerBound = controller.getCoefficientLowerBounds();
        double[] coefficientUpperBound = controller.getCoefficientUpperBounds();
        double predictionLowerBound = controller.getPredictionLowerBound();
        double predictionUpperBound = controller.getPredictionUpperBound();
        ArrayList<Double> predictionsUpperBound = controller.getPredictionsUpperBound();
        ArrayList<Double> predictionsLowerBound = controller.getPredictionsLowerBound();
        ArrayList<Announcement> deals = controller.getDeals();

        if (!controller.getIsMultipleRegression()){
            txtArea.clear();
            txtArea.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.NORMAL, FontPosture.REGULAR, Font.getDefault().getSize()));
            txtArea.setText("Confidence Intervals:\n");
            txtArea.appendText("Prediction Confidence Interval: ]" + interval[0][0] + " ; " + interval[0][1] + "[\n");
            txtArea.appendText("Intercept Confidence Interval: ]" + interval[1][0] + " ; " + interval[1][1] + "[\n");
            txtArea.appendText("Slope Confidence Interval: ]" + interval[2][0] + " ; " + interval[2][1] + "[\n");

            String explanation = "\n\n\nExplanation of Confidence Interval:\n" +
                    "-------------------------------------------------\n" +
                    "A confidence interval provides a range of values within which the true population parameter is likely to fall with a certain level of confidence.\n" +
                    "In the context of a regression analysis, a confidence interval for the regression coefficients can be calculated.\n" +
                    "These coefficients represent the effect of each predictor variable on the response variable.\n" +
                    "The confidence interval gives an estimate of the range of values for these coefficients that is likely to contain the true population value.\n" +
                    "The level of confidence, often expressed as a percentage (e.g., 95%), indicates the probability that the true parameter value falls within the interval.\n" +
                    "A wider confidence interval indicates greater uncertainty, while a narrower interval suggests more precise estimation.\n" +
                    "For example, a 95% confidence interval means that if we were to repeat the study many times and calculate the confidence interval each time, approximately 95% of those intervals would contain the true population parameter.\n" +
                    "The confidence interval can be interpreted as a measure of the precision and reliability of the estimated regression coefficients.\n" +
                    "If the confidence interval for a coefficient includes zero, it suggests that the coefficient may not be statistically significant and may not have a meaningful effect on the response variable.\n" +
                    "On the other hand, if the confidence interval does not include zero, it indicates that the coefficient is likely to be statistically significant and has a meaningful effect on the response variable.\n" +
                    "It's important to note that the interpretation of the confidence interval should be considered alongside other statistical measures and the context of the specific analysis.\n" +
                    "-------------------------------------------------\n";

            txtArea.appendText(explanation);

        }else{
            StringBuilder sb = new StringBuilder();

            sb.append("Predicted Value Confidence Intervals:\n");
            for (int i = 0; i < deals.size(); i++) {
                Announcement deal = deals.get(i);
                double lowerBound = predictionsLowerBound.get(i);
                double upperBound = predictionsUpperBound.get(i);
                sb.append("Property-").append(i)
                        .append(", Lower Bound: ").append(lowerBound)
                        .append(", Upper Bound: ").append(upperBound)
                        .append("\n");
            }

            String result = sb.toString();
            txtArea.clear();
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


        if (!controller.getIsMultipleRegression()){
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
                    "\n\n\nExplanation of ANOVA Table and F-test:\n" +
                    "-------------------------------------------------\n" +
                    "The ANOVA (Analysis of Variance) table provides a statistical summary of the regression model's performance.\n" +
                    "In the context of a linear regression analysis, the F-test assesses the overall significance of the regression model.\n" +
                    "The F-statistic is calculated as the ratio of the regression mean square to the residual mean square.\n" +
                    "The F-statistic follows an F-distribution, which is used to determine the statistical significance of the model.\n" +
                    "To make a decision about the null hypothesis, we compare the calculated F-value with the critical value.\n" +
                    "If the F-value exceeds the critical value, it suggests that the model as a whole is statistically significant.\n" +
                    "Conversely, if the F-value is below the critical value, there is not enough evidence to reject the null hypothesis.\n" +
                    "The choice of the critical value depends on the chosen significance level (alpha) and the degrees of freedom.\n" +
                    "The p-value associated with the F-statistic is also calculated, which represents the probability of obtaining the observed F-value or a more extreme value under the null hypothesis.\n" +
                    "If the p-value is less than the significance level, it indicates significant evidence to reject the null hypothesis.\n" +
                    "-------------------------------------------------\n" +
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

        }else{
            StringBuilder sb = new StringBuilder();
            sb.append("ANOVA Table:\n");
            sb.append(String.format("%-20s %20s %10s %10s %10s\n", "Source", "SS", "DF", "MS", "F"));
            sb.append(String.format("%-20s %20.2f %10d %10.2f %10.2f\n", "Regression", explainedSumOfSquares, numIndependentVariables, meanSquareRegression, fValue));
            sb.append(String.format("%-20s %20.2f %10f %10.2f\n", "Residual", residualSumOfSquares, degreesOfFreedomRSS, meanSquaredError));
            sb.append(String.format("%-20s %20.2f %10f\n", "Total", totalSumOfSquares, degreesOfFreedomTSS));
            sb.append("\n\n");


            sb.append("Explanation:\n");
            sb.append("The ANOVA table provides information about the variance and significance of the regression model.\n");
            sb.append("The 'Regression' row shows the sum of squares (SS), degrees of freedom (DF), mean square (MS), and F-value.\n");
            sb.append("The 'Residual' row shows the sum of squares (SS), degrees of freedom (DF), and mean square (MS).\n");
            sb.append("The 'Total' row shows the total sum of squares (SS) and degrees of freedom (DF).\n");
            sb.append("The F-value compares the variation explained by the regression model to the unexplained variation.\n");
            sb.append("A higher F-value indicates a more significant relationship between the independent variables and the dependent variable.\n");
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

        txtArea.clear();
        txtArea.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.NORMAL, FontPosture.REGULAR, Font.getDefault().getSize()));
        txtArea.setText("Values:\n");
        txtArea.appendText("n: " + n + "\n");
        txtArea.appendText("Intercept: " + intercept + "\n");
        txtArea.appendText("Slope: " + slope + "\n");
        txtArea.appendText("Confidence Level: " + confidenceLevel + "\n");
        txtArea.appendText("Alfa: " + alfa + "\n");
        txtArea.appendText("\n");
        txtArea.appendText("Regression line: y = " + slope + "x + (" + intercept + ")");

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

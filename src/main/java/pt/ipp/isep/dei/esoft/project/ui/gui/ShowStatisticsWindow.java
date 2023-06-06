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
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.StatisticsController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Statistics;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowStatisticsWindow implements Initializable {

    private final StatisticsController controller;
    private Stage showGraphicWindow;
    private final Statistics statistics = Statistics.getInstance();
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
    @FXML
    private Button btGraphic;

    public ShowStatisticsWindow() {
        controller = new StatisticsController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            FXMLLoader showGraphicLoader = new FXMLLoader();
            showGraphicLoader.setLocation(getClass().getResource("/ShowGraphicScene.fxml"));
            Parent showGraphicRoot = showGraphicLoader.load();
            Scene scene = new Scene(showGraphicRoot);
            showGraphicWindow = new Stage();
            showGraphicWindow.initModality(Modality.APPLICATION_MODAL);
            showGraphicWindow.setTitle("Graphic");
            showGraphicWindow.setResizable(false);
            showGraphicWindow.setScene(scene);
            ShowGraphicWindow showGraphicWindow = showGraphicLoader.getController();
            showGraphicWindow.associateParentUI(this);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }



    @FXML
    private void onBtPrices(ActionEvent event) {
        ArrayList<Double> forecastedPrices = controller.getForecastedPrices();
        ArrayList<Announcement> deals = controller.getDeals();
        txtArea.clear();
        txtArea.setText("Prices:\n");
        for (int i = 0; i < forecastedPrices.size(); i++) {
            String string = String.format("Property %d Forecasted Price: %-30f    Real Price: %-30f\n", i, forecastedPrices.get(i), deals.get(i).getProperty().getPrice());
            txtArea.appendText(string);
        }
    }

    @FXML
    private void onBtR(ActionEvent event) {
        double[] coefficients = controller.getCoefficients();
        txtArea.clear();
        txtArea.setText("Coefficients:\n");
        txtArea.appendText("R: " + coefficients[0] + "\n");
        txtArea.appendText("R2: " + coefficients[1] + "\n");
        txtArea.appendText("Adjusted R2: " + coefficients[2] + "\n");
    }

    @FXML
    private void onBtTest(ActionEvent event) {
        boolean[] rejects = controller.getRejects();
        boolean interceptReject = rejects[0];
        boolean slopeReject = rejects[1];
        double alfa = controller.getAlfa();
        double pValueIntercept = controller.getInterceptPValue();
        double pValueSlope = controller.getSlopePValue();

        txtArea.clear();
        txtArea.setText("Hypothesis Test:\n\n");
        txtArea.appendText("Intercept P-Value: " + pValueIntercept + "\n");
        txtArea.appendText("Slope P-Value: " + pValueSlope + "\n");
        txtArea.appendText("Alfa: " + alfa + "\n\n");
        if (interceptReject) {
            txtArea.appendText("Intercept PValue < alfa\n");
            txtArea.appendText("Intercept is statistically significant. Reject the null hypothesis.\n");
        } else {
            txtArea.appendText("Intercept PValue > alfa\n");
            txtArea.appendText("Intercept is not statistically significant. Fail to reject the null hypothesis.\n");
        }

        if (slopeReject) {
            txtArea.appendText("Slope PValue < alfa\n");
            txtArea.appendText("Slope is statistically significant. Reject the null hypothesis.\n");
        } else {
            txtArea.appendText("Slope PValue > alfa\n");
            txtArea.appendText("Slope is not statistically significant. Fail to reject the null hypothesis.\n");
        }
    }

    @FXML
    private void onBtInterval(ActionEvent event) {
        double[][] interval = controller.getConfidenceIntervals();
        txtArea.clear();
        txtArea.setText("Confidence Intervals:\n");
        //txtArea.appendText("Mean Confidence Interval: ]" + interval[0][0] + " ; " + interval[0][1] + "[\n");
        txtArea.appendText("Intercept Confidence Interval: ]" + interval[1][0] + " ; " + interval[1][1] + "[\n");
        txtArea.appendText("Slope Confidence Interval: ]" + interval[2][0] + " ; " + interval[2][1] + "[\n");
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

        txtArea.clear();
        String anovaTable = String.format("ANOVA Table\n" +
                        "-------------------------------------------------------------------------------------\n" +
                        "%-10s %-22s %-5s %-22s %-10s %-10s\n" +
                        "-------------------------------------------------------------------------------------\n" +
                        "%-10s %-22f %-5d %-22f %-10f %-10f\n" +
                        "%-10s %-22f %-5d %-22f\n" +
                        "%-10s %-22f %-5d\n" +
                        "-------------------------------------------------------------------------------------\n",
                "Source", "SS", "DF", "MS", "F", "p-value",
                "Regression", regressionSumOfSquares, regressionDegreesOfFreedom, regressionMeanSquare, fValue, pValue,
                "Residual", residualSumOfSquares, residualDegreesOfFreedom, residualMeanSquare,
                "Total", totalSumOfSquares, totalDegreesOfFreedom);



        txtArea.setFont(Font.font("Monospaced"));


        txtArea.appendText(anovaTable);
    }

    @FXML
    private void onBtValues(ActionEvent event) {

    }

    @FXML
    private void onBtGraphic(ActionEvent event) {

    }

    @FXML
    private void onBtReturn(ActionEvent event) {

    }


}

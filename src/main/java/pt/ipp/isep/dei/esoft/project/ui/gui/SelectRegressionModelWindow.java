package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.StatisticsController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectRegressionModelWindow implements Initializable {

    private final StatisticsController controller;
    private Scene showStatisticsScene;
    @FXML
    private Button btMultiple;

    @FXML
    private Button btReturn;

    @FXML
    private Button btSimpleArea;

    @FXML
    private Button btSimpleBathrooms;

    @FXML
    private Button btSimpleBedrooms;

    @FXML
    private Button btSimpleDistance;

    @FXML
    private Button btSimpleParking;
    @FXML
    private Button btConfidence90;
    @FXML
    private Button btConfidence95;
    @FXML
    private Button btConfidence99;

    @FXML
    private Label lbConfidence;

    public SelectRegressionModelWindow() {
        controller = new StatisticsController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader showStatisticsLoader = new FXMLLoader();
            showStatisticsLoader.setLocation(getClass().getResource("/ShowStatisticsScene.fxml"));
            Parent showStatisticsRoot = showStatisticsLoader.load();
            showStatisticsScene = new Scene(showStatisticsRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stage getMainStage() {
        return (Stage) this.btReturn.getScene().getWindow();
    }


    @FXML
    void onBtReturn(ActionEvent event) {
        Stage mainStage = getMainStage();
        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/AnalyseDealsScene.fxml"));
        Parent mainMenuRoot = null;
        try {
            mainMenuRoot = mainMenuLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene mainMenuScene = new Scene(mainMenuRoot);
        mainStage.setScene(mainMenuScene);
        mainStage.setTitle("Sale Forecast");
        mainStage.show();
    }

    @FXML
    void onBtMultiple(ActionEvent event) {
        ArrayList<Announcement> deals = controller.getDeals();
        controller.setIsMultipleRegression(true);
        controller.calcMultipleRegression(deals);
        Stage mainStage = getMainStage();
        mainStage.setScene(showStatisticsScene);
        mainStage.setTitle("Statistics");
        mainStage.show();
    }

    @FXML
    void onBtSimpleArea(ActionEvent event) {
        ArrayList<Announcement> deals = controller.getDeals();
        controller.setIsMultipleRegression(false);
        controller.calcSimpleRegressionArea(deals);
        Stage mainStage = getMainStage();
        mainStage.setScene(showStatisticsScene);
        mainStage.setTitle("Statistics");
        mainStage.show();
    }

    @FXML
    void onBtSimpleBathrooms(ActionEvent event) {
        ArrayList<Announcement> deals = controller.getDeals();
        controller.setIsMultipleRegression(false);
        controller.calcSimpleRegressionBathrooms(deals);
        Stage mainStage = getMainStage();
        mainStage.setScene(showStatisticsScene);
        mainStage.setTitle("Statistics");
        mainStage.show();
    }

    @FXML
    void onBtSimpleBedrooms(ActionEvent event) {
        ArrayList<Announcement> deals = controller.getDeals();
        controller.setIsMultipleRegression(false);
        controller.calcSimpleRegressionBedrooms(deals);
        Stage mainStage = getMainStage();
        mainStage.setScene(showStatisticsScene);
        mainStage.setTitle("Statistics");
        mainStage.show();
    }

    @FXML
    void onBtSimpleDistance(ActionEvent event) {
        ArrayList<Announcement> deals = controller.getDeals();
        controller.setIsMultipleRegression(false);
        controller.calcSimpleRegressionDistance(deals);
        Stage mainStage = getMainStage();
        mainStage.setScene(showStatisticsScene);
        mainStage.setTitle("Statistics");
        mainStage.show();
    }

    @FXML
    void onBtSimpleParking(ActionEvent event) {
        ArrayList<Announcement> deals = controller.getDeals();
        controller.setIsMultipleRegression(false);
        controller.calcSimpleRegressionParkingSpaces(deals);
        Stage mainStage = getMainStage();
        mainStage.setScene(showStatisticsScene);
        mainStage.setTitle("Statistics");
        mainStage.show();
    }

    @FXML
    private void onBtConfidence90(ActionEvent event) {
        controller.setConfidenceLevel(0.9);
        lbConfidence.setText("Confidence = 90%");
    }

    @FXML
    private void onBtConfidence95(ActionEvent event) {
        controller.setConfidenceLevel(0.95);
        lbConfidence.setText("Confidence = 95%");
    }

    @FXML
    private void onBtConfidence99(ActionEvent event) {
        controller.setConfidenceLevel(0.99);
        lbConfidence.setText("Confidence = 99%");
    }

    public void setLabel(String label) {
        lbConfidence.setText(label);
    }


}

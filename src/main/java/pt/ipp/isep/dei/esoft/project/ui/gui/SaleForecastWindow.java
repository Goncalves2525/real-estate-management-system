package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import pt.ipp.isep.dei.esoft.project.application.controller.ImportController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SaleForecastWindow implements Initializable {

    private final ImportController controller;
    private Scene selectRegressionModelScene;
    @FXML
    private Button btImport;

    @FXML
    private Button btReturn;
    @FXML
    private Button btDeals;

    @FXML
    private TextField txtField;

    public SaleForecastWindow() {
        controller = new ImportController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader selectRegressionLoader = new FXMLLoader();
            selectRegressionLoader.setLocation(getClass().getResource("/SelectRegressionModelScene.fxml"));
            Parent selectRegressionRoot = selectRegressionLoader.load();
            selectRegressionModelScene = new Scene(selectRegressionRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stage getMainStage() {
        return (Stage) this.btReturn.getScene().getWindow();
    }

    @FXML
    private void onBtImport(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();

        // Set the title of the file chooser dialog
        fileChooser.setTitle("Select File");

        // Show the file chooser dialog
        Stage mainStage = getMainStage();
        String selectedDirectory = fileChooser.showOpenDialog(mainStage).getAbsolutePath();
        if (!Objects.equals(selectedDirectory, "")) {
            ArrayList<String[]> dataToImport = controller.readFile(selectedDirectory, ";");
            String importResult = controller.importData(dataToImport);
            System.out.println(importResult);
            ArrayList<Announcement> deals = controller.getDeals();
        }

        mainStage = getMainStage();
        mainStage.setScene(selectRegressionModelScene);
        mainStage.setTitle("Select Regression Model");
        mainStage.show();
    }

    @FXML
    private void onBtDeals(ActionEvent event){
        ArrayList<Announcement> deals = controller.getDeals();

        Stage mainStage = getMainStage();
        mainStage.setScene(selectRegressionModelScene);
        mainStage.setTitle("Select Regression Model");
        mainStage.show();
    }

    @FXML
    private void onBtReturn(ActionEvent actionEvent){
        Stage mainStage = getMainStage();
        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/StoreManagerMenuScene.fxml"));
        Parent mainMenuRoot = null;
        try {
            mainMenuRoot = mainMenuLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene mainMenuScene = new Scene(mainMenuRoot);
        mainStage.setScene(mainMenuScene);
        mainStage.setTitle("Store Manager Menu");
        mainStage.show();
    }
}

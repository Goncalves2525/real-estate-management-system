package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuWindow implements Runnable{

    private MainMenuWindow mainMenuWindow;
    private Stage primaryStage;


    @FXML
    private Button btRegisterEmployee;

    @FXML
    private Button btImportCSV;

    @FXML
    private Button btLogout;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void associateParentUI(MainMenuWindow mainMenuWindow) {
        this.mainMenuWindow = mainMenuWindow;
    }


    @Override
    public void run() {


    }




    @FXML
    private void onbtRegisterEmployee(javafx.event.ActionEvent event) {

    }

    @FXML
    private void onbtImportCSV(javafx.event.ActionEvent event) {

    }

    @FXML
    private void onbtLogout(javafx.event.ActionEvent event) {
        btLogout.getScene().getWindow().hide();
    }


}

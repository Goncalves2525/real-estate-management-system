package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class EmployeeMenuWindow implements Runnable {
    @FXML
    private Label lb;

    @FXML
    private Button btAppointmentRequest;

    @FXML
    private Button btReturn;

    @Override
    public void run() {

    }

    @FXML
    public void onBtAppointmentRequest(ActionEvent actionEvent) {

    }

    @FXML
    public void onBtReturn(ActionEvent actionEvent) {
        Stage mainStage = getMainStage();
        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/MainMenuScene.fxml"));
        Parent mainMenuRoot = null;
        try {
            mainMenuRoot = mainMenuLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene mainMenuScene = new Scene(mainMenuRoot);
        mainStage.setScene(mainMenuScene);
        mainStage.setTitle("Real Estate USA");
        mainStage.show();
    }

    private Stage getMainStage() {
        return (Stage) btReturn.getScene().getWindow();
    }
}

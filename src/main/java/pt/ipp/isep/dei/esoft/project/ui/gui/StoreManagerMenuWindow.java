package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StoreManagerMenuWindow implements Initializable {

    private Scene saleForecastScene;

    @FXML
    private Button bt;
    @FXML
    private Button btReturn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader saleForecastLoader = new FXMLLoader();
            saleForecastLoader.setLocation(getClass().getResource("/AnalyseDealsScene.fxml"));
            Parent saleForecastRoot = saleForecastLoader.load();
            saleForecastScene = new Scene(saleForecastRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stage getMainStage() {
        return (Stage) this.bt.getScene().getWindow();
    }

    @FXML
    private void onBtAction(ActionEvent event) {
        Stage mainStage = getMainStage();
        mainStage.setScene(saleForecastScene);
        mainStage.setTitle("Sale Forecast");
        mainStage.show();
    }

    @FXML
    private void onBtReturn(ActionEvent event) {
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
}

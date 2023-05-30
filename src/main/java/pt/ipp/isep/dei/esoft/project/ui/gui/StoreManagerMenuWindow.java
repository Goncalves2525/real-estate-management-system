package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StoreManagerMenuWindow implements Initializable {

    private Scene saleForecastScene;

    @FXML
    private Button bt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader saleForecastLoader = new FXMLLoader();
            saleForecastLoader.setLocation(getClass().getResource("/SaleForecastScene.fxml"));
            Parent saleForecastRoot = saleForecastLoader.load();
            saleForecastScene = new Scene(saleForecastRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onBtAction(ActionEvent event) {

    }
}

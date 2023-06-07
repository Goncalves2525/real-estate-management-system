package pt.ipp.isep.dei.esoft.project.ui.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
//import javafx.stage.FileChooser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NetworkManagerMenuWindow implements Initializable {

    @FXML
    private Button btSort;

    @FXML
    private Button btReturn;

    @FXML
    private Button btnDivideStores;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    private Stage getSortStage() {
        return (Stage) this.btSort.getScene().getWindow();
    }

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

    private Stage getMainStage()
    {
        return (Stage) this.btReturn.getScene().getWindow();
    }


    public void onBtSelectionSort(ActionEvent event) {
    }

    public void onBtSort(ActionEvent event) {
        Stage mainStage = getSortStage();
        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/NetworkManagerSortScene.fxml"));
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

    public void onBtnDivideStores(ActionEvent actionEvent) {
        Stage mainStage = getSortStage();
        FXMLLoader divideStoresLoader = new FXMLLoader(getClass().getResource("/NetworkManagerDivideStoresScene.fxml"));
        Parent mainMenuRoot = null;
        try {
            mainMenuRoot = divideStoresLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene mainMenuScene = new Scene(mainMenuRoot);
        mainStage.setScene(mainMenuScene);
        mainStage.setTitle("Real Estate USA");
        mainStage.show();
    }
}

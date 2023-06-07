package pt.ipp.isep.dei.esoft.project.ui.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ImportController;
import pt.ipp.isep.dei.esoft.project.application.controller.StoreDivisionController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class NetworkManagerDivideStoresWindow implements Initializable {
    private final StoreDivisionController storeDivisionController = new StoreDivisionController();
    private final ImportController importController = new ImportController();
    @FXML
    private Button btReturn;
    @FXML
    private Button btImport;
    @FXML
    private Label importOperationLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void onBtReturn(ActionEvent actionEvent) {
        Stage mainStage = getMainStage();
        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/NetworkManagerMenuScene.fxml"));
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
        return (Stage) this.btReturn.getScene().getWindow();
    }


    public void onBtImport(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Set the title of the file chooser dialog
        fileChooser.setTitle("Select File");

        // Show the file chooser dialog
        Stage mainStage = getMainStage();
        String selectedDirectory = fileChooser.showOpenDialog(mainStage).getAbsolutePath();
        if (!Objects.equals(selectedDirectory, "")) {
            ArrayList<String[]> dataToImport = importController.readFile(selectedDirectory, ";");
            String importResult = importController.importDatatoprperty(dataToImport);
            importOperationLabel.setText(importResult);
            System.out.println(importResult);
        }
    }
}

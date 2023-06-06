package pt.ipp.isep.dei.esoft.project.ui.gui;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ImportController;
import pt.ipp.isep.dei.esoft.project.application.controller.PropertyOrderManagementController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class NetworkManagerSortWindow implements Initializable {
    private final PropertyOrderManagementController controller = new PropertyOrderManagementController();
    private final ImportController importController = new ImportController();
    @FXML
    private TableView<Property> PropertyTableView;
    @FXML
    private Button btAscendingSortInsertion;
    @FXML
    private Button btDescendingSortInsertion;
    @FXML
    private Button btAscendingSortSelection;
    @FXML
    private Button btDescendingSortSelection;
    @FXML
    private Button btReturn;
    @FXML
    private Button btImport;

    @FXML
    private TableColumn<Property, String> id;
    @FXML
    private TableColumn<Property, String> address;
    @FXML
    private TableColumn<Property, String> area;
    @FXML
    private TableColumn<Property, String> typeOfProperty;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        area.setCellValueFactory(new PropertyValueFactory<>("Area"));
        //typeOfProperty.setCellValueFactory(new PropertyValueFactory<>("TypeOfProperty"));

    }

    public void onbtAscendingSortInsertion(ActionEvent actionEvent) {
       ObservableList<Property> observableList = FXCollections.observableArrayList(controller.getPropertiesInsertionSortByAreaAscending());
        PropertyTableView.setItems(observableList);

    }

    public void onbtDescendingSortInsertion(ActionEvent actionEvent) {
        ObservableList<Property> observableList = FXCollections.observableArrayList(controller.getPropertiesInsertionSortByAreaDescending());
        PropertyTableView.setItems(observableList);
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
            System.out.println(importResult);
        }
    }


    public void onbtAscendingSortSelection(ActionEvent event) {
        ObservableList<Property> observableList = FXCollections.observableArrayList(controller.getPropertiesSelectionSortByAreaAscending());
        PropertyTableView.setItems(observableList);
    }

    public void onbtDescendingSortSelection(ActionEvent event) {
        ObservableList<Property> observableList = FXCollections.observableArrayList(controller. getPropertiesSelectionSortByAreaDescending());
        PropertyTableView.setItems(observableList);

    }
}

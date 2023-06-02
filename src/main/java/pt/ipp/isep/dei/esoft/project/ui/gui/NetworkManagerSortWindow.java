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
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.PropertyOrderManagementController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class NetworkManagerSortWindow implements Initializable {
    private final PropertyOrderManagementController controller = new PropertyOrderManagementController();
    @FXML
    private TableView<Property> propertyTable;
    @FXML
    private Button btAscendingSort;
    @FXML
    private Button btDescendingSort;
    @FXML
    private Button btReturn;
    @FXML
    private TableColumn<Property, Integer> id;
    @FXML
    private TableColumn<Property, TypeOfProperty> typeOfProperty;
    @FXML
    private TableColumn<Property, TransactionType> transactionType;
    @FXML
    private TableColumn<Property, Date> publishDate;
    @FXML
    private TableColumn<Property, Commission> commission;
    @FXML
    private TableColumn<Property, Double> area;
    @FXML
    private TableColumn<Property, Double> distanceFromCenter;
    @FXML
    private TableColumn<Property, Double> price;
    @FXML
    private TableColumn<Property, Address> address;
    @FXML
    private TableColumn<Property, Integer> numberOfBedrooms;
    @FXML

    private TableColumn<Property, Integer> numberOfBathrooms;
    @FXML
    private TableColumn<Property, Integer> numberOfParkingSpaces;
    @FXML
    private TableColumn<Property, Boolean> hasCentralHeating;
    @FXML
    private TableColumn<Property, Boolean> hasAirConditioning;
    @FXML
    private TableColumn<Property, Boolean> hasInabitableLoft;
    @FXML
    private TableColumn<Property, Boolean> hasBasement;
    @FXML
    private TableColumn<Property, SunExposure> sunExposure;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeOfProperty.setCellValueFactory(new PropertyValueFactory<>("typeOfProperty"));
        transactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        publishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        commission.setCellValueFactory(new PropertyValueFactory<>("commission"));
        area.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getArea()).asObject());



    }

    public void onbtAscendingSort(ActionEvent actionEvent) {
        ObservableList<Property> observableList = FXCollections.observableArrayList(controller.getPropertiesInsertionSortByAreaAscending());
        propertyTable.setItems(observableList);
    }

    public void onbtDescendingSort(ActionEvent actionEvent) {
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


}

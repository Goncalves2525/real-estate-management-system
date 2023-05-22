package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import pt.ipp.isep.dei.esoft.project.application.controller.ListAnnouncementsController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ListAnnouncementsWindow implements Initializable {

    private ListAnnouncementsController controller;

    @FXML
    private TableView<Announcement> announcementTable;
    @FXML
    private TableColumn<Announcement, Integer> id;
    @FXML
    private TableColumn<Announcement, TypeOfProperty> typeOfPropoerty;
    @FXML
    private TableColumn<Announcement, TransactionType> transactionType;
    @FXML
    private TableColumn<Announcement, Date> date;
    @FXML
    private TableColumn<Announcement, Commission> comission;
    @FXML
    private TableColumn<Announcement, Double> area;
    @FXML
    private TableColumn<Announcement, Integer> distanceFromCenter;
    @FXML
    private TableColumn<Announcement, Double> price;
    @FXML
    private TableColumn<Announcement, Address> address;
    @FXML
    private TableColumn<Announcement, Integer> numberOfBedrooms;
    @FXML
    private TableColumn<Announcement, Integer> numberOfBathRooms;
    @FXML
    private TableColumn<Announcement, Integer> numberOfParkingSpaces;
    @FXML
    private Button applyFilterButton;

    @FXML
    private Button applySortButton;
    @FXML
    private Button returnButton;

    @FXML
    private RadioButton rentRadio;

    @FXML
    private RadioButton saleRadio;

    @FXML
    private ComboBox<?> sortComboBox;

    @FXML
    private ComboBox<?> typeComboBox;

    @FXML
    private ComboBox<?> typeComboBox1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //id.setCellValueFactory(new PropertyValueFactory<>("ID"));
    }


    @FXML
    private void onReturnToMainMenuAction() throws IOException {
        Stage mainStage = getMainStage();

        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/MainMenuScene.fxml"));
        Parent mainMenuRoot = mainMenuLoader.load();
        Scene mainMenuScene = new Scene(mainMenuRoot);
        mainStage.setScene(mainMenuScene);
        mainStage.setTitle("Real Estate USA");
        mainStage.show();
    }

    private Stage getMainStage() {
        return (Stage) returnButton.getScene().getWindow();
    }

    private Scene getCurrentScene() {
        return returnButton.getScene();
    }

    @FXML
    private void applyFilters(ActionEvent event) {

    }

    @FXML
    private void applySort(ActionEvent event) {

    }


}

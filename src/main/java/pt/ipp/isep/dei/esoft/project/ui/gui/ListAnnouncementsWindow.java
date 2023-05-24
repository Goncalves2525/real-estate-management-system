package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.stage.Modality;
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

    private final ListAnnouncementsController controller = new ListAnnouncementsController();
    private Stage createOrderWindow;
    @FXML
    private TableView<Announcement> announcementTable;
    @FXML
    private TableColumn<Announcement, Integer> id;
    @FXML
    private TableColumn<Announcement, TypeOfProperty> typeOfProperty;
    @FXML
    private TableColumn<Announcement, TransactionType> transactionType;
    @FXML
    private TableColumn<Announcement, Date> publishDate;
    @FXML
    private TableColumn<Announcement, Commission> commission;
    @FXML
    private TableColumn<Announcement, Double> area;
    @FXML
    private TableColumn<Announcement, Double> distanceFromCenter;
    @FXML
    private TableColumn<Announcement, Double> price;
    @FXML
    private TableColumn<Announcement, Address> address;
    @FXML
    private TableColumn<Announcement, Integer> numberOfBedrooms;
    @FXML
    private TableColumn<Announcement, Integer> numberOfBathrooms;
    @FXML
    private TableColumn<Announcement, Integer> numberOfParkingSpaces;
    @FXML
    private TableColumn<Announcement, Boolean> hasCentralHeating;
    @FXML
    private TableColumn<Announcement, Boolean> hasAirConditioning;
    @FXML
    private TableColumn<Announcement, Boolean> hasInabitableLoft;
    @FXML
    private TableColumn<Announcement, Boolean> hasBasement;
    @FXML
    private TableColumn<Announcement, SunExposure> sunExposure;
    @FXML
    private Button applyFilterButton;

    @FXML
    private Button returnButton;
    @FXML
    private Button clearFiltersButton;
    @FXML
    private Button btPlaceOrder;

    @FXML
    private RadioButton rentRadio;

    @FXML
    private RadioButton saleRadio;

    @FXML
    private ComboBox<String> propertyTypeComboBox;

    @FXML
    private ComboBox<String> numberOfBedroomsComboBox;

    @FXML
    private ToggleGroup transactionTypeGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeOfProperty.setCellValueFactory(new PropertyValueFactory<>("typeOfProperty"));
        transactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        publishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        commission.setCellValueFactory(new PropertyValueFactory<>("commission"));
        area.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            return new SimpleDoubleProperty(property.getArea()).asObject();
        });
        distanceFromCenter.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            return new SimpleDoubleProperty(property.getDistanceFromCenter()).asObject();
        });
        price.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            return new SimpleDoubleProperty(property.getPrice()).asObject();
        });
        address.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            return new SimpleObjectProperty<>(property.getAddress());
        });
        numberOfBedrooms.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            if (property instanceof Residency) {
                int numberOfRooms = ((Residency) property).getNumberOfBedrooms();
                return new SimpleIntegerProperty(numberOfRooms).asObject();
            } else {
                return new SimpleIntegerProperty(0).asObject();
            }
        });
        numberOfBathrooms.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            if (property instanceof Residency) {
                int numberOfRooms = ((Residency) property).getNumberOfBathrooms();
                return new SimpleIntegerProperty(numberOfRooms).asObject();
            } else {
                return new SimpleIntegerProperty(0).asObject();
            }
        });
        numberOfParkingSpaces.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            if (property instanceof Residency) {
                int numberOfRooms = ((Residency) property).getNumberOfParkingSpaces();
                return new SimpleIntegerProperty(numberOfRooms).asObject();
            } else {
                return new SimpleIntegerProperty(0).asObject();
            }
        });
        hasCentralHeating.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            if (property instanceof Residency) {
                boolean hasCentralHeating = ((Residency) property).getHasCentralHeating();
                return new SimpleObjectProperty<>(hasCentralHeating);
            } else {
                return new SimpleObjectProperty<>(false);
            }
        });
        hasAirConditioning.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            if (property instanceof Residency) {
                boolean hasAirConditioning = ((Residency) property).getHasAirConditioning();
                return new SimpleObjectProperty<>(hasAirConditioning);
            } else {
                return new SimpleObjectProperty<>(false);
            }
        });
        hasInabitableLoft.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            if (property instanceof House) {
                boolean hasInabitableLoft = ((House) property).getHasInhabitableLoft();
                return new SimpleObjectProperty<>(hasInabitableLoft);
            } else {
                return new SimpleObjectProperty<>(false);
            }
        });
        hasBasement.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            if (property instanceof House) {
                boolean hasBasement = ((House) property).getHasBasement();
                return new SimpleObjectProperty<>(hasBasement);
            } else {
                return new SimpleObjectProperty<>(false);
            }
        });
        sunExposure.setCellValueFactory(data -> {
            Announcement announcement = data.getValue();
            Property property = controller.getPropertyByAnnouncement(announcement);
            if (property instanceof House) {
                SunExposure sunExposure = ((House) property).getSunExposure();
                return new SimpleObjectProperty<>(sunExposure);
            } else {
                return new SimpleObjectProperty<>(null);
            }
        });

        try{
            FXMLLoader createOrderLoader = new FXMLLoader();
            createOrderLoader.setLocation(getClass().getResource("/CreateOrderScene.fxml"));
            Parent createOrderRoot = createOrderLoader.load();
            Scene scene = new Scene(createOrderRoot);
            createOrderWindow = new Stage();
            createOrderWindow.initModality(Modality.APPLICATION_MODAL);
            createOrderWindow.setTitle("Create Order");
            createOrderWindow.setResizable(false);
            createOrderWindow.setScene(scene);
            CreateOrderWindow createOrderController = createOrderLoader.getController();
            createOrderController.associateParentUI(this);
        }catch (IOException e){
            throw new RuntimeException(e);
        }


    }

    @FXML
    private void onReturnToMainMenuAction(){
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
        return (Stage) returnButton.getScene().getWindow();
    }


    @FXML
    private void applyFilters(ActionEvent event) {
        String stringTypeOfProperty = null;
        TypeOfProperty typeOfProperty = null;
        TransactionType transactionType = null;
        String stringNumberOfRooms = null;
        int numberOfRooms = -1;

        if (propertyTypeComboBox.getValue() != null) {
            stringTypeOfProperty = propertyTypeComboBox.getValue();
            switch (stringTypeOfProperty) {
                case "House":
                    typeOfProperty = TypeOfProperty.HOUSE;
                    break;
                case "Apartment":
                    typeOfProperty = TypeOfProperty.APARTMENT;
                    break;
                case "Land":
                    typeOfProperty = TypeOfProperty.LAND;
                    break;
            }
        }
        if (numberOfBedroomsComboBox.getValue() != null) {
            stringNumberOfRooms = numberOfBedroomsComboBox.getValue();
            switch (stringNumberOfRooms) {
                case "1":
                    numberOfRooms = 1;
                    break;
                case "2":
                    numberOfRooms = 2;
                    break;
                case "3":
                    numberOfRooms = 3;
                    break;
                case "4":
                    numberOfRooms = 4;
                    break;
                case "5":
                    numberOfRooms = 5;
                    break;
            }
        }
        if (rentRadio.isSelected()) {
            transactionType = TransactionType.RENT;
        } else if (saleRadio.isSelected()) {
            transactionType = TransactionType.SALE;
        }

        ArrayList<Announcement> announcements;


        if (propertyTypeComboBox.getValue() == null && numberOfBedroomsComboBox.getValue() == null && !rentRadio.isSelected() && !saleRadio.isSelected()) {
            announcements = controller.getAllAnnouncementsSortedByDefualtCriteria();
            ObservableList<Announcement> announcementsList = FXCollections.observableArrayList(announcements);
            announcementTable.setItems(announcementsList);

        } else {
            announcements = controller.getFilteredAnnouncementsForGUI(typeOfProperty, transactionType, numberOfRooms);
            ObservableList<Announcement> announcementsList = FXCollections.observableArrayList(announcements);
            announcementTable.setItems(announcementsList);
        }

    }

    @FXML
    private void clearFilters(ActionEvent event) {
        propertyTypeComboBox.setValue(null);
        numberOfBedroomsComboBox.setValue(null);
        propertyTypeComboBox.setPromptText("Type of Property");
        numberOfBedroomsComboBox.setPromptText("Number of Rooms");
        rentRadio.setSelected(false);
        saleRadio.setSelected(false);
    }

    public Button getBtPlaceOrder() {
        return btPlaceOrder;
    }

    @FXML
    private void onBtPlaceOrder(ActionEvent actionEvent) {
        createOrderWindow.show();
    }
}

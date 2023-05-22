package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterEmployeeWindow {

    @FXML
    private Button btnRegisterEmployee;

    @FXML
    private Button btnBackToAdminMenu;

    @FXML
    private ChoiceBox<String> choiceBoxRoles;

    @FXML
    private ChoiceBox<String> choiceBoxAgency;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassaportCardNumber;

    @FXML
    private TextField txtTaxNumber;

    @FXML
    private TextField txtTelephoneNumber;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField txtState;

    @FXML
    private TextField txtDistrict;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtZipCode;


    @FXML
    void handleButtonRegisterEmployee(ActionEvent event) {

    }


    @FXML
    void onbtBackToAdminMenu(ActionEvent event) {
        try {
            FXMLLoader authLoader = new FXMLLoader();
            authLoader.setLocation(getClass().getResource("/AdminMenuScene.fxml"));
            Parent authRoot = authLoader.load();
            Scene scene = new Scene(authRoot);
            Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

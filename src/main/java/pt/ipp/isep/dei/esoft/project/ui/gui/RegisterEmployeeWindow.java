package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.io.IOException;
import java.util.ArrayList;

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
    private CheckBox checkboxAdress;

    @FXML
    private Label lbAdressInformation;

    @FXML
    private Label lbState;

    @FXML
    private Label lbDistrict;

    @FXML
    private Label lbCity;

    @FXML
    private Label lbStreet;

    @FXML
    private Label lbZipCode;
    

    @FXML
    private ListView<CheckBox> lvRoles;

    private RegisterEmployeeController controller;

    public RegisterEmployeeWindow() {
        this.controller = new RegisterEmployeeController();
    }

    @FXML
    public void initialize() {
        ArrayList<Role> rolesList = this.controller.getRolesToCreate();
        for (Role role : rolesList) {
            CheckBox checkBox = new CheckBox(role.toString());
            lvRoles.getItems().add(checkBox);
        }
    }





    @FXML
    void handleButtonRegisterEmployee(ActionEvent event) {

    }

    @FXML
    void handleCheckboxAdress(ActionEvent event) {
        boolean selected = checkboxAdress.isSelected();
        lbAdressInformation.setVisible(selected);
        lbState.setVisible(selected);
        lbDistrict.setVisible(selected);
        lbCity.setVisible(selected);
        lbStreet.setVisible(selected);
        lbZipCode.setVisible(selected);
        txtState.setVisible(selected);
        txtDistrict.setVisible(selected);
        txtCity.setVisible(selected);
        txtStreet.setVisible(selected);
        txtZipCode.setVisible(selected);
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

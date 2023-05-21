package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.*;


public class AuthenticationWindow implements Initializable {

    private MainMenuWindow mainMenuWindow;
    private Stage AdminMenuWindow;
    private Stage EmployeeMenuWindow;
    private Stage ClientMenuWindow;
    @FXML
    private Button btLogin;
    @FXML
    private Button btReturn;
    @FXML
    private TextField txtBoxEmail;
    @FXML
    private TextField txtBoxPassword;

    public void associateParentUI(MainMenuWindow mainMenuWindow) {
        this.mainMenuWindow = mainMenuWindow;
    }

    @FXML
    private void closeAuthenticationUI(javafx.event.ActionEvent event){
        txtBoxEmail.clear();
        txtBoxPassword.clear();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            FXMLLoader clientLoader = new FXMLLoader();
            clientLoader.setLocation(getClass().getResource("/ClientMenuScene.fxml"));
            Parent clientRoot = clientLoader.load();
            Scene clientScene = new Scene(clientRoot);
            ClientMenuWindow = new Stage();
            ClientMenuWindow.initModality(Modality.APPLICATION_MODAL);
            ClientMenuWindow.setTitle("Client Menu");
            ClientMenuWindow.setResizable(false);
            ClientMenuWindow.setScene(clientScene);

            FXMLLoader employeeLoader = new FXMLLoader();
            employeeLoader.setLocation(getClass().getResource("/EmployeeMenuScene.fxml"));
            Parent employeeRoot = employeeLoader.load();
            Scene employeeScene = new Scene(employeeRoot);
            EmployeeMenuWindow = new Stage();
            EmployeeMenuWindow.initModality(Modality.APPLICATION_MODAL);
            EmployeeMenuWindow.setTitle("Client Menu");
            EmployeeMenuWindow.setResizable(false);
            EmployeeMenuWindow.setScene(employeeScene);

            FXMLLoader adminLoader = new FXMLLoader();
            adminLoader.setLocation(getClass().getResource("/AdminMenuScene.fxml"));
            Parent adminRoot = adminLoader.load();
            Scene adminScene = new Scene(adminRoot);
            AdminMenuWindow = new Stage();
            AdminMenuWindow.initModality(Modality.APPLICATION_MODAL);
            AdminMenuWindow.setTitle("Administrator Menu");
            AdminMenuWindow.setResizable(false);
            AdminMenuWindow.setScene(adminScene);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void onBtReturnAction(javafx.event.ActionEvent event) {
        closeAuthenticationUI(event);
    }

    @FXML
    private void onBtLoginAction() {
        boolean success = false;
        success = mainMenuWindow.getAuthenticationController().doLogin(txtBoxEmail.getText(), txtBoxPassword.getText());

        if (success) {
            List<UserRoleDTO> roles = mainMenuWindow.getAuthenticationController().getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                createAlert(Alert.AlertType.WARNING, "Roles", "No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI, role);
                } else {
                    createAlert(Alert.AlertType.WARNING, "Roles", "No roles selected");
                }
            }
        }
        else {
            createAlert(Alert.AlertType.ERROR, "Login", "Login failed.");
        }
    }

    private List<MenuItem> getMenuItemForRoles() {
        List<UserRoleDTO> roles = mainMenuWindow.getAuthenticationController().getUserRoles();
        List<MenuItem> rolesUI = new ArrayList<>();
        for(UserRoleDTO role : roles){
            if(role.getDescription().equals(AuthenticationController.ROLE_ADMIN)){
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new AdminMenuWindow()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_EMPLOYEE)) {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_EMPLOYEE, new EmployeeMenuWindow()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_CLIENT)) {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_CLIENT, new ClientMenuWindow()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_STORE_NETWORK_MANAGER)) {
                //currently not in use; ignore the UI used
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_STORE_NETWORK_MANAGER, new MainMenuWindow()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_STORE_MANAGER)){
                //currently not in use; ignore the UI used
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_STORE_NETWORK_MANAGER, new MainMenuWindow()));
            } else {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_NO_ROLE, new MainMenuWindow()));
            }
        }

        return rolesUI;
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
        }
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                if (item.toString().equals(AuthenticationController.ROLE_ADMIN)) {
                    AdminMenuWindow.show();
                } else if (item.toString().equals(AuthenticationController.ROLE_EMPLOYEE)) {
                    EmployeeMenuWindow.show();
                } else if (item.toString().equals(AuthenticationController.ROLE_CLIENT)) {
                    ClientMenuWindow.show();
                }
            }
        }
        if (!found) {
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
        }
    }

    private Alert createAlert(Alert.AlertType alertType, String header, String message) {
        Alert alert = new Alert(alertType);

        alert.setTitle("ALERT");
        alert.setHeaderText(header);
        alert.setContentText(message);

        return alert;
    }

    @FXML
    public void onPasswordEnterPressed(javafx.scene.input.KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onBtLoginAction();
        }
    }



}

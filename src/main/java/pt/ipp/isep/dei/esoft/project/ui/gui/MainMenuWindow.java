package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainMenuWindow implements Runnable, Initializable {

    private final AuthenticationController authenticationController;
    private Scene listScene;
    private Scene employeeScene;
    private Scene adminScene;
    private Scene clientScene;
    private Scene storeManagerScene;
    private Scene networkManagerScene;
    @FXML
    private Button btLogin;
    @FXML
    private Button btListAnnouncements;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPass;


    public MainMenuWindow() {
        authenticationController = new AuthenticationController();
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            FXMLLoader clientLoader = new FXMLLoader();
            clientLoader.setLocation(getClass().getResource("/ClientMenuScene.fxml"));
            Parent clientRoot = clientLoader.load();
            clientScene = new Scene(clientRoot);

            FXMLLoader employeeLoader = new FXMLLoader();
            employeeLoader.setLocation(getClass().getResource("/EmployeeMenuScene.fxml"));
            Parent employeeRoot = employeeLoader.load();
            employeeScene = new Scene(employeeRoot);

            FXMLLoader adminLoader = new FXMLLoader();
            adminLoader.setLocation(getClass().getResource("/AdminMenuScene.fxml"));
            Parent adminRoot = adminLoader.load();
            adminScene = new Scene(adminRoot);

            FXMLLoader storeManagerLoader = new FXMLLoader();
            storeManagerLoader.setLocation(getClass().getResource("/StoreManagerMenuScene.fxml"));
            Parent storeManagerRoot = storeManagerLoader.load();
            storeManagerScene = new Scene(storeManagerRoot);

            FXMLLoader networkManagerLoader = new FXMLLoader();
            networkManagerLoader.setLocation(getClass().getResource("/NetworkManagerMenuScene.fxml"));
            Parent networkManagerRoot = networkManagerLoader.load();
            networkManagerScene = new Scene(networkManagerRoot);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private Stage getMainStage() {
        return (Stage) this.btLogin.getScene().getWindow();
    }

    public AuthenticationController getAuthenticationController() {
        return authenticationController;
    }

    @FXML
    private void onBtListAnnouncementsAction() throws IOException {
        FXMLLoader listLoader = new FXMLLoader();
        listLoader.setLocation(getClass().getResource("/ListAnnouncementsScene.fxml"));
        Parent listRoot = listLoader.load();
        ListAnnouncementsWindow listAnnouncementsWindow = listLoader.getController();
        listAnnouncementsWindow.getBtPlaceOrder().setVisible(false);
        listScene = new Scene(listRoot);
        Stage mainStage = getMainStage();
        mainStage.setScene(listScene);
        mainStage.setTitle("List Announcements");
        mainStage.show();
    }

    @FXML
    private void onBtLoginAction() {
        boolean success = false;
        Alert alert;
        success = getAuthenticationController().doLogin(txtEmail.getText(), txtPass.getText());


        if (success) {
            List<UserRoleDTO> roles = getAuthenticationController().getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                createAlert(Alert.AlertType.WARNING, "Roles", "No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI, role);
                } else {
                    alert = createAlert(Alert.AlertType.WARNING, "Roles", "No roles selected");
                    alert.show();
                }
            }
        }
        else {
            alert = createAlert(Alert.AlertType.ERROR, "Login", "Login failed.");
            alert.show();
        }
    }

    private List<MenuItem> getMenuItemForRoles() {
        List<UserRoleDTO> roles = getAuthenticationController().getUserRoles();
        List<MenuItem> rolesUI = new ArrayList<>();
        for(UserRoleDTO role : roles){
            if(role.getDescription().equals(AuthenticationController.ROLE_ADMIN)){
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new MainMenuWindow()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_EMPLOYEE)) {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_EMPLOYEE, new MainMenuWindow()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_CLIENT)) {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_CLIENT, new MainMenuWindow()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_STORE_NETWORK_MANAGER)) {

                rolesUI.add(new MenuItem(AuthenticationController.ROLE_STORE_NETWORK_MANAGER, new MainMenuWindow()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_STORE_MANAGER)){

                rolesUI.add(new MenuItem(AuthenticationController.ROLE_STORE_MANAGER, new MainMenuWindow()));
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
                    txtEmail.clear();
                    txtPass.clear();
                    Stage mainStage = getMainStage();
                    mainStage.setScene(adminScene);
                    mainStage.setTitle("Admin Menu");
                    mainStage.show();
                } else if (item.toString().equals(AuthenticationController.ROLE_EMPLOYEE)) {
                    txtEmail.clear();
                    txtPass.clear();
                    Stage mainStage = getMainStage();
                    mainStage.setScene(employeeScene);
                    mainStage.setTitle("Employee Menu");
                    mainStage.show();
                } else if (item.toString().equals(AuthenticationController.ROLE_CLIENT)) {
                    txtEmail.clear();
                    txtPass.clear();
                    Stage mainStage = getMainStage();
                    mainStage.setScene(clientScene);
                    mainStage.setTitle("Client Menu");
                    mainStage.show();
                } else if (item.toString().equals(AuthenticationController.ROLE_STORE_NETWORK_MANAGER)) {
                    txtEmail.clear();
                    txtPass.clear();
                    Stage mainStage = getMainStage();
                    mainStage.setScene(networkManagerScene);
                    mainStage.setTitle("Store Network Manager Menu");
                    mainStage.show();
                } else if (item.toString().equals(AuthenticationController.ROLE_STORE_MANAGER)) {
                    txtEmail.clear();
                    txtPass.clear();
                    Stage mainStage = getMainStage();
                    mainStage.setScene(storeManagerScene);
                    mainStage.setTitle("Store Manager Menu");
                    mainStage.show();
                } else {
                    System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
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

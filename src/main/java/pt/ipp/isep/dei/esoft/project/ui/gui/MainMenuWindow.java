package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuWindow implements Runnable, Initializable {

    private final AuthenticationController authenticationController;
    private Stage authenticationWindow;
    private Scene listScene;
    @FXML
    private Button btLogin;
    @FXML
    private Button btListAnnouncements;
    @FXML
    private TextArea txtBoxEmail;


    public MainMenuWindow() {
        authenticationController = new AuthenticationController();
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            FXMLLoader authLoader = new FXMLLoader();
            authLoader.setLocation(getClass().getResource("/AuthenticationScene.fxml"));
            Parent authRoot = authLoader.load();
            Scene authScene = new Scene(authRoot);
            authenticationWindow = new Stage();
            authenticationWindow.initModality(Modality.APPLICATION_MODAL);
            authenticationWindow.setTitle("Authentication");
            authenticationWindow.setResizable(false);
            authenticationWindow.setScene(authScene);
            AuthenticationWindow authenticationWindow = authLoader.getController();
            authenticationWindow.associateParentUI(this);

            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/ListAnnouncementsScene.fxml"));
            Parent listRoot = listLoader.load();
            listScene = new Scene(listRoot);


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
    private void onBtLoginAction() throws IOException {
        authenticationWindow.show();
    }

    @FXML
    private void onBtListAnnouncementsAction() throws IOException {
        Stage mainStage = getMainStage();
        mainStage.setScene(listScene);
        mainStage.setTitle("List Announcements");
        mainStage.show();
    }





}

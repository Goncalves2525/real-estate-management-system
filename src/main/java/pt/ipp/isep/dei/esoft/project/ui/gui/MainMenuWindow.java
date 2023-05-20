package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuWindow implements Runnable{

    private final AuthenticationController authenticationController;
    private Stage authenticationWindow;
    @FXML
    private Button btLogin;
    @FXML
    private TextArea txtBoxEmail;


    public MainMenuWindow() {
        authenticationController = new AuthenticationController();
    }

    @Override
    public void run() {
        try{
            FXMLLoader authLoader = new FXMLLoader();
            authLoader.setLocation(getClass().getResource("/AuthenticationScene.fxml"));
            Parent authRoot = authLoader.load();
            Scene authScene = new Scene(authRoot);
            authenticationWindow = new Stage();
            authenticationWindow.initModality(Modality.APPLICATION_MODAL);
            authenticationWindow.setTitle("Real Estate USA");
            authenticationWindow.setResizable(false);
            authenticationWindow.setScene(authScene);

            AuthenticationWindow authenticationWindow = authLoader.getController();
            authenticationWindow.associateParentUI(this);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public AuthenticationController getAuthenticationController() {
        return authenticationController;
    }

    @FXML
    private void onBtLoginAction() throws IOException {
        run();
        authenticationWindow.show();
    }


}

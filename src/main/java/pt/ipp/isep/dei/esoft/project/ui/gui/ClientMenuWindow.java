package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListAnnouncementsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuWindow implements Initializable {


    private Scene listScene;

    @FXML
    private Button btOrder;

    @FXML
    private Button btReturn;

    @FXML
    private Button btScheduleVisit;

    @FXML
    private Button btSubmitRequest;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/ListAnnouncementsScene.fxml"));
            Parent listRoot = listLoader.load();
            ListAnnouncementsWindow listAnnouncementsWindow = listLoader.getController();
            listAnnouncementsWindow.getBtPlaceOrder().setVisible(true);
            listScene = new Scene(listRoot);


        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private Stage getMainStage() {
        return (Stage) this.btReturn.getScene().getWindow();
    }

    @FXML
    void onBtOrder(ActionEvent event) {
        Stage mainStage = getMainStage();
        mainStage.setScene(listScene);
        mainStage.setTitle("List Announcements");
        mainStage.show();
    }

    @FXML
    private void onBtReturn(ActionEvent event) {
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

    @FXML
    private void onBtScheduleVisit(ActionEvent event) {

    }

    @FXML
    private void onBtSubmitRequest(ActionEvent event) {

    }


}

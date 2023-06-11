package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class DevTeamSceneWindow {

    @FXML
    private Button btReturn;

    @FXML
    private ProgressIndicator Prgbt;

    @FXML
    private Ellipse face, eye1, eye2, nose;

    @FXML
    private Polyline mouth;

    @FXML
    private void initialize() {
        face.setVisible(false);
        eye1.setVisible(false);
        eye2.setVisible(false);
        nose.setVisible(false);
        mouth.setVisible(false);
    }

    @FXML
    void funAction1(ActionEvent event) {
        showFunAlert("Miguel Silva says hello!");
    }

    @FXML
    void funAction2(ActionEvent event) {
        showFunAlert("Luís Leal is on fire!");
    }

    @FXML
    void funAction3(ActionEvent event) {
        showFunAlert("Diogo Santo just solved a tough bug!");
    }

    @FXML
    void funAction4(ActionEvent event) {
        showFunAlert("Ricardo Gonçalves is writing cool code!");
    }

    @FXML
    void funAction5(ActionEvent event) {
        showFunAlert("Tiago Brito is a magician!");
    }

    @FXML
    void rateAction(ActionEvent event) {
        Alert rateAlert = new Alert(Alert.AlertType.CONFIRMATION);
        rateAlert.setTitle("Rate the Team");
        rateAlert.setHeaderText("Rate the Team");
        rateAlert.setContentText("Please choose an option.");

        ButtonType buttonTypeOne = new ButtonType("Fantastic Team");
        ButtonType buttonTypeTwo = new ButtonType("Poor Team");

        rateAlert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = rateAlert.showAndWait();
        if (result.get() == buttonTypeOne) {
            showFunAlert("Thank you for the rating!");
        } else if (result.get() == buttonTypeTwo) {
            showFunAlert("Oops, the right answer is 'Fantastic Team'!");
        }
    }

    @FXML
    void onRd1(ActionEvent event) {
        face.setVisible(true);
        Prgbt.setProgress(Prgbt.getProgress() + 0.2);
    }

    @FXML
    void onRd2(ActionEvent event) {
        eye1.setVisible(true);
        Prgbt.setProgress(Prgbt.getProgress() + 0.2);
    }

    @FXML
    void onRd3(ActionEvent event) {
        eye2.setVisible(true);
        Prgbt.setProgress(Prgbt.getProgress() + 0.2);
    }

    @FXML
    void onRd4(ActionEvent event) {
        nose.setVisible(true);
        Prgbt.setProgress(Prgbt.getProgress() + 0.2);
    }

    @FXML
    void onRd5(ActionEvent event) {
        mouth.setVisible(true);
        Prgbt.setProgress(Prgbt.getProgress() + 0.2);
    }

    public void onBtReturn(ActionEvent actionEvent) {
        Stage mainStage = getMainStage();
        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/MainMenuScene.fxml"));
        Parent mainMenuRoot;
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
        return (Stage) btReturn.getScene().getWindow();
    }

    private void showFunAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dev Team Fun");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

package pt.ipp.isep.dei.esoft.project.ui.gui;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import pt.ipp.isep.dei.esoft.project.application.controller.VisitScheduleController;
import pt.ipp.isep.dei.esoft.project.domain.VisitSchedule;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class VisitScheduleRequestsWindow implements Initializable {

    @FXML
    private TableColumn<VisitSchedule, LocalDate> tcDate;
    @FXML
    public TableColumn<VisitSchedule, Time> tcStartTime;

    @FXML
    public TableColumn<VisitSchedule, Time> tcEndTime;

    @FXML
    private TableColumn<VisitSchedule, Integer> tcProperty;
    @FXML
    public TableColumn<VisitSchedule, String> tcAddress;

    @FXML
    public TableColumn<VisitSchedule, String> tcRequesterName;

    @FXML
    public TableColumn<VisitSchedule, Long> tcRequesterPhone;

    @FXML
    private Button btFilterSchedules;

    @FXML
    private Button btReturn;

    @FXML
    private Button btRespond;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpEndDate;

    @FXML
    private TableView<VisitSchedule> tvBookingRequests;



    private VisitScheduleController controller;

    public VisitScheduleRequestsWindow() {
        controller = new VisitScheduleController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listPendingVisits();
    }

    private void listPendingVisits() {
        String agentEmail = controller.getCurrentUserEmail();
        List<VisitSchedule> pendingVisits = controller.getPendingVisitsByAgentEmail(agentEmail);

        showObservableList(pendingVisits);
    }
    @FXML
    public void onbtFilterSchedules(ActionEvent actionEvent) {
        LocalDate startDate = dpStartDate.getValue();
        LocalDate endDate = dpEndDate.getValue();
        String agentEmail = controller.getCurrentUserEmail();
        List<VisitSchedule> filteredVisits = controller.getFilteredVisitsByAgentEmail(agentEmail, startDate, endDate);

        showObservableList(filteredVisits);
    }

    public void showObservableList(List<VisitSchedule> filteredVisits) {
        ObservableList<VisitSchedule> data = FXCollections.observableArrayList(filteredVisits);
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        tcEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        tcProperty.setCellValueFactory(new PropertyValueFactory<>("propertyID"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("adressOfProperty"));
        tcRequesterName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcRequesterPhone.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));

        tvBookingRequests.setItems(data);
    }

    @FXML
    public void onBtRespond(ActionEvent actionEvent) {
        int propertyID = 0;
        VisitSchedule visitSchedule = null;
        try {
            visitSchedule = tvBookingRequests.getSelectionModel().getSelectedItem();
            propertyID =  visitSchedule.getPropertyID();
        } catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Please select a visit to respond to.");
            alert.showAndWait();
        }

        if (visitSchedule != null && propertyID != 0) {
            ChoiceDialog<String> dialog = new ChoiceDialog<>();
            dialog.getItems().addAll("Accept", "Decline");
            dialog.setTitle("Respond to Visit Request");
            dialog.setHeaderText("Respond to Visit Request");
            dialog.setContentText("Please select your response:");
            dialog.setSelectedItem("Accept");

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                String response = result.get();
                System.out.println(response);

                if (response.equals("Accept")) {
                    approveVisit(visitSchedule);
                }
                else if (response.equals("Decline")) {
                    Dialog<String> textDialog = new Dialog<>();
                    textDialog.setTitle("Respond to Visit Request");
                    textDialog.setHeaderText("Provide your reason:");

                    TextArea textArea = new TextArea();
                    textArea.setPromptText("Please enter a reason for your response.");
                    textDialog.getDialogPane().setContent(textArea);
                    textDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                    textDialog.setResultConverter(dialogButton -> {
                        if (dialogButton == ButtonType.OK) {
                            return textArea.getText();
                        }
                        return null;
                    });

                    Optional<String> textResult = textDialog.showAndWait();
                    if (textResult.isPresent()) {
                        String reason = textResult.get();
                        System.out.println(reason);
                        removeVisit(visitSchedule);
                    }
                }
            }
        }
    }

    public void removeVisit(VisitSchedule visit) {
        controller.removeVisit(visit);
        listPendingVisits();
    }

    public void approveVisit(VisitSchedule visit) {
        controller.approveVisit(visit);
        listPendingVisits();
    }

    @FXML
    public void onBtReturn(ActionEvent actionEvent) {
        Stage mainStage = getMainStage();
        FXMLLoader employeeLoader = new FXMLLoader(getClass().getResource("/EmployeeMenuScene.fxml"));
        Parent employeeRoot = null;
        try {
            employeeRoot = employeeLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene employeeMenuScene = new Scene(employeeRoot);
        mainStage.setScene(employeeMenuScene);
        mainStage.setTitle("Employee Menu");
        mainStage.show();
    }

    private Stage getMainStage() {
        return (Stage) btReturn.getScene().getWindow();
    }

}

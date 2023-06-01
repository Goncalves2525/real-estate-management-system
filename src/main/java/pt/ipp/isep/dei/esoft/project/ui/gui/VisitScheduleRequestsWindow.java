package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import pt.ipp.isep.dei.esoft.project.application.controller.VisitScheduleController;
import pt.ipp.isep.dei.esoft.project.domain.VisitSchedule;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class VisitScheduleRequestsWindow implements Initializable {

    @FXML
    private TableColumn<VisitSchedule, Date> tcDate;
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
    private Button btAppointmentRequest;

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

        ObservableList<VisitSchedule> data = FXCollections.observableArrayList(pendingVisits);

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
    public void onBtAppointmentRequest(ActionEvent actionEvent) {

    }

    @FXML
    public void onBtRespond(ActionEvent actionEvent) {

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

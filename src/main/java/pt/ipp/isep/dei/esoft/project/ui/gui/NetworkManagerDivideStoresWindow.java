package pt.ipp.isep.dei.esoft.project.ui.gui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ImportController;
import pt.ipp.isep.dei.esoft.project.application.controller.StoreDivisionController;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.Tuple;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class NetworkManagerDivideStoresWindow implements Initializable {
    private final StoreDivisionController storeDivisionController = new StoreDivisionController();
    private final ImportController importController = new ImportController();
    @FXML
    public TableView tblDivideStores;
    @FXML
    public LineChart lineChart;
    @FXML
    private Button btReturn;
    @FXML
    private Button btImport;
    @FXML
    private Label importOperationLabel;
    @FXML
    private Button btnDivideStores;
    @FXML
    private Button btnRunTimeTests;

    @FXML
    private TableColumn<Property, String> storeID;

    @FXML
    private TableColumn<Property, Integer> noOfProperties;
    @FXML
    private TextArea txtAreaResults;

    private int testNNumber = 3;
    private XYChart.Series<Number, Number> series;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storeID.setCellValueFactory(new PropertyValueFactory<>("first"));
        noOfProperties.setCellValueFactory(new PropertyValueFactory<>("second"));
        btnRunTimeTests.setText("Run Runtime Test for n=" + testNNumber);
        tblDivideStores.setItems(getProperties());
        series = new XYChart.Series<>();
        lineChart.getData().add(series);
    }

    public ObservableList<Tuple<String, Integer>> getProperties() {
        ObservableList<Tuple<String, Integer>> properties = FXCollections.observableArrayList();
        ArrayList<Property> propertiesList = storeDivisionController.getProperties();
        //System.out.println(propertiesList.size());
        for (Property property : propertiesList) {
            if(property.getAgencyID() == 0){
                continue;
            }
            Tuple<String, Integer> tuple = new Tuple<>(""+property.getAgencyID(), 1);
            //tuple = new Tuple<>("" + property.getAgencyID(), 1);
            //properties.add(new Tuple<>(""+property.getAgencyID(), 1));

            if (agencyPropertySum.containsKey(tuple.getFirst())) {
                int currentSum = agencyPropertySum.get(tuple.getFirst());
                agencyPropertySum.put(tuple.getFirst(), currentSum + tuple.getSecond());
            } else {
                agencyPropertySum.put(tuple.getFirst(), tuple.getSecond());
            }
        }
        ObservableList<Tuple<String, Integer>> tupleList = FXCollections.observableArrayList();
        for (String key : agencyPropertySum.keySet()) {
            boolean exists = false;
            for (Tuple<String, Integer> tuple2 : tupleList) {
                if (tuple2.getFirst().equals(key)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                tupleList.add(new Tuple<>(key, agencyPropertySum.get(key)));
            }
        }
        //System.out.println("2 - " + tupleList.size());
        return tupleList;
    }

    public void onBtReturn(ActionEvent actionEvent) {
        Stage mainStage = getMainStage();
        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/NetworkManagerMenuScene.fxml"));
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
    private Stage getMainStage() {
        return (Stage) this.btReturn.getScene().getWindow();
    }

    HashMap<String, Integer> agencyPropertySum = new HashMap<>();

    public void onBtImport(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Set the title of the file chooser dialog
        fileChooser.setTitle("Select File");

        // Show the file chooser dialog
        Stage mainStage = getMainStage();
        String selectedDirectory = fileChooser.showOpenDialog(mainStage).getAbsolutePath();
        if (!Objects.equals(selectedDirectory, "")) {
            ArrayList<String[]> dataToImport = importController.readFile(selectedDirectory, ";");
            //String importResult = importController.importDatatoprperty(dataToImport);
            String importResult = importController.importData(dataToImport);

            importOperationLabel.setText(importResult);

            // Clear the agencyPropertySum hashmap
            agencyPropertySum.clear();

            for (String[] data: dataToImport) {
                String agencyID = data[25];

                // Update the property count
                agencyPropertySum.put(agencyID, agencyPropertySum.getOrDefault(agencyID, 0) + 1);
            }

            // Update table items
            ObservableList<Tuple<String, Integer>> tupleList = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> entry : agencyPropertySum.entrySet()) {
                tupleList.add(new Tuple<>(entry.getKey(), entry.getValue()));
            }
            tblDivideStores.setItems(tupleList);
        }
    }

    public void onbtnRunTimeTests(ActionEvent actionEvent) {
        series.getData().clear();
        if(testNNumber <= 30){
            List<Agency> agencyList = new ArrayList<>();
            for(int i = 1; i<=testNNumber; i++){
                agencyList.add(new Agency(i,i *3 ));
            }

            Map<String, Object> partitionResult = storeDivisionController.partitionTest2(agencyList);


            // Display the groups
            for (int i = 1; i <= 2; i++) {
                List<Agency> group = (List<Agency>) partitionResult.get("group" + i);
                txtAreaResults.appendText("Group " + i + ":\n");
                for (Agency agency : group) {
                    txtAreaResults.appendText("\tStore ID: " + agency.getId() + ", Properties: " + agency.getNoOfProperties() + "\n");
                }
                txtAreaResults.appendText("\n");
            }

            // Display execution time, input size, and difference of sums
            txtAreaResults.appendText("Input size: " + agencyList.size() + "\n");
            txtAreaResults.appendText("Execution time: " + partitionResult.get("executionTime") + " milliseconds\n");
            txtAreaResults.appendText("Difference of sums: " + partitionResult.get("differenceOfSums") + "\n\n");
            txtAreaResults.appendText("----------------------------------||----------------------------------\n\n");

            int numVariables = testNNumber;
            Long executionTime = (Long) partitionResult.get("executionTime");

            series.getData().add(new XYChart.Data<>(numVariables, executionTime));

            testNNumber = testNNumber +3;
        }
        btnRunTimeTests.setText("Run Runtime Test for n=" + testNNumber);
    }


    public void onBtnDivideStores(ActionEvent actionEvent) {
        series.getData().clear();
        List<Agency> list = new ArrayList<>();
        for (Object tuple : tblDivideStores.getItems()) {

            int i = Integer.parseInt(((Tuple<String, Integer>) tuple).getFirst());
            int j = ((Tuple<String, Integer>) tuple).getSecond();
            list.add(new Agency(i,j ));
        }

        Map<String, Object> partitionResult = storeDivisionController.partitionTest2(list);


        // Display the groups
        for (int i = 1; i <= 2; i++) {
            List<Agency> group = (List<Agency>) partitionResult.get("group" + i);
            txtAreaResults.appendText("Group " + i + ":\n");
            for (Agency agency : group) {
                txtAreaResults.appendText("\tStore ID: " + agency.getId() + ", Properties: " + agency.getNoOfProperties() + "\n");
            }
            txtAreaResults.appendText("\n");
        }

        // Display execution time, input size, and difference of sums
        txtAreaResults.appendText("Input size: " + list.size() + "\n");
        txtAreaResults.appendText("Execution time: " + partitionResult.get("executionTime") + " milliseconds\n");
        txtAreaResults.appendText("Difference of sums: " + partitionResult.get("differenceOfSums") + "\n\n");

        int numVariables = testNNumber;
        Long executionTime = (Long) partitionResult.get("executionTime");

        series.getData().add(new XYChart.Data<>(numVariables, executionTime));

    }
}

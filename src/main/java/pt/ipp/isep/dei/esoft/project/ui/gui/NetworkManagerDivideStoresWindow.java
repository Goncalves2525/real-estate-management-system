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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ImportController;
import pt.ipp.isep.dei.esoft.project.application.controller.StoreDivisionController;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storeID.setCellValueFactory(new PropertyValueFactory<>("first"));
        noOfProperties.setCellValueFactory(new PropertyValueFactory<>("second"));
        btnRunTimeTests.setText("Run Runtime Test for n=" + testNNumber);
        tblDivideStores.setItems(getProperties());
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
            //mudar para reflection
            ArrayList<String[]> dataToImport = importController.readFile(selectedDirectory, ";");
            String importResult = importController.importDatatoprperty(dataToImport);
            importOperationLabel.setText(importResult);
            //acrescentar dados à tabela
            //apresentar dados da forma:
            //Loja x -> num propriedades
            ObservableList<Tuple<String, Integer>> tupleList = FXCollections.observableArrayList();
            for (String[] data: dataToImport) {
                String agencyID = data[25];
                int amountOfProperties = 1;

                Tuple<String, Integer> tuple = new Tuple<>(agencyID, amountOfProperties);

                if (agencyPropertySum.containsKey(tuple.getFirst())) {
                    int currentSum = agencyPropertySum.get(tuple.getFirst());
                    agencyPropertySum.put(tuple.getFirst(), currentSum + tuple.getSecond());
                } else {
                    agencyPropertySum.put(tuple.getFirst(), tuple.getSecond());
                }


            }
            for (String key : agencyPropertySum.keySet()) {
                boolean exists = false;
                for (Tuple<String, Integer> tuple : tupleList) {
                    if (tuple.getFirst().equals(key)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    tupleList.add(new Tuple<>(key, agencyPropertySum.get(key)));
                }
            }
            tblDivideStores.setItems(tupleList);
        }
    }

    public void onbtnRunTimeTests(ActionEvent actionEvent) {
        //storeDivisionController.dividePartitions(testNNumber);
        if(testNNumber <= 30){
            txtAreaResults.setText(storeDivisionController.partitionTest2(testNNumber));
            testNNumber = testNNumber +3;
        }
        btnRunTimeTests.setText("Run Runtime Test for n=" + testNNumber);

    }

    public void onBtnDivideStores(ActionEvent actionEvent) {
        List<Integer> list = new ArrayList<>();
        for (Object tuple : tblDivideStores.getItems()) {
            list.add(((Tuple<String, Integer>) tuple).getSecond());
        }

        txtAreaResults.setText(storeDivisionController.dividePartitions(list));
    }
}

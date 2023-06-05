package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import pt.ipp.isep.dei.esoft.project.domain.Statistics;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowStatisticsWindow implements Initializable {

    private final Statistics statistics = Statistics.getInstance();
    @FXML
    private TextArea txtArea;
    @FXML
    private Button btShow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    private void onBtShow(ActionEvent event){
        ArrayList<Double> forecastedPrices = statistics.getForecastedPrices();

        txtArea.setText("Forecasted Prices:\n");
        for (Double price : forecastedPrices) {
            txtArea.appendText(price + "\n");
        }
    }


}

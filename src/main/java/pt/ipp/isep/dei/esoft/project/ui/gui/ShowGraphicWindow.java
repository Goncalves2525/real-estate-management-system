package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowGraphicWindow implements Initializable {
    private ShowStatisticsWindow showStatisticsWindow;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void associateParentUI(ShowStatisticsWindow showStatisticsWindow) {
        this.showStatisticsWindow = showStatisticsWindow;
    }
}

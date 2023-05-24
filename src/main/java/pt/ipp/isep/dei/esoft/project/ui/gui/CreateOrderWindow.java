package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateOrderWindow implements Initializable {

    private ListAnnouncementsWindow listAnnouncementsWindow;

    @FXML
    private ComboBox comboBoxID;
    @FXML
    private TextField txtAmount;
    @FXML
    private Button btPlaceOrder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void associateParentUI(ListAnnouncementsWindow listAnnouncementsWindow) {
        this.listAnnouncementsWindow = listAnnouncementsWindow;
    }

    @FXML
    public void onBtPlaceOrder(ActionEvent event) {

    }

}

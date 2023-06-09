package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.File;
import java.io.IOException;



public class MainGUI extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/MainMenuScene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Real Estate USA");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Repositories.getInstance().serialize();
        super.stop();
    }

    public static void main(String[] args) {
        File f = new File("RealEstateUSA.ser");
        if(f.exists() && !f.isDirectory()) {
            Repositories.getInstance().deserialize();
        }
        else{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.run();
            Repositories.getInstance().serialize();
        }

        try {
            launch();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.run();
//        launch();
    }
}

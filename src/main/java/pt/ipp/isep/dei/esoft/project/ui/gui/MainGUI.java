package pt.ipp.isep.dei.esoft.project.ui.gui;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
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
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        //Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
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
        System.out.println("Closing application");
        Repositories.getInstance().serialize();
        super.stop();
    }

    public static void main(String[] args) {
        File f = new File("RealEstateUSA.ser");
        Bootstrap bootstrap = new Bootstrap();
        if(f.exists() && !f.isDirectory()) {
            bootstrap.runUsers();
            Repositories.getInstance().deserialize();
        }
        else{
            bootstrap.run();
            Repositories.getInstance().serialize();
        }

//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.run();
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

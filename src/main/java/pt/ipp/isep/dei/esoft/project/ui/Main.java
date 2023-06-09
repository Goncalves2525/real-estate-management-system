package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

import java.awt.event.WindowEvent;
import java.io.File;

public class Main {

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
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Repositories.getInstance().serialize()));
    }

    void windowClosing(WindowEvent e) {
        Repositories.getInstance().serialize();
    }
}
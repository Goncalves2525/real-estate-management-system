package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.RegisterEmployeeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AdminUI implements Runnable {
    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register an Employee", new RegisterEmployeeUI()));
        options.add(new MenuItem("Import Data", new ImportUI()));
        //options.add(new MenuItem("Register a Store", new ShowTextUI("⚠ Functionality not implemented ⚠")));
        //options.add(new MenuItem("Specify districts, municipalities, and parishes", new ShowTextUI("⚠ Functionality not implemented ⚠")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}

package pt.ipp.isep.dei.esoft.project.ui.console;


//import pt.ipp.isep.dei.esoft.project.ui.console.CreateTaskUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class SubmitOrderUI implements Runnable {
    public SubmitOrderUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        //options.add(new MenuItem("Create Task", new CreateTaskUI()));
        options.add(new MenuItem("Option 1 ", new ShowTextUI("You have chosen Option A.")));
        options.add(new MenuItem("Option 2 ", new ShowTextUI("You have chosen Option B.")));
        options.add(new MenuItem("Option 3 ", new ShowTextUI("You have chosen Option C.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}

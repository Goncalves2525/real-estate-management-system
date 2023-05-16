package pt.ipp.isep.dei.esoft.project.ui.console.menu;


//import pt.ipp.isep.dei.esoft.project.ui.console.CreateTaskUI;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateOrderUI;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ListAnnouncementsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.PublishAnnouncementUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class ClientUI implements Runnable {
    public ClientUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("View properties", new ListAnnouncementsUI()));
        options.add(new MenuItem("Schedule a visit", new CreateOrderUI()));
        options.add(new MenuItem("Submit request for Listing", new CreateRequestUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nClient Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}

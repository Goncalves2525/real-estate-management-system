package pt.ipp.isep.dei.esoft.project.ui.console;


//import pt.ipp.isep.dei.esoft.project.ui.console.CreateTaskUI;
import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Comission;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class PublishAnnouncementUI implements Runnable {

    private final PublishAnnouncementController controller;


    /**
     * Instantiates a new PublishRequestUI.
     */
    public PublishAnnouncementUI() {
        controller = new PublishAnnouncementController();
    }


    /**
     * Run.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        List<Announcement> announcements = controller.getAnnouncementsByUser();

        do{
            System.out.println("The following announcements are available to publish:");
            for (Announcement a : announcements) {
                printAnnouncement(a);
            }
            int id = Utils.readIntegerFromConsole("Please select the ID of the announcement you want to publish: ");
            if(controller.getAnnouncementById(id) != null){
                chooseCommissionOrValue(id);
                controller.publishAnnouncement(id);
                System.out.println("Announcement published successfully!\n");
                announcements = controller.getAnnouncementsByUser();
            }
            else{
                System.out.println("The ID you have entered is not valid. Please try again.\n");
            }
        }
        while (!announcements.isEmpty());
        if (announcements.isEmpty()) {
            System.out.println("There are no announcements to publish!");
        }


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nPublish Property Announcement Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

    private void chooseCommissionOrValue(int id) {
        Comission comission = new Comission();
        System.out.println("Do you want to set a commission or a value for this announcement?");
        System.out.println("1 - Fixed amount");
        System.out.println("2 - Percentage");
        int option = 0;
        do{
            option = Utils.readIntegerFromConsole("Please select an option: ");
            if (option == 1) {
                float commissionFixedValue = Utils.readFloatFromConsole("You have chosen to set a fixed amount for this announcement commission.\nPlease state it:");
                comission = commissionFixedValue(id,commissionFixedValue);
            }
            else if (option == 2) {
                double commissionFixedValue = Utils.readDoubleFromConsole("You have chosen to set a percentage for this announcement commission.\nPlease state it:");
                comission = setCommissionPercentage(id,commissionFixedValue);
            }
        }while (option != 1 && option != 2);
        if(comission.getValue() > 0 || comission.getPercentage() > 0) {
            controller.setCommission(id, comission);
        }
    }

    private Comission commissionFixedValue(int id, float commissionFixedValue) {
        Comission comission = new Comission(commissionFixedValue);
        return comission;
        //controller.setCommission(id, comission);
    }

    private Comission setCommissionPercentage(int id, double commissionPercentage) {
        Comission comission = new Comission(commissionPercentage);
        return comission;
        //controller.setCommission(id, comission);
    }

    private void printAnnouncement(Announcement a) {
        System.out.println("---------------------------------");
        System.out.println(a.toString());
    }
}

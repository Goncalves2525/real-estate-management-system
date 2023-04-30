package pt.ipp.isep.dei.esoft.project.ui.console;


//import pt.ipp.isep.dei.esoft.project.ui.console.CreateTaskUI;
import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Commission;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        if (announcements.isEmpty()) {
            System.out.println("There are no announcements to publish!");
        }
        else{
            do{
                System.out.println("The following announcements are available to publish:");
                for (Announcement a : announcements) {
                    printAnnouncement(a);
                }
                int id = 0;
                try{
                    System.out.println("Please select the ID of the announcement you want to publish:\n");
                    //id = Utils.readIntegerFromConsole("Please select the ID of the announcement you want to publish: ");
                    Scanner sc = new Scanner(System.in);
                    id = sc.nextInt();
                    if(controller.getAnnouncementById(id) != null){
                        chooseCommissionFixedAmountOrPercentage(id);
                        System.out.println("You've set the commission for the announcement with ID " + id + ".\n");
                        System.out.println("You've chosen to publish the announcement with ID " + id + ".\nDo you confirm? (Y/N)");
                        String confirmation = sc.next();
                        do{
                            if(confirmation.equals("Y") || confirmation.equals("y")){
                                controller.publishAnnouncement(id);
                                System.out.println("Announcement published successfully!\n");
                                announcements = controller.getAnnouncementsByUser();
                            }
                        }while (!confirmation.equals("Y") && !confirmation.equals("y") && !confirmation.equals("N") && !confirmation.equals("n"));
                    }
                    else{
                        System.out.println("The ID you have entered is not valid or is not associated with your email. Please try again.\n");
                    }
                } catch (Exception e) {
                    System.out.println("The ID you have entered is not valid. Please try again.\n");
                }
            }
            while (!announcements.isEmpty());
        }

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nPublish Property Announcement Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

    private void chooseCommissionFixedAmountOrPercentage(int id) {
        Commission commission = new Commission();
        System.out.println("Do you want to set a commission or a value for this announcement?");
        System.out.println("1 - Fixed amount");
        System.out.println("2 - Percentage");
        int option = 0;
        do{
            option = Utils.readIntegerFromConsole("Please select an option: ");
            if (option == 1) {
                float commissionFixedAmount = 0;
                do{
                    commissionFixedAmount = Utils.readFloatFromConsole("You have chosen to set a fixed amount for this announcement commission.\nPlease state it:");
                    commission = commissionFixedAmount(id,commissionFixedAmount);
                    if(commissionFixedAmount<0){
                        System.out.println("The value you have entered is not valid. Please try again.\n");
                    }
                }while(commissionFixedAmount > 0);
            }
            else if (option == 2) {
                double commissionPercentage = 0;
                do{
                    commissionPercentage = Utils.readDoubleFromConsole("You have chosen to set a percentage for this announcement commission.\nPlease state it:");
                    commission = setCommissionPercentage(id,commissionPercentage);
                    if(commissionPercentage <= 0 || commissionPercentage > 1){
                        System.out.println("The value you have entered is not valid. Please try again.\n");
                    }
                }while (commissionPercentage > 0 && commissionPercentage <= 1);
            }
        }while (option != 1 && option != 2);
        if(commission.getValue() > 0 || commission.getPercentage() > 0) {
            controller.setCommission(id, commission);
        }
    }

    private Commission commissionFixedAmount(int id, float commissionFixedValue) {
        Commission commission = new Commission(commissionFixedValue);
        return commission;
    }

    private Commission setCommissionPercentage(int id, double commissionPercentage) {
        Commission commission = new Commission(commissionPercentage);
        return commission;
    }

    private void printAnnouncement(Announcement a) {
        System.out.println("---------------------------------");
        System.out.println(a.toString());
    }
}

package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.VisitScheduleController;
import pt.ipp.isep.dei.esoft.project.domain.VisitSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VisitScheduleEvaluationUI implements Runnable {

    private VisitScheduleController controller;
    private Scanner sc = new Scanner(System.in);

    public VisitScheduleEvaluationUI() {
        controller = new VisitScheduleController();
    }


    @Override
    public void run() {
        ArrayList<VisitSchedule> unapprovedVisits = controller.getUnapprovedVisits();
        if (unapprovedVisits.isEmpty()) {
            System.out.println("No unapproved visits to show.");
            return;
        }

        for (int i = 0; i < unapprovedVisits.size(); i++) {
            System.out.println((i + 1) + ". " + unapprovedVisits.get(i).toString());
        }

        System.out.println("Select a visit to approve or remove (or enter a negative number to exit):");
        int visitIndex = sc.nextInt() - 1;
        sc.nextLine(); // Consume newline left-over
        if (visitIndex < 0) {
            System.out.println("Exiting...");
            return;
        }

        String response;
        do {
            System.out.println("Do you want to approve or remove the visit? (A/R)");
            response = sc.nextLine();
            if (!response.equalsIgnoreCase("A") && !response.equalsIgnoreCase("R")) {
                System.out.println("Invalid input. Please enter A or R:");
            }
        } while (!response.equalsIgnoreCase("A") && !response.equalsIgnoreCase("R"));

        if (response.equalsIgnoreCase("A")) {
            controller.approveVisit(unapprovedVisits.get(visitIndex));
            System.out.println("Visit approved.");
        } else {
            controller.removeVisit(unapprovedVisits.get(visitIndex));
            System.out.println("Visit removed.");
        }
    }



    private List<VisitSchedule> listPendingVisits() {
        List<VisitSchedule> pendingVisits = controller.getPendingVisits();
        if (pendingVisits.isEmpty()) {
            return pendingVisits;
        }

        System.out.println("Pending Visits:");
        for (int i = 0; i < pendingVisits.size(); i++) {
            System.out.println((i + 1) + ". " + pendingVisits.get(i).toString());
        }

        return pendingVisits;
    }

    private int selectVisit(List<VisitSchedule> pendingVisits) {
        System.out.println("Enter the index number of the visit you want to approve (or 0 to exit):");
        int index = sc.nextInt() - 1;

        if (index >= 0 && index < pendingVisits.size()) {
            return index;
        } else if (index == -1) {
            return -1;
        } else {
            System.out.println("Invalid index. Please try again.");
            return selectVisit(pendingVisits);
        }
    }

    private void approveVisit(VisitSchedule visitSchedule) {
        controller.approveVisit(visitSchedule);
    }
}

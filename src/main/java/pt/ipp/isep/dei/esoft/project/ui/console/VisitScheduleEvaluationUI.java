package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.VisitScheduleController;
import pt.ipp.isep.dei.esoft.project.domain.VisitSchedule;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
        List<VisitSchedule> pendingVisits = listPendingVisits();
        if (pendingVisits.isEmpty()) {
            System.out.println("No pending visits to show.");
            return;
        }

        System.out.println("Select a visit to approve or remove (or enter a negative number to exit):");

        int visitIndex = -1;

        while (true) {
            try {
                visitIndex = sc.nextInt() - 1;
                sc.nextLine(); // Consume newline
                if (visitIndex < 0) {
                    return;
                }
                System.out.println(pendingVisits.get(visitIndex)); // This will throw IndexOutOfBoundsException if index is invalid
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer:");
                sc.nextLine(); // Consume invalid input
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please enter a number between 1 and " + pendingVisits.size() + ":");
                sc.nextLine(); // Consume invalid input
            }
        }

        String response="";
        do {
            System.out.println("Do you want to approve or remove the visit? (A/R)");
            response = sc.nextLine();
            if (!response.equalsIgnoreCase("A") && !response.equalsIgnoreCase("R")) {
                System.out.println("Invalid input. Please enter A or R:");
            }
        } while (!response.equalsIgnoreCase("A") && !response.equalsIgnoreCase("R"));

        if (response.equalsIgnoreCase("A")) {
            controller.approveVisit(pendingVisits.get(visitIndex));
            System.out.println("Visit approved.");
        } else {
            controller.removeVisit(pendingVisits.get(visitIndex));
            System.out.println("Visit removed.");
        }
    }




    private List<VisitSchedule> listPendingVisits() {
        String agentEmail = controller.getCurrentUserEmail();
        List<VisitSchedule> pendingVisits = controller.getPendingVisitsByAgentEmail(agentEmail);
        if (pendingVisits.isEmpty()) {
            return pendingVisits;
        }

        System.out.println("Pending Visits:");
        for (int i = 0; i < pendingVisits.size(); i++) {
            System.out.println((i + 1) + ". " + pendingVisits.get(i).toString());
        }

        return pendingVisits;
    }


}

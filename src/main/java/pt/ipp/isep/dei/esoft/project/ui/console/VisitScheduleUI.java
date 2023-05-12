package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.VisitScheduleController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VisitScheduleUI implements Runnable {
    private VisitScheduleController controller = new VisitScheduleController();
    private Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        boolean keepRunning = true;
        while (keepRunning) {
            int announcementID = selectAnnouncement();
            if (announcementID < 0) {
                return;
            }
            scheduleVisit(announcementID);
            keepRunning = askForAnotherVisit();
        }
    }

    private boolean askForAnotherVisit() {
        String response;
        do {
            System.out.println("Do you want to schedule another visit? (Y/N)");
            response = sc.nextLine();
            if (!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N")) {
                System.out.println("Invalid input. Please enter Y or N:");
            }
        } while (!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N"));
        return response.equalsIgnoreCase("Y");
    }


    private int selectAnnouncement() {
        int announcementID = -1;
        boolean continueLoop = true;
        do {
            try {
                System.out.println("Select the announcement (ID) you want to schedule a visit for (or enter a negative number to exit):");
                announcementID = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer number.");
                sc.nextLine(); // To consume the invalid token
                continue;
            }
            if (announcementID < 0) {
                System.out.println("Exiting...");
                return -1;
            }
            continueLoop = !controller.verifyAnnouncementID(announcementID);
            if (!continueLoop) {
                System.out.println("The announcement you selected is: " + announcementID);
            } else {
                System.out.println("Invalid announcement ID. Please try again.");
            }
        } while (continueLoop);
        return announcementID;
    }


    private void scheduleVisit(int announcementID) {
        LocalDate visitDate = inputDate();
        LocalTime startTime;
        LocalTime endTime;

        // Getting the phone number of the current user
        int phoneNumber = controller.getCurrentUserPhone();

        do {
            startTime = inputTime("start");
            endTime = inputTime("end");

            if (!startTime.isBefore(endTime)) {
                System.out.println("Invalid time range. Start time must be before end time.");
            }
        } while (!startTime.isBefore(endTime));

        if (!controller.isOverlappingWithExistingSchedules(phoneNumber, visitDate, startTime, endTime)) {
            controller.saveVisitSchedule(announcementID, controller.getCurrentUserName(), phoneNumber, visitDate, startTime, endTime,false);
            System.out.println("Your visit has been scheduled.");
        } else {
            System.out.println("This visit overlaps with an existing visit. Please choose a different time.");
        }

    }


    private LocalDate inputDate() {
        while (true) {
            try {
                System.out.println("Enter visit date (DD/MM/YYYY):");
                String dateString = sc.nextLine();
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please use the format DD/MM/YYYY.");
            }
        }
    }

    private LocalTime inputTime(String timeType) {
        while (true) {
            try {
                System.out.println("Enter visit " + timeType + " time (HH:mm):");
                String timeString = sc.nextLine();
                return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time. Please use the format HH:mm.");
            }
        }
    }
}

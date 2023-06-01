package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.VisitScheduleController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Luis Leal 1100253@isep.ipp.pt
 */

/**
 * VisitScheduleUI class.
 * <p>
 * This class is responsible for creating the UI for the user to schedule a visit.
 */
public class VisitScheduleUI implements Runnable {
    /**
     * Instance variables.
     */
    private VisitScheduleController controller = new VisitScheduleController();
    private Scanner sc = new Scanner(System.in);

    /**
     * Run method.
     * <p>
     * This method is responsible for running the UI for the user to schedule a visit.
     */
    @Override
    public void run() {
        ArrayList<Announcement> announcements = controller.getAllAnnouncementsSortedByDefualtCriteria();
        showAnnouncements(announcements);
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

    /**
     * Show Announcements method.
     * <p>
     * This method is responsible for showing the user the announcements and asking for the announcement ID.
     *
     * @param publishedPropertiesList
     */
    private void showAnnouncements(ArrayList<Announcement> publishedPropertiesList) {
        System.out.println("-------------------------\n" +
                "|  Published Properties  |\n" +
                "-------------------------\n\n");

        for (Announcement announcement : publishedPropertiesList) {
            System.out.println("-----------------------------");
            System.out.print(announcement.toString());
            System.out.println(controller.getPropertyByAnnouncement(announcement));
            System.out.println("-----------------------------\n");
        }
    }

    /**
     * Ask for another visit method.
     *
     */
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

    /**
     * Select announcement method.
     */
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
            if (continueLoop) {
                System.out.println("Invalid announcement ID. Please try again.");
            }
        } while (continueLoop);
        return announcementID;
    }

    /**
     * Input date and time method.
     * <p>
     *
     * @param announcementID
     */
    private void scheduleVisit(int announcementID) {
        LocalDate visitDate = inputDate();
        LocalTime startTime;
        LocalTime endTime;

        // Getting the phone number of the current user
        long phoneNumber = controller.getCurrentUserPhone();

        do {
            startTime = inputTime("start");
            endTime = inputTime("end");

            if (!startTime.isBefore(endTime)) {
                System.out.println("Invalid time range. Start time must be before end time.");
            }
        } while (!startTime.isBefore(endTime));

        if (!controller.isOverlappingWithExistingSchedules(phoneNumber, visitDate, startTime, endTime)) {
            System.out.println("Are you trying to schedule a visit to the property with the ID "+ announcementID+  " to date " + visitDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " from " + startTime + " to " + endTime + ".");
            if (isTheInformationCorrect()){
            controller.saveVisitSchedule(announcementID, controller.getCurrentUserName(), phoneNumber, visitDate, startTime, endTime,false, controller.getPropertyAddressByAnnouncementId(announcementID));
            System.out.println("Your visit has sent to the Agent for confirmation.");
            } else {
                System.out.println("Your visit has not been scheduled.");
            }

        } else {
            System.out.println("This visit overlaps with an existing visit. Please choose a different time.");
        }

    }

    /**
     * Check if the information is correct method.
     * @return
     */
    private boolean isTheInformationCorrect() {
        System.out.println("Is the information correct? (Y/N)");
        String response;
        do {
            response = sc.nextLine();
            if (!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N")) {
                System.out.println("Invalid input. Please enter Y or N:");
            }
        } while (!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N"));
        if (response.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Input date method
     */
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

    /**
     * Input time method.
     * @param timeType
     * @return LocalTime
     */
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

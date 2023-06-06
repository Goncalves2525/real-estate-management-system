package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class for sending emails.
 */
public class EmailSender {

    /**
     * Send a email to the agent.
     */
    public void sendEmailToAgent(int announcementID, String name, long telephoneNumber, LocalDate date, LocalTime startTime, LocalTime endTime, String agentEmail, String adressOfProperty) {
        String filename = "EmailToAgent_" + agentEmail + "_Announcement_" + announcementID + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            if (agentEmail != null) {
                writer.write("To: " + agentEmail + "\n\n");
            }
            writer.write("Subject: Visit Schedule - Announcement " + announcementID + "\n\n");
            writer.write("Dear Agent,\n\n");
            if (name != null) {
                writer.write("My name is " + name + ". I am interested in the property listed under announcement number " + announcementID + " which is located at " + adressOfProperty + ".\n\n");
            }
            if (date != null && startTime != null && endTime != null) {
                writer.write("I would like to propose a visit on " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " starting from " + startTime + " until " + endTime + ". Please feel free to contact me at the following telephone number: " + telephoneNumber + " if there are any issues with the proposed timing.\n\n");
            }
            writer.write("I look forward to your confirmation of the visit.\n");
            writer.write("Thank you for your attention.\n\n");
            if (name != null) {
                writer.write("Best regards,\n");
                writer.write(name);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
            e.printStackTrace();
        }
    }

    public void respondToBookingRequestEmail(String requesterEmail, int propertyId, String propertyLocation,
                                             LocalDate visitDate, LocalTime startTime, LocalTime endTime,
                                             boolean isAccepted, String agentName, long agentPhone, String reason) {
        // Format date and time
        String formattedDate = visitDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String formattedStartTime = startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String formattedEndTime = endTime.format(DateTimeFormatter.ofPattern("HH:mm"));

        // Create subject and body of the email based on whether the request is accepted or rejected
        String subject = isAccepted ? "Your Visit Booking Request Has Been Accepted" : "Your Visit Booking Request Has Been Rejected";
        String body = "Dear Customer,\n\n" +
                "Thank you for your interest in the property listed with ID: " + propertyId +
                " and located at: " + propertyLocation + ".\n\n" +
                "You had requested a visit for the date: " + formattedDate +
                ", with a start time at: " + formattedStartTime +
                " and ending at: " + formattedEndTime + ".\n\n" +
                (isAccepted ? "We are pleased to inform you that your booking request has been accepted. You will be greeted by our agent " + agentName + ".\n" +
                        "In case of any changes or queries, you may contact them at the following number: " + agentPhone + ".\n\n" +
                        "We look forward to welcoming you for the visit." :
                        "We regret to inform you that your booking request has been rejected for the following reason:\n\n" + reason +
                                "\n\nIf you have any doubts and need help you may contact the agent " + agentName + " with the following number: " + agentPhone + "\n") +
                "\nBest Regards,\n" +
                agentName;

        // Send the email
        Utils.sendEmail(requesterEmail, subject, body);
    }




}

package pt.ipp.isep.dei.esoft.project.ui.console;

import java.util.Scanner;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class DevTeamUI implements Runnable {

    public DevTeamUI() {

    }

    public void run() {
        System.out.println("\n");
        System.out.print("Development Team:\n");
        System.out.print("\t Carlos Silva - 1111180@isep.ipp.pt \n");
        System.out.print("\t Diogo Santo - 1212039@isep.ipp.pt \n");
        System.out.print("\t Luís Leal - 1100253@isep.ipp.pt \n");
        System.out.print("\t Ricardo Gonçalves - 1221720@isep.ipp.pt \n");
        System.out.print("\t Tiago Brito - 1222135@isep.ipp.pt \n");
        System.out.println("\n");

        askFeedback();

    }
    public static void askFeedback() {
        Scanner scanner = new Scanner(System.in);
        boolean fantastic = false;
        System.out.println("Is this team fantastic? Y/N");

        while (!fantastic) {
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("y")) {
                fantastic = true;
                System.out.println("Thank you for your feedback!");
            } else {
                System.out.println("Invalid response. Please enter 'Y'");
            }
        }
    }
}


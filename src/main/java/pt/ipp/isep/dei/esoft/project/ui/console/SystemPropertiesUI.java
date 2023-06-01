package pt.ipp.isep.dei.esoft.project.ui.console;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class SystemPropertiesUI implements Runnable {

    public SystemPropertiesUI() {
    }

    private void ReadOption(Scanner sc) {
        int option = sc.nextInt();
        switch (option) {
            case 1:
                PrintEmailProperties();
                break;
            case 2:
                PrintSMSProperties();
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    private void PrintSMSProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("sms.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find file");
                return;
            }
            prop.load(input);
            prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void PrintEmailProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("email.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find file");
                return;
            }
            prop.load(input);
            prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        System.out.println("1-Email");
        System.out.println("2-SMS");
        Scanner sc = new Scanner(System.in);
        ReadOption(sc);
    }

}


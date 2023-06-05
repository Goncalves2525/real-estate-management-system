package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class Utils {

    static public String readLineFromConsole(String prompt) {
        try {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Float readFloatFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                Float value = Float.parseFloat(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println();
        System.out.println("0 - Cancel");
    }

    static public Object selectsObject(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            try {
                value = Integer.valueOf(input);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    /**
     * Sends an email to a recipient
     * @param mail - the recipient's email
     * @param subject - the email's subject
     * @param body - the email's body
     */
    public static Boolean sendEmail(String mail, String subject, String body) {
        try{
            Properties prop = loadPropertiesFromFile("email.properties");
            String host = prop.getProperty("host");
            String port = prop.getProperty("port");
            String user = prop.getProperty("user");
            String pass = prop.getProperty("password");
            String from = prop.getProperty("from");

            FileWriter myWriter = new FileWriter("emailNotification.txt");
            myWriter.write("From: " + from + "\n");
            myWriter.write("To: " + mail + "\n");
            myWriter.write("Subject: " + subject + "\n");
            myWriter.write("Body: " + body + "\n");
            myWriter.close();
            return true;
        }catch (Exception e){
            System.out.println("An error occurred." + e.getMessage());
            return false;
        }
    }

    /**
     * Sends a SMS to a recipient
     * @param number - the recipient's number
     * @param message - the message to send
     */
    public Boolean sendSMS(String number, String message) {
        try{
            Properties prop = loadPropertiesFromFile("email.properties");
            String numberFrom = prop.getProperty("number");
            String password = prop.getProperty("password");
            String user = prop.getProperty("user");

            FileWriter myWriter = new FileWriter("smsNotification.txt");
            myWriter.write("From: " + numberFrom + "\n");
            myWriter.write("To: " + number + "\n");
            myWriter.write("Message: " + message + "\n");
            myWriter.close();
            return true;
        }catch (Exception e){
            System.out.println("An error occurred." + e.getMessage());
            return false;
        }
    }

    /**
     * Loads properties from a file
     * @param fileName - the name of the file
     * @return
     */
    public static Properties loadPropertiesFromFile(String fileName) {
        Properties prop = new Properties();
        try (InputStream input = Utils.class.getClassLoader().getResourceAsStream(fileName)) {

            if (input == null) {
                System.out.println("Error finding file");
                return null;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }


}

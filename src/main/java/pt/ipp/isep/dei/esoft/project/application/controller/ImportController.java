package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

/**
 * This class is the controller for the user story of publishing announcements.
 */
public class ImportController {

    private AnnouncementRepository announcementRepository;

    /**
     * Instantiates a new Import controller.
     */
    public ImportController() {
        this.announcementRepository = Repositories.getInstance().getAnnouncementRepository();
    }

    /**
     * Reads a file.
     * @param file file to read
     * @param delimiter delimiter
     * @return result of the read operation
     */
    public ArrayList<String[]> readFile(String file, String delimiter) {
        ArrayList<String[]> readResult = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(file));
            sc.useDelimiter(delimiter);
            //to ignore the first line (headers); maybe change this in the future
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                line = line.replaceAll("\"", ""); // Remove quotes
                String[] fields = line.split(delimiter);
                readResult.add(fields);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return readResult;
    }

    /** Checks if a file exists.
     * @param fileName name of the file
     * @return true if the file exists, false otherwise
     */
    public boolean checkFile(String fileName) {
        File f = new File(fileName);
        return f.exists();
    }

    /**
     * Imports data from a file.
     * @param dataToImport data to import
     * @return result of the import operation
     */
    public String importData(ArrayList<String[]> dataToImport) {
        String importResult = "";
        int totalImported = 0;
        int totalItemsToImport = dataToImport.size();
        try {

            for (String[] data : dataToImport) {
                try {
                    announcementRepository.addAnnouncementFromImportedFile(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2].trim()), data[3], data[4], data[5], data[6], Integer.parseInt(data[7].trim()), data[8], Integer.parseInt(data[9].trim()), data[10], data[11], data[12], data[13], data[14], data[15], data[16], data[17], Integer.parseInt(data[18].trim()), Integer.parseInt(data[19].trim()), Integer.parseInt(data[20].trim()), data[21], parseDate(data[22]), parseDate(data[23]), data[24],Integer.parseInt(data[25].trim()), data[26], data[27], data[28], data[29]);
                    totalImported++;
                    importResult += "\nAnnouncement imported: " + data[0];
                } catch (Exception e) {
                    importResult += "\nError importing Announcement: " + data[0] + " - " + e.getMessage();
                }
            }
        } catch (Exception e) {
            importResult += "Error importing data";
        }
        return importResult;
    }

    /**
     * Parses a date.
     * @param date string date to parse
     * @return parsed date
     */
    private Date parseDate(String date) {
        String[] dateSplit = date.split("-");
        return new Date(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]));
    }

    //create a method to load properties from a file with the Properties class
    public void loadProperties(String file) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("path/filename"));
        } catch (IOException e) {
        }
    }




    public void sendEmail(String mail) {




    }
}

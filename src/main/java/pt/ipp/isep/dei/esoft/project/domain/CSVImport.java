package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CSVImport implements DataImporter, Serializable {

    private AnnouncementRepository announcementRepository;

    /**
     * Imports data from a CSV file.
     * @param filePath
     */
    @Override
    public void importData(String filePath) {
        String delimiter = ";";
        ArrayList<String[]> readResult = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filePath));
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
        importData(readResult);
        //return readResult;
    }

    public String importData(ArrayList<String[]> dataToImport) {
        Repositories repositories = Repositories.getInstance();
        announcementRepository = repositories.getAnnouncementRepository();
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
}

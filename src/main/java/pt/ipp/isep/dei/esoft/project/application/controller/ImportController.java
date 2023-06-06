package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.DataImporter;
import pt.ipp.isep.dei.esoft.project.domain.SunExposure;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

/**
 * This class is the controller for the user story of publishing announcements.
 */
public class ImportController {

    private final AnnouncementRepository dealRepository;
    private final PropertyRepository propertyRepository;

    /**
     * Instantiates a new Import controller.
     */
    public ImportController() {

        this.dealRepository = Repositories.getInstance().getDealRepository();
        this.propertyRepository = Repositories.getInstance().getPropertyRepository();
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
                    dealRepository.addAnnouncementFromImportedFile(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2].trim()), data[3], data[4], data[5], data[6], Integer.parseInt(data[7].trim()), data[8], Integer.parseInt(data[9].trim()), data[10], data[11], data[12], data[13], data[14], data[15], data[16], data[17], Integer.parseInt(data[18].trim()), Integer.parseInt(data[19].trim()), Integer.parseInt(data[20].trim()), data[21], parseDate(data[22]), parseDate(data[23]), data[24], Integer.parseInt(data[25].trim()), data[26], data[27], data[28], data[29]);
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

    public void importData(String filePath) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("legacySystemFile.properties")) {
            properties.load(input);
            String fileType = filePath.split("\\.")[1];
            String importerClassName = properties.getProperty("importer"+fileType);
            Class<?> importerClass = Class.forName(importerClassName);
            DataImporter importer = (DataImporter) importerClass.getDeclaredConstructor().newInstance();
            importer.importData(filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendEmail(String mail) {


    }

    public ArrayList<Announcement> getDeals() {
        return dealRepository.getAllAnnouncementsSortedByDefualtCriteria();
    }

    public String importDatatoprperty(ArrayList<String[]> dataToImport) {
        String importResult = "";
        int totalImported = 0;
        int totalItemsToImport = dataToImport.size();
        try {
            for (String[] data : dataToImport) {
                try {
                    int announcementId = Integer.parseInt(data[0]);
                    String ownerName = data[1];
                    int ownerPassportNum = Integer.parseInt(data[2].trim());
                    String ownerTIN = data[3];
                    String ownerEmail = data[4];
                    String ownerPhone = data[5];
                    String propertyType = data[6];
                    double propertyArea = Double.parseDouble(data[7]);
                    String propertyLocation = data[8];
                    double propertyDistanceFromCenter = Double.parseDouble(data[9].trim());
                    int propertyNumberBedrooms = Integer.parseInt(data[10].trim());
                    int propertyNumberBathrooms = Integer.parseInt(data[11].trim());
                    int propertyNumParking = Integer.parseInt(data[12].trim());
                    boolean propertyCentralHeating = Boolean.parseBoolean(data[13]);
                    boolean propertyAirconditioned = Boolean.parseBoolean(data[14]);
                    boolean propertyBasement = Boolean.parseBoolean(data[15]);
                    boolean propertyLoft = Boolean.parseBoolean(data[16]);
                    String propertySunExposure = data[17];
                    double propertyRequestedSaleRentPrice = Double.parseDouble(data[18].trim());
                    double propertySaleRentPrice = Double.parseDouble(data[19].trim());
                    double commission = Double.parseDouble(data[20].trim());
                    String contractDuration = data[21];
                    Date propertyDateAnnounceRequest = parseDate(data[22]);
                    Date propertyDateOfSale = parseDate(data[23]);
                    String typeBusiness = data[24];
                    int storeID = Integer.parseInt(data[25].trim());
                    String storeName = data[26];
                    String storeLocation = data[27];
                    String storePhoneNumber = data[28];
                    String storeEmailAddress = data[29];

                    int propertyId;

                    if (propertyType.equalsIgnoreCase("House")) {
                        int numberOfBedrooms = propertyNumberBedrooms;
                        int numberOfBathrooms = propertyNumberBathrooms;
                        int numberOfParkingSpaces = propertyNumParking;
                        boolean hasCentralHeating = propertyCentralHeating;
                        boolean hasAirConditioning = propertyAirconditioned;
                        boolean hasBasement = propertyBasement;
                        boolean hasInhabitableLoft = propertyLoft;
                        SunExposure sunExposure = SunExposure.valueOf(propertySunExposure);
                        Address address = createAddress(data[8]);
                        propertyId = propertyRepository.addHouse(propertyArea, propertyDistanceFromCenter, propertySaleRentPrice, address,
                                numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning,
                                hasBasement, hasInhabitableLoft, sunExposure);
                    } else if (propertyType.equalsIgnoreCase("Apartment")) {
                        int numberOfBedrooms = propertyNumberBedrooms;
                        int numberOfBathrooms = propertyNumberBathrooms;
                        int numberOfParkingSpaces = propertyNumParking;
                        boolean hasCentralHeating = propertyCentralHeating;
                        boolean hasAirConditioning = propertyAirconditioned;
                        Address address = createAddress(data[8]);
                        propertyId = propertyRepository.addApartment(propertyArea, propertyDistanceFromCenter, propertySaleRentPrice, address,
                                numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning);
                    } else if (propertyType.equalsIgnoreCase("Land")) {
                        Address address = createAddress(data[8]);
                        propertyId = propertyRepository.addLand(propertyArea, propertyDistanceFromCenter, propertySaleRentPrice, address);
                    } else {
                        throw new IllegalArgumentException("Invalid property type: " + propertyType);
                    }

                    totalImported++;
                    importResult += "\nProperty imported: " + propertyId + ", Property ID: " + propertyId;
                } catch (Exception e) {
                    importResult += "\nError importing Property: " + data[0] + " - " + e.getMessage();
                }
            }
        } catch (Exception e) {
            importResult += "Error importing data";
        }
        return importResult;
    }
    private Address createAddress(String addressDetails) {
        String[] parts = addressDetails.split(",");
        if (parts.length == 4) {
            String street = parts[0].trim();
            String city = parts[1].trim();
            String state = parts[2].trim();
            String country = "USA";
            int zipCode = Integer.parseInt(parts[3].trim());
            return new Address(street, city, state, country, zipCode);
        } else {
            throw new IllegalArgumentException("Invalid address format: " + addressDetails);
        }
    }

}

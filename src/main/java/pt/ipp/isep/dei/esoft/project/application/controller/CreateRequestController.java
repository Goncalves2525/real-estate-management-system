package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Create Request Controller Class.
 *
 * This class is the controller for the user story of creating a request.
 *
 * This class serves as an intermediary between the user interface and the repository and domain classes.
 */

public class CreateRequestController {
    /**
     * Instantiates a new Create Request Controller.
     *
     * Repository instances are obtained from the Repositories class
     */
    private AgencyRepository agencyRepository = null;
    private EmployeeRepository employeeRepository = null;
    private AnnouncementRepository announcementRepository = null;

    private PropertyRepository propertyRepository = null;
    private CreateRequestController controller = null;
    private Agency[] agencyList;

    public CreateRequestController() {


        this.employeeRepository = Repositories.getInstance().getEmployeeRepository();
        this.announcementRepository = Repositories.getInstance().getAnnouncementRepository();
        this.propertyRepository = Repositories.getInstance().getPropertyRepository();
        this.agencyRepository = Repositories.getInstance().getAgencyRepository();
    }


    /**
     * This method returns the list of employees from the repository
     */
    public Employee getAgentByEmail(String agentEmail) {
        return employeeRepository.getEmployeeByEmail(agentEmail);
    }

    /**
     * This method creates an announcement for a house
     * @param agent - agent
     * @param transactionType - transaction type
     * @param contractDuration - contract duration
     * @param typeOfProperty - type of property
     * @param propertyID - property id
     */
    public void createAnnouncementHouse(Employee agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty, int propertyID) {
        Date publishDate = new Date();
        Commission commission = new Commission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, propertyID ,typeOfProperty, transactionType, publishDate, commission, photos);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }
    /**
     * This method creates an announcement for an apartment
     * @param agent - agent
     * @param transactionType - transaction type
     * @param contractDuration - contract duration
     * @param typeOfProperty - type of property
     * @param propertyID - property id
     */
    public void createAnnouncementLand(Employee agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty, int propertyID) {
        Date publishDate = new Date();
        Commission commission = new Commission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, propertyID,typeOfProperty, transactionType, publishDate, commission, photos);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }
    /**
     * This method creates an announcement for an apartment
     * @param agent - agent
     * @param transactionType - transaction type
     * @param contractDuration - contract duration
     * @param typeOfProperty - type of property
     * @param propertyID - property id
     */

    public void createAnnouncementApartment(Employee agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty, int propertyID) {
        Date publishDate = new Date();
        Commission commission = new Commission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, propertyID,typeOfProperty, transactionType, publishDate, commission, photos);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }

    public int createPropertyHouse(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning, boolean hasBasement, boolean hasInhabitableLoft, SunExposure sunExposure){
         return propertyRepository.addHouse(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning, hasBasement, hasInhabitableLoft, sunExposure);
    }

    public int createPropertyApartment(double area, double distanceFromCenter, double price, Address address, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces, boolean hasCentralHeating, boolean hasAirConditioning){
         return propertyRepository.addApartment(area, distanceFromCenter, price, address, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, hasCentralHeating, hasAirConditioning);
    }

    public int createPropertyLand(double area, double distanceFromCenter, double price, Address address){
         return propertyRepository.addLand(area, distanceFromCenter, price, address);
    }


    /**
     * This method returns the list of agencies
     */
 public Agency[] AgencyOptions() {
        Agency[] agencyList = new Agency[agencyRepository.getAgencies().size()];
        for (int i = 0; i < agencyRepository.getAgencies().size(); i++) {
            agencyList[i] = agencyRepository.getAgencies().get(i);

        }
        return agencyList;
    }



}

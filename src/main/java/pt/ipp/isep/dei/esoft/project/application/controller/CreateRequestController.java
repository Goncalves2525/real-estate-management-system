package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgencyListRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

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
    private AgencyListRepository agencyListRepository = null;
    private EmployeeRepository employeeRepository = null;
    private AnnouncementRepository announcementRepository = null;
    private CreateRequestController controller = null;
    private Agency[] agencyList;

    public CreateRequestController() {


        this.employeeRepository = Repositories.getInstance().getEmployeeRepository();
        this.announcementRepository = Repositories.getInstance().getAnnouncementRepository();
        this.agencyListRepository = Repositories.getInstance().getAgencyListRepository();
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
     * @param property - property
     */
    public void createannouncemntHouse(Employee agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty,Property property) {
        Date publishDate = new Date();
        Comission comission = new Comission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, property,typeOfProperty, transactionType, publishDate, comission, photos);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }
    /**
     * This method creates an announcement for an apartment
     * @param agent - agent
     * @param transactionType - transaction type
     * @param contractDuration - contract duration
     * @param typeOfProperty - type of property
     * @param property - property
     */
    public void createannouncemntLand(Employee agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty,Property property) {
        Date publishDate = new Date();
        Comission comission = new Comission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, property,typeOfProperty, transactionType, publishDate, comission, photos);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }
    /**
     * This method creates an announcement for an apartment
     * @param agent - agent
     * @param transactionType - transaction type
     * @param contractDuration - contract duration
     * @param typeOfProperty - type of property
     * @param property - property
     */

    public void createannouncemntApartment(Employee agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty,Property property) {
        Date publishDate = new Date();
        Comission comission = new Comission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, property,typeOfProperty, transactionType, publishDate, comission, photos);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }

    /**
     * This method returns the list of agencies
     */
 public Agency[] AgencyOptions() {
        Agency[] agencyList = new Agency[agencyListRepository.getAgencies().size()];
        for (int i = 0; i < agencyListRepository.getAgencies().size(); i++) {
            agencyList[i] = agencyListRepository.getAgencies().get(i);

        }
        return agencyList;
    }



}

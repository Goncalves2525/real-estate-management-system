package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgencyListRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateRequestController {

    private AgencyListRepository agencyListRepository = null;
    private EmployeeRepository employeeRepository = null;
    private AnnouncementRepository announcementRepository = null;
    private CreateRequestController controller = null;
    private Agency[] agencyList;
    public CreateRequestController() {

        //getAgencyListRepository();
        //getAgentListRepository();
        this.employeeRepository = Repositories.getInstance().getEmployeeRepository();
        this.announcementRepository = Repositories.getInstance().getAnnouncementRepository();
        this.agencyListRepository = Repositories.getInstance().getAgencyListRepository();
    }

    public List<Employee> getAgentList() {
        return employeeRepository.getEmployeeList();
    }

    private void getAgencyListRepository() {

    }

    public Employee getAgentByEmail(String agentEmail) {
        return employeeRepository.getEmployeeByEmail(agentEmail);

    }




    public void createannouncemntHouse(Employee agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty,Property property) {
        Date publishDate = new Date();
        Comission comission = new Comission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, property,typeOfProperty, transactionType, publishDate, comission, photos);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }

    public void createannouncemntLand(Employee agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty,Property property) {
        Date publishDate = new Date();
        Comission comission = new Comission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, property,typeOfProperty, transactionType, publishDate, comission, photos);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }

    public void createannouncemntApartment(Employee agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty,Property property) {
        Date publishDate = new Date();
        Comission comission = new Comission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, property,typeOfProperty, transactionType, publishDate, comission, photos);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }

 public Agency[] AgencyOptions() {
        Agency[] agencyList = new Agency[agencyListRepository.getAgencies().size()];
        for (int i = 0; i < agencyListRepository.getAgencies().size(); i++) {
            agencyList[i] = agencyListRepository.getAgencies().get(i);

        }
        return agencyList;
    }

   public Employee[] AgentOptions() {
       Employee[] agentList = new Employee[employeeRepository.getEmployeeList().size()];
       for (int i = 0; i < employeeRepository.getEmployeeList().size(); i++) {
           agentList[i] = employeeRepository.getEmployeeList().get(i);
           System.out.printf("%-8s %20s %15s %s%n", i+1, agentList[i].getName(), agentList[i].getEmail());

       }
       return agentList;
   }


}

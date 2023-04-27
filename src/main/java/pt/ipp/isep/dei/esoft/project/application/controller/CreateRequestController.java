package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgencyListRepository;
import pt.ipp.isep.dei.esoft.project.repository.AgentListRepository;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateRequestController {

    private AgencyListRepository agencyListRepository = null;
    private AgentListRepository agentListRepository = null;
    private AnnouncementRepository announcementRepository = null;
    private CreateRequestController controller = null;
    
public CreateRequestController() {
        //getAgencyListRepository();
        //getAgentListRepository();
    this.agentListRepository = Repositories.getInstance().getAgentListRepository();
    this.announcementRepository = Repositories.getInstance().getAnnouncementRepository();
    }


    public List<Agent> getAgentList() {
        return agentListRepository.getAgentList();
    }

    private void getAgencyListRepository() {
        
    }

    public Agent getAgentByEmail(String agentEmail) {
        return agentListRepository.getAgentByEmail(agentEmail);

    }




    public void createannouncemntHouse(Agent agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty,Property property) {
        Date publishDate = new Date();
        Comission comission = new Comission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, property,typeOfProperty, transactionType, publishDate, comission, photos);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }

    public void createannouncemntLand(Agent agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty,Property property,Land land) {
        Date publishDate = new Date();
        Comission comission = new Comission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, property,typeOfProperty, transactionType, publishDate, comission, photos,land);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }

    public void createannouncemntApartment(Agent agent, TransactionType transactionType, int contractDuration, TypeOfProperty typeOfProperty,Property property, Apartment apartment) {
        Date publishDate = new Date();
        Comission comission = new Comission();
        ArrayList<Photo> photos = new ArrayList<>();
        Announcement announcement = new Announcement(agent, property,typeOfProperty, transactionType, publishDate, comission, photos,apartment);
        announcementRepository.addAnnouncementFromOwner(announcement);
    }
}

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

    public void createAnnouncement(double area, double distanceFromCenter, double price, Address address, TypeOfProperty typeOfProperty, TransactionType transactionType, Date date, ArrayList<Photo> photos) {

    //if (typeOfProperty instanceof House) {

    //    House house = new House(area, distanceFromCenter, price,address,numberOfBedrooms,numberOfBathrooms,numberOfParkingSpaces,hasCentralHeating,hasAirConditioning,hasBasement,hasLoft, sunExposure);
    //       }
   // else if (typeOfProperty instanceof Apartment) {

    //        Apartment apartment = new Apartment(area, distanceFromCenter, price,address,numberOfBedrooms,numberOfBathrooms,numberOfParkingSpaces,hasCentralHeating,hasAirConditioning);
    //}

    //announcementRepository.addAnnouncementByRequest(property, typeOfProperty, transactionType, date, photos);

    }


}

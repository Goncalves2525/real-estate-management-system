package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.repository.AgencyListRepository;
import pt.ipp.isep.dei.esoft.project.repository.AgentListRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class CreateRequestController {

    private AgencyListRepository agencyListRepository = null;
    private AgentListRepository agentListRepository = null;
    private CreateRequestController controller = null;
    
public CreateRequestController() {
        //getAgencyListRepository();
        //getAgentListRepository();
    this.agentListRepository = Repositories.getInstance().getAgentListRepository();
    }


    public List<Agent> getAgentList() {
        return agentListRepository.getAgentList();
    }

    private void getAgencyListRepository() {
        
    }

    public Agent getAgentByEmail(String agentEmail) {
        return agentListRepository.getAgentByEmail(agentEmail);

    }


}

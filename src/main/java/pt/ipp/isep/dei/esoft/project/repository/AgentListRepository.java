package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgentListRepository {

    private final List<Agent> AgentList = new ArrayList<>();

    public Optional<Agent> add(Agent agent) {

        Optional<Agent> newAgent = Optional.empty();
        boolean operationSuccess = false;

        newAgent = Optional.of(agent.clone());
        operationSuccess = AgentList.add(newAgent.get());

        if (!operationSuccess) {
            newAgent = Optional.empty();
        }

        return newAgent;

    }

}

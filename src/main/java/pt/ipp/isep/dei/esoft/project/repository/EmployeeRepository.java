package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    private final List<Agent> AgentList = new ArrayList<>();
    private final ArrayList<Employee> employeeList = new ArrayList<>();

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

    public Agent getAgentByEmail(String agentEmail) {
        for (Agent agent : AgentList) {
            if (agent.getEmail().equals(agentEmail)) {
                return agent;
            }
        }
        return null;
    }

    public boolean addEmployee(Employee employee) {
        if (!employeeExists(employee.getEmail())) {
            return employeeList.add(employee);
        }
        return false;
    }

    public boolean employeeExists(String email) {
        return employeeList.stream().anyMatch(employee -> employee.hasEmail(email));
    }

    //returns the list of agents
    public List<Agent> getAgentList() {
        return this.AgentList;
    }
}

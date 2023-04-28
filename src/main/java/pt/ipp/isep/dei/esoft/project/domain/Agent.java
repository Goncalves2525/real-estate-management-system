package pt.ipp.isep.dei.esoft.project.domain;

import java.util.HashSet;
import java.util.Set;

public class Agent extends User implements Cloneable {

    private Agency agency;

    public Agent(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, Set<Role> roles) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address, roles);
        this.agency = agency;
    }

    public Agent(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.EMPLOYEE);
        this.setRoles(roles);
    }
    public Agent(String email, Agency agency) {
        super(email, agency);
    }

    public Agent(String email) {
        super(email);
    }

    public boolean isAgentInAgency(Agency agency) {
        return agency.agents.contains(this);
    }

    public Agency getAgency() {
        return this.agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Agent clone() {
        try {
            return (Agent) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " agency=" + agency;
    }

}

package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Agent class.
 * Extends User class.
 * Implements Cloneable interface.
 *
 */
public class Agent extends User implements Cloneable {

    private Agency agency;


    /**
     * Agent Full Constructor
     * @param name name
     * @param email email
     * @param passportCardNumber passport card number
     * @param taxNumber tax number
     * @param telephoneNumber telephone number
     * @param address address
     * @param agency agency
     * @param roles roles
     */
    public Agent(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, List<Role> roles) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address, roles);
        this.agency = agency;
    }

    /**
     * Agent Constructor without address, agency and roles
     * @param name name
     * @param email email
     * @param passportCardNumber passport card number
     * @param taxNumber tax number
     * @param telephoneNumber telephone number
     *
     */
    public Agent(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber);
        List<Role> roles = new ArrayList<>();
        roles.add(Role.AGENT);
        this.setRoles(roles);
    }

    /**
     * Agent Constructor with only name and email
     * @param email email
     *
     */
    public Agent(String email, Agency agency) {
        super(email, agency);
    }

    /**
     * Agent Constructor with only email
     * @param email email
     *
     */
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

    public String getAgentName() {
        return super.getName();
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

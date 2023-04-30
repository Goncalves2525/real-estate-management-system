package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Agency class that contains all the information about an agency.
 */
public class Agency {

    private int id;
    private String name;
    private String emailAddress;
    private int phoneNumber;

    private Address address;

    ArrayList<Employee> agents = new ArrayList<>();

    /**
     * Agency Constructor
     * @param id id
     * @param name name
     * @param emailAddress email address
     * @param phoneNumber phone number
     * @param address address
     * @param agents agents
     */
    public Agency(int id, String name, String emailAddress, int phoneNumber, Address address, ArrayList<Employee> agents) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.agents = agents;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Employee> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<Employee> agents) {
        this.agents = agents;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Agency: " + this.name + " - " + this.emailAddress + " - " + this.phoneNumber + " - " + this.id + ".";
    }

    //Clone agency
    /**
     * Clone agency.
     *
     * @return the agency
     */
    public Agency clone() {
        Agency clone = new Agency(this.id, this.name, this.emailAddress, this.phoneNumber, this.address, this.agents);
        clone.id = (this.id);
        clone.name = (this.name);
        clone.emailAddress = (this.emailAddress);
        clone.phoneNumber = (this.phoneNumber);
        clone.address = (this.address);
        clone.agents = (this.agents);

        return clone;
    }
}

package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

/**
 * Agency class that contains all the information about an agency.
 */
public class Agency {

    private final int id;
    private String name;
    private String emailAddress;
    private long phoneNumber;

    private Address address;

    private static int idCounter = 1;

    ArrayList<Employee> agents = new ArrayList<>();

    /**
     * Agency Constructor
     * @param name name
     * @param emailAddress email address
     * @param phoneNumber phone number
     * @param address address
     * @param agents agents
     */
    public Agency(String name, String emailAddress, long phoneNumber, Address address, ArrayList<Employee> agents) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.agents = agents;
        id = idCounter++;
    }

    /**
     * Agency Constructor
     * @param name name
     * @param emailAddress email address
     * @param phoneNumber phone number
     */
    public Agency(String name, String emailAddress, long phoneNumber) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        id = idCounter++;
    }

    /**
     * Agency Constructor with existing ID
     * @param id id
     * @param name name
     * @param emailAddress email address
     * @param phoneNumber phone number
     * @param address address
     */
    public Agency(int id, String name, String emailAddress, long phoneNumber, Address address) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public long getPhoneNumber() {
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
        Agency clone = new Agency(this.name, this.emailAddress, this.phoneNumber, this.address, this.agents);
        clone.name = (this.name);
        clone.emailAddress = (this.emailAddress);
        clone.phoneNumber = (this.phoneNumber);
        clone.address = (this.address);
        clone.agents = (this.agents);

        return clone;
    }
}

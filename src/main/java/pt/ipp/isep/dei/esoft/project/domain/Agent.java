package pt.ipp.isep.dei.esoft.project.domain;

public class Agent extends User implements Cloneable{

    private Agency agency;

    public Agent(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency);
    }

    public Agent(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, Role.EMPLOYEE);
    }

    public Agent(String email) {
        super(email);
    }

    public boolean isAgentInAgency(Agency agency){
        return agency.agents.contains(this);
    }


    public Agent clone(){
        try {
            return (Agent) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    //to string
    @Override
    public String toString() {
        return super.toString()  + " agency=" + agency;
    }

}

package pt.ipp.isep.dei.esoft.project.domain;

public class Agent extends User{


    public Agent(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address);
    }

    public Agent(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber);
    }

    public Agent(String email) {
        super(email);
    }


}

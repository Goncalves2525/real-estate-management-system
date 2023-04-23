package pt.ipp.isep.dei.esoft.project.domain;

public class SystemAdministrator extends User{
    public SystemAdministrator(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address);
    }

    public SystemAdministrator(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber);
    }

    public SystemAdministrator(String email) {
        super(email);
    }


}

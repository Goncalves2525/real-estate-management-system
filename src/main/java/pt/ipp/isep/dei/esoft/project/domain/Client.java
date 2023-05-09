package pt.ipp.isep.dei.esoft.project.domain;


/**
 * This class represents a Client and its characteristics.
 * Class Client extends EndUser.
 */
public class Client extends Person {

    /**
     * @param name name
     * @param email email
     * @param passportCardNumber passport card number
     * @param taxNumber tax number
     * @param telephoneNumber telephone number
     * @param address address
     */
    public Client(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address);
    }

    public Client(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber){
        super(name, email, passportCardNumber, taxNumber, telephoneNumber);
    }

}

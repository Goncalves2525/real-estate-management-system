package pt.ipp.isep.dei.esoft.project.domain;


import java.io.Serializable;

/**
 * This class represents a Client and its characteristics.
 * Class Client extends EndUser.
 */
public class Client extends Person implements Serializable {

    /**
     * @param name name
     * @param email email
     * @param passportCardNumber passport card number
     * @param taxNumber tax number
     * @param telephoneNumber telephone number
     * @param address address
     */
    public Client(String name, String email, int passportCardNumber, int taxNumber, long telephoneNumber, Address address) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address);
    }

    public Client(String name, String email, int passportCardNumber, int taxNumber, long telephoneNumber){
        super(name, email, passportCardNumber, taxNumber, telephoneNumber);
    }



}

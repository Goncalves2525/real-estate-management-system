package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;


/**
 * This class represents a Client and its characteristics.
 * Class Client extends EndUser.
 */
public class Client extends EndUser{

    /**
     * @param name name
     * @param email email
     * @param passportCardNumber passport card number
     * @param taxNumber tax number
     * @param telephoneNumber telephone number
     * @param address address
     * @param agency agency
     */
    public Client(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency);
    }

}

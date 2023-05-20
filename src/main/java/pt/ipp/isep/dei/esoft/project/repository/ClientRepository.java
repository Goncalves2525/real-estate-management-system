package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Client;

import java.util.ArrayList;

/**
 * The Client repository.
 */
public class ClientRepository {

    private ArrayList<Client> clientList = new ArrayList<>();

    /**
     * @param clientEmail email of the client
     * @return true if client exists, false otherwise
     */
    public boolean clientExists(String clientEmail) {
        for (Client client : clientList) {
            if (client.getEmail().equals(clientEmail)) {
                return true;
            }
        }
        return false;
    }


    public boolean addClient(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address) {
        Client client = new Client(name, email, passportCardNumber, taxNumber, telephoneNumber, address);
        if (!clientExists(client.getEmail())) {
            clientList.add(client);
            return true;
        }
        return false;
    }


    /**
     * @param clientEmail email of the client
     * @return client with the given email
     */
    public Client getClientByEmail(String clientEmail) {
        for (Client client : clientList) {
            if (client.getEmail().equals(clientEmail)) {
                return client;
            }
        }
        return null;
    }


}

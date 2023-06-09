package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Client;

import java.io.*;
import java.util.ArrayList;

/**
 * The Client repository.
 */
public class ClientRepository implements Serializable{

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

    /**
     * Add client
     * @param name
     * @param email
     * @param passportCardNumber
     * @param taxNumber
     * @param telephoneNumber
     * @param address
     * @return true if client is added, false otherwise
     */
    public boolean addClient(String name, String email, int passportCardNumber, int taxNumber, long telephoneNumber, Address address) {
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


    public void serialize(String fileName) {
        try{
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(clientList);

            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize(String fileName) {
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            clientList = (ArrayList<Client>) in.readObject();

            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

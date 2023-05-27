package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Client;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {
    private ClientRepository clientRepository;
    private Address address;


    @BeforeEach
    void setUp() {
        clientRepository = new ClientRepository();
        address = new Address("Street", "City", "Country", 1234);
    }


    @Test
    void testClientExistsTrue() {
        clientRepository.addClient("John Doe", "john@gmail.com", 123456, 12345, 123456789, address);
        assertTrue(clientRepository.clientExists("john@gmail.com"));
    }

    @Test
    void testClientExistsFalse() {
        clientRepository.addClient("John Doe", "john@gmail.com", 123456, 12345, 123456789, address);
        assertFalse(clientRepository.clientExists("not_exists@gmail.com"));
    }

    @Test
    void testAddClientTrue() {
        boolean added = clientRepository.addClient("John Doe", "john@gmail.com", 123456, 12345, 123456789, address);
        assertTrue(added);
        assertTrue(clientRepository.clientExists("john@gmail.com"));
    }

    @Test
    void testAddClientFalse() {
        boolean added = clientRepository.addClient("John Doe", "john@gmail.com", 123456, 12345, 123456789, address);
        boolean duplicate = clientRepository.addClient("John Doe", "john@gmail.com", 123456, 12345, 123456789, address);
        assertFalse(duplicate);
    }

    @Test
    void testGetClientByEmailTrue() {
        clientRepository.addClient("John Doe", "john@gmail.com", 123456, 12345, 123456789, address);
        Client client = clientRepository.getClientByEmail("john@gmail.com");
        assertNotNull(client);
        assertEquals("John Doe", client.getName());
    }

    @Test
    void testGetClientByEmailFalse() {
        clientRepository.addClient("John Doe", "john@gmail.com", 123456, 12345, 123456789, address);
        Client notExists = clientRepository.getClientByEmail("not_exists@gmail.com");
        assertNull(notExists);
    }
}
# US 007 - As an unregistered person, register in the system to buy, sell or rent properties

# 4. Tests 

**Test 1:** Test if the client exists in the repository

    @Test
    void testClientExistsTrue() {
    clientRepository.addClient("John Doe", "john@gmail.com", 123456, 12345, 123456789, address);
    assertTrue(clientRepository.clientExists("john@gmail.com"));
    }


**Test 2:** Test if the client is added to the repository

    @Test
    void testAddClientTrue() {
        boolean added = clientRepository.addClient("John Doe", "john@gmail.com", 123456, 12345, 123456789, address);
        assertTrue(added);
        assertTrue(clientRepository.clientExists("john@gmail.com"));
    }

**Test 3:** Test if is possible to get a client by email

    @Test
    void testGetClientByEmailTrue() {
        clientRepository.addClient("John Doe", "john@gmail.com", 123456, 12345, 123456789, address);
        Client client = clientRepository.getClientByEmail("john@gmail.com");
        assertNotNull(client);
        assertEquals("John Doe", client.getName());
    }

# 5. Construction (Implementation)


## Class RegisterUserUI

```java
public class RegisterUserUI implements Runnable {

    private final RegisterUserController controller = new RegisterUserController();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        String name = inputName(sc);

        String email = inputEmail(sc);

        int passportCardNumber = inputPassportCardNumber(sc);

        int taxNumber = inputTaxNumber(sc);

        int telephoneNumber = inputTelephoneNumber(sc);

        Address address = inputAddress(sc);

        String password = controller.generatePassword();

        processRegistration(password, name, email, passportCardNumber, taxNumber, telephoneNumber, address);
    }
```       


## Class RegisterUserController 

```java
public boolean registerClient(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address) {
        return getClientRepository().addClient(name, email, passportCardNumber, taxNumber, telephoneNumber, address);
        }
```       
 


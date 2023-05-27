package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.PasswordGenerator;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class RegisterUserController {

    private PasswordGenerator passwordGenerator = new PasswordGenerator();
    private AuthenticationRepository authenticationRepository = null;

    private ClientRepository clientRepository = null;

    /**
     * Instantiates a new Register user controller.
     */
    public RegisterUserController() {
        getAuthenticationRepository();
        getClientRepository();
    }

    /**
     * Gets authentication repository.
     *
     * @return the authentication repository
     */

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Gets client repository.
     *
     * @return the client repository
     */
    private ClientRepository getClientRepository() {
        if (clientRepository == null) {
            Repositories repositories = Repositories.getInstance();
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;
    }

    /**
     * Register client
     *
     * @param name              the name
     * @param email             the email
     * @param passportCardNumber the passport card number
     * @param taxNumber         the tax number
     * @param telephoneNumber   the telephone number
     * @param address           the address
     * @return True if client is added, false otherwise
     */
    public boolean registerClient(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address) {
        return getClientRepository().addClient(name, email, passportCardNumber, taxNumber, telephoneNumber, address);
    }


    /**
     * Create user
     *
     * @param name     the name
     * @param email    the email
     * @param password the password
     * @param role     the role
     */
    public void createUser(String name, String email, String password, String role) {
        getAuthenticationRepository().addUserWithRole(name, email, password, role);
    }

    /**
     * Generate password
     *
     * @return the string password
     */
    public String generatePassword() {
        return passwordGenerator.generatePassword();
    }
}
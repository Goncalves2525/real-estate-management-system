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

    public RegisterUserController() {
        getAuthenticationRepository();
        getClientRepository();
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private ClientRepository getClientRepository() {
        if (clientRepository == null) {
            Repositories repositories = Repositories.getInstance();
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;
    }

    public boolean registerClient(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address) {
        return getClientRepository().addClient(name, email, passportCardNumber, taxNumber, telephoneNumber, address);
    }

    public void createUser(String name, String email, String password, String role) {
        getAuthenticationRepository().addUserWithRole(name, email, password, role);
    }

    public String generatePassword() {
        return passwordGenerator.generatePassword();
    }
}
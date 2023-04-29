package pt.ipp.isep.dei.esoft.project.application.controller.authorization;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class AuthenticationController {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";
    public static final String ROLE_CLIENT = "CLIENT";
    public static final String ROLE_STORE_NETWORK_MANAGER = "STORE_NETWORK_MANAGER";
    public static final String ROLE_STORE_MANAGER = "STORE_MANAGER";
    public static final String ROLE_NO_ROLE = "";

    //private final ApplicationSession applicationSession;
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationController() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    public boolean doLogin(String email, String pwd) {
        try {
            return authenticationRepository.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    //get user email
    public String getUserEmail() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserSession().getUserId().getEmail();
        }
        return null;
    }

    public List<UserRoleDTO> getUserRoles() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    public void doLogout() {
        authenticationRepository.doLogout();
    }
}

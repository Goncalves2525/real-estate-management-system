package pt.ipp.isep.dei.esoft.project.repository;


import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;

import java.util.Optional;

public class AuthenticationRepository {
    private final AuthFacade authenticationFacade = new AuthFacade();

    /**
     * Login
     * @param email - email
     * @param pwd - password
     * @return boolean - success
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Logout
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Get current user
     * @return UserSession - current user session
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Adds user Role
     * @param id - id
     * @param description - description
     * @return boolean - success
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Adds User with a Role
     * @param name - name
     * @param email - email
     * @param pwd - password
     * @param roleId - role id
     * @return boolean - success
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }

}

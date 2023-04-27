package pt.ipp.isep.dei.esoft.project.application.session;

import pt.ipp.isep.dei.esoft.project.domain.Role;
import pt.ipp.isep.dei.esoft.project.domain.User;

public class UserSession {

    public static boolean isLoggedIn(User user) {
        if (user != null)
            return true;
        else
            return false;
    }

    //method to check if user has session
    public boolean hasSession(User user) {
        if (user != null) {
            return true;
        }
        return false;
    }

    //method to check if the user is an administrator
    public boolean isAdmin(User user) {
        if (user != null) {
            if (user.getRoles().contains(Role.ADMIN)) {
                return true;
            }
        }
        return false;
    }


}

package pt.ipp.isep.dei.esoft.project.application.session;

import pt.ipp.isep.dei.esoft.project.domain.EndUser;
import pt.ipp.isep.dei.esoft.project.domain.Role;

public class UserSession {

    public static boolean isLoggedIn(EndUser endUser) {
        if (endUser != null)
            return true;
        else
            return false;
    }

    //method to check if user has session
    public boolean hasSession(EndUser endUser) {
        if (endUser != null) {
            return true;
        }
        return false;
    }

    //method to check if the user is an administrator
    public boolean isAdmin(EndUser endUser) {
        if (endUser != null) {
            if (endUser.getRoles().contains(Role.ADMIN)) {
                return true;
            }
        }
        return false;
    }


}

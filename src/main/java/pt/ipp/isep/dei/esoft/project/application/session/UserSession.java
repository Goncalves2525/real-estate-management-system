package pt.ipp.isep.dei.esoft.project.application.session;

import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.Role;

public class UserSession {

    public static boolean isLoggedIn(Person person) {
        if (person != null)
            return true;
        else
            return false;
    }

    //method to check if user has session
    public boolean hasSession(Person person) {
        if (person != null) {
            return true;
        }
        return false;
    }

    //method to check if the user is an administrator
    public boolean isAdmin(Person person) {
        if (person != null) {
            if (person.getRoles().contains(Role.ADMIN)) {
                return true;
            }
        }
        return false;
    }


}

package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
    /**
     * This class represents the repository for roles.
     */

public class RoleRepository implements Serializable {

    private ArrayList<Role> roles = new ArrayList<>();

        /**
         * List of roles.
         * @return
         */
    public ArrayList<Role> getRolesToCreate() {
        //return only a few roles
        ArrayList<Role> rolesToReturn = new ArrayList<>();
        ArrayList<Role> roles = new ArrayList<>(Arrays.asList(Role.values()));
        for (Role role : roles) {
            if(!role.toString().equals("ADMIN") && !role.toString().equals("CLIENT")){
                rolesToReturn.add(role);
            }
        }
        return rolesToReturn;
    }
    /**
     * Get the list of roles.
     *
     * @return list of roles
     */
    public Role getRoleByValue(int value) {
        ArrayList<Role> roles = new ArrayList<>(Arrays.asList(Role.values()));
        for (Role role : roles) {
            if (role.getValue() == value) {
                return role;
            }
        }
        return null;
    }
}

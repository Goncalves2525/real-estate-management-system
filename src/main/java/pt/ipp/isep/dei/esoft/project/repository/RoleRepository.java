package pt.ipp.isep.dei.esoft.project.repository;



import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
    /**
     * This class represents the repository for roles.
     */

public class RoleRepository {

    private final List<Role> roles = new ArrayList<>();

        /**
         * List of roles.
         * @return
         */
    public List<Role> getRolesToCreate() {
        //return only a few roles
        List<Role> rolesToReturn = new ArrayList<>();
        List<Role> roles = Arrays.asList(Role.values());
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
        List<Role> roles = Arrays.asList(Role.values());
        for (Role role : roles) {
            if (role.getValue()==value) {
                return role;
            }
        }
        return null;
    }
}

package pt.ipp.isep.dei.esoft.project.repository;



import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RoleRepository {

    private final List<Role> roles = new ArrayList<>();

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

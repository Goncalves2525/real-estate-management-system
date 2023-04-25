package pt.ipp.isep.dei.esoft.project.ui.console.authorization;


import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ListPropertiesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.PublishRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.AdminUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AuthenticationUI implements Runnable {
    private final AuthenticationController ctrl;

    public AuthenticationUI() {
        ctrl = new AuthenticationController();
    }

    public void run() {
        boolean success = doLogin();

        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                System.out.println("No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI, role);
                } else {
                    System.out.println("No role selected.");
                }
            }
        }
        this.logout();
    }

    private List<MenuItem> getMenuItemForRoles() {
        List<UserRoleDTO> roles = ctrl.getUserRoles();
        List<MenuItem> rolesUI = new ArrayList<>();
        for(UserRoleDTO role : roles){
            if(role.getDescription().equals(AuthenticationController.ROLE_ADMIN)){
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new AdminUI()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_EMPLOYEE)) {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_EMPLOYEE, new PublishRequestUI()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_CLIENT)) {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_CLIENT, new ListPropertiesUI()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_STORE_NETWORK_MANAGER)) {
                //currently not in use; ignore the UI used
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_STORE_NETWORK_MANAGER, new MainMenuUI()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_STORE_MANAGER)){
                //currently not in use; ignore the UI used
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_STORE_NETWORK_MANAGER, new MainMenuUI()));
            } else {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_NO_ROLE, new MainMenuUI()));
            }
        }

        return rolesUI;
    }

    private boolean doLogin() {
        boolean success = false;
        int maxAttempts = 3;
        do {
            maxAttempts--;
            String id = Utils.readLineFromConsole("Enter UserId/Email: ");
            String pwd = Utils.readLineFromConsole("Enter Password: ");

            success = ctrl.doLogin(id, pwd);
            if (!success) {
                System.out.println("Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
            }

        } while (!success && maxAttempts > 0);

        return success;
    }

    private void logout() {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                item.run();
            }
        }
        if (!found) {
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
        }
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
        }
    }

}

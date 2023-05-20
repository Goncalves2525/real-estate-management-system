package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class MainMenuUI implements Runnable {

    public MainMenuUI() {
    }

    private final AuthenticationController controller = new AuthenticationController();


    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
//        if(controller.getUserRoles() != null){
//            if(controller.getUserRoles().contains("ADMIN")){
//                options.add(new MenuItem("Register an Employee", new DevTeamUI()));
//                options.add(new MenuItem("Logout", new AuthenticationUI()));
//            } else if (controller.getUserRoles().contains("EMPLOYEE")) {
//                options.add(new MenuItem("List Properties", new ListPropertiesUI())); //Criar tb ListPropertiesController e ListPropertiesMapper
//                options.add(new MenuItem("Publish a Sale", new PublishPropertyUI()));//Criar tb PublishSaleController e PublishedSaleMapper
//                options.add(new MenuItem("Submit an Order", new SubmitOrderUI()));//Criar tb SubmitOrderController e SubmitOrderMapper
//                options.add(new MenuItem("Logout", new AuthenticationUI()));
//            }
//        }
//        else{
            options.add(new MenuItem("Login", new AuthenticationUI()));
            options.add(new MenuItem("Register", new RegisterUserUI()));
            options.add(new MenuItem("View Properties", new ListAnnouncementsUI()));
            options.add(new MenuItem("Dev Team", new DevTeamUI()));
            options.add(new MenuItem("Manage property orders", new PropertyOrderManagementUI()));

        //}


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }


}

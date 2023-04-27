package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        //addTaskCategories();
        //addOrganization();
        addUsers();
        addAgencies();
        addAgents();
        addAnnouncements();
    }

    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");
        organization.addEmployee(new Employee("admin@this.app"));
        organization.addEmployee(new Employee("employee@this.app"));
        organizationRepository.add(organization);
    }

    private void addAgencies(){
        //get Agency repository
        AgencyListRepository agencyListRepository = Repositories.getInstance().getAgencyListRepository();
        List<Agent> agents = new ArrayList<>();
        List<Agent> agents2 = new ArrayList<>();
        agents.add(new Agent("Agent1"));
        agents.add(new Agent("Agent2"));
        agents.add(new Agent("Agent3"));
        agents2.add(new Agent("Agent1"));
        agents2.add(new Agent("Agent2"));
        agents2.add(new Agent("Agent3"));
        Agency agency = new Agency(1, "Agency1", "agency1@this.app", 934875844, new Address("street1", "City1", "District1", "State1", 1234), agents);
        Agency agency2 = new Agency(2, "Agency2", "agency2@this.app", 934875845, new Address("street2", "City2", "District2", "State2", 4321), agents2);
        agencyListRepository.add(agency);
        agencyListRepository.add(agency2);
    }

    private void addAgents(){
        //get Agency repository
        AgentListRepository agentListRepository = Repositories.getInstance().getAgentListRepository();
        Agent agent = new Agent("Agent1@this.app");
        agentListRepository.add(agent);
    }

    private void addAnnouncements(){
        AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
        Comission c1 = new Comission();
        Date d1 = new Date(2019, 10, 10);
        Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        Property p1 = new House(150, 30, 250000, a1, 1, 2, true, true);
        Property p2 = new House(150, 30, 100000, a1, 2, 2, true, true);
        Property p3 = new House(150, 30, 500000, a1, 3, 2, true, true);
        Property p4 = new House(150, 30, 300000, a1, 4, 2, true, true);
        Property p5 = new House(150, 30, 400000, a1, 5, 2, true, true);
        Property p6 = new Apartment(150, 30, 250000, a1, 1, 2);
        Property p7 = new Apartment(150, 30, 100000, a1, 2, 2);
        Property p8 = new Apartment(150, 30, 500000, a1, 3, 2);
        Property p9 = new Apartment(150, 30, 300000, a1, 4, 2);
        Property p10 = new Apartment(150, 30, 400000, a1, 5, 2);
        Property p11 = new Land(150, 30, 250000, a1);
        Property p12 = new Land(150, 30, 100000, a1);
        Property p13 = new Land(150, 30, 500000, a1);
        Property p14 = new Land(150, 30, 300000, a1);
        Property p15 = new Land(150, 30, 400000, a1);
        announcementRepository.addAnnouncement(p1, TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null);
        announcementRepository.addAnnouncement(p2, TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null);
        announcementRepository.addAnnouncement(p3, TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null);
        announcementRepository.addAnnouncement(p4, TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null);
        announcementRepository.addAnnouncement(p5, TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null);
        announcementRepository.addAnnouncement(p6, TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null);
        announcementRepository.addAnnouncement(p7, TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null);
        announcementRepository.addAnnouncement(p8, TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null);
        announcementRepository.addAnnouncement(p9, TypeOfProperty.APARTMENT, TransactionType.RENT, d1, c1, null);
        announcementRepository.addAnnouncement(p10, TypeOfProperty.APARTMENT, TransactionType.RENT, d1, c1, null);
        announcementRepository.addAnnouncement(p11, TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null);
        announcementRepository.addAnnouncement(p12, TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null);
        announcementRepository.addAnnouncement(p13, TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null);
        announcementRepository.addAnnouncement(p14, TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null);
        announcementRepository.addAnnouncement(p15, TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null);
    }

    private void addTaskCategories() {
        //TODO: add bootstrap Task Categories here

        //get task category repository
//        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
//        taskCategoryRepository.add(new TaskCategory("Analysis"));
//        taskCategoryRepository.add(new TaskCategory("Design"));
//        taskCategoryRepository.add(new TaskCategory("Implementation"));
//        taskCategoryRepository.add(new TaskCategory("Development"));
//        taskCategoryRepository.add(new TaskCategory("Testing"));
//        taskCategoryRepository.add(new TaskCategory("Deployment"));
//        taskCategoryRepository.add(new TaskCategory("Maintenance"));
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,
                AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserWithRole("MainAdministrator", "admin@this.app", "admin",
            AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Client", "client@this.app", "pwd",
                AuthenticationController.ROLE_CLIENT);


    }


}

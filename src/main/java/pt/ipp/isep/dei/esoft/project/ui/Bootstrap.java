package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Calendar;
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

    private void addAgencies(){
        //get Agency repository
        AgencyListRepository agencyListRepository = Repositories.getInstance().getAgencyListRepository();
        List<Employee> agents = new ArrayList<>();
        List<Employee> agents2 = new ArrayList<>();
        Agency agency = new Agency(1, "Agency1", "agency1@this.app", 934875844, new Address("street1", "City1", "District1", "State1", 1234), agents);
        Agency agency2 = new Agency(2, "Agency2", "agency2@this.app", 934875845, new Address("street2", "City2", "District2", "State2", 4321), agents2);
        agencyListRepository.add(agency);
        agencyListRepository.add(agency2);
    }

    private void addAgents(){
        //get Agency repository
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        List<Employee> agents = new ArrayList<>();
        List<Employee> agents2 = new ArrayList<>();
        Agency agency = new Agency(1, "Agency1", "agency1@this.app", 934875844, new Address("street1", "City1", "District1", "State1", 1234), agents);
        Agency agency2 = new Agency(2, "Agency2", "agency2@this.app", 934875845, new Address("street2", "City2", "District2", "State2", 4321), agents2);

        employeeRepository.add(new Employee("employee@this.app",agency));
        employeeRepository.add(new Employee("Agent11",agency));
        employeeRepository.add(new Employee("Agent111",agency));
        employeeRepository.add(new Employee("Agent2",agency2));
        employeeRepository.add(new Employee("Agent22",agency2));
        employeeRepository.add(new Employee("Agent222",agency2));
    }

    private void addAnnouncements(){
       AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
       Comission c1 = new Comission();
       Date d1 = new Date(2022, Calendar.APRIL, 10);
       Agent agent1 = new Agent("employee@this.app");
       Agent agent2 = new Agent("employee2@this.app");
       Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
       Address a2 = new Address("Rua 2", "Porto", "Porto", "Douro", 12345);
       Property p1 = new House(150, 30, 250000, a1, 1, 2, true, true);
       Property p2 = new House(150, 30, 100000, a1, 2, 2, true, true);
       Property p3 = new House(150, 30, 500000, a1, 3, 2, true, true);
       Property p4 = new House(150, 30, 300000, a2, 4, 2, true, true);
       Property p5 = new House(150, 30, 400000, a2, 5, 2, true, true);
       Property p6 = new Apartment(150, 30, 250000, a1, 1, 2);
       Property p7 = new Apartment(150, 30, 100000, a1, 2, 2);
       Property p8 = new Apartment(150, 30, 500000, a1, 3, 2);
       Property p9 = new Apartment(150, 30, 300000, a2, 4, 2);
       Property p10 = new Apartment(150, 30, 400000, a2, 5, 2);
       Property p11 = new Land(150, 30, 250000, a1);
       Property p12 = new Land(150, 30, 100000, a1);
       Property p13 = new Land(150, 30, 500000, a1);
       Property p14 = new Land(150, 30, 300000, a2);
       Property p15 = new Land(150, 30, 400000, a2);
       announcementRepository.addAnnouncement(p1, TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncement(p1, TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, true);
       announcementRepository.addAnnouncement(p2, TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncement(p2, TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, true);
       announcementRepository.addAnnouncement(p3, TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, true);
       announcementRepository.addAnnouncement(p4, TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncement(p5, TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, true);
       announcementRepository.addAnnouncement(p5, TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncement(p6, TypeOfProperty.APARTMENT, TransactionType.RENT, d1, c1, null, true);
       announcementRepository.addAnnouncement(p7, TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncement(p7, TypeOfProperty.APARTMENT, TransactionType.RENT, d1, c1, null, true);
       announcementRepository.addAnnouncement(p8, TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncement(p9, TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncement(p10, TypeOfProperty.APARTMENT, TransactionType.RENT, d1, c1, null, true);
       announcementRepository.addAnnouncement(p10, TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncement(p11, TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncement(p11, TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null, true);
       announcementRepository.addAnnouncement(p12, TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncement(p12, TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null, true);
       announcementRepository.addAnnouncement(p13, TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncement(p14, TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null, true);
       announcementRepository.addAnnouncement(p15, TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null, true);
       announcementRepository.addAnnouncement(p15, TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, true);
       announcementRepository.addAnnouncementWithAgent(agent1, p11, TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, false);
       announcementRepository.addAnnouncementWithAgent(agent1, p13, TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, false);
       announcementRepository.addAnnouncementWithAgent(agent1, p14, TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, false);
       announcementRepository.addAnnouncementWithAgent(agent2, p12, TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null, false);
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

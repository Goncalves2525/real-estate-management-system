package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addUsers();
        addAgencies();
        addAgents();
        addAnnouncements();
    }

    private void addAgencies() {
        //get Agency repository
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        ArrayList<Employee> agents = new ArrayList<>();
        ArrayList<Employee> agents2 = new ArrayList<>();
        Agency agency = new Agency("Agency1", "agency1@this.app", 934875844, new Address("street1", "City1", "District1", "State1", 1234), agents);
        Agency agency2 = new Agency("Agency2", "agency2@this.app", 934875845, new Address("street2", "City2", "District2", "State2", 4321), agents2);
        agencyRepository.add(agency);
        agencyRepository.add(agency2);
    }

    private void addAgents() {
        //get Agency repository
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        ArrayList<Employee> agents = new ArrayList<>();
        ArrayList<Employee> agents2 = new ArrayList<>();


        employeeRepository.add(new Employee("employee@this.app", 1));
        employeeRepository.add(new Employee("Agent11", 1));
        employeeRepository.add(new Employee("Agent111", 1));
        employeeRepository.add(new Employee("Agent2", 2));
        employeeRepository.add(new Employee("Agent22", 2));
        employeeRepository.add(new Employee("Agent222", 2));
    }

    private void addAnnouncements() {
        AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
        PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();
        Commission c1 = new Commission();
        ArrayList<Employee> agents = new ArrayList<>();
        Date d1 = new Date(2022, Calendar.APRIL, 10);
        Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        ArrayList<Role> roles = new ArrayList<>();
        Employee agent1 = new Employee("Agent1", "employee@this.app", 123456789, 123, 123, new Address("street", "city", "district", "state", 1234), roles, 1);
        Employee agent2 = new Employee("Agent2", "employee2@this.app", 123456789, 123, 123, new Address("street", "city", "district", "state", 1234), roles, 1);
        Address a2 = new Address("Rua 2", "Porto", "Porto", "Douro", 12345);
        propertyRepository.addHouse(150, 30, 250000, a1, 1, 2, 1, true, true, true, true, SunExposure.NORTH);
        propertyRepository.addHouse(150, 30, 100000, a1, 2, 2, 1, true, true, true, true, SunExposure.NORTH);
        propertyRepository.addHouse(150, 30, 500000, a1, 3, 2, 1, true, true, true, true, SunExposure.NORTH);
        propertyRepository.addHouse(150, 30, 300000, a2, 4, 2, 1, true, true, true, true, SunExposure.NORTH);
        propertyRepository.addHouse(150, 30, 400000, a2, 5, 2, 1, true, true, true, true, SunExposure.NORTH);
        propertyRepository.addApartment(150, 30, 250000, a1, 1, 2, 1, true, true);
        propertyRepository.addApartment(150, 30, 100000, a1, 2, 2, 1, true, true);
        propertyRepository.addApartment(150, 30, 500000, a1, 3, 2, 1, true, true);
        propertyRepository.addApartment(150, 30, 300000, a2, 4, 2, 1, true, true);
        propertyRepository.addApartment(150, 30, 400000, a2, 5, 2, 1, true, true);
        propertyRepository.addLand(150, 30, 250000, a1);
        propertyRepository.addLand(150, 30, 100000, a1);
        propertyRepository.addLand(150, 30, 500000, a1);
        propertyRepository.addLand(150, 30, 300000, a2);
        propertyRepository.addLand(150, 30, 400000, a2);

        ArrayList<Property> properties = propertyRepository.getAllProperties();

        announcementRepository.addAnnouncement(properties.get(0).getId(), TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(1).getId(), TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(2).getId(), TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(3).getId(), TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(4).getId(), TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(5).getId(), TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(6).getId(), TypeOfProperty.APARTMENT, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(7).getId(), TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(8).getId(), TypeOfProperty.APARTMENT, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(9).getId(), TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(10).getId(), TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(11).getId(), TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(12).getId(), TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(13).getId(), TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncement(properties.get(14).getId(), TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, true);

        announcementRepository.addAnnouncementWithAgent(agent1, properties.get(10).getId(), TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, false);
        announcementRepository.addAnnouncementWithAgent(agent1, properties.get(5).getId(), TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, false);
        announcementRepository.addAnnouncementWithAgent(agent1, properties.get(1).getId(), TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, false);
        announcementRepository.addAnnouncementWithAgent(agent2, properties.get(13).getId(), TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null, false);

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

        authenticationRepository.addUserWithRole("Client2", "client2@this.app", "pwd",
                AuthenticationController.ROLE_CLIENT);


    }


}

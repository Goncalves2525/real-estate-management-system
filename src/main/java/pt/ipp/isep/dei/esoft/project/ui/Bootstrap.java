package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addUsers();
        addAgencies();
        addAgents();
        addAnnouncements();
        addClients();
        addOrders();
        addVisitSchedule();
        addEmailProperties();
        addSMSProperties();
    }

    private void addVisitSchedule() {
        VisitScheduleRepository visitScheduleRepository = Repositories.getInstance().getVisitScheduleRepository();
        visitScheduleRepository.addVisitSchedule(new VisitSchedule(5, "Name5", 9100000005L, LocalDate.of(2023, 6, 5), LocalTime.of(10, 5), LocalTime.of(11, 0), false, "employee@this.app","Street of happy 5"));
        visitScheduleRepository.addVisitSchedule(new VisitSchedule(1, "Name1", 9100000001L, LocalDate.of(2023, 6, 1), LocalTime.of(10, 1), LocalTime.of(11, 0), false, "employee@this.app","Street of happy 1"));
        visitScheduleRepository.addVisitSchedule(new VisitSchedule(6, "Name6", 9100000006L, LocalDate.of(2023, 6, 6), LocalTime.of(10, 6), LocalTime.of(11, 0), false, "employee@this.app","Street of happy 6"));
        visitScheduleRepository.addVisitSchedule(new VisitSchedule(4, "Name4", 9100000004L, LocalDate.of(2023, 6, 4), LocalTime.of(10, 4), LocalTime.of(11, 0), false, "employee@this.app","Street of happy 4"));
        visitScheduleRepository.addVisitSchedule(new VisitSchedule(2, "Name2", 9100000002L, LocalDate.of(2023, 6, 2), LocalTime.of(10, 2), LocalTime.of(11, 0), false, "employee@this.app","Street of happy 2"));
        visitScheduleRepository.addVisitSchedule(new VisitSchedule(3, "Name3", 9100000003L, LocalDate.of(2023, 6, 3), LocalTime.of(10, 3), LocalTime.of(11, 0), false, "emailMal@this.app","Street of happy 3"));
    }
    private void addSMSProperties() {
        try{
            OutputStream output = new FileOutputStream("src/main/resources/sms.properties");
            Properties prop = new Properties();

            prop.setProperty("user","isepSMS");
            prop.setProperty("password","isepSMS");
            prop.setProperty("number", "123456789");
            // save properties to project root folder
            prop.store(output, null);
        }
        catch (IOException ex){
            System.out.println("Error reading file or file not found");
        }
    }

    private void addEmailProperties() {
        try{
            OutputStream output = new FileOutputStream("src/main/resources/email.properties");
            Properties prop = new Properties();

            prop.setProperty("user","isepEmail");
            prop.setProperty("password","isepEmail");
            prop.setProperty("host","isep.ipp.pt");
            prop.setProperty("port","123");
            prop.setProperty("smtp","smtp");
            prop.setProperty("from", "no-reply@this.app");
            // save properties to project root folder
            prop.store(output, null);
        }
        catch (IOException ex){
            System.out.println("Error reading file or file not found");
        }
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



        employeeRepository.add(new Employee("Luis","employee@this.app", 1));
        employeeRepository.add(new Employee("Ricardo","Agent11", 1));
        employeeRepository.add(new Employee("Miguel","Agent111", 1));
        employeeRepository.add(new Employee("Diogo","Agent2", 2));
        employeeRepository.add(new Employee("Maria","Agent22", 2));
        employeeRepository.add(new Employee("Joaquim","Agent222", 2));
    }

    private void addAnnouncements() {
        AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
        PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();
        Commission c1 = new Commission();
        ArrayList<Employee> agents = new ArrayList<>();
        Date d1 = new Date(2022, Calendar.APRIL, 10);
        Address a1 = new Address("Rua 1", "Braga", "Braga", "Minho", 12345);
        ArrayList<Role> roles = new ArrayList<>();
        Employee agent1 = new Employee("Agent1", "employee@this.app", 123456789, 123, 912345421, new Address("street", "city", "district", "state", 1234), roles, 1);
        Employee agent2 = new Employee("Agent2", "employee2@this.app", 123123123, 456, 912341234, new Address("street", "city", "district", "state", 1234), roles, 1);
        Address a2 = new Address("Rua 2", "Porto", "Porto", "Douro", 12345);
        Address a3 = new Address("Rua 3", "Póvoa de Varzim", "Porto", "Porto Norte", 44905);
        propertyRepository.addHouse(150, 30, 250000, a1, 1, 2, 1, true, true, true, true, SunExposure.NORTH);

        propertyRepository.addApartment(150, 30, 250000, a1, 1, 2, 1, true, true);
        propertyRepository.addApartment(150, 30, 100000, a1, 2, 2, 1, true, true);
        propertyRepository.addApartment(150, 30, 400000, a2, 5, 2, 1, true, true);
        propertyRepository.addLand(150, 30, 250000, a1);
        propertyRepository.addHouse(150, 30, 100000, a1, 2, 2, 1, true, true, true, true, SunExposure.NORTH);
        propertyRepository.addHouse(150, 30, 500000, a1, 3, 2, 1, true, true, true, true, SunExposure.NORTH);
        propertyRepository.addHouse(150, 30, 300000, a2, 4, 2, 1, true, true, true, true, SunExposure.NORTH);
        propertyRepository.addHouse(150, 30, 400000, a2, 5, 2, 1, true, true, true, true, SunExposure.NORTH);
        propertyRepository.addLand(150, 30, 100000, a1);
        propertyRepository.addLand(150, 30, 500000, a1);
        propertyRepository.addApartment(150, 30, 500000, a1, 3, 2, 1, true, true);
        propertyRepository.addApartment(150, 30, 300000, a2, 4, 2, 1, true, true);
        propertyRepository.addLand(150, 30, 300000, a2);
        propertyRepository.addLand(150, 30, 400000, a2);
        propertyRepository.addLand(230, 5, 300000, a3);

        ArrayList<Property> properties = propertyRepository.getAllProperties();

        announcementRepository.addAnnouncementWithAgent(agent1,properties.get(0).getId(), TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent1,properties.get(1).getId(), TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent1,properties.get(13).getId(), TypeOfProperty.HOUSE, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent1,properties.get(12).getId(), TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent1,properties.get(4).getId(), TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent1,properties.get(5).getId(), TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent2,properties.get(6).getId(), TypeOfProperty.APARTMENT, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent2,properties.get(7).getId(), TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent2,properties.get(8).getId(), TypeOfProperty.APARTMENT, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent2,properties.get(5).getId(), TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent2,properties.get(10).getId(), TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent2,properties.get(11).getId(), TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent2,properties.get(9).getId(), TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent2,properties.get(2).getId(), TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null, true);
        announcementRepository.addAnnouncementWithAgent(agent2,properties.get(14).getId(), TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, true);

        announcementRepository.addAnnouncementWithAgent(agent1, properties.get(10).getId(), TypeOfProperty.LAND, TransactionType.SALE, d1, c1, null, false);
        announcementRepository.addAnnouncementWithAgent(agent1, properties.get(5).getId(), TypeOfProperty.APARTMENT, TransactionType.SALE, d1, c1, null, false);
        announcementRepository.addAnnouncementWithAgent(agent1, properties.get(1).getId(), TypeOfProperty.HOUSE, TransactionType.RENT, d1, c1, null, false);
        announcementRepository.addAnnouncementWithAgent(agent2, properties.get(13).getId(), TypeOfProperty.LAND, TransactionType.RENT, d1, c1, null, false);

    }


    private void addClients() {
        // Create a new client
        Address clientAddress = new Address("client street", "client city", "client district", "client state", 1234);

        // Add the new client to the client repository
        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
        clientRepository.addClient("Joao Comprador", "client1@this.app", 11111111, 222222222, 1765656578, clientAddress);

    }


    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER, AuthenticationController.ROLE_STORE_MANAGER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_NETWORK_MANAGER, AuthenticationController.ROLE_STORE_NETWORK_MANAGER);

        authenticationRepository.addUserWithRole("MainAdministrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Client", "client@this.app", "pwd",
                AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserWithRole("Client2", "client2@this.app", "pwd",
                AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserWithRole("Joao Comprador", "client1@this.app", "pwd",
                AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserWithRole("Store Manager", "storemanager@this.app", "pwd",
                AuthenticationController.ROLE_STORE_MANAGER);

        authenticationRepository.addUserWithRole("Network Manager", "networkmanager@this.app", "pwd",
                AuthenticationController.ROLE_STORE_NETWORK_MANAGER);


    }
    private void addOrders() {
        OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();
        Order order1 = new Order(505521, 1, "aaaa@as.pt",new Date(2022, Calendar.AUGUST, 10),OrderState.PENDING);
        Order order2 = new Order(401511, 2, "bbaa@as.pt",new Date(2022, Calendar.NOVEMBER, 10),OrderState.PENDING);
        Order order3 = new Order(3012211, 2, "ccaa@as.pt",new Date(2022, Calendar.DECEMBER, 10),OrderState.PENDING);
        Order order4 = new Order(23262, 0, "ddaaa@as.pt",new Date(2022, Calendar.APRIL, 10),OrderState.PENDING);
        Order order5 = new Order(565656, 0, "eeaaa@as.pt",new Date(2022, Calendar.APRIL, 10),OrderState.PENDING);
        Order order6 = new Order(98888, 3, "fffff@as.pt",new Date(2022, Calendar.APRIL, 15),OrderState.PENDING);
        Order order7 = new Order(132, 3, "ggggg@as.pt",new Date(2022, Calendar.APRIL, 11),OrderState.PENDING);
        Order order8 = new Order(6252, 8, "hhhhh@as.pt",new Date(2022, Calendar.APRIL, 1),OrderState.PENDING);
        Order order9 = new Order(9658, 5, "iiiiii@as.pt",new Date(2022, Calendar.APRIL, 9),OrderState.PENDING);
        Order order10 = new Order(23262, 5, "jjjjjj@as.pt",new Date(2022, Calendar.APRIL, 28),OrderState.PENDING);
        Order order11= new Order(59999, 8, "kkkkk@as.pt",new Date(2022, Calendar.APRIL, 20),OrderState.PENDING);
        Order order12= new Order(966222300, 1, "lllll@as.pt",new Date(2022, Calendar.APRIL, 10),OrderState.PENDING);
        Order order13= new Order(2352, 1, "mmmmm@as.pt",new Date(2022, Calendar.MARCH, 10),OrderState.PENDING);
        Order order14= new Order(45566131, 0, "ooooo@as.pt",new Date(2022, Calendar.APRIL, 10),OrderState.PENDING);
        orderRepository.addOrder(order1);
        orderRepository.addOrder(order2);
        orderRepository.addOrder(order3);
        orderRepository.addOrder(order4);
        orderRepository.addOrder(order5);
        orderRepository.addOrder(order6);
        orderRepository.addOrder(order7);
        orderRepository.addOrder(order8);
        orderRepository.addOrder(order9);
        orderRepository.addOrder(order10);
        orderRepository.addOrder(order11);
        orderRepository.addOrder(order12);
        orderRepository.addOrder(order13);
        orderRepository.addOrder(order14);





    }


}

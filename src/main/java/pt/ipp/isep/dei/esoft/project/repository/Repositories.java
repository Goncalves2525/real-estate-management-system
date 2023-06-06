package pt.ipp.isep.dei.esoft.project.repository;

/**
 *  Repositories.
 * <p>
 * This class will be responsible for managing all repositories.
 /**
 *
 */
public class Repositories {

    private static final Repositories instance = new Repositories();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    AnnouncementRepository announcementRepository = new AnnouncementRepository();
    AnnouncementRepository dealRepository = new AnnouncementRepository();
    AgencyRepository agencyRepository = new AgencyRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    RoleRepository roleRepository = new RoleRepository();
    ClientRepository clientRepository = new ClientRepository();
    OrderRepository orderRepository = new OrderRepository();
    PropertyRepository propertyRepository = new PropertyRepository();
    VisitScheduleRepository visitScheduleRepository = new VisitScheduleRepository();

    PropertyRepository propertyDealRepository = new PropertyRepository();

    private Repositories() {
    }

    public static Repositories getInstance() {
        return instance;
    }

    public AnnouncementRepository getAnnouncementRepository() {
        return announcementRepository;
    }

    public AnnouncementRepository getDealRepository() {
        return dealRepository;
    }
    public PropertyRepository getPropertyDealRepository() {
        return propertyDealRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public AgencyRepository getAgencyRepository() {
        return agencyRepository;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }


    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public PropertyRepository getPropertyRepository() {
        return propertyRepository;
    }

    public VisitScheduleRepository getVisitScheduleRepository() {
        return visitScheduleRepository;
    }

}

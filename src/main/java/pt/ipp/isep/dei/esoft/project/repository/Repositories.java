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
    AgencyRepository agencyRepository = new AgencyRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    RoleRepository roleRepository = new RoleRepository();

    private Repositories() {
    }

    public static Repositories getInstance() {
        return instance;
    }

    public AnnouncementRepository getAnnouncementRepository() {
        return announcementRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public AgencyRepository getAgencyListRepository() {
        return agencyRepository;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

}

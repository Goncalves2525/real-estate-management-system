package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static final Repositories instance = new Repositories();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    AnnouncementRepository announcementRepository = new AnnouncementRepository();
    AgencyListRepository agencyListRepository = new AgencyListRepository();
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

    public AgencyListRepository getAgencyListRepository() {
        return agencyListRepository;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

}

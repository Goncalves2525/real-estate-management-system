package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static final Repositories instance = new Repositories();
    TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
    OrganizationRepository organizationRepository = new OrganizationRepository();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    AnnouncementRepository announcementRepository = new AnnouncementRepository();
    AgencyListRepository agencyListRepository = new AgencyListRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    BackRepository backRepository = new BackRepository();
    RoleRepository roleRepository = new RoleRepository();
    

    private Repositories() {
    }

    public static Repositories getInstance() {
        return instance;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
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
    public EmployeeRepository getAgentListRepository() {
        return employeeRepository;
    }

    public BackRepository getEmployeeRepository() {
        return backRepository;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }
}

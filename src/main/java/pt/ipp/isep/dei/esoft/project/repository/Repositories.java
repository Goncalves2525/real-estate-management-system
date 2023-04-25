package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static final Repositories instance = new Repositories();
    TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
    OrganizationRepository organizationRepository = new OrganizationRepository();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    AnnouncementRepository announcementRepository = new AnnouncementRepository();
    AgencyListRepository agencyListRepository = new AgencyListRepository();
    AgentListRepository agentListRepository = new AgentListRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();



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

    public AnnouncementRepository getPublishedPropertyRepository() {
        return announcementRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }
    public AgencyListRepository getAgencyListRepository() {
        return agencyListRepository;
    }
    public AgentListRepository getAgentListRepository() {
        return agentListRepository;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }


}

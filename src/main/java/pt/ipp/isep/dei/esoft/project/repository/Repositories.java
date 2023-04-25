package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static final Repositories instance = new Repositories();
    TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
    OrganizationRepository organizationRepository = new OrganizationRepository();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    PublishedPropertyRepository publishedPropertyRepository = new PublishedPropertyRepository();
    AgencyListRepository agencyListRepository = new AgencyListRepository();
    AgentListRepository agentListRepository = new AgentListRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();



    private Repositories() {
    }

    public static Repositories getInstance() {
        return instance;
    }

//    public OrganizationRepository getOrganizationRepository() {
//        return organizationRepository;
//    }
//
//    public TaskCategoryRepository getTaskCategoryRepository() {
//        return taskCategoryRepository;
//    }

    public PublishedPropertyRepository getPublishedPropertyRepository() {
        return publishedPropertyRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }


}

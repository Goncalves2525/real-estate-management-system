package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static final Repositories instance = new Repositories();
    TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
    OrganizationRepository organizationRepository = new OrganizationRepository();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();

    PublishedPropertyRepository publishedPropertyRepository = new PublishedPropertyRepository();

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

    public PublishedPropertyRepository getPropertyRepository() {
        return publishedPropertyRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }


}

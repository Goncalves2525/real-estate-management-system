package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.Optional;

public class ListPropertiesController {

//    private OrganizationRepository organizationRepository = null;
//    private TaskCategoryRepository taskCategoryRepository = null;
    private PublishedPropertyRepository publishedPropertyRepository = null;
    private AuthenticationRepository authenticationRepository = null;


    //Repository instances are obtained from the Repositories class
    public ListPropertiesController() {
//        getOrganizationRepository();
//        getTaskCategoryRepository();
        getPropertyRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public ListPropertiesController(OrganizationRepository organizationRepository,
                                    TaskCategoryRepository taskCategoryRepository,
                                    AuthenticationRepository authenticationRepository) {
//        this.organizationRepository = organizationRepository;
//        this.taskCategoryRepository = taskCategoryRepository;
        this.publishedPropertyRepository = publishedPropertyRepository;
        this.authenticationRepository = this.authenticationRepository;
    }

//    private TaskCategoryRepository getTaskCategoryRepository() {
//        if (taskCategoryRepository == null) {
//            Repositories repositories = Repositories.getInstance();
//
//            //Get the TaskCategoryRepository
//            taskCategoryRepository = repositories.getTaskCategoryRepository();
//        }
//        return taskCategoryRepository;
//    }
//
//
//    private OrganizationRepository getOrganizationRepository() {
//        if (organizationRepository == null) {
//            Repositories repositories = Repositories.getInstance();
//            organizationRepository = repositories.getOrganizationRepository();
//        }
//        return organizationRepository;
//
//    }
    private PublishedPropertyRepository getPropertyRepository() {
        if (publishedPropertyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            publishedPropertyRepository = repositories.getPropertyRepository();
        }
        return publishedPropertyRepository;

    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }


//    public Optional<Task> createTask(String reference, String description, String informalDescription,
//                                     String technicalDescription, Integer duration, Double cost,
//                                     String taskCategoryDescription) {
//
//        TaskCategory taskCategory = getTaskCategoryByDescription(taskCategoryDescription);
//
//        Employee employee = getEmployeeFromSession();
//        Optional<Organization> organization = getOrganizationRepository().getOrganizationByEmployee(employee);
//
//        Optional<Task> newTask = Optional.empty();
//
//        if (organization.isPresent()) {
//            newTask = organization.get()
//                    .createTask(reference, description, informalDescription, technicalDescription, duration, cost,
//                            taskCategory, employee);
//        }
//        return newTask;
//    }

//    private Employee getEmployeeFromSession() {
//        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
//        return new Employee(email.getEmail());
//    }
//
//    private TaskCategory getTaskCategoryByDescription(String taskCategoryDescription) {
//        TaskCategoryRepository taskCategoryRepository = getTaskCategoryRepository();
//
//        //Get the TaskCategory by its description
//        TaskCategory taskCategoryByDescription =
//                getTaskCategoryRepository().getTaskCategoryByDescription(taskCategoryDescription);
//        return taskCategoryByDescription;
//
//    }
//
//
//    //return the list of task categories
//    public List<TaskCategory> getTaskCategories() {
//        TaskCategoryRepository taskCategoryRepository = getTaskCategoryRepository();
//        return taskCategoryRepository.getTaskCategories();
//    }

    //returns the list of properties
    public Optional<Property> getProperties() {
        PublishedPropertyRepository publishedPropertyRepository = getPropertyRepository();
        return publishedPropertyRepository.getProperties("");
    }
}

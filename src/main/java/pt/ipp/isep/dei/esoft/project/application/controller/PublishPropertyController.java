package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.PublishedProperty;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Optional;

public class PublishPropertyController {


    private PublishedPropertyRepository publishedPropertyRepository = null;

    private AuthenticationRepository authenticationRepository = null;


    //Repository instances are obtained from the Repositories class
    public PublishPropertyController() {
        //getOrganizationRepository();
        //getTaskCategoryRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public PublishPropertyController(AuthenticationRepository authenticationRepository,
                                     PublishedPropertyRepository publishedPropertyRepository) {
        this.publishedPropertyRepository = publishedPropertyRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private PublishedPropertyRepository getPublishedPropertyRepository(){
        if(publishedPropertyRepository == null){
            Repositories repositories = Repositories.getInstance();
            publishedPropertyRepository = repositories.getPublishedPropertyRepository();
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

    public Optional<PublishedProperty> createPublishedProperty(String reference, String description, String informalDescription,
                                     String technicalDescription, Integer duration, Double cost,
                                     String taskCategoryDescription) {

        Optional<PublishedProperty> newPublishedProperty = Optional.empty();
        return newPublishedProperty;
    }

    private Employee getEmployeeFromSession() {Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();return new Employee(email.getEmail());}

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
}

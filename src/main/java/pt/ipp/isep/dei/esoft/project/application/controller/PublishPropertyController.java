package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Optional;

public class PublishPropertyController {


    private AnnouncementRepository announcementRepository = null;

    private AuthenticationRepository authenticationRepository = null;


    //Repository instances are obtained from the Repositories class
    public PublishPropertyController() {
        //getOrganizationRepository();
        //getTaskCategoryRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public PublishPropertyController(AuthenticationRepository authenticationRepository,
                                     AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private AnnouncementRepository getPublishedPropertyRepository(){
        if(announcementRepository == null){
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
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

    public Optional<Announcement> createPublishedProperty(String reference, String description, String informalDescription,
                                                          String technicalDescription, Integer duration, Double cost,
                                                          String taskCategoryDescription) {

        Optional<Announcement> newPublishedProperty = Optional.empty();
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

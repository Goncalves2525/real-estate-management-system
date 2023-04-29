package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Comission;
import pt.ipp.isep.dei.esoft.project.domain.Request;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

public class PublishAnnouncementController {
    private AnnouncementRepository announcementRepository = null;
    private AuthenticationRepository authenticationRepository = null;


    /**
     * Instantiates a new Publish announcement controller.
     */
    public PublishAnnouncementController() {
        this.announcementRepository = Repositories.getInstance().getAnnouncementRepository();;
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    private AnnouncementRepository getAnnouncementRepository(){
        if(announcementRepository == null){
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }

    /**
     * @return List<Announcement> the list of announcements
     */
    public List<Announcement> getAnnouncementsByUser(){
        String userEmail = authenticationRepository.getCurrentUserSession().getUserId().getEmail();
        return announcementRepository.getAnnouncementsByAgent(userEmail);
    }

    public void publishAnnouncement(int id) {
        Announcement announcement = announcementRepository.getAnnouncementById(id);
        if (announcement.getId() >= 0) {
            announcement.setIsPublished();
        }
    }

    public void setCommission(int id, Comission commission) {
        Announcement announcement = announcementRepository.getAnnouncementById(id);
        if (announcement.getId() >= 0) {
            announcement.setComission(commission);
        }
    }

    public Announcement getAnnouncementById(int id){
        try {
            return announcementRepository.getAnnouncementById(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

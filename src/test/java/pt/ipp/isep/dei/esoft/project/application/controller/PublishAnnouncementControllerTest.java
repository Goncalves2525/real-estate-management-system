package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

class PublishAnnouncementControllerTest {

    private AnnouncementRepository announcementRepository;
    private AuthenticationRepository authenticationRepository;
    private PublishAnnouncementController controller;

    @BeforeEach
    void setUp() {
        announcementRepository = mock(AnnouncementRepository.class);
        authenticationRepository = mock(AuthenticationRepository.class);
        controller = new PublishAnnouncementController();
    }

    @Test
    void getAnnouncementById_InvalidId_ReturnNull() {
        // Arrange
        int id = -1;
        if(announcementRepository.getAnnouncementById(id) == null){
            throw new IllegalArgumentException();
        }
        // Actual announcement
        Announcement actualAnnouncement = controller.getAnnouncementById(id);

        // Assert
        assertNull(actualAnnouncement);
    }



}
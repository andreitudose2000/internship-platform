package service;

import model.Employer;
import model.Webinar;
import repository.actions.WebinarRepository;
import repository.actionsimpl.WebinarRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

public class WebinarService {

    private final WebinarRepository webinarRepository = new WebinarRepositoryImpl();
    private final LoggingService<Webinar> webinarLoggingService = new LoggingService<Webinar>(Webinar.class);

    public void addWebinar(Employer employer, Webinar webinar) {
        webinarLoggingService.logAction("createWebinar", LocalDateTime.now());
        webinarRepository.addWebinarForEmployer(employer, webinar);
    }

    public List<Webinar> retrieveWebinarsForEmployer(int employerId) {
        webinarLoggingService.logAction("readWebinar", LocalDateTime.now());
        return webinarRepository.retrieveWebinarsForEmployer(employerId);
    }

    public Webinar retrieveWebinarById(int webinarId) {
        webinarLoggingService.logAction("readWebinar", LocalDateTime.now());
        return webinarRepository.retrieveWebinarById(webinarId);
    }

    public List<Webinar> retrieveAllAvailableWebinars() {
        webinarLoggingService.logAction("readWebinar", LocalDateTime.now());
        return webinarRepository.retrieveAllFutureWebinars();
    }

    public void setWebinarFinished(int webinarId) {
        Webinar webinar = retrieveWebinarById(webinarId);
        webinar.setFinished(true);
        modifyWebinarById(webinarId, webinar);
    }

    public void modifyWebinarById(int id, Webinar webinar) {
        webinarLoggingService.logAction("updateWebinar", LocalDateTime.now());
        webinarRepository.modifyWebinarById(id, webinar);
    }

    public void removeWebinarById(int id) {
        webinarLoggingService.logAction("deleteWebinar", LocalDateTime.now());
        webinarRepository.removeWebinarById(id);
    }

    // Singleton

    private static WebinarService INSTANCE = null;

    private WebinarService() {}

    public static WebinarService getINSTANCE() {

        if (INSTANCE == null) {
            INSTANCE = new WebinarService();
        }
        return INSTANCE;
    }
}

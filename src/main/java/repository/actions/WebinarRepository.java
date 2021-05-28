package repository.actions;

import model.Employer;

import java.util.List;
import model.Webinar;

public interface WebinarRepository {

    int addWebinarForEmployer(Employer employer, Webinar webinar);
    List<Webinar> retrieveWebinarsForEmployer(int employerId);
    Webinar retrieveWebinarById(int webinarId);
    List<Webinar> retrieveAllFutureWebinars();
    void modifyWebinarById(int webinarId, Webinar webinar);
    void removeWebinarById(int webinarId);
}

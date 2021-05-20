package repository.actions;

import model.Employer;
import model.Job;

import java.util.List;

public interface JobRepository {

    int addJobForEmployer(Employer employer, Job job);
    List<Job> retrieveJobsForEmployer(int employerId);
    void modifyJobById(int jobId, Job job);
    void removeJobById(int jobId);
}

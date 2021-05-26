package repository.actions;

import model.Employer;
import model.Job;

import java.util.List;

public interface JobRepository {

    int addJobForEmployer(Employer employer, Job job);
    List<Job> retrieveJobsForEmployer(int employerId);
    Job retrieveJobById(int jobId);
    List<Job> retrieveAllActiveJobs();
    void modifyJobById(int jobId, Job job);
    void removeJobById(int jobId);
}

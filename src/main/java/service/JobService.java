package service;

import model.Employer;
import model.Job;
import repository.actions.JobRepository;
import repository.actionsimpl.JobRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

public class JobService {

    private final JobRepository jobRepository = new JobRepositoryImpl();
    private final LoggingService<Job> jobLoggingService = new LoggingService<Job>(Job.class);

    public void addJob(Employer employer, Job job) {
        jobLoggingService.logAction("createJob", LocalDateTime.now());
        jobRepository.addJobForEmployer(employer, job);
    }

    public List<Job> retrieveJobsForEmployer(int employerId) {
        jobLoggingService.logAction("readJob", LocalDateTime.now());
        return jobRepository.retrieveJobsForEmployer(employerId);
    }

    public Job retrieveJobById(int jobId) {
        jobLoggingService.logAction("readJob", LocalDateTime.now());
        return jobRepository.retrieveJobById(jobId);
    }

    public List<Job> retrieveAllActiveJobs() {
        jobLoggingService.logAction("readJob", LocalDateTime.now());
        return jobRepository.retrieveAllActiveJobs();
    }

    public void setJobInactive(int jobId) {
        Job job = retrieveJobById(jobId);
        job.setJobActive(false);
        modifyJobById(jobId, job);
    }

    public void modifyJobById(int id, Job job) {
        jobLoggingService.logAction("updateJob", LocalDateTime.now());
        jobRepository.modifyJobById(id, job);
    }

    public void removeJobById(int id) {
        jobLoggingService.logAction("deleteJob", LocalDateTime.now());
        jobRepository.removeJobById(id);
    }

    // Singleton

    private static JobService INSTANCE = null;

    private JobService() {}

    public static JobService getINSTANCE() {

        if (INSTANCE == null) {
            INSTANCE = new JobService();
        }
        return INSTANCE;
    }
}

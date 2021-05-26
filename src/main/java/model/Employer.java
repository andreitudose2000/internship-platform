package model;

import utils.Loggable;

import java.util.*;

public class Employer
implements Loggable {

    private int id;
    private String employerName;
    private List<Job> employerJobs = new ArrayList<>();
    private Set<Webinar> employerWebinars = new TreeSet<>();

    public Employer(int id, String employerName) {
        this.id = id;
        this.employerName = employerName;
    }

    public Employer(String employerName) {
        this.employerName = employerName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employer ").append(employerName).append("\nInternships:");
        for(Job job : employerJobs) {
            sb.append(job).append(" - ");
            sb.append(job.isJobActive() ? "active" : "inactive").append('\n');
        }

        return sb.toString();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public List<Job> getEmployerJobs() {
        return employerJobs;
    }

    public void setEmployerJobs(List<Job> employerJobs) {
        this.employerJobs = employerJobs;
    }

    public Set<Webinar> getEmployerWebinars() {
        return employerWebinars;
    }

    public void setEmployerWebinars(Set<Webinar> employerWebinars) {
        this.employerWebinars = employerWebinars;
    }
}

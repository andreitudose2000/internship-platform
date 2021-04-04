import java.util.*;

public class Employer {
    private String employerName;
    private List<Job> employerJobs = new ArrayList<>();
    private Set<Webinar> employerWebinars = new TreeSet<>();

    public void addJob(Job job) throws CloneNotSupportedException {
        employerJobs.add((Job)job.clone());
    }

    public void removeJob(Job job) {
        job.notifyProfiles();
        employerJobs.remove(job);
    }

    public void addWebinar(Webinar webinar) throws CloneNotSupportedException {
        employerWebinars.add((Webinar)webinar.clone());
    }

    public void removeWebinar(Webinar webinar) {
        webinar.notifyProfiles();
        employerWebinars.remove(webinar);
    }

    public Employer(String employerName) {
        this.employerName = employerName;
    }

    public Employer(String employerName, ArrayList<Job> employerJobs) {
        this.employerName = employerName;
        Collections.copy(this.employerJobs, employerJobs);
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
}

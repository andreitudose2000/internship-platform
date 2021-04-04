import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employer {
    private String employerName;
    private List<Job> employerJobs = new ArrayList<>();

    public void addJob(Job job) {
        employerJobs.add(job);
    }

    public void removeJob(Job job) {
        job.notifyProfiles();
        employerJobs.remove(job);
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
}

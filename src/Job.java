import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Job implements Observable, Cloneable {
    private boolean jobActive;
    private String jobTitle;
    private int periodInMonths;
    private int salary;
    private List<String> jobRequirements = new ArrayList<>();
    private List<String> jobAttributes = new ArrayList<>();

    public Job(String jobTitle, int periodInMonths, int salary){
        jobActive = true;
        this.jobTitle = jobTitle;
        this.periodInMonths = periodInMonths;
        this.salary = salary;
    }

    public Job(String jobTitle, int periodInMonths, int salary,
               ArrayList<String> jobRequirements, ArrayList<String> jobAttributes){
        this(jobTitle, periodInMonths, salary);
        Collections.copy(this.jobRequirements, jobRequirements);
        Collections.copy(this.jobAttributes, jobAttributes);
    }

    private List<Observer> observerList = new ArrayList<>();
    public void subscribe(Observer profile) {
        observerList.add(profile);
    }
    public void unsubscribe(Observer profile) {
        observerList.remove(profile);
    }
    public void notifyProfiles() {
        for(Observer o : observerList){
            o.updateObserver("Job update: \"" + this.jobTitle + "\"");
        }
    }

    public boolean isJobActive() {
        return jobActive;
    }

    public void setJobActive(boolean jobActive) {
        this.jobActive = jobActive;
        this.notifyProfiles();
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        this.notifyProfiles();
    }

    public int getPeriodInMonths() {
        return periodInMonths;
    }

    public void setPeriodInMonths(int periodInMonths) {
        this.periodInMonths = periodInMonths;
        this.notifyProfiles();
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
        this.notifyProfiles();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Job) super.clone();
    }
}

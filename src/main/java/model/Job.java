package model;

import utils.Loggable;

public class Job
implements Loggable {

    private int id;
    private boolean jobActive;
    private String jobTitle;
    private int periodInMonths;
    private int salary;
    private int employerId;

    public Job(int id, String jobTitle, int periodInMonths, int salary, boolean jobActive){
        this.id = id;
        this.jobTitle = jobTitle;
        this.periodInMonths = periodInMonths;
        this.salary = salary;
        this.jobActive = jobActive;
    }

    public Job(String jobTitle, int periodInMonths, int salary, boolean jobActive){
        this.jobTitle = jobTitle;
        this.periodInMonths = periodInMonths;
        this.salary = salary;
        this.jobActive = jobActive;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Job) super.clone();
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobActive=" + jobActive +
                ", jobTitle='" + jobTitle + '\'' +
                ", periodInMonths=" + periodInMonths +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isJobActive() {
        return jobActive;
    }

    public void setJobActive(boolean jobActive) {
        this.jobActive = jobActive;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getPeriodInMonths() {
        return periodInMonths;
    }

    public void setPeriodInMonths(int periodInMonths) {
        this.periodInMonths = periodInMonths;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }
}

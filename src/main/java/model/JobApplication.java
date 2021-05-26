package model;

public class JobApplication {

    private int id;
    private Student student;
    private Job job;

    public JobApplication(int id, Student student, Job job) {
        this.id = id;
        this.student = student;
        this.job = job;
    }

    public JobApplication(Student student, Job job) {
        this.student = student;
        this.job = job;
    }

    public JobApplication(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "id=" + id +
                ", student=" + student +
                ", job=" + job +
                '}';
    }
}

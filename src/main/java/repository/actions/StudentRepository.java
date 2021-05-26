package repository.actions;

import model.Job;
import model.JobApplication;
import model.Student;

import java.util.List;

public interface StudentRepository {

    /**
     * @param student student to add
     */
    int addStudent(Student student);

    /**
     * @param studentId studentId
     * @return list of Students
     */
    Student retrieveStudentById(int studentId);

    Student retrieveStudentByEmail(String email);

    /**
     * @param studentId studentId
     * @param student student
     */
    void modifyStudentById(int studentId, Student student);

    /**
     * @param studentId studentId
     */
    void removeStudentById(int studentId);

    int applyForJob(Student student, Job job);

    List<JobApplication> retrieveJobApplicationsForStudent(Student student);

    void removeJobApplication(Student student, Job job);
}

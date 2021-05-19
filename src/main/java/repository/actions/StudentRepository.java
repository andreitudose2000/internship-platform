package repository.actions;

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

    /**
     * @param studentId studentId
     * @param student student
     */
    void modifyStudentById(int studentId, Student student);

    /**
     * @param studentId studentId
     */
    void removeStudentById(int studentId);

}

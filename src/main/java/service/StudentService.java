package service;

import model.Student;
import repository.actions.StudentRepository;
import repository.actionsimpl.StudentRepositoryImpl;

import java.time.LocalDateTime;

public class StudentService {

    private StudentRepository studentRepository = new StudentRepositoryImpl();

    private LoggingService<Student> studentLoggingService = new LoggingService<>(Student.class);

    public void addStudent(Student student) {
        studentLoggingService.logAction("createStudent", LocalDateTime.now());
        studentRepository.addStudent(student);
    }

    public Student retrieveStudentById(int id) {
        studentLoggingService.logAction("readStudent", LocalDateTime.now());
        return studentRepository.retrieveStudentById(id);
    }

    public void modifyStudentById(int id, Student student) {
        studentLoggingService.logAction("updateStudent", LocalDateTime.now());
        studentRepository.modifyStudentById(id, student);
    }

    public void removeStudentById(int id) {
        studentLoggingService.logAction("deleteStudent", LocalDateTime.now());
        studentRepository.removeStudentById(id);
    }

    // Singleton

    private static StudentService INSTANCE = null;

    private StudentService() {}

    public static StudentService getINSTANCE() {

        if (INSTANCE == null) {
            INSTANCE = new StudentService();
        }

        return INSTANCE;
    }
}

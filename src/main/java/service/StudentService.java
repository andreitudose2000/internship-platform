package service;

import exception.IncorrectEmailException;
import exception.StudentNotFoundException;
import model.Job;
import model.JobApplication;
import model.Student;
import repository.actions.StudentRepository;
import repository.actionsimpl.StudentRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

public class StudentService {

    private StudentRepository studentRepository = new StudentRepositoryImpl();

    private LoggingService<Student> studentLoggingService = new LoggingService<>(Student.class);

    public void addStudent(Student student)
            throws IncorrectEmailException {
        if (!verifyEmail(student.getEmail())) {
            throw new IncorrectEmailException(student.getEmail());
        }
        studentLoggingService.logAction("createStudent", LocalDateTime.now());
        studentRepository.addStudent(student);
    }

    public Student retrieveStudentById(int id) {
        studentLoggingService.logAction("readStudent", LocalDateTime.now());
        return studentRepository.retrieveStudentById(id);
    }

    public Student retrieveStudentByEmail(String email) throws StudentNotFoundException {
        studentLoggingService.logAction("readStudent", LocalDateTime.now());
        Student student = studentRepository.retrieveStudentByEmail(email);
        if (student == null) {
            throw new StudentNotFoundException("Emailul " + email + " nu exista in baza de date");
        }
        return student;
    }

    public void modifyStudentById(int id, Student student) {
        studentLoggingService.logAction("updateStudent", LocalDateTime.now());
        studentRepository.modifyStudentById(id, student);
    }

    public void removeStudentById(int id) {
        studentLoggingService.logAction("deleteStudent", LocalDateTime.now());
        studentRepository.removeStudentById(id);
    }

    public void applyToJob(Student student, Job job) {
        studentLoggingService.logAction("studentAppliedForJob", LocalDateTime.now());
        studentRepository.applyForJob(student, job);
    }

    public List<JobApplication> retrieveJobApplicationsForStudent(Student student) {
        studentLoggingService.logAction("readJobApplication", LocalDateTime.now());
        return studentRepository.retrieveJobApplicationsForStudent(student);
    }

    public void removeJobApplication(Student student, Job job) {
        studentLoggingService.logAction("removedStudentJobApplication", LocalDateTime.now());
        studentRepository.removeJobApplication(student, job);
    }

    public boolean verifyEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(emailRegex, email);
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

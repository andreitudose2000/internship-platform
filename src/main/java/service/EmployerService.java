package service;

import model.Employer;
import model.Student;
import repository.actions.EmployerRepository;
import repository.actions.StudentRepository;
import repository.actionsimpl.EmployerRepositoryImpl;
import repository.actionsimpl.StudentRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

public class EmployerService {

    private EmployerRepository employerRepository = new EmployerRepositoryImpl();
    private LoggingService<Employer> employerLoggingService = new LoggingService<Employer>(Employer.class);

    public void addEmployer(Employer employer) {
        employerLoggingService.logAction("createEmployer", LocalDateTime.now());
        employerRepository.addEmployer(employer);
    }

    public Employer retrieveEmployerById(int id) {
        employerLoggingService.logAction("readEmployer", LocalDateTime.now());
        return employerRepository.retrieveEmployerById(id);
    }

    public Employer retrieveEmployerByName(String name) {
        employerLoggingService.logAction("readEmployer", LocalDateTime.now());
        return employerRepository.retrieveEmployerByName(name);
    }

    public List<Employer> retrieveAllEmployers() {
        employerLoggingService.logAction("readEmployer", LocalDateTime.now());
        return employerRepository.retrieveAllEmployers();
    }

    public void modifyEmployerById(int id, Employer employer) {
        employerLoggingService.logAction("updateEmployer", LocalDateTime.now());
        employerRepository.modifyEmployerById(id, employer);
    }

    public void removeEmployerById(int id) {
        employerLoggingService.logAction("deleteEmployer", LocalDateTime.now());
        employerRepository.removeEmployerById(id);
    }

    // Singleton

    private static EmployerService INSTANCE = null;

    private EmployerService() {}

    public static EmployerService getINSTANCE() {

        if (INSTANCE == null) {
            INSTANCE = new EmployerService();
        }

        return INSTANCE;
    }
}

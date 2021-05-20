package service;

import com.mysql.cj.log.Log;
import model.Student;
import model.fields.ProjectField;
import repository.actions.ProjectFieldRepository;
import repository.actionsimpl.ProjectFieldRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectFieldService {

    private ProjectFieldRepository projectFieldRepository = new ProjectFieldRepositoryImpl();
    private LoggingService<ProjectField> projectFieldLoggingService
            = new LoggingService<>(ProjectField.class);

    public void addProjectFieldForStudent(Student student, ProjectField projectField) {
        projectFieldLoggingService.logAction("createProjectField", LocalDateTime.now());
        projectFieldRepository.addProjectFieldForStudent(student, projectField);
    }

    public List<ProjectField> retrieveProjectFieldsForStudent(int id) {
        projectFieldLoggingService.logAction("readProjectField", LocalDateTime.now());
        return projectFieldRepository.retrieveProjectFieldsForStudent(id);
    }

    public void modifyProjectFieldById(int id, ProjectField projectField) {
        projectFieldLoggingService.logAction("updateProjectField", LocalDateTime.now());
        projectFieldRepository.modifyProjectFieldForStudent(id, projectField);
    }

    public void removeProjectFieldById(int id) {
        projectFieldLoggingService.logAction("deleteProjectField", LocalDateTime.now());
        projectFieldRepository.removeProjectField(id);
    }

    // Singleton

    private static ProjectFieldService INSTANCE = null;

    private ProjectFieldService() {}

    public static ProjectFieldService getINSTANCE() {

        if (INSTANCE == null) {
            INSTANCE = new ProjectFieldService();
        }

        return INSTANCE;
    }
}

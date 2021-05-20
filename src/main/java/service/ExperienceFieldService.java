package service;

import com.mysql.cj.log.Log;
import model.Student;
import model.fields.ExperienceField;
import repository.actions.ExperienceFieldRepository;
import repository.actionsimpl.ExperienceFieldRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

public class ExperienceFieldService {

    private ExperienceFieldRepository experienceFieldRepository = new ExperienceFieldRepositoryImpl();
    private LoggingService<ExperienceField> experienceFieldLoggingService
            = new LoggingService<>(ExperienceField.class);

    public void addExperienceFieldForStudent(Student student, ExperienceField experienceField) {
        experienceFieldLoggingService.logAction("createExperienceField", LocalDateTime.now());
        experienceFieldRepository.addExperienceFieldForStudent(student, experienceField);
    }

    public List<ExperienceField> retrieveExperienceFieldsForStudent(int id) {
        experienceFieldLoggingService.logAction("readExperienceField", LocalDateTime.now());
        return experienceFieldRepository.retrieveExperienceFieldsForStudent(id);
    }

    public void modifyExperienceFieldById(int id, ExperienceField experienceField) {
        experienceFieldLoggingService.logAction("updateExperienceField", LocalDateTime.now());
        experienceFieldRepository.modifyExperienceFieldForStudent(id, experienceField);
    }

    public void removeExperieceFieldById(int id) {
        experienceFieldLoggingService.logAction("deleteExperienceField", LocalDateTime.now());
        experienceFieldRepository.removeExperienceField(id);
    }

    // Singleton

    private static ExperienceFieldService INSTANCE = null;

    private ExperienceFieldService() {}

    public static ExperienceFieldService getINSTANCE() {

        if (INSTANCE == null) {
            INSTANCE = new ExperienceFieldService();
        }

        return INSTANCE;
    }
}

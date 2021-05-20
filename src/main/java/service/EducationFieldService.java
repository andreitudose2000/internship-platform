package service;

import model.Student;
import model.fields.EducationField;
import repository.actions.EducationFieldRepository;
import repository.actions.StudentRepository;
import repository.actionsimpl.EducationFieldRepositoryImpl;
import repository.actionsimpl.StudentRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

public class EducationFieldService {

    private final EducationFieldRepository educationFieldRepository = new EducationFieldRepositoryImpl();
    private final LoggingService<EducationField> educationFieldLoggingService
            = new LoggingService<>(EducationField.class);

    public void addEducationFieldForStudent(Student student, EducationField educationField) {
        educationFieldLoggingService.logAction("createEducationField", LocalDateTime.now());
        educationFieldRepository.addEducationFieldForStudent(student, educationField);
    }

    public List<EducationField> retrieveEducationFieldsForStudent(int id) {
        educationFieldLoggingService.logAction("readEducationField", LocalDateTime.now());
        return educationFieldRepository.retrieveEducationFieldsForStudent(id);
    }

    public void modifyEducationFieldById(int id, EducationField educationField) {
        educationFieldLoggingService.logAction("updateEducationField", LocalDateTime.now());
        educationFieldRepository.modifyEducationFieldForStudent(id, educationField);
    }

    public void removeEducationFieldById(int id) {
        educationFieldLoggingService.logAction("deleteEducationField", LocalDateTime.now());
        educationFieldRepository.removeEducationField(id);
    }

    // Singleton

    private static EducationFieldService INSTANCE = null;

    private EducationFieldService() {}

    public static EducationFieldService getINSTANCE() {

        if (INSTANCE == null) {
            INSTANCE = new EducationFieldService();
        }

        return INSTANCE;
    }
}

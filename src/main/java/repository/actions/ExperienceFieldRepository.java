package repository.actions;

import model.Student;
import model.fields.ExperienceField;

import java.util.List;

public interface ExperienceFieldRepository {

    /**
     * @param student student
     * @param experienceField experienceField
     * @return id for newly added
     */
    int addExperienceFieldForStudent(Student student, ExperienceField experienceField);

    /**
     * @param studentId studentId
     * @return list of ExperienceFields for Student
     */
    List<ExperienceField> retrieveExperienceFieldsForStudent(int studentId);


    /**
     * @param experienceFieldId experienceFieldId
     * @param experienceField experienceField
     */
    void modifyExperienceFieldForStudent(int experienceFieldId, ExperienceField experienceField);

    /**
     * @param experienceFieldId experienceFieldId
     */
    void removeExperienceField(int experienceFieldId);
}

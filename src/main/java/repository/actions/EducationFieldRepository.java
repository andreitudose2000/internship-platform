package repository.actions;

import model.Student;
import model.fields.EducationField;
import model.fields.ExperienceField;

import java.util.List;

public interface EducationFieldRepository {

    /**
     * @param student student
     * @param educationField educationField
     * @return id for newly added
     */
    int addEducationFieldForStudent(Student student, EducationField educationField);

    /**
     * @param studentId studentId
     * @return list of EducationFields for Student
     */
    List<EducationField> retrieveEducationFieldsForStudent(int studentId);

    /**
     * @param educationFieldId educationFieldId
     * @param educationField educationField
     */
    void modifyEducationFieldForStudent(int educationFieldId, EducationField educationField);

    /**
     * @param educationFieldId educationFieldId
     */
    void removeEducationField(int educationFieldId);
}

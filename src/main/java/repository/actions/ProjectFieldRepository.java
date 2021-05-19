package repository.actions;

import model.Student;
import model.fields.EducationField;
import model.fields.ProjectField;

import java.util.List;

public interface ProjectFieldRepository {

    /**
     * @param student student
     * @param projectField projectField
     * @return id for newly added
     */
    int addProjectFieldForStudent(Student student, ProjectField projectField);

    /**
     * @param studentId studentId
     * @return list of ProjectFields for Student
     */
    List<ProjectField> retrieveProjectFieldsForStudent(int studentId);

    /**
     * @param projectFieldId projectFieldId
     * @param projectField projectField
     */
    void modifyProjectFieldForStudent(int projectFieldId, ProjectField projectField);

    /**
     * @param projectFieldId projectFieldId
     */
    void removeProjectField(int projectFieldId);
}

package repository.actionsimpl;

import model.Student;
import model.fields.EducationField;
import model.fields.ProjectField;
import repository.actions.EducationFieldRepository;
import repository.actions.ProjectFieldRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class ProjectFieldRepositoryImpl implements ProjectFieldRepository {

    DbConnection dbConnection = DbConnection.getInstance();

    TimelineFieldRepositoryImpl timelineFieldRepository = new TimelineFieldRepositoryImpl();

    @Override
    public List<ProjectField> retrieveProjectFieldsForStudent(int studentId) {

        List<ProjectField> projectFieldList = new ArrayList<>();

        try{
            PreparedStatement preparedStatement
                    = dbConnection.getDBConnection().prepareStatement(RETRIEVE_PROJECT_FIELDS_FOR_STUDENT);
            preparedStatement.setInt(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProjectField projectField
                        = new ProjectField(resultSet.getInt(1), // id
                        resultSet.getDate(2), // startDate
                        resultSet.getDate(3), // finishDate
                        resultSet.getString(4), // description
                        resultSet.getString(5)); // name
                projectFieldList.add(projectField);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return projectFieldList;
    }

    @Override
    public int addProjectFieldForStudent(Student student, ProjectField projectField) {

        try{

            int timelineFieldId = timelineFieldRepository.addTimelineFieldForStudent(student, projectField);

            PreparedStatement preparedStatement  = dbConnection.getDBConnection()
                    .prepareStatement(INSERT_PROJECT_FIELD_FOR_STUDENT);
            preparedStatement.setInt(1, timelineFieldId);
            preparedStatement.setString(2, projectField.getProjectName());

            preparedStatement.executeUpdate();

            return timelineFieldId;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void modifyProjectFieldForStudent(int projectFieldId, ProjectField projectField) {
        try (PreparedStatement preparedStatement = dbConnection.getDBConnection()
                .prepareStatement(UPDATE_PROJECT_FIELD_BY_ID)) {

            timelineFieldRepository.modifyTimelineField(projectFieldId, projectField);

            preparedStatement.setString(1, projectField.getProjectName());
            preparedStatement.setInt(2, projectFieldId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProjectField (int projectFieldId) {

        try {

            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(DELETE_PROJECT_FIELD_BY_ID);
            preparedStatement.setInt(1, projectFieldId);

            preparedStatement.executeUpdate();

            preparedStatement.close();

            timelineFieldRepository.removeTimelineField(projectFieldId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

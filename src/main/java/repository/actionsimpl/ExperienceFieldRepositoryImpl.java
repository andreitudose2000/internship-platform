package repository.actionsimpl;

import model.Student;
import model.fields.ExperienceField;
import repository.actions.ExperienceFieldRepository;
import utils.DbConnection;
import static utils.Queries.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperienceFieldRepositoryImpl implements ExperienceFieldRepository {

    DbConnection dbConnection = DbConnection.getInstance();

    TimelineFieldRepositoryImpl timelineFieldRepository = new TimelineFieldRepositoryImpl();

    @Override
    public int addExperienceFieldForStudent(Student student, ExperienceField experienceField) {

        try{

            int timelineFieldId = timelineFieldRepository.addTimelineFieldForStudent(student, experienceField);

            PreparedStatement preparedStatement  = dbConnection.getDBConnection()
                    .prepareStatement(INSERT_EXPERIENCE_FIELD_FOR_STUDENT);
            preparedStatement.setInt(1, timelineFieldId);
            preparedStatement.setString(2, experienceField.getPositionName());
            preparedStatement.setString(3, experienceField.getInstitutionName());

            preparedStatement.executeUpdate();

            return timelineFieldId;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public List<ExperienceField> retrieveExperienceFieldsForStudent(int studentId) {

        List<ExperienceField> experienceFieldList = new ArrayList<>();

        try{
            PreparedStatement preparedStatement
                    = dbConnection.getDBConnection().prepareStatement(RETRIEVE_EXPERIENCE_FIELDS_FOR_STUDENT);
            preparedStatement.setInt(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ExperienceField experienceField
                        = new ExperienceField(resultSet.getInt(1), // id
                        resultSet.getDate(2), // startDate
                        resultSet.getDate(3), // finishDate
                        resultSet.getString(4), // description
                        resultSet.getString(5), // positionName
                        resultSet.getString(6)); // institutionName
                experienceFieldList.add(experienceField);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return experienceFieldList;
    }


    @Override
    public void modifyExperienceFieldForStudent(int experienceFieldId, ExperienceField experienceField) {

        try (PreparedStatement preparedStatement = dbConnection.getDBConnection()
                .prepareStatement(UPDATE_EXPERIENCE_FIELD_BY_ID)) {

            timelineFieldRepository.modifyTimelineField(experienceFieldId, experienceField);

            preparedStatement.setString(1, experienceField.getPositionName());
            preparedStatement.setString(2, experienceField.getInstitutionName());
            preparedStatement.setInt(3, experienceFieldId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeExperienceField (int experienceFieldId) {

        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(DELETE_EXPERIENCE_FIELD_BY_ID);
            preparedStatement.setInt(1, experienceFieldId);

            preparedStatement.executeUpdate();

            preparedStatement.close();

            timelineFieldRepository.removeTimelineField(experienceFieldId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

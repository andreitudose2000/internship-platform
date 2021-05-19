package repository.actionsimpl;

import model.Student;
import model.fields.EducationField;
import repository.actions.EducationFieldRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class EducationFieldRepositoryImpl implements EducationFieldRepository {

    DbConnection dbConnection = DbConnection.getInstance();

    TimelineFieldRepositoryImpl timelineFieldRepository = new TimelineFieldRepositoryImpl();

    @Override
    public List<EducationField> retrieveEducationFieldsForStudent(int studentId) {

        List<EducationField> educationFieldList = new ArrayList<>();

        try{
            PreparedStatement preparedStatement
                    = dbConnection.getDBConnection().prepareStatement(RETRIEVE_EDUCATION_FIELDS_FOR_STUDENT);
            preparedStatement.setInt(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EducationField educationField
                        = new EducationField(resultSet.getInt(1), // id
                        resultSet.getDate(2), // startDate
                        resultSet.getDate(3), // finishDate
                        resultSet.getString(4), // description
                        resultSet.getString(5), // specializationName
                        resultSet.getString(6)); // positionName
                educationFieldList.add(educationField);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return educationFieldList;
    }

    @Override
    public int addEducationFieldForStudent(Student student, EducationField educationField) {

        try{

            int timelineFieldId = timelineFieldRepository.addTimelineFieldForStudent(student, educationField);

            PreparedStatement preparedStatement  = dbConnection.getDBConnection()
                    .prepareStatement(INSERT_EDUCATION_FIELD_FOR_STUDENT);
            preparedStatement.setInt(1, timelineFieldId);
            preparedStatement.setString(2, educationField.getSpecializationName());
            preparedStatement.setString(3, educationField.getInstitutionName());

            preparedStatement.executeUpdate();

            return timelineFieldId;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void modifyEducationFieldForStudent(int educationFieldId, EducationField educationField) {
        try (PreparedStatement preparedStatement = dbConnection.getDBConnection()
                .prepareStatement(UPDATE_EDUCATION_FIELD_BY_ID)) {

            timelineFieldRepository.modifyTimelineField(educationFieldId, educationField);

            preparedStatement.setString(1, educationField.getSpecializationName());
            preparedStatement.setString(2, educationField.getInstitutionName());
            preparedStatement.setInt(3, educationFieldId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEducationField (int educationFieldId) {

        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(DELETE_EDUCATION_FIELD_BY_ID);
            preparedStatement.setInt(1, educationFieldId);

            preparedStatement.executeUpdate();

            preparedStatement.close();

            timelineFieldRepository.removeTimelineField(educationFieldId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

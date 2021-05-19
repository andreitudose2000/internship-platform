package repository.actionsimpl;

import model.Student;
import model.fields.TimelineField;
import repository.actions.TimelineFieldRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static utils.Queries.*;

public class TimelineFieldRepositoryImpl implements TimelineFieldRepository {

    DbConnection dbConnection = DbConnection.getInstance();

    @Override
    public int addTimelineFieldForStudent(Student student, TimelineField timelineField) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(INSERT_TIMELINE_FIELD_FOR_STUDENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, timelineField.getStartDate());
            preparedStatement.setDate(2, timelineField.getFinishDate());
            preparedStatement.setString(3, timelineField.getDescription());
            preparedStatement.setInt(4, student.getId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return Integer.parseInt(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public void modifyTimelineField(int timelineFieldId, TimelineField timelineField) {
        try (PreparedStatement preparedStatement = dbConnection.getDBConnection()
                .prepareStatement(UPDATE_TIMELINE_FIELD_BY_ID)) {

            preparedStatement.setDate(1, timelineField.getStartDate());
            preparedStatement.setDate(2, timelineField.getFinishDate());
            preparedStatement.setString(3, timelineField.getDescription());
            preparedStatement.setInt(4, timelineFieldId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTimelineField(int timelineFieldId) {

        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(DELETE_TIMELINE_FIELD_BY_ID);
            preparedStatement.setInt(1, timelineFieldId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

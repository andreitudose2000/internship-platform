package repository.actionsimpl;

import model.Employer;
import model.Webinar;
import repository.actions.WebinarRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class WebinarRepositoryImpl implements WebinarRepository {

    DbConnection dbConnection = DbConnection.getInstance();

    @Override
    public int addWebinarForEmployer(Employer employer, Webinar webinar) {

        ResultSet resultSet;
        try{

            PreparedStatement preparedStatement  = dbConnection.getDBConnection()
                    .prepareStatement(INSERT_WEBINAR_FOR_EMPLOYER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, webinar.getName());
            preparedStatement.setDate(2, webinar.getStartDate());
            preparedStatement.setTime(3, webinar.getStartTime());
            preparedStatement.setString(4, webinar.getPlatform());
            preparedStatement.setBoolean(5, webinar.isFinished());
            preparedStatement.setInt(6, employer.getId());

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
    public List<Webinar> retrieveWebinarsForEmployer (int employerId) {

        List<Webinar> webinarList = new ArrayList<>();

        try{
            PreparedStatement preparedStatement
                    = dbConnection.getDBConnection().prepareStatement(RETRIEVE_WEBINARS_FOR_EMPLOYER);
            preparedStatement.setInt(1, employerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Webinar webinar
                        = new Webinar(resultSet.getInt(1), // id
                        resultSet.getString(2), //name
                        resultSet.getDate(3), // startDate
                        resultSet.getTime(4), // startTime
                        resultSet.getString(5), // platform
                        resultSet.getBoolean(6), // finished
                        resultSet.getInt(7)); // employer_id
                webinarList.add(webinar);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return webinarList;
    }

    @Override
    public Webinar retrieveWebinarById(int webinarId) {

        Webinar webinar = null;
        try {
            PreparedStatement preparedStatement
                    = dbConnection.getDBConnection().prepareStatement(RETRIEVE_WEBINAR_BY_ID);
            preparedStatement.setInt(1, webinarId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                webinar = new Webinar(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getTime(4),
                        resultSet.getString(5),
                        resultSet.getBoolean(6),
                        resultSet.getInt(7));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return webinar;
    }

    @Override
    public List<Webinar> retrieveAllFutureWebinars() {
        List<Webinar> webinarList = new ArrayList<>();

        try (PreparedStatement preparedStatement = dbConnection.getDBConnection()
                .prepareStatement(RETRIEVE_ALL_FUTURE_WEBINARS)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Webinar webinar = new Webinar(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getTime(4),
                        resultSet.getString(5),
                        resultSet.getBoolean(6),
                        resultSet.getInt(7));
                webinarList.add(webinar);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return webinarList;
    }

    @Override
    public void modifyWebinarById(int webinarId, Webinar webinar) {
        try (PreparedStatement preparedStatement = dbConnection.getDBConnection()
                .prepareStatement(UPDATE_WEBINAR_BY_ID)) {

            preparedStatement.setString(1, webinar.getName());
            preparedStatement.setDate(2, webinar.getStartDate());
            preparedStatement.setTime(3, webinar.getStartTime());
            preparedStatement.setString(4, webinar.getPlatform());
            preparedStatement.setBoolean(5, webinar.isFinished());
            preparedStatement.setInt(6, webinarId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeWebinarById(int webinarId) {
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(DELETE_WEBINAR_BY_ID);
            preparedStatement.setInt(1, webinarId);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

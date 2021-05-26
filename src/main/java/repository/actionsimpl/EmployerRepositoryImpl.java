package repository.actionsimpl;

import model.Employer;
import model.Student;
import repository.actions.EmployerRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class EmployerRepositoryImpl implements EmployerRepository {

    DbConnection dbConnection = DbConnection.getInstance();

    @Override
    public int addEmployer(Employer employer) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(INSERT_EMPLOYER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employer.getEmployerName());

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
    public Employer retrieveEmployerById(int employerId) {
        Employer employer = null;

        try {
            PreparedStatement preparedStatement
                    = dbConnection.getDBConnection().prepareStatement(RETRIEVE_EMPLOYER_BY_ID);
            preparedStatement.setInt(1, employerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employer = new Employer(
                        resultSet.getInt(1),     // id
                        resultSet.getString(2)); // name
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employer;
    }

    @Override
    public Employer retrieveEmployerByName(String name) {

        Employer employer = null;
        try {
            PreparedStatement preparedStatement
                    = dbConnection.getDBConnection().prepareStatement(RETRIEVE_EMPLOYER_BY_NAME);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employer = new Employer(
                        resultSet.getInt(1),     // id
                        resultSet.getString(2)); // name
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employer;
    }

    @Override
    public List<Employer> retrieveAllEmployers() {

        List<Employer> employerList = new ArrayList<>();

        try (PreparedStatement preparedStatement = dbConnection.getDBConnection()
                .prepareStatement(RETRIEVE_ALL_EMPLOYERS)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employer employer = new Employer(resultSet.getInt(1), resultSet.getString(2));
                employerList.add(employer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employerList;
    }

    @Override
    public void modifyEmployerById(int employerId, Employer employer) {
        try (PreparedStatement preparedStatement = dbConnection.getDBConnection()
                .prepareStatement(UPDATE_EMPLOYER_BY_ID)) {

            preparedStatement.setString(1, employer.getEmployerName());
            preparedStatement.setInt(2, employerId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEmployerById(int employerId) {
        try {

            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(DELETE_EMPLOYER_BY_ID);
            preparedStatement.setInt(1, employerId);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

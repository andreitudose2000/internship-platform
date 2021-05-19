package repository.actionsimpl;

import model.Student;
import repository.actions.StudentRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static utils.Queries.*;

public class StudentRepositoryImpl implements StudentRepository {

    DbConnection dbConnection = DbConnection.getInstance();

    @Override
    public int addStudent(Student student) {

        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(INSERT_STUDENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setDate(3, student.getBirthday());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setString(5, student.getUniversity());
            preparedStatement.setString(6, student.getHeadline());

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
    public Student retrieveStudentById(int studentId) {

        Student student = null;

        try {
            PreparedStatement preparedStatement
                    = dbConnection.getDBConnection().prepareStatement(RETRIEVE_STUDENT_BY_ID);
            preparedStatement.setInt(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = new Student(
                        resultSet.getInt(1),    // id
                        resultSet.getString(2), // firstName
                        resultSet.getString(3), // lastName
                        resultSet.getDate(4),   // birthday
                        resultSet.getString(5), // email
                        resultSet.getString(6), // university
                        resultSet.getString(7)  // headline
                );
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void modifyStudentById(int studentId, Student student) {

        try (PreparedStatement preparedStatement = dbConnection.getDBConnection()
                .prepareStatement(UPDATE_STUDENT_BY_ID)) {

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setDate(3, student.getBirthday());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setString(5, student.getUniversity());
            preparedStatement.setString(6, student.getHeadline());
            preparedStatement.setInt(7, studentId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeStudentById(int studentId) {
        try {

            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(DELETE_STUDENT_BY_ID);
            preparedStatement.setInt(1, studentId);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

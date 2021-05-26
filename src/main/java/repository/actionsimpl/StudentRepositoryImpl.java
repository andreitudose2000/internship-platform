package repository.actionsimpl;

import model.Job;
import model.JobApplication;
import model.Student;
import repository.actions.JobRepository;
import repository.actions.StudentRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class StudentRepositoryImpl implements StudentRepository {

    DbConnection dbConnection = DbConnection.getInstance();

    JobRepository jobRepository = new JobRepositoryImpl();

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
    public Student retrieveStudentByEmail(String email) {
        Student student = null;

        try {
            PreparedStatement preparedStatement
                    = dbConnection.getDBConnection().prepareStatement(RETRIEVE_STUDENT_BY_EMAIL);
            preparedStatement.setString(1, email);

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

    @Override
    public int applyForJob(Student student, Job job) {

        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(APPLY_FOR_JOB, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, student.getId());
            preparedStatement.setInt(2, job.getId());

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
    public List<JobApplication> retrieveJobApplicationsForStudent(Student student) {
        List<JobApplication> jobApplicationList = new ArrayList<>();

        try (PreparedStatement preparedStatement = dbConnection.getDBConnection()
                .prepareStatement(RETRIVE_JOB_APPLICATIONS_FOR_STUDENT)) {
            preparedStatement.setInt(1, student.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int jobApplicationId = resultSet.getInt(1);
                Job job = jobRepository.retrieveJobById(resultSet.getInt(3));

                JobApplication jobApplication = new JobApplication(jobApplicationId, student, job);
                jobApplicationList.add(jobApplication);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobApplicationList;
    }

    @Override
    public void removeJobApplication(Student student, Job job) {

        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(REMOVE_JOB_APPLICATION);

            preparedStatement.setInt(1, student.getId());
            preparedStatement.setInt(2, job.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

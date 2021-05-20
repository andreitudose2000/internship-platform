package repository.actionsimpl;

import model.Employer;
import model.Job;
import model.fields.ExperienceField;
import repository.actions.JobRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class JobRepositoryImpl implements JobRepository {

    DbConnection dbConnection = DbConnection.getInstance();

    @Override
    public int addJobForEmployer(Employer employer, Job job) {

        ResultSet resultSet;
        try{

            PreparedStatement preparedStatement  = dbConnection.getDBConnection()
                    .prepareStatement(INSERT_JOB_FOR_EMPLOYER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, job.getJobTitle());
            preparedStatement.setInt(2, job.getPeriodInMonths());
            preparedStatement.setInt(3, job.getSalary());
            preparedStatement.setInt(4, employer.getId());

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
    public List<Job> retrieveJobsForEmployer(int employerId) {

        List<Job> jobList = new ArrayList<>();

        try{
            PreparedStatement preparedStatement
                    = dbConnection.getDBConnection().prepareStatement(RETRIEVE_JOBS_FOR_EMPLOYER);
            preparedStatement.setInt(1, employerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Job job
                        = new Job(resultSet.getInt(1), // id
                        resultSet.getString(2), //title
                        resultSet.getInt(3), // periodInMonths
                        resultSet.getInt(4)); // salary
                jobList.add(job);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    @Override
    public void modifyJobById(int jobId, Job job) {
        try (PreparedStatement preparedStatement = dbConnection.getDBConnection()
                .prepareStatement(UPDATE_JOB_BY_ID)) {

            preparedStatement.setString(1, job.getJobTitle());
            preparedStatement.setInt(2, job.getPeriodInMonths());
            preparedStatement.setInt(3, job.getSalary());
            preparedStatement.setBoolean(4, job.isJobActive());
            preparedStatement.setInt(5, jobId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeJobById(int jobId) {
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection()
                    .prepareStatement(DELETE_JOB_BY_ID);
            preparedStatement.setInt(1, jobId);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

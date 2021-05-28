package utils;

public class Queries {

    public static final String INSERT_TIMELINE_FIELD_FOR_STUDENT
            = "INSERT INTO timeline_fields(id, start_date, finish_date, description, student_id)" +
            " VALUES(null, ?, ?, ?, ?)";

    public static final String UPDATE_TIMELINE_FIELD_BY_ID
            = "UPDATE internship_platform.timeline_fields tf" +
            " SET tf.start_date = ?, tf.finish_date = ?, tf.description = ?" +
            " WHERE tf.id = ?";

    public static final String DELETE_TIMELINE_FIELD_BY_ID
            = "DELETE FROM timeline_fields" +
            " WHERE id = ?";


    public static final String INSERT_EXPERIENCE_FIELD_FOR_STUDENT
            = "INSERT INTO experience_fields(id, position_name, institution_name)" +
            " VALUES(?, ?, ?)";

    public static final String UPDATE_EXPERIENCE_FIELD_BY_ID
            = "UPDATE internship_platform.experience_fields ef" +
            " SET ef.position_name = ?, ef.institution_name = ?" +
            " WHERE ef.id = ?";

    public static final String RETRIEVE_EXPERIENCE_FIELDS_FOR_STUDENT
            = "SELECT tf.id, tf.start_date, tf.finish_date, tf.description, ef.position_name, ef.institution_name" +
            " FROM internship_platform.timeline_fields tf" +
            " JOIN internship_platform.experience_fields ef" +
            " ON ef.id = tf.id" +
            " WHERE tf.student_id = ?";

    public static final String DELETE_EXPERIENCE_FIELD_BY_ID
            = "DELETE FROM experience_fields" +
            " WHERE id = ?";


    public static final String INSERT_EDUCATION_FIELD_FOR_STUDENT
            = "INSERT INTO education_fields(id, specialization_name, institution_name)" +
            " VALUES(?, ?, ?)";

    public static final String RETRIEVE_EDUCATION_FIELDS_FOR_STUDENT
            = "SELECT tf.id, tf.start_date, tf.finish_date, tf.description, ef.specialization_name, ef.institution_name" +
            " FROM internship_platform.timeline_fields tf" +
            " JOIN internship_platform.education_fields ef" +
            " ON ef.id = tf.id" +
            " WHERE tf.student_id = ?";

    public static final String UPDATE_EDUCATION_FIELD_BY_ID
            = "UPDATE internship_platform.education_fields ef" +
            " SET ef.specialization_name = ?, ef.institution_name = ?" +
            " WHERE ef.id = ?";

    public static final String DELETE_EDUCATION_FIELD_BY_ID
            = "DELETE FROM education_fields" +
            " WHERE id = ?";


    public static final String INSERT_PROJECT_FIELD_FOR_STUDENT
            = "INSERT INTO project_fields(id, name)" +
            " VALUES(?, ?)";

    public static final String RETRIEVE_PROJECT_FIELDS_FOR_STUDENT
            = "SELECT tf.id, tf.start_date, tf.finish_date, tf.description, pf.name" +
            " FROM internship_platform.timeline_fields tf" +
            " JOIN internship_platform.project_fields pf" +
            " ON pf.id = tf.id" +
            " WHERE tf.student_id = ?";

    public static final String UPDATE_PROJECT_FIELD_BY_ID
            = "UPDATE internship_platform.project_fields pf" +
            " SET pf.name = ?" +
            " WHERE pf.id = ?";

    public static final String DELETE_PROJECT_FIELD_BY_ID
            = "DELETE FROM project_fields" +
            " WHERE id = ?";


    public static final String INSERT_STUDENT
            = "INSERT INTO internship_platform.students(id, first_name, last_name, birthday, email, university, headline)" +
            " VALUES(null, ?, ?, ?, ?, ?, ?)";

    public static final String RETRIEVE_STUDENT_BY_ID
            = "SELECT s.id, s.first_name, s.last_name, s.birthday, s.email, s.university, s.headline" +
            " FROM internship_platform.students s" +
            " WHERE s.id = ?";

    public static final String RETRIEVE_STUDENT_BY_EMAIL
            = "SELECT s.id, s.first_name, s.last_name, s.birthday, s.email, s.university, s.headline" +
            " FROM internship_platform.students s" +
            " WHERE s.email = ?";

    public static final String UPDATE_STUDENT_BY_ID
            = "UPDATE internship_platform.students s" +
            " SET s.first_name = ?, s.last_name = ?, s.birthday = ?, s.email = ?, s.university = ?, s.headline = ? " +
            " WHERE s.id = ?";

    public static final String DELETE_STUDENT_BY_ID
            = "DELETE FROM internship_platform.students" +
            " WHERE id = ?";

    public static final String INSERT_EMPLOYER
            = "INSERT INTO internship_platform.employers(id, name)" +
            " VALUES(null, ?)";

    public static final String RETRIEVE_EMPLOYER_BY_ID
            = "SELECT e.id, e.name" +
            " FROM internship_platform.employers e" +
            " WHERE e.id = ?";

    public static final String RETRIEVE_EMPLOYER_BY_NAME
            = "SELECT e.id, e.name" +
            " FROM internship_platform.employers e" +
            " WHERE e.name = ?";

    public static final String RETRIEVE_ALL_EMPLOYERS
            = "SELECT e.id, e.name" +
            " FROM internship_platform.employers e";

    public static final String UPDATE_EMPLOYER_BY_ID
            = "UPDATE internship_platform.employers e" +
            " SET e.name = ? " +
            " WHERE e.id = ?";

    public static final String DELETE_EMPLOYER_BY_ID
            = "DELETE FROM internship_platform.employers" +
            " WHERE id = ?";


    public static final String INSERT_JOB_FOR_EMPLOYER
            = "INSERT INTO jobs(id, title, period_in_months, salary, active, employer_id)" +
            " VALUES(null, ?, ?, ?, ?, ?)";

    public static final String RETRIEVE_JOBS_FOR_EMPLOYER
            = "SELECT j.id, j.title, j.period_in_months, j.salary, j.active" +
            " FROM internship_platform.jobs j" +
            " WHERE j.employer_id = ?";

    public static final String RETRIEVE_JOB_BY_ID
            = "SELECT j.id, j.title, j.period_in_months, j.salary, j.active" +
            " FROM internship_platform.jobs j" +
            " WHERE j.id = ?";

    public static final String RETRIEVE_ALL_ACTIVE_JOBS
            = "SELECT j.id, j.title, j.period_in_months, j.salary, j.active" +
            " FROM internship_platform.jobs j" +
            " WHERE j.active = 1";

    public static final String UPDATE_JOB_BY_ID
            = "UPDATE internship_platform.jobs j" +
            " SET j.title = ?, j.period_in_months = ?, j.salary = ?, j.active = ?" +
            " WHERE j.id = ?";

    public static final String DELETE_JOB_BY_ID
            = "DELETE FROM jobs" +
            " WHERE id = ?";

    public static final String APPLY_FOR_JOB
            = "INSERT INTO internship_platform.job_applications" +
            " VALUES(null, ?, ?)";

    public static final String RETRIVE_JOB_APPLICATIONS_FOR_STUDENT
            = "SELECT ja.id, ja.student_id, ja.job_id" +
            " FROM internship_platform.job_applications ja" +
            " WHERE ja.student_id = ?";

    public static final String REMOVE_JOB_APPLICATION
            = "DELETE FROM job_applications" +
            " WHERE student_id = ? AND job_id = ?";

    public static final String INSERT_WEBINAR_FOR_EMPLOYER
            = "INSERT INTO webinars(id, name, start_date, start_time, platform, finished, employer_id)" +
            " VALUES(null, ?, ?, ?, ?, ?, ?)";

    public static final String RETRIEVE_WEBINARS_FOR_EMPLOYER
            = "SELECT w.id, w.name, w.start_date, w.start_time, w.platform, w.finished, w.employer_id" +
            " FROM internship_platform.webinars w" +
            " WHERE w.employer_id = ?";

    public static final String RETRIEVE_WEBINAR_BY_ID
            = "SELECT w.id, w.name, w.start_date, w.start_time, w.platform, w.finished, w.employer_id" +
            " FROM internship_platform.webinars w" +
            " WHERE w.id = ?";

    public static final String RETRIEVE_ALL_FUTURE_WEBINARS
            = "SELECT w.id, w.name, w.start_date, w.start_time, w.platform, w.finished, w.employer_id" +
            " FROM internship_platform.webinars w" +
            " WHERE w.finished = 0";

    public static final String UPDATE_WEBINAR_BY_ID
            = "UPDATE internship_platform.webinars w" +
            " SET w.name = ?, w.start_date = ?, w.start_time = ?, w.platform = ?, w.finished = ?" +
            " WHERE w.id = ?";

    public static final String DELETE_WEBINAR_BY_ID
            = "DELETE FROM webinars" +
            " WHERE id = ?";

}

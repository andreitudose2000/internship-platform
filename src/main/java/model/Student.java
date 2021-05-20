package model;

import model.fields.*;
import utils.Loggable;

import java.sql.Date;
import java.util.*;

import java.time.LocalDate;

public class Student
implements Cloneable, Loggable {

    private int id;

    // obligatorii
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;

    // optionale
    private String university;
    private String headline;
    private Set<EducationField> educationFields = new TreeSet<>();
    private Set<ExperienceField> experienceFields = new TreeSet<>();
    private Set<ProjectField> projectFields = new TreeSet<>();

    private List<InboxMessage> inboxMessages;

    public Student(int id, String firstName, String lastName, Date birthday, String email, String university, String headline) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.university = university;
        this.headline = headline;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", university='" + university + '\'' +
                ", headline='" + headline + '\'' + '\n' +
                ", educationFields=" + educationFields +
                ", experienceFields=" + experienceFields +
                ", projectFields=" + projectFields +
                ", inbox=" + inboxMessages +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Set<EducationField> getEducationFields() {
        return educationFields;
    }

    public void setEducationFields(Set<EducationField> educationFields) {
        this.educationFields = educationFields;
    }

    public Set<ExperienceField> getExperienceFields() {
        return experienceFields;
    }

    public void setExperienceFields(Set<ExperienceField> experienceFields) {
        this.experienceFields = experienceFields;
    }

    public Set<ProjectField> getProjectFields() {
        return projectFields;
    }

    public void setProjectFields(Set<ProjectField> projectFields) {
        this.projectFields = projectFields;
    }
}

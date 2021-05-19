import model.Employer;
import model.Student;
import model.fields.EducationField;
import model.fields.ExperienceField;
import model.fields.ProjectField;
import repository.actions.*;
import repository.actionsimpl.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Student student = new Student(1, "Andrei2", "Tudose2", Date.valueOf("2000-06-12"),
                "andreitudose2002@gmail.com", "FMI Unibuc", "Second year student");
        StudentRepository studentRepository = new StudentRepositoryImpl();
        //studentRepository.addStudent(student);
        //System.out.println(studentRepository.retrieveStudentById(1));
        //studentRepository.modifyStudentById(1, student);
        //studentRepository.removeStudentById(1);


        ExperienceField experienceField = new ExperienceField(1, Date.valueOf("2021-07-01"), Date.valueOf("2021-09-30"),
                "desc", "Amazon", "Software Engineer");
        ExperienceFieldRepository experienceFieldRepository = new ExperienceFieldRepositoryImpl();
        //int id = experienceFieldRepository.addExperienceFieldForStudent(student, experienceField);
        //experienceFieldRepository.retrieveExperienceFieldsForStudent(student.getId()).forEach(System.out::println);
        //experienceFieldRepository.updateExperienceFieldForStudent(3, experienceField);
        //experienceFieldRepository.removeExperienceField(18);


        ProjectField projectField = new ProjectField(22, Date.valueOf("2021-05-18"), Date.valueOf("2021-05-19"),
                "Proiect in Java cu JDBC", "Proiect PAO etapa3");
        ProjectFieldRepository projectFieldRepository = new ProjectFieldRepositoryImpl();
        //projectFieldRepository.addProjectFieldForStudent(student, projectField);
        //projectFieldRepository.modifyProjectFieldForStudent(17, projectField);
        //projectFieldRepository.retrieveProjectFieldsForStudent(1).forEach(System.out::println);
        //projectFieldRepository.removeProjectField(17);

        EducationField educationField = new EducationField(22, Date.valueOf("2021-05-18"), Date.valueOf("2021-05-19"),
                "info", "FMI Unibuc", "informatica");
        EducationFieldRepository educationFieldRepository = new EducationFieldRepositoryImpl();
        //educationFieldRepository.addEducationFieldForStudent(student, educationField);
        //educationFieldRepository.modifyEducationFieldForStudent(19, educationField);
        //educationFieldRepository.retrieveEducationFieldsForStudent(1).forEach(System.out::println);
        //educationFieldRepository.removeEducationField(19);

        Employer employer = new Employer(1, "ADR");
        EmployerRepository employerRepository = new EmployerRepositoryImpl();
        //employerRepository.addEmployer(employer);
        //System.out.println(employerRepository.retrieveEmployerById(1));
        //employerRepository.modifyEmployerById(1, employer);
        //employerRepository.removeEmployerById(1);


    }
}

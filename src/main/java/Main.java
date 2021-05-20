import model.Employer;
import model.Job;
import model.Student;
import model.fields.EducationField;
import model.fields.ExperienceField;
import model.fields.ProjectField;
import repository.actions.*;
import repository.actionsimpl.*;
import service.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        StudentService studentService = StudentService.getINSTANCE();
        EmployerService employerService = EmployerService.getINSTANCE();
        EducationFieldService educationFieldService = EducationFieldService.getINSTANCE();
        ExperienceFieldService experienceFieldService = ExperienceFieldService.getINSTANCE();
        ProjectFieldService projectFieldService = ProjectFieldService.getINSTANCE();
        JobService jobService = JobService.getINSTANCE();

    }
}

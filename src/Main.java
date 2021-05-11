import exceptions.EmployerNotFoundException;
import exceptions.StudentNotFoundException;
import fields.EducationField;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.IOService;

import java.io.IOException;
import java.time.YearMonth;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        IOService<Student> studentIOService = new IOService<>(Student::new);

        List<Student> studentList = studentIOService.retrieveObjects("students.csv");

        Student student = studentList.get(0);

        student.getEducationFields().forEach(System.out::println);
        student.getExperienceFields().forEach(System.out::println);
        student.getProjectFields().forEach(System.out::println);
        student.getInbox().forEach(System.out::println);

        studentIOService.saveObjects("students2.csv", studentList);

    }

    public static void abc(String[] args)
            throws CloneNotSupportedException, EmployerNotFoundException, StudentNotFoundException {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        fields.TimelineField t = context.getBean("educationField", EducationField.class);

        System.out.println(t.getDescription());

        context.close();

        InternshipService service = InternshipService.getInstance();


        service.addEmployer("Google");
        service.addEmployer("Amazon");
        service.addEmployer("Microsoft");

        service.addJob("Google", "DevOps Intern", 3, 3000);
        service.addJob("Google", "Java Developer Intern", 3, 3200);
        service.addJob("Google", "FrontEnd Developer Intern", 3, 2800);

        service.addJob("Amazon", "Junior Data Scientist", 6, 4000);
        service.addJob("Amazon", "Software Development Intern", 3, 3200);
        service.addJob("Amazon", "AWS Cloud Support Associate", 6, 3500);

        service.addJob("Microsoft", "Junior Data Scientist", 6, 4000);
        service.addJob("Microsoft", "Software Development Intern", 3, 3200);
        service.addJob("Microsoft", "Technical Engineer Support", 6, 4000);

        service.addStudent("Andrei", "Tudose",
                "2000-06-12", "andreitudose2000@gmail.com",
                "0712-123-123",
                "Faculty of Mathematics and Computer Science, University of Bucharest",
                "Second year Computer Science student");
        service.addStudent("Andreea", "Ciurescu",
                "2001-09-25", "andreea.ciurescu@sunibuc.ro",
                "0723-234-234",
                "Faculty of Mathematics and Computer Science, University of Bucharest",
                "First year Computer Science student"
                );

        service.studentApply("Andrei", "Tudose", "Google", "Java Developer Intern");

        service.removeJobApplication("Andrei", "Tudose", "Google", "Java Developer Intern");

        service.addEducationFieldToStudent("Andrei", "Tudose", YearMonth.parse("2020-10"), YearMonth.parse("2023-06"),
                "Relevant courses:", "Faculty of Mathematics and Computer Science", "Computer Science");

        service.addProjectFieldToStudent("Andrei", "Tudose", YearMonth.parse("2021-01"), YearMonth.parse("2021-02"),
                "C++ OOP code that imitates basic SQL DDL, DML and DQL functionality.", "SQL in C++");


    }
}

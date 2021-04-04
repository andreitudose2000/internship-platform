import exceptions.EmployerNotFoundException;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Employer> companies = new ArrayList<>();
    static List<StudentProfile> students = new ArrayList<>();

    public static void studentApply (StudentProfile profile, String companyName, String jobName) throws EmployerNotFoundException {
        Employer company = companies.stream()
                .filter(e -> e.getEmployerName().equals(companyName))
                .findAny()
                .orElse(null);
        if (company == null) {
            throw new EmployerNotFoundException("Employer \"" + companyName + "\" not found.");
        }
        Job job = company.getEmployerJobs().stream()
                .filter(j -> j.getJobTitle().equals(jobName))
                .findAny()
                .orElse(null);
        if (job != null) {
            profile.applyForJob(job);
        }
    }

    public static void studentApply(StudentProfile profile, Job job) {
        profile.applyForJob(job);
    }

    public static void main(String[] args) {
        //Scanner readConsole = new Scanner(System.in);

        companies.add(new Employer("Google"));
        companies.add(new Employer("Amazon"));
        companies.add(new Employer("Microsoft"));

        companies.get(0).addJob(new Job("DevOps Intern", 3, 3000));
        companies.get(0).addJob(new Job("Java Developer Intern", 3, 3200));
        companies.get(0).addJob(new Job("FrontEnd Developer Intern", 3, 2800));

        companies.get(1).addJob(new Job("Junior Data Scientist", 6, 4000));
        companies.get(1).addJob(new Job("Software Development Intern", 3, 3200));
        companies.get(1).addJob(new Job("AWS Cloud Support Associate", 6, 3500));

        companies.get(2).addJob(new Job("Junior Data Scientist", 6, 4000));
        companies.get(2).addJob(new Job("Software Development Intern", 3, 3200));
        companies.get(2).addJob(new Job("Technical Engineer Support", 6, 4000));

        try {
            StudentProfile student1 = new StudentProfile.ProfileBuilder("Andrei", "Tudose",
                    "2000-06-12", "andreitudose2000@gmail.com")
                    .setPhoneNumber("0712-123-123")
                    .setHeadline("Second year Computer Science student")
                    .setUniversity("Faculty of Mathematics and Computer Science, University of Bucharest")
                    .addEducationField(YearMonth.parse("2019-10"), YearMonth.parse("2022-06"),
                            "Relevant courses: OOP, Data Structures, Software Development Methods, Web Applications Development" ,
                            "Faculty of Mathematics and Computer Science", "Computer Science")
                    .build();
            students.add(student1);

            StudentProfile student2 = new StudentProfile.ProfileBuilder("Andreea", "Ciurescu",
                    "2001-09-25", "andreea.ciurescu@sunibuc.ro")
                    .setPhoneNumber("0723-234-234")
                    .setHeadline("First year Computer Science student")
                    .setUniversity("Faculty of Mathematics and Computer Science, University of Bucharest")
                    .addEducationField(YearMonth.parse("2020-10"), YearMonth.parse("2023-06"), "Relevant courses:" ,
                            "Faculty of Mathematics and Computer Science", "Computer Science")
                    .build();
            students.add(student2);

        } catch (exceptions.IncorrectEmailException | exceptions.IncorrectPhoneNumberException e) {
            e.printStackTrace();
        }

        try {
            studentApply(students.get(0), companies.get(0).getEmployerJobs().get(0));
            studentApply(students.get(1), "Microsoft", "Technical Engineer Support");
        }
        catch (exceptions.EmployerNotFoundException e ) { System.out.println("Employer not found"); }

        companies.get(0).getEmployerJobs().get(0).setJobActive(false);
        companies.get(2).getEmployerJobs().get(2).setJobActive(false);

        students.get(0).seeUnreadMessages();

        students.get(1).seeUnreadMessages();

        students.get(1).removeJobApplicaion(companies.get(2).getEmployerJobs().get(2));
    }
}

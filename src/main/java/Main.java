import exception.EmployerNotFoundException;
import exception.IncorrectEmailException;
import exception.StudentNotFoundException;
import model.*;
import model.fields.EducationField;
import model.fields.ExperienceField;
import model.fields.ProjectField;
import service.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;


public class Main {

    private final static StudentService studentService = StudentService.getINSTANCE();
    private final static EmployerService employerService = EmployerService.getINSTANCE();
    private final static EducationFieldService educationFieldService = EducationFieldService.getINSTANCE();
    private final static ExperienceFieldService experienceFieldService = ExperienceFieldService.getINSTANCE();
    private final static ProjectFieldService projectFieldService = ProjectFieldService.getINSTANCE();
    private final static JobService jobService = JobService.getINSTANCE();
    private final static WebinarService webinarService = WebinarService.getINSTANCE();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("login sau register?");
        String option = scanner.nextLine().strip();
        if (option.equals("login")) {
            login(scanner);
            return;
        }
        if (option.equals("register")) {
            register(scanner);
        }
    }

    private static void login(Scanner scanner) {
        System.out.println("Doriti sa va logati ca student sau employer?");
        String option = scanner.nextLine().strip();

        if (option.equals("student")) {
            loginStudent(scanner);
            return;

        }
        if (option.equals("employer")) {
            loginEmployer(scanner);
            return;
        }

        System.out.println("Optiune invalida");

    }

    private static void loginStudent(Scanner scanner) {
        System.out.println("Logare in contul de student");
        System.out.println("Introduceti email:");
        String email = scanner.nextLine().strip();
        try {
            Student student = studentService.retrieveStudentByEmail(email);
            meniuStudent(scanner, student);
        } catch (StudentNotFoundException e) {
            System.out.println("Userul nu exista in baza de date");
            loginStudent(scanner);
        }
    }

    private static void loginEmployer(Scanner scanner) {
        System.out.println("Logare in contul de firma");
        System.out.println("Introduceti numele firmei");
        String employerName = scanner.nextLine().strip();
        try {
            Employer employer = employerService.retrieveEmployerByName(employerName);
            meniuEmployer(scanner, employer);
        } catch(EmployerNotFoundException e) {
            System.out.println("Employerul nu exista in baza de date");
        }


    }

    private static void register(Scanner scanner) {
        System.out.println("Doriti sa va inregistrati ca student sau employer?");
        String option = scanner.nextLine().strip();

        if (option.equals("student")) {
            registerStudent(scanner);
            return;

        }
        if (option.equals("employer")) {
            registerEmployer(scanner);
            return;
        }

        System.out.println("Optiune invalida");
    }

    private static void registerStudent(Scanner scanner) {

        System.out.println("Inregistrare cont student");
        System.out.println("Introduceti email");
        String email = scanner.nextLine().strip();

        System.out.println("Introduceti prenumele");
        String firstName = scanner.nextLine().strip();

        System.out.println("Introduceti numele de familie");
        String lastName = scanner.nextLine().strip();

        System.out.println("Introduceti data nasterii (AAAA-LL-ZZ)");
        Date birthday = Date.valueOf(scanner.nextLine().strip());

        System.out.println("Introduceti universitatea");
        String university = scanner.nextLine().strip();

        System.out.println("Introduceti o scurta descriere");
        String headline = scanner.nextLine().strip();

        Student student = new Student(firstName, lastName, birthday, email, university, headline);
        try {
            studentService.addStudent(student);
        } catch (IncorrectEmailException e) {
            System.out.println("Email incorect, incercati din nou");
            register(scanner);
        }
        loginStudent(scanner);
    }

    private static void registerEmployer(Scanner scanner) {
        System.out.println("Inregistrare cont employer");
        System.out.println("Introduceti numele firmei");
        String employerName = scanner.nextLine().strip();

        Employer employer = new Employer(employerName);
        employerService.addEmployer(employer);

        loginEmployer(scanner);
    }

    private static void meniuStudent(Scanner scanner, Student student) {
        String option;
        do {
            System.out.println("Optiuni: \n" +
                    "x. Vezi profilul tau \n" +
                    "1. Adauga camp educatie \n" +
                    "2. Adauga camp experienta \n" +
                    "3. Adauga proiect \n" +
                    "4. Vezi angajatorii \n" +
                    "5. Vezi toate ofertele active \n" +
                    "6. Aplica la un internship \n" +
                    "7. Vezi la ce internship-uri ai aplicat \n" +
                    "8. Renunta la a aplica la un job \n" +
                    "9. Vezi webinarele la care poti participa \n" +
                    "10. Stergere cont \n" +
                    "0. Iesire");
            option = scanner.nextLine().strip();
            if (option.equals("x")) {
                student = studentService.retrieveStudentById(student.getId());
                System.out.println(student);
                List<EducationField> educationFieldList
                        = educationFieldService.retrieveEducationFieldsForStudent(student.getId());
                List<ExperienceField> experienceFieldList
                        = experienceFieldService.retrieveExperienceFieldsForStudent(student.getId());
                List<ProjectField> projectFieldList
                        = projectFieldService.retrieveProjectFieldsForStudent(student.getId());
                System.out.println("\nEducatie:");
                educationFieldList.forEach(System.out::println);
                System.out.println("\nExperienta:");
                experienceFieldList.forEach(System.out::println);
                System.out.println("\nProiecte:");
                projectFieldList.forEach(System.out::println);
            }
            if (option.equals("1")) {
                System.out.println("Introduceti data de inceput (AAAA-LL-ZZ)");
                Date startDate = Date.valueOf(scanner.nextLine().strip());

                System.out.println("Introduceti data de sfarsit (AAAA-LL-ZZ)");
                Date finishDate = Date.valueOf(scanner.nextLine().strip());

                System.out.println("Introduceti o scurta descriere");
                String description = scanner.nextLine().strip();

                System.out.println("Introduceti numele specializarii");
                String specializationName = scanner.nextLine().strip();

                System.out.println("Introduceti numele institutiei");
                String institutionName = scanner.nextLine().strip();

                EducationField educationField
                        = new EducationField(startDate, finishDate, description, specializationName, institutionName);

                educationFieldService.addEducationFieldForStudent(student, educationField);
            }
            if (option.equals("2")) {
                System.out.println("Introduceti data de inceput (AAAA-LL-ZZ)");
                Date startDate = Date.valueOf(scanner.nextLine().strip());

                System.out.println("Introduceti data de sfarsit (AAAA-LL-ZZ)");
                Date finishDate = Date.valueOf(scanner.nextLine().strip());

                System.out.println("Introduceti o scurta descriere");
                String description = scanner.nextLine().strip();

                System.out.println("Introduceti numele pozitiei pe care ati lucrat");
                String positionName = scanner.nextLine().strip();

                System.out.println("Introduceti numele institutiei");
                String institutionName = scanner.nextLine().strip();

                ExperienceField experienceField
                        = new ExperienceField(startDate, finishDate, description, positionName, institutionName);

                experienceFieldService.addExperienceFieldForStudent(student, experienceField);
            }
            if (option.equals("3")) {
                System.out.println("Introduceti data de inceput (AAAA-LL-ZZ)");
                Date startDate = Date.valueOf(scanner.nextLine().strip());

                System.out.println("Introduceti data de sfarsit (AAAA-LL-ZZ)");
                Date finishDate = Date.valueOf(scanner.nextLine().strip());

                System.out.println("Introduceti o scurta descriere");
                String description = scanner.nextLine().strip();

                System.out.println("Introduceti numele proiectului");
                String projectName = scanner.nextLine().strip();

                ProjectField projectField
                        = new ProjectField(startDate, finishDate, description, projectName);

                projectFieldService.addProjectFieldForStudent(student, projectField);
                System.out.println("");
            }
            if (option.equals("4")) {

                List<Employer> employerList = employerService.retrieveAllEmployers();

                for (Employer employer : employerList) {
                    List<Job> jobList = jobService.retrieveJobsForEmployer(employer.getId());

                    System.out.println(employer);
                    jobList.forEach(System.out::println);
                }

                System.out.println("");
            }
            if (option.equals("5")) {
                System.out.println("Oferte active:");
                jobService.retrieveAllActiveJobs().forEach(System.out::println);
                System.out.println("");
            }
            if (option.equals("6")) {
                System.out.println("Introduceti id-ul jobului");
                int jobId = Integer.parseInt(scanner.nextLine().strip());

                Job job = jobService.retrieveJobById(jobId);
                if (job == null) {
                    System.out.println("Nu exista job-ul cautat");
                    continue;
                }
                studentService.applyToJob(student, job);
                System.out.println("Ati aplicat la job-ul\n" + job);
            }
            if (option.equals("7")) {
                System.out.println("Ati aplicat la urmatoarele internship-uri:");
                studentService.retrieveJobApplicationsForStudent(student).forEach(System.out::println);
            }

            if (option.equals("8")) {
                System.out.println("Introduceti id-ul jobului la care renuntati:");
                int jobId = scanner.nextInt();
                Job job = jobService.retrieveJobById(jobId);
                studentService.removeJobApplication(student, job);
            }
            if (option.equals("9")) {
                System.out.println("Webinare de la angajatori:");
                webinarService.retrieveAllAvailableWebinars().forEach(System.out::println);
                System.out.println("");
            }
            if (option.equals("10")) {
                studentService.removeStudentById(student.getId());
                return;
            }

        } while (!option.equals("0"));
    }

    private static void meniuEmployer(Scanner scanner, Employer employer) {
        String option;
        do {
            System.out.println("Optiuni: \n" +
                    "1. Vezi joburile firmei \n" +
                    "2. Adauga job \n" +
                    "3. Seteaza job inactiv \n" +
                    "4. Sterge job \n" +
                    "5. Vezi webinarele firmei \n" +
                    "6. Adauga webinar \n" +
                    "7. Sterge webinar \n" +
                    "8. Stergere cont \n" +
                    "0. Iesire");
            option = scanner.nextLine().strip();
            if (option.equals("1")) {
                List<Job> jobList = jobService.retrieveJobsForEmployer(employer.getId());
                System.out.println("Job-urile curente:");
                jobList.forEach(System.out::println);
                continue;
            }
            if (option.equals("2")) {
                System.out.println("Introduceti titlul jobului");
                String jobTitle = scanner.nextLine().strip();

                System.out.println("Introduceti durata internshipului (in luni)");
                int periodInMonths = scanner.nextInt();

                System.out.println("Introduceti salariul platit");
                int salary = scanner.nextInt();

                Job job = new Job(jobTitle, periodInMonths, salary, true);
                jobService.addJob(employer, job);
                continue;
            }
            if (option.equals("3")) {
                System.out.println("Introduceti id-ul job-ului");
                int jobId = scanner.nextInt();

                jobService.setJobInactive(jobId);
                continue;
            }
            if (option.equals("4")) {
                jobService.retrieveJobsForEmployer(employer.getId()).forEach(System.out::println);
                System.out.println("Introduceti id-ul jobului pe care vreti sa il stergeti");
                int jobId = scanner.nextInt();
                jobService.removeJobById(jobId);
                continue;
            }
            if (option.equals("5")) {
                List<Webinar> webinarList = webinarService.retrieveWebinarsForEmployer(employer.getId());
                System.out.println("Webinarele firmei:");
                webinarList.forEach(System.out::println);
                continue;
            }
            if (option.equals("6")) {
                System.out.println("Introduceti titlul webinarului");
                String title = scanner.nextLine().strip();

                System.out.println("Introduceti data");
                Date startDate = Date.valueOf(scanner.nextLine().strip());

                System.out.println("Introduceti ora");
                Time startTime = Time.valueOf(scanner.nextLine().strip());

                System.out.println("Introduceti platforma pe care are loc");
                String platform = scanner.nextLine().strip();

                Webinar webinar = new Webinar(title, startDate, startTime, platform, false, employer.getId());
                webinarService.addWebinar(employer, webinar);
                continue;
            }
            if (option.equals("7")) {
                webinarService.retrieveWebinarsForEmployer(employer.getId()).forEach(System.out::println);
                System.out.println("Introduceti id-ul webinarului pe care vreti sa il stergeti");
                int webinarId = scanner.nextInt();
                webinarService.removeWebinarById(webinarId);
                continue;
            }
            if (option.equals("8")) {
                employerService.removeEmployerById(employer.getId());
                return;
            }
        } while (!option.equals("0"));
    }
}

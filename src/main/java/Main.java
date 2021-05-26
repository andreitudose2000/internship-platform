import model.Employer;
import model.Job;
import model.Student;
import model.fields.EducationField;
import model.fields.ExperienceField;
import model.fields.ProjectField;
import service.*;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;


public class Main {

    private final static StudentService studentService = StudentService.getINSTANCE();
    private final static EmployerService employerService = EmployerService.getINSTANCE();
    private final static EducationFieldService educationFieldService = EducationFieldService.getINSTANCE();
    private final static ExperienceFieldService experienceFieldService = ExperienceFieldService.getINSTANCE();
    private final static ProjectFieldService projectFieldService = ProjectFieldService.getINSTANCE();
    private final static JobService jobService = JobService.getINSTANCE();

    public static void main(String[] args) {

//        Employer employer = employerService.retrieveEmployerById(2);
//        Student student = studentService.retrieveStudentById(1);
//        List<Job> jobs = jobService.retrieveJobsForEmployer(2);

//        studentService.applyToJob(student, jobs.get(0));
//        studentService.removeJobApplication(student, jobs.get(0));

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
        Student student = studentService.retrieveStudentByEmail(email);

        if (student == null) {
            System.out.println("Userul nu exista in baza de date");
            return;
        }

        meniuStudent(scanner, student);
    }

    private static void loginEmployer(Scanner scanner) {
        System.out.println("Logare in contul de firma");
        System.out.println("Introduceti numele firmei");
        String employerName = scanner.nextLine().strip();
        Employer employer = employerService.retrieveEmployerByName(employerName);

        if (employer == null) {
            System.out.println("Employerul nu exista in baza de date");
            return;
        }

        meniuEmployer(scanner, employer);
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
        studentService.addStudent(student);
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
                    "8. Stergere cont \n" +
                    "0. Iesire");
            option = scanner.nextLine().strip();
            if (option.equals("x")) {
                student = studentService.retrieveStudentById(student.getId());
                System.out.println(student);
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

                employerService.retrieveAllEmployers().forEach(System.out::println);

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
                    "4. Stergere cont \n" +
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
                employerService.removeEmployerById(employer.getId());
                return;
            }
        } while (!option.equals("0"));
    }
}
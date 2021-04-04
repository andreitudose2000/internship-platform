import exceptions.IncorrectEmailException;
import exceptions.IncorrectPhoneNumberException;
import fields.EducationField;
import fields.ExperienceField;
import fields.ProjectField;

import java.time.YearMonth;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.*;

//Clasa modeleaza un profil de utilizator student care contine
//campuri atat obligatorii cat si neobligatorii
//ca sa treaca validarea, cele obligatorii trebuie sa nu fie null
public class Student implements Observer {
    public void updateObserver(String message){
        inbox.add(new InboxMessage(message));
    }

    public void seeUnreadMessages() {
        for (InboxMessage inboxMessage : inbox) {
            System.out.println(inboxMessage.getMessage());
        }
    }

    //obligatorii
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    //optionale
    private String university;
    //private File image;
    private String headline;
    private ArrayList<String> programmingLanugages;
    private ArrayList<String> foreignLanguages;
    private ArrayList<String> preferredClasses;
    private Set<EducationField> educationFields = new TreeSet<>();
    private Set<ExperienceField> experienceFields = new TreeSet<>();
    private Set<ProjectField> projectFields = new TreeSet<>();

    private List<InboxMessage> inbox = new ArrayList<>();

    private Student(ProfileBuilder profileBuilder){
        this.firstName = profileBuilder.firstName;
        this.lastName = profileBuilder.lastName;
        this.birthday = profileBuilder.birthday;
        this.university = profileBuilder.university;
        this.headline = profileBuilder.headline;
        this.educationFields = profileBuilder.educationFields;
        this.experienceFields = profileBuilder.experienceFields;
        this.projectFields = profileBuilder.projectFields;
    }

    public void applyForJob(Job job) {
        job.subscribe(this);
    }

    public void removeJobApplication(Job job) {
        job.unsubscribe(this);
    }

    static public class ProfileBuilder{
        //obligatorii
        private String firstName;
        private String lastName;
        private LocalDate birthday;
        private String email;

        //optionale
        private String university;
        private String headline;
        private Set<EducationField> educationFields = new TreeSet<>();
        private Set<ExperienceField> experienceFields = new TreeSet<>();
        private Set<ProjectField> projectFields = new TreeSet<>();
        private char[] phoneNumber = new char[10];

        public ProfileBuilder(Student student) throws CloneNotSupportedException {
            this.firstName = student.firstName;
            this.lastName = student.lastName;
            this.birthday = student.birthday;
            this.university = student.university;
            this.headline = student.headline;
            this.educationFields = student.educationFields;
            this.experienceFields = student.experienceFields;
            this.projectFields = student.projectFields;
        }

        public ProfileBuilder(String firstName, String lastName, String birthday, String email) throws IncorrectEmailException {
            this.firstName = firstName;
            this.lastName = lastName;
            String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            if (!Pattern.matches(emailRegex, email)) {
                throw new IncorrectEmailException("Incorrect email: " + email);
            }

            try {
                //Locale.setDefault(Locale.FRANCE);
                this.birthday = LocalDate.parse(birthday);
            } catch (DateTimeParseException e){
                System.out.println("error formatting birthday");
            }
        }

        public ProfileBuilder setUniversity(String university){
            this.university = university;
            return this;
        }

        public ProfileBuilder setHeadline(String headline){
            this.headline = headline;
            return this;
        }

        public ProfileBuilder setPhoneNumber(String phoneNumber) throws IncorrectPhoneNumberException {
            String phoneRegex = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";
            if (!Pattern.matches(phoneRegex, phoneNumber)) {
                throw new IncorrectPhoneNumberException("Incorrect phone number: " + phoneNumber);
            }
            return this;
        }


        public ProfileBuilder addEducationField(YearMonth startDate, YearMonth finishDate, String description, String institutionName, String specializationName){
            this.educationFields.add(new EducationField(startDate, finishDate, description, institutionName, specializationName));
            return this;
        }

        public ProfileBuilder addExperienceField(YearMonth startDate, YearMonth finishDate, String description, String positionName, String institutionName){
            this.experienceFields.add(new ExperienceField(startDate, finishDate, description, positionName, institutionName));
            return this;
        }

        public ProfileBuilder addProjectField(YearMonth startDate, YearMonth finishDate, String description, String projectName){
            this.projectFields.add(new ProjectField(startDate, finishDate, description, projectName));
            return this;
        }

        public Student build(){
            return new Student(this);
        }

    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", university='" + university + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void update(Student student) {
        this.firstName = student.firstName;
        this.lastName = student.lastName;
        this.birthday = student.birthday;
        this.university = student.university;
        this.headline = student.headline;
        this.educationFields = student.educationFields;
        this.experienceFields = student.experienceFields;
        this.projectFields = student.projectFields;
    }
}

# Platforma de internshipuri pentru studenti

## Tipurile de obiecte
* **Student**
* **Employer**
* **Job**
* **JobApplication**
* **model.Webinar**
* **Timeline Field**, cu subtipurile: 
  - **EducationField**
  - **ExperienceField**
  - **ProjectField**
* **InboxMessage**
* Trei tipuri de exceptii:
  - **EmployerNotFoundException**
  - **IncorrectEmailException**
  - **StudentNotFoundException**

## Tipurile de interogari/actiuni
#### StudentService - operatii CRUD pe Student
* Un student se poate inregistra cu email-ul si datele personale. El va fi adaugat in DB prin metoda `addStudent(Student): void`
* Un student se poate autentifica in cont cu email-ul. Se verifica daca acesta exista in DB: `retrieveStudentByEmail(String): Student`
* Un student poate vedea toate detaliile profilului sau. Acestea vor fi extrase din DB: `retrieveStudentById(int): Student`
* Un student isi poate sterge contul `removeStudentById(int): void`

#### StudentService - operatii CRUD pe JobApplication
* Un student poate aplica la un job: `applyToJob(Student, Job): void`
* Un student poate vedea la ce job-uri a aplicat: `retrieveJobApplicationsForStudent(Student): [JobApplication]`
* Un student poate renunta la un job la care a aplicat: `removeJobApplication(Student, Job): void`

#### [Experience / Education / Project]FieldService - operatii CRUD pe Field-uri
* Un student isi poate adauga la profil campuri de experienta, educatie sau proiecte personale, prin metodele `addExperienceFieldForStudent(Student, ExperienceField): void` , `addEducationFieldForStudent(Student, EducationField): void`, `addProjectFieldForStudent(Student, ProjectField): void`

#### EmployerService - operatii CRUD pe Employer
* Un angajator se poate inregistra. El va fi adaugat in DB prin metoda `addEmployer(Employer): void`
* Un angajator isi poate sterge contul din aplicatie: `removeEmployerById(int): void`
* Un student poate vedea toti angajatorii, prin metoda `retrieveAllEmployers(): [Employer]`

#### JobService - operatii CRUD pe Job
* Un angajator poate vedea job-urile pe care le are la momentul curent: `retrieveJobsForEmployer(int): [Job]`
* Un angajator poate adauga o oferta de job: `addJob(Employer, Job): void`
* Un angajator poate seta o oferta de job inactiva: `setJobInactive(int): void`

## Colectii
ArrayList si TreeSet

## Mosteniri
EducationField, ExperienceField, ProjectField extind TimelineField.

EmployerNotFoundException, IncorrectEmailException si StudentNotFoundException extind Exception.

## Interfete
Comparable<T>, Cloneable, Loggable

## Logging
Clasa generica LoggingService<T> permite crearea cate unui sistem de logare pentru fiecare entitate care implementeaza interfata marker `Loggable`.

In fiecare clasa Service injectez un LoggingService<T> specific entitatii.

La apelul oricarei actiuni de CRUD din clasele Service, se scrie in fisierul .CSV (cel corespunzator clasei) actiunea si timestamp-ul. Aceste fisiere log se afla in folderul `log`.

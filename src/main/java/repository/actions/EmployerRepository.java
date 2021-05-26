package repository.actions;

import model.Employer;

import java.util.List;

public interface EmployerRepository {

    int addEmployer(Employer employer);
    Employer retrieveEmployerById(int employerId);
    Employer retrieveEmployerByName(String name);
    List<Employer> retrieveAllEmployers();
    void modifyEmployerById(int employerId, Employer employer);
    void removeEmployerById(int employerId);
}

package repository.actions;

import model.Employer;

public interface EmployerRepository {

    int addEmployer(Employer employer);
    Employer retrieveEmployerById(int employerId);
    void modifyEmployerById(int employerId, Employer employer);
    void removeEmployerById(int employerId);
}

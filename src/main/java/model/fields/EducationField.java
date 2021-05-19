package model.fields;


import java.sql.Date;
import java.time.YearMonth;

public class EducationField extends TimelineField {

    private int id;
    private String institutionName;
    private String specializationName;

    public EducationField(int id, Date startDate, Date finishDate, String description, String specializationName, String institutionName){
        super(id, startDate, finishDate, description);
        this.id = id;
        this.specializationName = specializationName;
        this.institutionName = institutionName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "EducationField{" +
                super.toString() +
                ", specializationName='" + specializationName + '\'' +
                ", institutionName='" + institutionName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }
}

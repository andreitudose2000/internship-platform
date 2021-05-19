package model.fields;

import java.sql.Date;
import java.time.YearMonth;

public class ExperienceField extends TimelineField {

    private int id;
    private String institutionName;
    private String positionName;

    public ExperienceField(int id, Date startDate, Date finishDate, String description, String positionName, String institutionName) {
        super(id, startDate, finishDate, description);
        this.id = id;
        this.positionName = positionName;
        this.institutionName = institutionName;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return  "ExperienceField{" +
                super.toString() +
                ", positionName='" + positionName + '\'' +
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}

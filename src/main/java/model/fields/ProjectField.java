package model.fields;

import java.sql.Date;
import java.time.YearMonth;

public class ProjectField extends TimelineField {

    private int id;
    private String projectName;

    public ProjectField(int id, Date startDate, Date finishDate, String description, String projectName) {
        super(id, startDate, finishDate, description);
        this.id = id;
        this.projectName = projectName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "ProjectField{" +
                super.toString() +
                ", projectName='" + projectName + '\'' +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}

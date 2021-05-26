package model.fields;

import utils.Loggable;

import java.sql.Date;

public class ProjectField extends TimelineField
implements Loggable {

    private int id;
    private String projectName;

    public ProjectField(int id, Date startDate, Date finishDate, String description, String projectName) {
        super(id, startDate, finishDate, description);
        this.id = id;
        this.projectName = projectName;
    }

    public ProjectField(Date startDate, Date finishDate, String description, String projectName) {
        super(startDate, finishDate, description);
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

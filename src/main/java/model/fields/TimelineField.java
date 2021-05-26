package model.fields;

import java.sql.Date;
import java.time.YearMonth;

public class TimelineField
implements Comparable<TimelineField>, Cloneable {

    private int id;
    private Date startDate;
    private Date finishDate;
    private String description;
    private int studentId;
    private Integer educationFieldId;
    private Integer experienceFieldId;
    private Integer projectFieldId;

    public TimelineField(int id, Date startDate, Date finishDate, String description) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.description = description;
    }

    public TimelineField(Date startDate, Date finishDate, String description) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.description = description;
    }

    @Override
    public int compareTo(TimelineField o) {
        int comp = this.startDate.compareTo(o.startDate);
        if (comp != 0) {
            return comp;
        }
        return this.finishDate.compareTo(o.finishDate);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", description='" + description + '\'';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Integer getEducationFieldId() {
        return educationFieldId;
    }

    public void setEducationFieldId(Integer educationFieldId) {
        this.educationFieldId = educationFieldId;
    }

    public Integer getExperienceFieldId() {
        return experienceFieldId;
    }

    public void setExperienceFieldId(Integer experienceFieldId) {
        this.experienceFieldId = experienceFieldId;
    }

    public Integer getProjectFieldId() {
        return projectFieldId;
    }

    public void setProjectFieldId(Integer projectFieldId) {
        this.projectFieldId = projectFieldId;
    }
}

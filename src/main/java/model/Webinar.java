package model;

import utils.Loggable;

import java.sql.Date;
import java.sql.Time;

public class Webinar
implements Loggable {

    private int id;
    private String name;
    private Date startDate;
    private Time startTime;
    private String platform;
    private boolean finished = false;
    private int employerId;


    public Webinar(String name, Date startDate, Time startTime, String platform, boolean finished, int employerId) {
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.platform = platform;
        this.finished = finished;
        this.employerId = employerId;
    }

    public Webinar(int id, String name, Date startDate, Time startTime, String platform, boolean finished, int employerId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.platform = platform;
        this.finished = finished;
        this.employerId = employerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    @Override
    public String toString() {
        return "Webinar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", platform='" + platform + '\'' +
                ", finished=" + finished +
                ", employerId=" + employerId +
                '}';
    }
}

package model;

import java.time.LocalDateTime;

public class Webinar
implements Comparable<Webinar>, Cloneable {

    private int id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private String platform;
    private boolean reminderSent = false;
    private boolean finished = false;
    private int employerId;

    public Webinar(String name, LocalDateTime startDate, LocalDateTime finishDate, String platform) {
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.platform = platform;
    }

    @Override
    public int compareTo(Webinar o) {
        return this.startDate.compareTo(o.startDate);
    }

    public Object clone()throws CloneNotSupportedException{
        return (Webinar)super.clone();
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public boolean isReminderSent() {
        return reminderSent;
    }

    public void setReminderSent(boolean reminderSent) {
        this.reminderSent = reminderSent;
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
}

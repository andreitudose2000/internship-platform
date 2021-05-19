package model;

public class InboxMessage {

    private int id;
    private final String message;
    private boolean read;
    private int studentId;

    InboxMessage(String message) {
        this.message = message;
        this.read = false;
    }

    InboxMessage(String message, boolean read) {
        this.message = message;
        this.read = read;
    }

    @Override
    public String toString() {
        return "InboxMessage{" +
                "message='" + message + '\'' +
                ", read=" + read +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}

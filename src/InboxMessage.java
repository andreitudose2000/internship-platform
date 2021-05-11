public class InboxMessage {

    final private String message;
    private boolean read;
    InboxMessage(String message) {
        this.message = message;
        this.read = false;
    }

    InboxMessage(String message, boolean read) {
        this.message = message;
        this.read = read;
    }

    public String getMessage() {
        this.markRead();
        return this.message;
    }

    public void markRead(){
        this.read = true;
    }

    @Override
    public String toString() {
        return "InboxMessage{" +
                "message='" + message + '\'' +
                ", read=" + read +
                '}';
    }
}

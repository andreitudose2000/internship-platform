public class InboxMessage {
    final private String message;
    private boolean read;
    InboxMessage(String message) {
        this.message = message;
        this.read = false;
    }

    public String getMessage() {
        this.markRead();
        return this.message;
    }

    public void markRead(){
        this.read = true;
    }

}

package HailStorm.threading.interThreadComms;

/**
 * Created by Development on 9/28/2016.
 */
public class Message {

    private String sender;
    private Object message;

    public Message(String sender, Object message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public Object getMessage() {
        return message;
    }
}

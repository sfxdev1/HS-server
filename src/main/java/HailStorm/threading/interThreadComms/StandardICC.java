package HailStorm.threading.interThreadComms;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Development on 9/28/2016.
 */
public class StandardICC {
    private static StandardICC instance;
    private boolean initialised = false;
    private HashMap<String, LinkedList<Message>> mailboxes;


    private StandardICC() {

    }

    public static StandardICC getInstance() {
        if (instance != null) {
            instance = new StandardICC();
        }
        return instance;
    }

    public LinkedList getMessages(String id) {
        LinkedList<Message> fin = mailboxes.get(id);
        mailboxes.put(id, new LinkedList<Message>());
        return fin;
    }

    public void send(String to, Message sending) {
        mailboxes.get(to).add(sending);
    }

    public void register(String id) {
        mailboxes.put(id, new LinkedList<>());
    }
}

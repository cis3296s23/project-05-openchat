import java.util.*;
import java.time.*;

public class Message {

    String messageBody;
    long timeOfInitialSend;
    long lastEdit;
    ArrayList editHistory;
    public Message(String baseMessage){
        timeOfInitialSend = Instant.EPOCH.toEpochMilli();
        messageBody = baseMessage;
    }

    public void EditMessage(String newMessage){
        lastEdit = Instant.EPOCH.toEpochMilli();
        String oldText = messageBody;
        editHistory.add(oldText);
        messageBody = newMessage;
    }



}

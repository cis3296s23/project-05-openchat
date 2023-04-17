import java.util.*;
import java.time.*;

public class Message {

    String messageBody;
    long timeOfInitialSend;
    long lastEdit;
    ArrayList editHistory;
    int sender;
    public Message(String baseMessage,int sentby){
        timeOfInitialSend = Instant.EPOCH.toEpochMilli();
        messageBody = baseMessage;
        sender = sentby;
    }

    public void EditMessage(String newMessage){
        lastEdit = Instant.EPOCH.toEpochMilli();
        String oldText = messageBody;
        editHistory.add(oldText);
        messageBody = newMessage;
    }



}

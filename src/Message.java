import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;

public class Message {

    String messageBody;
    long timeOfInitialSend;
    long lastEdit;
    ArrayList editHistory;
    private String userName;

    public Message(String baseMessage, String userName){
        this.userName = userName;
        timeOfInitialSend = Instant.EPOCH.toEpochMilli();
        messageBody = baseMessage;
    }

    public void EditMessage(String newMessage){
        lastEdit = Instant.EPOCH.toEpochMilli();
        String oldText = messageBody;
        editHistory.add(oldText);
        messageBody = newMessage;
    }

    public String setMessage(String text){
        messageBody = text;
        return messageBody;
    }

    public String getDate(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    @Override
    public String toString(){
        return String.format("[%s]\n%s: %s\n", getDate(), userName, messageBody);
    }

}

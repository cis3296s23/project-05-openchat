import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageRoom {
    int roomId;
    static int roomCount = 0;
    String roomName;
    public ArrayList<Message> messageStack = new ArrayList<Message>();
    private HashMap<String, Boolean> clientsConnected = new HashMap<>();
    public int MessageRoom(String roomName) {
        roomId = roomCount;
        roomCount++;
        if(roomName != null){
            this.roomName = roomName;
        }

        return roomId;
    }
    public boolean containsUser(String userID){
       return clientsConnected.containsKey(userID);
    }
}

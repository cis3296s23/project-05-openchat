import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageRoom {
    int roomId;
    static int roomCount = 0;
    String roomName;
<<<<<<< HEAD
    public ArrayList<Message> messageStack = new ArrayList<Message>();
    private HashMap<String, Boolean> clientsConnected = new HashMap<>();
    public int MessageRoom(String roomName) {
=======
    Client[] ConnectedClients;
    public ArrayList<Message> messageStack = new ArrayList<Message>();
    public int MessageRoom() {
>>>>>>> 4c4b4df4f461a15af05e0d03fe29edeefa1965b9
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

import java.util.ArrayList;

public class MessageRoom {
    int roomId;
    static int roomCount;
    String roomName;
    Client[] ConnectedClients;
    ArrayList<Message> messageStack = new ArrayList<Message>();
    public int MessageRoom() {
        roomId = roomCount;
        roomCount++;


        return roomId;
    }
}

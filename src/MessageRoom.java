import java.util.ArrayList;

public class MessageRoom {
    int roomId;
    static int roomCount = 0;
    String roomName;
    Client[] ConnectedClients;
    public ArrayList<Message> messageStack = new ArrayList<Message>();
    public int MessageRoom() {
        roomId = roomCount;
        roomCount++;


        return roomId;
    }
}

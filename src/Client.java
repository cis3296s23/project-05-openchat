// A Java program for a Client
import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class Client {
    // initialize socket and input output streams
    private Socket socket = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private int ClientId;
    private static int currentclients = 0;
    private Server ServerOwner;
    private String address;
    private int port;
    private ChatView view;

    // constructor to put ip address and port
    public Client(String address, int port, ChatView view)
    {
        ClientId = currentclients; //Each client has a unique incremental Idw
        currentclients++;
        ServerOwner = Server.ThisServer;
        this.address = address;
        this.port = port;
        // establish a connection
        System.out.println("current clientid:  " + ClientId );
        this.view = view;
        view.show();
    }

    public void newConnection(){
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());

            while(true){
                // takes input from view
                String input = view.sentText;

                if(!input.isEmpty()){
                    out.writeUTF(input);
                    out.flush();
                    view.sentText = "";
                }
                // read input from the server
                in = new DataInputStream(socket.getInputStream());
                if (in.available() > 0) {
                    Message response = new Message(in.readUTF(), 1, "Server");
                    view.appendMessage(response.toString());
                }

                // wait a short amount of time before checking again
                TimeUnit.MILLISECONDS.sleep(10);
            }
        } catch (IOException | InterruptedException u) {
            u.printStackTrace();
        }
    }

    public int getClientId(){
        return ClientId;
    }

    public void requestGroup(int[] targetids){
        // ServerOwner.requestConnection(ClientId,targetids);
        ServerOwner.createChatRoom();
    }
}
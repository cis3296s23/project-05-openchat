// A Java program for a Client
import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Client {
    // initialize socket and input output streams
    private Socket socket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private int ClientId;
    private static int currentclients = 0;
    private Server ServerOwner;
    private String address;
    private int port;
    private ChatView view;
    public ArrayList<SerializableSocketAddress> currentClients;
    public ArrayList<String> connectClientIds  = new ArrayList<>();

    private int currentTargetGroup;

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
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            // start a new thread for reading input from the server
            Thread handleResponse = new Thread(() ->{
                getResponse(in);
            });
            handleResponse.start();

            while(true){
                // takes input from view
                String input = view.sentText;
                if(!input.isEmpty()){
                    Message out_msg = new Message(input, this.getClientId(), "Client "
                            + this.getClientId());
                    out.writeObject(out_msg);
                    out.flush();
                    view.sentText = "";
                }
                // wait a short amount of time before checking again
                TimeUnit.MILLISECONDS.sleep(10);
            }
        } catch (IOException | InterruptedException u) {
            u.printStackTrace();
        }
    }

    private void getResponse(ObjectInputStream in){
        try{
            while(true){
                // read input from the server
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object obj = ois.readObject();
                System.out.println("Got somethign" + obj.getClass());
                if (obj instanceof ArrayList) {
                    ArrayList<SerializableSocketAddress> receivedList = (ArrayList<SerializableSocketAddress>) obj;
                    System.out.println("Received client list");
                    for (SerializableSocketAddress socketAddress : receivedList) {
                        // do something with socketAddress
                        connectClientIds.add(socketAddress.userID);
                        System.out.println("Address connected: "+ socketAddress.toInetSocketAddress().getAddress().toString() + " User ID: " + socketAddress.userID );
                    }
                    currentClients= receivedList;
                    view.userList.removeAllItems();
                    view.userList.addItem(receivedList);
                } else{
                    Message response = (Message) in.readObject();
                    view.appendMessage(response);

                }

            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public int getClientId(){
        return ClientId;
    }

    public void requestGroup(int[] targetids){
        // ServerOwner.requestConnection(ClientId,targetids);
    }
}
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
    private DataInputStream input = null;
    private DataOutputStream out = null;
    private int ClientId;
    private static int currentclients = 0;
<<<<<<< HEAD
    public ArrayList<SerializableSocketAddress> currentClients;
=======
    private ArrayList<SerializableSocketAddress> currentClients;
>>>>>>> 4c4b4df4f461a15af05e0d03fe29edeefa1965b9
    public static Server ServerOwner;
    private int currentTargetGroup;

    // constructor to put ip address and port
    public Client(String address, int port) throws IOException, ClassNotFoundException {
        ClientId = currentclients; //Each client has a unique incremental Idw
        currentclients++;
        System.out.println(ServerOwner.ServerOpen);
        // establish a connection
        System.out.println("current clientid:  " + ClientId );
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input = new DataInputStream(System.in);
 
            // sends output to the socket
            out = new DataOutputStream(
                socket.getOutputStream());
        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }

        Thread t = new Thread(new Runnable() {
            public void run() {
                String line = "";

                while (!line.equals("Over")) {
                    try {
                        line = input.readLine();
                        out.writeUTF(line);
                    }
                    catch (IOException i) {
                        System.out.println(i);
                    }
                }

                // close the connection
                try {
                    input.close();
                    out.close();
                    socket.close();
                }
                catch (IOException i) {
                    System.out.println(i);
                }
            }
        });
        t.start();
        //Temporary solution VV
        int[] ar = {1};
        this.requestGroup(ar);
        while(true){
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Object obj = ois.readObject();
            if (obj instanceof ArrayList) {
                ArrayList<SerializableSocketAddress> receivedList = (ArrayList<SerializableSocketAddress>) obj;
                System.out.println("Received client list");
                for (SerializableSocketAddress socketAddress : receivedList) {
                    // do something with socketAddress
                    System.out.println("Address connected: "+ socketAddress.toInetSocketAddress().getAddress().toString() + " User ID: " + socketAddress.userID );
                }
                currentClients= receivedList;
            }
          //  ois.close();
        }

    }

    public void requestGroup(int[] targetids){
       // ServerOwner.requestConnection(ClientId,targetids);
        //int groupId = Server.createChatRoom();
       // currentTargetGroup = groupId;
    }

    public void sendMessage(int groupId){

    }

    public static void main(String args[]) throws InterruptedException, IOException, ClassNotFoundException {
      //  TimeUnit.SECONDS.sleep(5);
        Thread.sleep(5000);
        Client client = new Client("localhost", 25565);

    }
}
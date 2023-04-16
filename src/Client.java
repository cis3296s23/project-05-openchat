// A Java program for a Client
import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
 
public class Client {
    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    private int ClientId;
    private static int currentclients = 0;
    private Server ServerOwner;
    private int currentTargetGroup;
    // constructor to put ip address and port
    public Client(String address, int port)
    {
        ClientId = currentclients; //Each client has a unique incremental Idw
        currentclients++;
        ServerOwner = Server.ThisServer;
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
 
        // string to read message from input
        String line = "";
 
        // keep reading until "Over" is input
       /* while (!line.equals("Over")) {
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
        }*/
    }

    public void requestGroup(int[] targetids){
       // ServerOwner.requestConnection(ClientId,targetids);
        int groupId = ServerOwner.createChatRoom();
        currentTargetGroup = groupId;
    }

    public void sendMessage(int groupId){

    }

    public static void main(String args[]) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Client client = new Client("localhost", 5000);
    }
}
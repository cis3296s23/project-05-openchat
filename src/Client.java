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
    // constructor to put ip address and port
    public Client(String address, int port) throws IOException {
        socket = new Socket(address, port);
    }

}
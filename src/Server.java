// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server
{
	//initialize socket and input stream
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;
	private ChatModel model;

	// constructor with port
	public Server(int port, int maxClients) throws IOException {
		model = new ChatModel(port, maxClients);
	}

	public void start() throws IOException{
		model.getConnections();
	}

	public ServerSocket getServer(){
		return server;
	}

	public static void main(String args[])
	{

	}
}
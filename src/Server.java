// A Java program for a Server
import java.net.*;
import java.io.*;
import java.util.HashMap;

public class Server extends Thread
{
	//initialize socket and input stream
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;
	private int serverPort;
	public boolean ServerOpen = false;
	public static Server ThisServer;
	private Client[] ConnectedClients;
	private HashMap<Integer,MessageRoom> messageRooms;
	// constructor with port
	public Server(int port)
	{
		// starts server and waits for a connection
		ServerOpen = true;
		serverPort = port;
		ThisServer = this;
		messageRooms = new HashMap<Integer,MessageRoom>();
	}

	public boolean isClientConnected(String clientAddress){
		boolean status = false;

		return status;
	};

	public void run() {
		try {

			String publicIp = "localhost"; // replace with your public IP address
			int port = 25565; // replace with the port you want to listen on
			InetAddress addr = Inet4Address.getByName(publicIp);
			System.out.println("Server socket listening on " + InetAddress.getLocalHost().getHostAddress() + ":" + port + addr.isReachable(5) );

			ServerSocket server = new ServerSocket(port);//, 0, addr);

			//server = new ServerSocket(serverPort);
			System.out.println("Server started");

			while (ServerOpen) {
				System.out.println("Waiting for a client ...");

				socket = server.accept();
				System.out.println("Client accepted");
				Thread t = new Thread(new Runnable() {
					public void run() {
						try{
							handleConnection(socket);
							socket.close();
						}catch (IOException i) {
							System.out.println(i);
						}
					}
				});
				t.start();
			}
		} catch (IOException i) {
			System.out.println(i);
		}
	}

	public int createChatRoom(){

		MessageRoom createdRoom = new MessageRoom();
		messageRooms.put(createdRoom.roomId,createdRoom);
		System.out.println("room created");
		return createdRoom.roomId;
	};


	public boolean requestConnection(int requesterId, int[] clientIds){
		boolean success = false;


		return success;
	}

	public void sendMessageToRoom(int roomId,int clientId,String message){
		Message messageObject = new Message(message,clientId);
		MessageRoom targetRoom = messageRooms.get(roomId);
		System.out.println("Sending message: ' " + messageObject.messageBody + " '.");

	}


	private void handleConnection(Socket socket) {
		try {
			// takes input from the client socket
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			String line = "";

			// reads message from client until "Over" is sent
			while (!line.equals("Over")) {
				try {
					line = in.readUTF();
					System.out.println(line);
				} catch (IOException i) {
					System.out.println(i);
				}
			}
			System.out.println("Closing connection");

			// close input stream
			in.close();
		} catch (IOException i) {
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{


	}
}
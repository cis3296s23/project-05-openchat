// A Java program for a Server

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Server extends Thread
{
	//initialize socket and input stream
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;
	private int serverPort;
	public static boolean ServerOpen = false;
	public static Server ThisServer;
	private static final HashMap<String,Socket> ConnectedClients = new HashMap<>();
	static final HashMap<Integer,MessageRoom> messageRooms = new HashMap<Integer,MessageRoom>();
	// constructor with port
	public Server(int port)
	{
		// starts server and waits for a connection
		ServerOpen = true;
		serverPort = port;
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
			System.out.println("Server socket listening on " + InetAddress.getLocalHost().getHostAddress() + ":" + port + "Reachable?:" + addr.isReachable(5) );

			ServerSocket server = new ServerSocket(port);//, 0, addr);

			//server = new ServerSocket(serverPort);
			System.out.println("Server started");

			while (ServerOpen) {
				System.out.println("Waiting for a client ...");

				socket = server.accept();
				ConnectedClients.put(UUID.randomUUID().toString(),socket);

				updateClientList();

				//System.out.println("Client accepted. Current Connected Clients: " + ConnectedClients.toString());
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

	public static void updateClientList() throws IOException {
		ArrayList<Serializable> clientMap = new ArrayList<>();

		for(String ID: ConnectedClients.keySet()){
			Socket socket = ConnectedClients.get(ID);
			clientMap.add(new SerializableSocketAddress((InetSocketAddress)socket.getLocalSocketAddress(),ID));
		}

		for(String ID: ConnectedClients.keySet()){
			Socket socket = ConnectedClients.get(ID);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(clientMap);
			//oos.close();
		}
	}


	public static int createChatRoom(){

		MessageRoom createdRoom = new MessageRoom();
		messageRooms.put(createdRoom.roomId,createdRoom);
		System.out.println("room created" + messageRooms.toString());
		return createdRoom.roomId;
	};


	public boolean requestConnection(int requesterId, int[] clientIds){
		boolean success = false;


		return success;
	}

	public static void sendMessageToRoom(int roomId,int clientId,String message){
		Message messageObject = new Message(message,clientId);
		MessageRoom targetRoom = messageRooms.get(roomId);
		System.out.println("Sending message: '" + messageObject.messageBody + "'. to " + messageRooms.toString());
		if(targetRoom == null){
			System.out.println("Failed to access null room");
			return;
		}
		targetRoom.messageStack.add(messageObject);
		System.out.println(roomId + "Current stack:" + targetRoom.messageStack.toString());

	}


	private static boolean processCommand(String commandLine){
		boolean commandProccessed = false;
		//System.out.println("processing-'" +commandLine.toCharArray().toString() );
		if(commandLine.equals("--CreateRoom")){
			System.out.println("Creating room");
			commandProccessed = true;
			createChatRoom();
		};

		return commandProccessed;
	};

	private void handleConnection(Socket socket) {
		try {
			// takes input from the client socket
			//System.out.println("Handling the req");
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			String line = "";

			// reads message from client until "Over" is sent
			while (!line.equals("Over")) {
				try {
					line = in.readUTF();
					if(!processCommand(line)){
						sendMessageToRoom(0,0, line);

					}

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

	public static void main(String args[]) {
		Server thread = new Server(25565);
		ThisServer = thread;
		//thread.start();
		Server.ThisServer.start();
		Server.ThisServer.messageRooms.get(1);
		while(thread.ServerOpen){
			//await input
		}

	}
}
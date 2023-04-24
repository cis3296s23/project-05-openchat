// A Java program for a Server
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server extends Thread
{
	//initialize socket and input stream
	private Socket		 socket = null;
	private ServerSocket server = null;
	private int serverPort;
	public boolean ServerOpen = false;
	public static Server ThisServer;
	private static final HashMap<String,Socket> ConnectedClients = new HashMap<>();
	public static int clientCount = 0;
	int maxClients = 5;
	static final HashMap<Integer,MessageRoom> messageRooms = new HashMap<Integer,MessageRoom>();
	public HashMap<Socket, ObjectOutputStream> connectedClients;
	// constructor with port
	public Server(int port)
	{
		// starts server and waits for a connection
		ServerOpen = true;
		serverPort = port;
		ThisServer = this;
		connectedClients = new HashMap<>();
	}

	public boolean isClientConnected(String clientAddress){
		boolean status = false;

		return status;
	}

	public void run() {
		try {
			server = new ServerSocket(serverPort);
			System.out.println("Server started");
			int numClients=0;

			String[] threadNames = new String[maxClients];

			while (ServerOpen && numClients!=maxClients) {
				System.out.println("Waiting for a client ...");
				socket = server.accept();
				connectedClients.put(socket, new ObjectOutputStream(socket.getOutputStream()));
				numClients++; //stops us from always waiting for more clients
				System.out.println("Client accepted");
				Thread t = new Thread(() -> {
					try{
						handleConnection(socket);
						socket.close();
					}catch (IOException i) {
						System.out.println(i);
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


	public static void updateClientMessageList()throws IOException {


		for(String clientID: ConnectedClients.keySet()){
			ArrayList<MessageRoom> roomList = new ArrayList<>();

			for(int roomId: messageRooms.keySet()){
				MessageRoom targetRoom = messageRooms.get(roomId);
				if (targetRoom.containsUser(clientID)){
					roomList.add(targetRoom);
				}
			}


			Socket socket = ConnectedClients.get(clientID);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(roomList);
			//oos.close();
		}
	}

	public static int createChatRoom() throws IOException {
		MessageRoom createdRoom = new MessageRoom();
		messageRooms.put(createdRoom.roomId,createdRoom);
		System.out.println("room created" + messageRooms.toString());
		updateClientMessageList();
		return createdRoom.roomId;
	}


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


	private static boolean processCommand(String commandLine) throws IOException {
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
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			Message line = new Message("",0,"");

			// reads message from client until "Over" is sent
			while (!line.messageBody.equals("Over")) {
				try {
					line = (Message) in.readObject();
					System.out.println("\nServer: " + line.toString() + " THREAD_ID: " + currentThread() +"\n");

					// Send message to all other connected clients
					for(Socket client : connectedClients.keySet()){
						if(client != socket){
							ObjectOutputStream out = connectedClients.get(client);
							out.writeObject(line);
							out.flush();
						}
					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
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
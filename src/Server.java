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
	public List<Socket> connectedClients;
	private HashMap<Integer,MessageRoom> messageRooms;
	// constructor with port
	public Server(int port)
	{
		// starts server and waits for a connection
		ServerOpen = true;
		serverPort = port;
		ThisServer = this;
		connectedClients = new ArrayList<>();
	}

	public boolean isClientConnected(String clientAddress){
		boolean status = false;

		return status;
	}

	public void startServer(int maxClients) {
		try {
			server = new ServerSocket(serverPort);
			System.out.println("Server started");
			int numClients=0;
			String[] threadNames = new String[maxClients];

			while (ServerOpen && numClients!=maxClients) {
				//TODO If max clients is reached we can close this loop - DONE with numClients
				System.out.println("Waiting for a client ...");
				socket = server.accept();
				connectedClients.add(socket);
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

	public int createChatRoom(){

		MessageRoom createdRoom = new MessageRoom();
		messageRooms.put(createdRoom.roomId,createdRoom);
		System.out.println("room created");
		return createdRoom.roomId;
	}


	public boolean requestConnection(int requesterId, int[] clientIds){
		boolean success = false;
		return success;
	}

	public void sendMessageToRoom(int roomId,int clientId,String message){
		Message messageObject = new Message(message,clientId, "Me");
		MessageRoom targetRoom = messageRooms.get(roomId);
		System.out.println("Sending message: ' " + messageObject.messageBody + " '.");

	}


	private void handleConnection(Socket socket) {
		try {
			// takes input from the client socket
			DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			String line = "";

			// reads message from client until "Over" is sent
			while (!line.equals("Over")) {
				try {
					// TODO relay messages to clients from the client list
					line = in.readUTF();
					System.out.println("Server: " + line + " THREAD_ID: " + currentThread());

					// Send message to all other connected clients
					for(Socket client : connectedClients){
						if(client != socket){
							DataOutputStream out = new DataOutputStream(client.getOutputStream());
							out.writeUTF(line);
							out.flush();
						}
					}
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
}
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
	public List<Client> connectedClients;
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

	public void startServer() {
		try {
			server = new ServerSocket(serverPort);
			System.out.println("Server started");

			while (ServerOpen) {
				//TODO If max clients is reached we can close this loop
				System.out.println("Waiting for a client ...");

				socket = server.accept();
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
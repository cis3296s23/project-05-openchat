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
	public HashMap<Socket, ObjectOutputStream> connectedClients;
	private HashMap<Integer,MessageRoom> messageRooms;
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

	public void startServer(int maxClients) {
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
		Message messageObject = new Message(message, clientId,"Me");
		MessageRoom targetRoom = messageRooms.get(roomId);
		System.out.println("Sending message: ' " + messageObject.messageBody + " '.");

	}


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
					ArrayList<Socket> listOfKeys = new ArrayList<Socket>(connectedClients.keySet()); //puts all the sockets into this thingy
					System.out.println(listOfKeys);
					// Send message to all other connected clients
					for(Socket client : connectedClients.keySet()){
						if(ChatView.recipientID==1 && client.equals(listOfKeys.get(0))){ //for client one, get socket at index 0
							ObjectOutputStream out = connectedClients.get(client);
							out.writeObject(line);
							out.flush();

						}else if(ChatView.recipientID==2 && client.equals(listOfKeys.get(2))){ //get socket at index 2 for client two
							ObjectOutputStream out = connectedClients.get(client);
							out.writeObject(line);
							out.flush();
						}else if(ChatView.recipientID==3 && client.equals(listOfKeys.get(1))){ //if(ChatView.recipientID==3)//get socket at index 1 for client 3
							ObjectOutputStream out = connectedClients.get(client);
							out.writeObject(line);
							out.flush();
						}
						//ObjectOutputStream out = connectedClients.get(client);
						//out.writeObject(line);
						//out.flush();

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
}

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
	public ArrayList<Socket> listOfKeys;
	// constructor with port
	public Server(int port)
	{
		// starts server and waits for a connection
		ServerOpen = true;
		serverPort = port;
		ThisServer = this;
		connectedClients = new HashMap<>();
		listOfKeys = new ArrayList<>();
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
				listOfKeys.add(socket); //puts all the sockets into this thingy in correct order

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
					//puts all the sockets into this thingy
					System.out.println(listOfKeys);
					// Send message to all other connected clients
					for(Socket client : connectedClients.keySet()){
						if(ChatView.recipientID == 0){ //if it wants to go to everyone
							if(client!= socket){ //WHILE GOING THROUGH FOR LOOP, check each client against socket, shoul;d send message to each one except self
								ObjectOutputStream out = connectedClients.get(client);
								out.writeObject(line);
								out.flush();
							}
						}
						for(int i=0; i< ChatModel.maxClients; i++){ //run through clients, if it matches the recipient, give them the message
							if(ChatView.recipientID == i+1 && client.equals(listOfKeys.get(i))){
								ObjectOutputStream out = connectedClients.get(client);
								out.writeObject(line);
								out.flush();
							}
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
}
//Problems: clients aren't being added into connected clients in correct order
//also recipientid isnt updated when clicking on different GUI only if reclicked in box again
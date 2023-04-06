// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server extends Thread
{
	//initialize socket and input stream
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;
	private int serverPort;
	public boolean ServerOpen = false;
	private Client[] ConnectedClients;
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
			server = new ServerSocket(serverPort);
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
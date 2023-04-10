import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatModel {
    private ServerSocket server;
    private List<Socket> clients;
    private List<PrintWriter> writers;
    private int port;
    private int maxClients;
    private int numClients;

    public ChatModel(int port, int maxClients) throws IOException {
        this.port = port;
        server = new ServerSocket(port);
        clients = new ArrayList<>();
        writers = new ArrayList<>();
        this.maxClients = maxClients;
        this.numClients = 0;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void getConnections() throws IOException {

        while(true){
            Socket clientSocket = server.accept();

            if(numClients > maxClients){
                // Reject the connection if the maximum number of clients is reached
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writer.println("The server has reached its maximum capacity.");
                writer.flush();
                clientSocket.close();
            }else{
                clients.add(clientSocket);
                numClients++;
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        }
    }


    public void sendMessage(String msg){
        for(PrintWriter writer : writers){
            writer.println(msg);
            writer.flush();
        }
    }

    private class ClientHandler implements Runnable{
        private Socket client;
        private BufferedReader reader;
        private PrintWriter writer;

        public ClientHandler(Socket client) throws IOException {
            this.client = client;
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(), true);
            writers.add(writer);
        }

        @Override
        public void run() {
            try{
                while(true){
                    String msg = reader.readLine();

                    // If client disconnected then remove it
                    if(msg == null){
                        clients.remove(client);
                        writers.remove(writer);
                        numClients--;
                        break;
                    }

                    // Send message to all clients
                    for(PrintWriter writer : writers){
                        if(writer != this.writer){
                            writer.println(msg);
                            writer.flush();
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

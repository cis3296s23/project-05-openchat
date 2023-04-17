import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChatModel {
    private List<Client> clients;
    private int port;
    private int maxClients;
    private int numClients;
    private Server server;

    public ChatModel(int port, int maxClients) throws IOException {
        this.port = port;
        clients = new ArrayList<>();
        this.maxClients = maxClients;
        this.numClients = 0;
    }

    public void startServer() {
        server = new Server(port);

        Thread thread = new Thread(() -> {
            server.startServer();
        });
        thread.start();
    }

    // Could make this into a method to add more clients
    public void startClient(){
        ChatView view = new ChatView();
        view.addSendButtonListener(new SendButtonListener(view));
        Client client = new Client("127.0.0.1", port, view);

        // Each client must run on a separate thread
        Thread Cthread = new Thread(() ->{
            client.newConnection();
        });
        Cthread.start();

        ChatView view2 = new ChatView();
        view2.addSendButtonListener(new SendButtonListener(view2));
        Client client_2 = new Client("127.0.0.1", port, view2);

        Thread C_2thread = new Thread(() ->{
            client_2.newConnection();
        });
        C_2thread.start();
    }

    private class SendButtonListener implements ActionListener {
        public ChatView view;
        public SendButtonListener(ChatView view){
            this.view = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = view.getInputText();
            // Update the view
            view.appendMessage(input);
            view.sentText = input;
            view.clearInputText();
        }
    }

}

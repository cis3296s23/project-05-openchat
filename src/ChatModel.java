import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

public class ChatModel {
    private List<Client> clients; //i want to make a map here, client name: identifying address.
    private int port;
    public static int maxClients;
    private int numClients;
    private Server server;
    public static int UXClientID;

    public ChatModel(int port, int maxClients) throws IOException {
        this.port = port;
        clients = new ArrayList<>();
        this.maxClients = maxClients;
        this.numClients = 0;
    }

    public void startServer() {
        server = new Server(port);

        Thread thread = new Thread(() -> {
            server.startServer(maxClients);
        });
        thread.start();
    }

    // Could make this into a method to add more clients
    public void startClient(){
        //creates as many clients as there are inputted in maxclients in ChatController
        String[] clientNames = new String[maxClients];
        for(int i=0; i<maxClients; i++){
            String temp = "Client " + (i+1);
            clientNames[i] = temp; //put each client name into string array for drop down
        }
        for(int i=0; i<maxClients; i++){
            //create clientID for GUI
            UXClientID = i +1;

            //remove current client from list of possible clients that you can chat with
            String[] tempArray = clientNames; //neccessary to repopulate with ALL options
            List<String> list = new ArrayList<String>(Arrays.asList(tempArray));
            list.remove(i);
            tempArray = list.toArray(new String[0]); //reset temp ARRAY

            ChatView view = new ChatView(UXClientID, tempArray); //pass string array and current client's ID
            view.addSendButtonListener(new SendButtonListener(view));
            Client client = new Client("127.0.0.1", port, view);
            // Each client must run on a separate thread
            Thread Cthread = new Thread(() ->{ //creates seperate threads with same name
                client.newConnection();
            });
            Cthread.start();
        }

    }

    private class SendButtonListener implements ActionListener {
        public ChatView view;
        public SendButtonListener(ChatView view){
            this.view = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = view.getInputText();
            Message msg = new Message(input,0, "Me");
            // Update the view
            /*String choice = (String) view.userMenu.getSelectedItem();
            String intBoxOutput = choice.replaceAll("[^0-9]","");
            ChatView.recipientID = Integer.parseInt(intBoxOutput); //update chatview's selection  when done
            */
            view.appendMessage(msg);
            view.sentText = input;
            view.clearInputText();
        }
    }

}

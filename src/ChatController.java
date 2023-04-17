import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class ChatController {
    private ChatModel model;


    public ChatController(ChatModel model){
        this.model = model;

        //view.addSendButtonListener(new SendButtonListener());
    }

    public void startApp(){
        model.startServer();
        model.startClient();
    }

    public static void main(String[] args) throws IOException {
        ChatModel model = new ChatModel(5000, 2);
        ChatController controller = new ChatController(model);
        controller.startApp();
    }
}


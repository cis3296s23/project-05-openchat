import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ChatController {
    private ChatModel model;


    public ChatController(ChatModel model){
        this.model = model;

        //view.addSendButtonListener(new SendButtonListener());
    }

    public void startApp() throws InterruptedException {
        System.out.println("Starting it up");
        model.startServer();
        TimeUnit.MILLISECONDS.sleep(1000);
        model.startClient();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ChatModel model = new ChatModel(888, 3);
        ChatController controller = new ChatController(model);
        controller.startApp();
    }
}


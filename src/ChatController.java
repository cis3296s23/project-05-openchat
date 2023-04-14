import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChatController {
    //TODO Add chat model
    private ChatView view;
    private ChatModel model;


    public ChatController(ChatView view, ChatModel model){
        this.view = view;
        this.model = model;

        view.addSendButtonListener(new SendButtonListener());
    }


    public void run(){
        view.show();
    }


    private class SendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = view.getInputText();

            // Update the view
            view.appendMessage(input);
            model.sendMessage(input);
            view.clearInputText();
        }
    }

    public static void main(String[] args) throws IOException {
        ChatModel model = new ChatModel(888, 2);
        ChatView view = new ChatView();
        ChatController controller = new ChatController(view, model);

        Thread thread = new Thread(() ->{
            try{
                model.getConnections();
            }catch(IOException e){
                e.printStackTrace();
            }
        });
        thread.start();

        controller.run();
    }
}


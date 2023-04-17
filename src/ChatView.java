import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatView {
    private final JFrame frame = new JFrame();
    private final JButton send = new JButton("Send");
    private final JTextField textField = new JTextField(50);
    private final JTextArea textArea = new JTextArea(15, 25);
    public String sentText = "";


    public ChatView(){
        // Setup Window
        frame.setTitle("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(send);
        frame.add(textField, BorderLayout.NORTH);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(send, BorderLayout.SOUTH);
        frame.pack();
    }

    // Update GUI text area
    public void appendMessage(String message){
        Message msg = new Message(message, 0,"Me");
        textArea.append(msg.toString());
    }

    public String getInputText(){
        return textField.getText();
    }

    public void clearInputText(){
        textField.setText("");
    }

    public void show(){
        frame.setVisible(true);
    }

    public void addSendButtonListener(ActionListener listener){
        send.addActionListener(listener);
    }
}

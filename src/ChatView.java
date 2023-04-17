import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
public class ChatView extends JFrame implements ItemListener {
    JPanel buttonsPanel;
    JPanel dropBox;
    private final JFrame frame = new JFrame();
    private final JButton send = new JButton("Send");
    private final JTextField typeBar = new JTextField(50);
    private final JTextArea textArea = new JTextArea(15, 25);
    public String sentText = "";


    public ChatView(){
        // Setup Window
        textArea.setText("New ChatRoom:\n" );
        //create drop box

        String[] clientNames= {"Client One", "Client Two", "Client Three", "Client Four", "Groupchat"};
        JComboBox<String> userMenu = new JComboBox<String>(clientNames);
        dropBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel labelTwo = new JLabel("Chatting with:   ");
        dropBox.add(labelTwo);
        dropBox.add(userMenu);



        //handle what happens when things are chosen in drop  box
        String choice = (String) userMenu.getSelectedItem();
        userMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> userMenu = (JComboBox<String>) e.getSource();
                String choice = (String) userMenu.getSelectedItem();
                JOptionPane.showMessageDialog(frame, "You selected to chat with " + choice);
                textArea.setText("New ChatRoom:\n" ); //clear text area for new chat with different person; can be changed to set text to previous sql logging

            }
        });
        //panel for send button
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(typeBar);
        buttonsPanel.add(send);
        frame.setTitle("Welcome to OpenChat, Client One" );//this will be implemented as a + userName later on once we instantiate userIDs
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(send);


        frame.add(dropBox, BorderLayout.NORTH);
        //frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        //button
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        //frame.add(send, BorderLayout.SOUTH);
        frame.pack();


    }

    // Update GUI text area
    public void appendMessage(String message){
        Message msg = new Message(message,0, "Me");
        textArea.append(msg.toString());
    }

    public String getInputText(){
        return typeBar.getText();
    }

    public void clearInputText(){
        typeBar.setText("");
    }

    public void show(){
        frame.setVisible(true);
    }

    public void addSendButtonListener(ActionListener listener){
        send.addActionListener(listener);
    }


    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
//ISSUE: two labels on north are overwriting each other- can only have pne- hopw to fix?
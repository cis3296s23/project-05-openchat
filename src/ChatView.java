import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.SwingConstants;

public class ChatView extends JFrame implements ItemListener {
    JPanel buttonsPanel;
    JPanel dropBox;
    JPanel titleLabel;
    private final JFrame frame = new JFrame();
    private final JButton send = new JButton("Send");
    private final JTextField typeBar = new JTextField(50);
    private final JTextArea textArea = new JTextArea(15, 25);


    public ChatView(){
        // Setup Window
        //create an object
        //create drop box
        String[] clientNames= {"Client One", "Client Two", "Client Three", "Client Four", "Groupchat"};
        JComboBox<String> userMenu = new JComboBox<String>(clientNames);
        frame.add(userMenu);
        //sets up panel so that drop box is on top right
        dropBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        titleLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //words
        JLabel labelOne = new JLabel ("Welcome to OpenChat");
        labelOne.setFont(new Font("Serif", Font.PLAIN, 14));
        JLabel labelTwo = new JLabel("Chatting with:   ");
        titleLabel.add(labelOne);
        dropBox.add(labelTwo);
        dropBox.add(userMenu);

        //frame.add(userMenu,javax.swing.SwingConstants.NORTH_EAST);
        String choice = (String) userMenu.getSelectedItem();
        userMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> userMenu = (JComboBox<String>) e.getSource();
                String choice = (String) userMenu.getSelectedItem();
                JOptionPane.showMessageDialog(frame, "You selected to chat with " + choice);


            }
        });
        //panel for send button
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(typeBar);
        buttonsPanel.add(send);
        frame.setTitle("Client");//this needs to be changed so window can be reused by server
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
        Message msg = new Message(message, "Me");
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
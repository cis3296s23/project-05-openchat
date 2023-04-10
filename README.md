# OpenChat

# Vision Statement
This project is FOR individuals seeking to connect through real-time text based chatting software. WHO want 
the availability of communicating through standard chatting practices but also enhancing the workflow of developers.
THE OpenChat application is a software based application THAT allows the accessibility of editing code and communicating remotely.
UNLIKE other applications OUR PRODUCT will remain light-weight and easy to use through simplistic UI designs and be centered around
making communication more effective and productive.


# Project Description
OpenChat is a real-time chatting software that allows users to connect and interact with each other effectively and efficiently.
This project utilzes Java Sockets to allow for this connection between users. Our goal is not only a chatting application but
rather a chatting application that improves the lives of employees at the work place. We are doing this by adding features such 
as, editing code snippets inside the chat message in real-time. 

![image](https://user-images.githubusercontent.com/89660661/229372009-312297dc-d0c3-4e5c-a06c-ae6525c91533.png)

# How to run
Currently, the installation of this application is very simple.
  - First fork / clone the repo to your local Java IDE (Preferrably Intellij)
  - Once you have access to all the files you can then simply run (main) the server first (This is done since client will terminate without a server connection available)
  - Then run the main method of the client file
  - You can now check your terminal at the bottom of your screen, you should see two terminals (Server, Client)
  - Once this is established you can simply type messages back and forth to Server & Client (String "Over" ends the process)

# How to contribute
Follow this project board to know the latest status of the project: [http://...]([http://...])  

### How to build
- Use this github repository: ... 
- Specify what branch to use for a more stable release or for cutting edge development.  
- Use InteliJ 11
- Specify additional library to download if needed 
- What file and target to compile and run. 
- What is expected to happen when the app start. 

#UML Diagram


###Description
This is the UML class diagram for this project. The chat software is focused mainly in the chat controller. 
Chat controller is what allows the entire project to run. It uses chat view and chat model in an association model that is one to one. 
Chat view sets up the front end user interface through building a Graphic User Interface with Java Swing and is called once for every call of chat controller.
Chat view is also associated with Message in a 1 to 1 relationship. Chat view calls message to ensure records of the message, the date, and time are kept as well as allows messages to be set and edited in the front end.  
ChatController also has a nested private class called sendButtonListener that listens for when the user hits the send button and then is able to move messages from server to client and vice versa. sendButtonListener implements the class ActionListener to do this. 
Meanwhile, chat model conducts the back end connections between server and client and works to send messages from one to the other. 
Chat Model has its own nested private class called Client Handler that implements runnable to create threads that read and write the messages for chat model. 
Chat Model is also called in Server which is a class for setting up the sockets and network programming necessary for this project. Server has a mutual association with Client as they use each other.  
Lastly, we have one other class called MessageRoom that will be integrated into the main network soon. This class will set up group chats so that multiple clients and servers can talk to each other at once. 
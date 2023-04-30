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

![openchat_Demo](https://user-images.githubusercontent.com/89660661/234086779-7507e53f-9727-406b-a969-7b6deb00c280.png)


# How to run
Currently, the installation of this application is very simple.
  - First download the .jar file located within the main branch
  - If you are on windows you must also download the .bat file and run first
  - You must have the latest version of Java installed within your PC
 - Java Windows Download - https://www.java.com/download/ie_manual.jsp
 - Java Mac Download - https://www.java.com/en/download/apple.jsp
 - Once run it will pop-up with three windows that you can use to communicate between different clients
  

# How to contribute
Follow this project board to know the latest status of the project: [http://...]([http://...])  

### How to build
- Clone this github repository: ... 
- Use the main branch as it is the most up-to-date  
- Use InteliJ 11
- Specify additional library to download if needed 
- Set your JDK to run on ChatController as this is the main function
- When running the default is set to 3 chat windows, you can change this within ChatController, Simply move the windows to see all of them 

# UML Diagram

![OpenChatUML drawio](https://user-images.githubusercontent.com/60800202/234344468-51158945-34f7-4944-a64c-59ecf69d4d48.png)

### Description
This is the UML class diagram for this project. The chat software begins in the chat controller with the main class kept there. 
Chat controller is what allows the entire project to run. It uses chat model in an association model that is one to one. 
Chat model is then associated with ChatView, Client and Server, and contains an inner class called SendButtonListener. SendButtonListener listens in the front end for when the user hits the send button and then is able to move messages from client 1 to client 2 for example. sendButtonListener implements the class ActionListener to do this. Chat View is used to up the front end user interface through building a Graphic User Interface with Java Swing and JFrame. Each time a client is created, a new ChatView view object is created. This ensures each client receives their own messaging window.
Chat view is also associated with Message in a 1 to 1 relationship. Chat View calls message to ensure records of the message, the date, and time are kept as well as allows messages to be set and edited in the front end. ChatController also has a nested private class called. Similarly, Client and Serve both use message in the back end to send text and dtaes across sockets. Each client can have 0 to infiinite messages and it's the same for server.
Within the Client class, clients are set up with their information and each assigned a thread to manage connections. Server handles the connections and uses message to gather texts and dates and send them to the correct clients that are passed in through chatModel arrays and chatView recipientID.  These classes are not connected because they handle their communication and data through ChatModel.  

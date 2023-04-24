import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class testClass {

    @Test
    void testStartServer() throws IOException {
        ChatModel model = new ChatModel(1234, 2);
        model.startServer();
        assertTrue(true); // this test just verifies that the server can start without throwing an exception
    }
    @Test
    void testStartClient() throws IOException {
        ChatModel model = new ChatModel(1234, 2);
        model.startServer();
        model.startClient();
        // Add assertions here to verify that the clients were actually created and started
    }

    @Test
    void testSendButtonListener() throws IOException {
        ChatModel model = new ChatModel(1234, 2);
        ChatView view = new ChatView(1, new String[]{"Client 2"});
        model.new SendButtonListener(view).actionPerformed(null);
        // Add assertions here to verify that the message was sent correctly
    }
    /*public void MultiClientConnection() throws InterruptedException{
        Server thread = new Server(25565);
        thread.start();

        TimeUnit.SECONDS.sleep(3);


        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    Client client1 = new Client("localhost", 25565);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Client client2 = new Client("localhost", 25565);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                try {
                    Client client3 = new Client("localhost", 25565);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        t3.start();

        TimeUnit.SECONDS.sleep(2);
        assertTrue(Server.clientCount >=3);

    }


    @Test
    public void ValidServerConnection() throws InterruptedException{
        Server thread = new Server(25565);
        thread.start();

        TimeUnit.SECONDS.sleep(2);
        assertTrue(Server.ServerOpen);

    }

    @Test
    public void SingleClientConnection() throws InterruptedException{
        Server thread = new Server(25565);
        thread.start();

        TimeUnit.SECONDS.sleep(3);


        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    Client client1 = new Client("localhost", 25565);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(2);
       assertEquals(1,Server.clientCount);

    }

    @Test
    public void ClientRecievesUpdatedList() throws InterruptedException {
        Server thread = new Server(25565);
        thread.start();

        TimeUnit.SECONDS.sleep(3);

        Client[] client1 = null;// = new Client[1];
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    client1[0] = new Client("localhost", 25565);

                    assertTrue( client1[0].currentClients.size()>0);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(3);

    }
    @Test
    public void ConnectionEstablished() throws InterruptedException {
        Server thread = new Server(25565);
        thread.start();

        TimeUnit.SECONDS.sleep(3);


        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    Client client1 = new Client("localhost", 25565);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Client client2 = new Client("localhost", 25565);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                try {
                    Client client3 = new Client("localhost", 25565);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        t3.start();

    }*/



}
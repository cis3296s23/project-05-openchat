import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class testClass {

    @Test
<<<<<<< HEAD
    public void MultilientConnection() throws InterruptedException{
=======
    public void remoteConnect() throws InterruptedException{
       // Client client1 = new Client("127.0.0.1", 5000);
        TimeUnit.SECONDS.sleep(5);

    }
    @Test
    public void createConnect() throws InterruptedException, IOException, ClassNotFoundException {
>>>>>>> 4c4b4df4f461a15af05e0d03fe29edeefa1965b9
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

<<<<<<< HEAD
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

=======
>>>>>>> 4c4b4df4f461a15af05e0d03fe29edeefa1965b9

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

<<<<<<< HEAD
        TimeUnit.SECONDS.sleep(1);
=======
        //TimeUnit.SECONDS.sleep(1);
>>>>>>> 4c4b4df4f461a15af05e0d03fe29edeefa1965b9
       // Client client2 = new Client("127.0.0.1", 5000);



<<<<<<< HEAD
=======
        while(true){
            //await input
        }
>>>>>>> 4c4b4df4f461a15af05e0d03fe29edeefa1965b9
    }



}
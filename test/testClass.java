import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class testClass {



    @Test
    public void remoteConnect() throws InterruptedException{
       // Client client1 = new Client("127.0.0.1", 5000);
        TimeUnit.SECONDS.sleep(5);

    }
    @Test
    public void createConnect() throws InterruptedException, IOException, ClassNotFoundException {
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

        //TimeUnit.SECONDS.sleep(1);
       // Client client2 = new Client("127.0.0.1", 5000);



        while(true){
            //await input
        }
    }



}
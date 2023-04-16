import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class testClass {



    @Test
    public void remoteConnect() throws InterruptedException{
        Client client1 = new Client("127.0.0.1", 5000);
        TimeUnit.SECONDS.sleep(5);

    }
    @Test
    public void createConnect()throws InterruptedException{
        Server thread = new Server(25565);
        thread.start();

        TimeUnit.SECONDS.sleep(3);

        Client client1 = new Client("localhost", 25565);
        int[] ar = {1,2};
        client1.requestGroup(ar);
        //TimeUnit.SECONDS.sleep(1);
       // Client client2 = new Client("127.0.0.1", 5000);



        while(thread.ServerOpen){
            //await input
        }
    }



}
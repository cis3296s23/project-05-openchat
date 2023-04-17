import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class SerializableSocketAddress implements Serializable {
    private String hostname;
    private int port;
    public String userID;

    public SerializableSocketAddress(InetSocketAddress address,String userID) {
        this.hostname = address.getAddress().getHostAddress();
        this.port = address.getPort();
        this.userID= userID;
    }

    public InetSocketAddress toInetSocketAddress() {
        return new InetSocketAddress(hostname, port);
    }
}
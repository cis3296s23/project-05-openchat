import java.io.Serializable;

public class ShutdownSignal implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private boolean isShutdown;


    public ShutdownSignal(String message, boolean isShutdown) {
        this.message = message;
        this.isShutdown = isShutdown;
    }

    public String getMessage() {
        return message;
    }

    public boolean isShutdown() {
        return isShutdown;
    }
}

package export;

/**
 * Theis exceptions is thrown if the user enter a wrong input type
 */
public class WronginputException extends Exception {
    public WronginputException(String message) {
        super(message);
    }

    public WronginputException(Throwable cause) {
        super(cause);
    }
}

package export;

/**
 * Theis exceptions is thrown if the user enter a wrong input type
 */
public class WrongInputException extends Exception {
    public WrongInputException(String message) {
        super(message);
    }

    public WrongInputException(Throwable cause) {
        super(cause);
    }
}

package export;

/**
 * The Exception is thrown when there is a problem intializing the game
 */
public class GameInitialziationException extends Exception {
    public GameInitialziationException(String message) {
        super(message);
    }

    public GameInitialziationException(Throwable cause) {
        super(cause);
    }
}

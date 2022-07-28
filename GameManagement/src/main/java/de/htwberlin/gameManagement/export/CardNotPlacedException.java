package de.htwberlin.gameManagement.export;

/**
 * The exception is thrown if something with the card placement went wwrong
 */
public class CardNotPlacedException extends Exception{

    public CardNotPlacedException(String message) {
        super(message);
    }

    public CardNotPlacedException(Throwable cause) {
        super(cause);
    }
}

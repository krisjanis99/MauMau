package de.htwberlin.gameManagement.export;

/**
 * The exception is thrown if something with the card placement went wwrong
 */
public class CardNotplaced extends Exception{

    public CardNotplaced(String message) {
        super(message);
    }

    public CardNotplaced(Throwable cause) {
        super(cause);
    }
}

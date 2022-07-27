package de.htwberlin.rulesetManagement.export;

/**
 * The excption is thrown if a technical error happens
 */
public class GameErrorTech extends Exception{

    public GameErrorTech(String message) {
        super(message);
    }

    public GameErrorTech(Throwable cause) {
        super(cause);
    }
}

package de.htwberlin.rulesetManagement.export;

/**
 * The excption is thrown if a technical error happens
 */
public class GameTechnicalErrorException extends Exception {

    public GameTechnicalErrorException(String message) {
        super(message);
    }

    public GameTechnicalErrorException(Throwable cause) {
        super(cause);
    }
}

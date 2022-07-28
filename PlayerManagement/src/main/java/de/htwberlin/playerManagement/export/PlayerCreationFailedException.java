package de.htwberlin.playerManagement.export;

public class PlayerCreationFailedException extends Exception{

    public PlayerCreationFailedException(String message) {
        super(message);
    }

    public PlayerCreationFailedException(Throwable throwable) {
        super(throwable);
    }

}

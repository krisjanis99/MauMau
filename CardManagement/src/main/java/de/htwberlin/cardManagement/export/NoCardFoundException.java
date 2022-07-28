package de.htwberlin.cardManagement.export;


/**
 * the exception is thrown if a a deck is empty  and we wanzted to access a card from it
 */
public class NoCardFoundException extends IndexOutOfBoundsException  {
    public NoCardFoundException(String message ){super(message);}

}

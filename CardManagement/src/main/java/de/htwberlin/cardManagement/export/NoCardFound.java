package de.htwberlin.cardManagement.export;


/**
 * the exception is thrown if a a deck is empty  and we wanzted to access a card from it
 */
public class NoCardFound extends IndexOutOfBoundsException  {
    public NoCardFound(String message ){super(message);}

}

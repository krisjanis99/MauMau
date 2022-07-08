package de.htwberlin.cardManagement.export;

/**
 * The interface Card service.
 */
public interface CardService {

    /**
     * Gets card as a string.
     *
     * @param card the card
     * @return the card as string
     */
    String getCardAsString(Card card);
}

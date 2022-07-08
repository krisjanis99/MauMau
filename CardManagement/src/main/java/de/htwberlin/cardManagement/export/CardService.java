package de.htwberlin.cardManagement.export;

import de.htwberlin.cardManagement.entity.Card;

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

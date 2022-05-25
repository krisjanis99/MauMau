package de.htwberlin.cardManagement.export;

import de.htwberlin.cardManagement.export.Card;

/**
 * The interface Card service.
 */
public interface CardService {

    /**
     * Gets card as string.
     *
     * @param card the card
     * @return the card as string
     */
    String getCardAsString(Card card);

}

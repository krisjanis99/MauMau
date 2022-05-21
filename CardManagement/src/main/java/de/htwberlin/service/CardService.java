package de.htwberlin.service;

import de.htwberlin.entity.Card;

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

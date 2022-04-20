package de.htwberlin.service;

import de.htwberlin.entity.Card;
import de.htwberlin.entity.GameRule;

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

    /**
     * Check if card has a game action.
     *
     * @param card the card
     * @return the game rule for the card
     */
    GameRule CheckIfCardHasGameRule(Card card);

}

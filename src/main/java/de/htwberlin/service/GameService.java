package de.htwberlin.service;

import de.htwberlin.entity.Card;
import de.htwberlin.entity.Game;

/**
 * The interface for Game service.
 */
public interface GameService {

    /**
     * Starts a new game.
     *
     * @return configured game
     */
    Game startNewGame();

    /**
     * Place card on the game deck.
     *
     * @param card the card
     * @return boolean which shows if the card placement was successful
     */
    Boolean placeCard(Card card);

}

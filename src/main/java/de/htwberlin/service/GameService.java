package de.htwberlin.service;

import de.htwberlin.entity.Card;
import de.htwberlin.entity.Game;

/**
 * The interface Game service.
 */
public interface GameService {

    /**
     * Start new game game.
     *
     * @return the game
     */
    Game startNewGame();

    /**
     * Place card boolean.
     *
     * @param card the card
     * @return the boolean
     */
    Boolean placeCard(Card card);

}

package de.htwberlin.service;

import de.htwberlin.entity.Card;
import de.htwberlin.entity.Game;
import de.htwberlin.entity.Player;

import java.util.List;

/**
 * The interface for Game service.
 */
public interface GameService {

    /**
     * Starts and configures a new game.
     *
     * @param players the players which play the game
     * @return a new configured game
     */
    Game startNewGame(List<Player> players);

    /**
     * Place card in the ongoing game.
     *
     * @param game the ongoing the game
     * @param card the card to be placed
     * @return the game with a placed card
     */
    Game placeCard(Game game, Card card);

    /**
     * Take top card off hidden deck in the game.
     *
     * @param game the ongoing game
     * @return changed game
     */
    Game takeTopCardOffDeck(Game game);


}

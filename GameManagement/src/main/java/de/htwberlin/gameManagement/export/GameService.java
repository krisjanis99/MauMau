package de.htwberlin.gameManagement.export;

import de.htwberlin.cardManagement.entity.Card;
import de.htwberlin.gameManagement.entity.Game;
import de.htwberlin.playerManagement.entity.Player;

import java.util.List;
import java.util.Optional;

/**
 * The interface for creating and changing the Game object.
 */
public interface GameService {

    /**
     * Starts and configures a new game.
     *
     * @param players the players which play the game
     * @return a new configured game
     */
    Optional<Game> startNewGame(List<Player> players);

    /**
     * Place card in the ongoing game.
     *
     * @param game the ongoing the game
     * @param card the card to be placed
     * @return the game with a placed card
     */
    Game placeCard(Game game, Card card) throws CardNotPlacedException;

    /**
     * Take top card off hidden deck in the game and gives it to the current active Player.
     *
     * @param game the ongoing game
     * @return changed game
     */
    Game takeTopCardOffDeck(Game game);

    /**
     * Changes the current player to the next one in the list.
     *
     * @param game the ongoing game
     * @return changed game
     */
    Game switchToNextPlayer(Game game);

}

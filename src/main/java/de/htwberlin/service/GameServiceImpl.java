package de.htwberlin.service;


import de.htwberlin.entity.Card;
import de.htwberlin.entity.Game;
import de.htwberlin.entity.Player;
import lombok.Getter;

import java.util.List;

@Getter
public class GameServiceImpl implements GameService {


    /**
     * Starts and configures a new game.
     *
     * @param players the players which play the game
     * @return a new configured game
     */
    @Override
    public Game startNewGame(List<Player> players) {
        return null;
    }


    /**
     * Place card in the ongoing game.
     *
     * @param game the ongoing the game
     * @param card the card to be placed
     * @return the game with a placed card
     */
    @Override
    public Game placeCard(Game game, Card card) {
        return null;
    }


    /**
     * Take top card off hidden deck in the game.
     *
     * @param game the ongoing game
     * @return changed game
     */
    @Override
    public Game takeTopCardOffDeck(Game game) {
        return null;
    }
}

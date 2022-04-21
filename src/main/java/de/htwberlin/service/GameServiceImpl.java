package de.htwberlin.service;


import de.htwberlin.entity.Game;
import de.htwberlin.entity.Player;
import lombok.Getter;

import java.util.List;

@Getter
public class GameServiceImpl implements GameService{


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
     * Place card game.
     *
     * @param game the ongoing the game
     * @return the game
     */
    @Override
    public Game placeCard(Game game) {
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

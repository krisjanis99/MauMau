package de.htwberlin.gameManagement.impl;


import com.google.inject.Inject;
import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.gameManagement.export.Game;
import de.htwberlin.gameManagement.export.GameService;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class GameServiceImpl implements GameService {

    @Inject
    GameRuleService gameRuleService;


    /**
     * Starts and configures a new game.
     *
     * @param players     the players which play the game
     * @param gameRuleSet the game rule set for the game
     *                    *0 - classic rules
     *                    *1 - additional rules
     * @return a new configured game
     */
    @Override
    public Optional<Game> startNewGame(List<Player> players, int gameRuleSet) {
        gameRuleService.getGameRuleSet(gameRuleSet);
        return Optional.empty();
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

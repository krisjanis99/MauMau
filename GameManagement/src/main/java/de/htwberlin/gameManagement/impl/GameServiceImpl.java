package de.htwberlin.gameManagement.impl;


import com.google.inject.Inject;
import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.export.CardDeckService;
import de.htwberlin.gameManagement.export.Game;
import de.htwberlin.gameManagement.export.GameService;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Getter
public class GameServiceImpl implements GameService {
    private static final Logger logger = LogManager.getLogger(GameServiceImpl.class);
    @Inject
    GameRuleService gameRuleService;

    @Inject
    CardDeckService cardDeckService;


    /**
     * Starts and configures a new game.
     *
     * @param players the players which play the game
     * @return a new configured game
     */
    @Override
    public Optional<Game> startNewGame(List<Player> players) {

        List<Card> gameCards = cardDeckService.getNewDeck();
        gameCards = cardDeckService.shuffleDeck(gameCards);

        //every player gets 6 cards
        for (int i = 0; i < players.size(); i++) {
            List<Card> playerCards = gameCards.subList(0, 5);
            gameCards.removeAll(playerCards);
            players.get(i).setPlayerCards(playerCards);
        }
        logger.info("every player was assigned 6 cars");

        Game game = new Game(players);
        int factor = 100;
        int i = (int) (Math.random() * factor);
        int randomPlayerIndex = i * players.size();
        game.setCurrentActivePlayer(players.get(randomPlayerIndex));
        game.setCardDeck(gameCards);
        game.setCurrentDirectionIsClockwise(true);
        game.setCardsToDraw(0);
        game.setGameEnded(false);
        logger.info("game is ready");

        return Optional.of(game);
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
        List<Card> gameCards = game.getPlacedCardDeck();
        gameCards.add(card);
        game.setPlacedCardDeck(gameCards);
        logger.info("card was placed ");
        return game;
    }


    /**
     * Take top card off hidden deck in the game and
     * gives it to the current active player.
     *
     * @param game the ongoing game
     * @return changed game
     */
    @Override
    public Game takeTopCardOffDeck(Game game) {
        List<Card> cards = game.getCardDeck();
        Card drawedCard = cards.get(cards.size() - 1);
        logger.info(" card to be drawn found ");
        cards.remove(drawedCard);
        logger.info(" card was taken ");
        game.setCardDeck(cards);
        List<Card> playerCards = game.getCurrentActivePlayer().getPlayerCards();
        playerCards.add(drawedCard);
        logger.info("card wax added to the player cards ");
        game.getCurrentActivePlayer().setPlayerCards(playerCards);
        return game;
    }

}

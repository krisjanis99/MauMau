package de.htwberlin.gameManagement.impl;


import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.export.CardDeckService;
import de.htwberlin.gameManagement.export.Game;
import de.htwberlin.gameManagement.export.GameService;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.picocontainer.annotations.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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

        try {
            for (Player player : players) {
                List<Card> playerCards = new ArrayList<>(gameCards.subList(0, 5));
                player.setPlayerCards(playerCards);
                gameCards.removeAll(playerCards);
            }

            logger.info("every player was assigned 6 cards");
            Game game = new Game(players, gameCards);
            logger.info("game is ready");
            return Optional.of(game);
        } catch (NullPointerException e){
            logger.error(String.format("Error while creating a new game: %s", e.getMessage()));
        }
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
        if (!cards.isEmpty()){
            Card drawnCard = cards.get(cards.size() - 1);
            logger.info(" card to be drawn found ");
            cards.remove(drawnCard);
            logger.info(" card was taken ");
            game.setCardDeck(cards);
            List<Card> playerCards = game.getCurrentActivePlayer().getPlayerCards();
            playerCards.add(drawnCard);
            logger.info("card wax added to the player cards ");
            game.getCurrentActivePlayer().setPlayerCards(playerCards);
            return game;
        }
        logger.info("No cards left in current Deck. Game is returned unchanged");
        return game;
    }

}

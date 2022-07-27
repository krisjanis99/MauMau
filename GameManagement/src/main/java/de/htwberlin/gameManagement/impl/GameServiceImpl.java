package de.htwberlin.gameManagement.impl;


import de.htwberlin.gameManagement.export.CardNotplaced;
import de.htwberlin.playerManagement.entity.Player;
import de.htwberlin.cardManagement.entity.Card;
import de.htwberlin.cardManagement.export.CardDeckService;
import de.htwberlin.gameManagement.entity.Game;
import de.htwberlin.gameManagement.export.GameService;
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
    CardDeckService cardDeckService;

    /**
     * Starts and configures a new game.
     *
     * @param players the players which play the game
     * @return a new configured game
     */
    @Override
    public Optional<Game> startNewGame(List<Player> players) {

        List<Card> gameCards = new ArrayList<>(cardDeckService.getNewDeck());
        gameCards = cardDeckService.shuffleDeck(gameCards);

        try {
            for (Player player : players) {
                List<Card> playerCards = new ArrayList<>(gameCards.subList(0, 5));
                player.setPlayerCards(playerCards);
                gameCards = gameCards.subList(5, gameCards.size());
            }

            logger.info("every player was assigned 5 cards");
            Game game = new Game(players, gameCards);
            logger.info("game is ready");

            placeCard(game, gameCards.get(gameCards.size() - 1));
            game.setCardDeck(gameCards);
            logger.info("First card was placed");

            return Optional.of(game);
        } catch (NullPointerException | CardNotplaced e) {
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
    public Game placeCard(Game game, Card card) throws CardNotplaced {
        if(game==null || card==null){
            throw new CardNotplaced("Arguments are null, please check your game or card valuse");
        }
        List<Card> gameCards = new ArrayList<>(game.getPlacedCardDeck());
        gameCards.add(card);
        game.setPlacedCardDeck(gameCards);
        game.setCurrentSymbol(card.getSymbol());
        game.setCurrentRank(card.getRank());
        logger.info("card was placed ");
        List<Card> playerCards = game.getCurrentActivePlayer().getPlayerCards();
        playerCards.remove(card);
        game.getCurrentActivePlayer().setPlayerCards(playerCards);
        logger.info("The card was removed from a player if he placed it.");
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

        List<Card> cards = List.copyOf(game.getCardDeck());

        if (cards.isEmpty()){
            logger.info("No cards left in current Deck.  A new Deck will be added.");
            cards = cardDeckService.shuffleDeck(cardDeckService.getNewDeck());
        }

        Card drawnCard = cards.get(cards.size() - 1);
        logger.info(" card to be drawn found ");
        cards = cards.subList(0, cards.size() - 1);
        logger.info(" card was taken ");
        game.setCardDeck(cards);
        List<Card> playerCards = game.getCurrentActivePlayer().getPlayerCards();
        playerCards.add(drawnCard);
        logger.info("card wax added to the player cards ");
        game.getCurrentActivePlayer().setPlayerCards(playerCards);
        return game;
    }

    /**
     * Changes the current player to the next one in the list.
     *
     * @param game the ongoing game
     * @return changed game
     */
    @Override
    public Game switchToNextPlayer(Game game) {
        int currentPlayerIndex = game.getPlayerList().indexOf(game.getCurrentActivePlayer());
        if (game.getCurrentDirectionIsClockwise()) {
            if (currentPlayerIndex == game.getPlayerList().size() - 1) {
                game.setCurrentActivePlayer(game.getPlayerList().get(0));
            } else {
                game.setCurrentActivePlayer(game.getPlayerList().get(currentPlayerIndex + 1));
            }
        } else {
            if (currentPlayerIndex == 0) {
                game.setCurrentActivePlayer(game.getPlayerList().get(game.getPlayerList().size() - 1));
            } else {
                game.setCurrentActivePlayer(game.getPlayerList().get(currentPlayerIndex - 1));
            }
        }
        return game;
    }

}

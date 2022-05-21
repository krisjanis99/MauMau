package de.htwberlin.service;

import de.htwberlin.entity.Card;
import de.htwberlin.entity.Game;
import de.htwberlin.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    /**
     * check if a card can be placed on the the placed card deck
     *
     * @param card           the card to be placed
     * @param placedCardDeck the deck on which the card to be placed
     * @return ture if the car can be placed on the placerdCardDeck, other than that then false
     */
    boolean cardPlaceable(Card card, List<Card> placedCardDeck);

    /**
     * Check if card has a game action.
     *
     * @param card the card
     * @param ruleset the imported Ruleset used in game
     * @return the game rule for the card
     */
    Optional<String> checkIfCardHasGameRule(Map<Card.Rank, String> ruleset, Card card);

}

package de.htwberlin.gameManagement.export;

import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.entity.Player;

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
     * @param gameRuleSet the game rule set for the game
     *      *0 - classic rules
     *      *1 - additional rules
     * @return a new configured game
     */
    Optional<Game> startNewGame(List<Player> players, int gameRuleSet);

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
     * @param gameRuleSet    the used game rule set
     * @return true if the card can be placed on the CardDeck, other than that then false
     */
    boolean cardPlaceable(Card card, List<Card> placedCardDeck, Map<Card.Rank, String> gameRuleSet);

    /**
     * Check if card has a game action.
     *
     * @param card the card
     * @param ruleset the imported Ruleset used in game
     * @return the game rule for the card
     */
    Optional<String> checkIfCardHasGameRule(Map<Card.Rank, String> ruleset, Card card);

}

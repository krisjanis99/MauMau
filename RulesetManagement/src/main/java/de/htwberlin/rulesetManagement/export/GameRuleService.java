package de.htwberlin.rulesetManagement.export;

import de.htwberlin.cardManagement.export.Card;

import java.util.Map;
import java.util.Optional;

/**
 * The interface Game rule service.
 */
public interface GameRuleService {

    /**
     * Gets the current used game rule set.
     *
     * @return the active game rule set
     */
    Map<Card.Rank, String> getActiveGameRuleset();

    /**
     * Checks if a Card is placeable in the game.
     *
     * @param card   the card
     * @param rank   the current game rank
     * @param symbol the current game symbol
     * @return the boolean which says if the card can be placed
     */
    public boolean cardPlaceable(Card card, Card.Symbol symbol, Card.Rank rank);

    /**
     * Check if card has a game action.
     *
     * @param card the card
     * @return the game rule for the card
     */
    Optional<String> checkIfCardHasGameRule(Card card);

}

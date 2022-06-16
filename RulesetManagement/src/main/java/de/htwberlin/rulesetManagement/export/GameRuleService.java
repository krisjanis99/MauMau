package de.htwberlin.rulesetManagement.export;

import de.htwberlin.cardManagement.export.Card;

import java.util.List;
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
     * @param card          the card
     * @param currentSymbol the current symbol
     * @param currentRank   the current rank
     * @return the boolean which says if the card can be placed
     */
    boolean cardPlaceable(Card card, Card.Symbol currentSymbol, Card.Rank currentRank);

    /**
     * Check if card has a game action.
     *
     * @param card the card
     * @return the game rule for the card
     */
    Optional<String> checkIfCardHasGameRule(Card card);

}

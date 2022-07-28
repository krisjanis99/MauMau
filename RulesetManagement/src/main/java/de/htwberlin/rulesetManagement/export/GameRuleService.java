package de.htwberlin.rulesetManagement.export;

import de.htwberlin.cardManagement.entity.Card;

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
     * @param symbol the current game symbol
     * @param rank   the current game rank
     * @return the boolean which says if the card can be placed
     * @throws GameTechnicalErrorException thrown if a technical error happens
     */
    boolean cardPlaceable(Card card, Card.Symbol symbol, Card.Rank rank) throws GameTechnicalErrorException;

    /**
     * Check if card has a game action.
     *
     * @param card the card
     * @return the game rule for the card
     * @throws GameTechnicalErrorException thrown if a technical error happens
     */
    Optional<String> checkIfCardHasGameRule(Card card) throws GameTechnicalErrorException;

}

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
     * Gets the requested game rule set.
     *
     * @param gameRuleSet the game rule set. The selection happens through the int:
     *                    0 - classic rules
     *                    1 - additional rules
     * @return the chosen game rule set
     */
    Optional<Map<Card.Rank, String>> getGameRuleSet(int gameRuleSet);


    /**
     * check if a card can be placed on the placed card deck
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
     * @param card    the card
     * @return the game rule for the card
     */
    Optional<String> checkIfCardHasGameRule(Card card);

}

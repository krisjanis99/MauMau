package de.htwberlin.rulesetManagement.impl;

import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.rulesetManagement.export.GameRuleService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class GameRuleServiceImpl implements GameRuleService {

    private final Map<Card.Rank, String> CLASSIC_RULESET = Map.ofEntries(
            entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS"),
            entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT"),
            entry(Card.Rank.JACK, "WISH_NEW_SYMBOL")
    );

    private final Map<Card.Rank, String> ADDITIONAL_RULESET = Map.ofEntries(
            entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS"),
            entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT"),
            entry(Card.Rank.NINE, "CHANGE_DIRECTION"),
            entry(Card.Rank.TEN, "JOKER"),
            entry(Card.Rank.JACK, "WISH_NEW_SYMBOL")
    );

    /**
     * Gets game rule set.
     *
     * @param gameRuleSet the game rule set
     *                    0 - classic rules
     *                    1 - additional rules
     * @return the chosen game rule set
     */
    @Override
    public Optional<Map<Card.Rank, String>> getGameRuleSet(int gameRuleSet) {
        if (gameRuleSet == 0) {
            return Optional.of(CLASSIC_RULESET);
        } else if (gameRuleSet == 1) {
            return Optional.of(ADDITIONAL_RULESET);
        }
        return Optional.empty();
    }

    /**
     * check if a card can be placed on the placed card deck
     *
     * @param card           the card to be placed
     * @param placedCardDeck the deck on which the card to be placed
     * @param gameRuleSet    the used game rule set
     * @return true if the card can be placed on the CardDeck, other than that then false
     */
    @Override
    public boolean cardPlaceable(Card card, List<Card> placedCardDeck, Map<Card.Rank, String> gameRuleSet) {
        return false;
    }

    /**
     * Check if card has a game action.
     *
     * @param card    the card
     * @return the game rule for the card
     */
    @Override
    public Optional<String> checkIfCardHasGameRule(Card card) {
        return Optional.empty();
    }
}

package de.htwberlin.rulesetManagement.impl;

import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.rulesetManagement.export.GameRuleService;

import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class GameRuleServiceImpl implements GameRuleService {

    private final Map<Card.Rank, String> classicRuleset = Map.ofEntries(
            entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS"),
            entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT"),
            entry(Card.Rank.JACK, "WISH_NEW_SYMBOL")
    );

    private final Map<Card.Rank, String> additionalRuleset = Map.ofEntries(
            entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS"),
            entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT"),
            entry(Card.Rank.NINE, "CHANGE_DIRECTION"),
            entry(Card.Rank.TEN, "JOKER"),
            entry(Card.Rank.JACK, "WISH_NEW_SYMBOL")
    );

    private final Map<Card.Rank, String> activeGameRuleset = classicRuleset;

    /**
     * Gets the current used game rule set.
     *
     * @return the active game rule set
     */
    @Override
    public Map<Card.Rank, String> getActiveGameRuleset() {
        return activeGameRuleset;
    }

    /**
     * Checks if a Card is placeable in the game.
     *
     * @param card          the card
     * @param currentSymbol the current symbol
     * @param currentRank   the current rank
     * @return the boolean which says if the card can be placed
     */
    @Override
    public boolean cardPlaceable(Card card, Card.Symbol currentSymbol, Card.Rank currentRank) {
        if (currentRank == card.getRank() || currentSymbol == card.getSymbol()){
            return true;
        }
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
        if (activeGameRuleset.containsKey(card.getRank())){
            return Optional.of(activeGameRuleset.get(card.getRank()));
        }
        return Optional.empty();
    }
}

package de.htwberlin.rulesetManagement.impl;

import de.htwberlin.cardManagement.entity.Card;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class GameRuleServiceImpl implements GameRuleService {
    private static final Logger logger = LogManager.getLogger(GameRuleServiceImpl.class);


    private final Map<Card.Rank, String> activeGameRuleset = Map.ofEntries(
            entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS"),
            entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT"),
            entry(Card.Rank.NINE, "CHANGE_DIRECTION"),
            entry(Card.Rank.TEN, "JOKER"),
            entry(Card.Rank.JACK, "WISH_NEW_SYMBOL")
    );

    /**
     * Gets the active game rule set.
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
     * @param card   the card
     * @param rank   the current game rank
     * @param symbol the current game symbol
     * @return the boolean which says if the card can be placed
     */
    @Override
    public boolean cardPlaceable(Card card, Card.Symbol symbol, Card.Rank rank) {
        logger.info("checking if the card can be placed ");
        if (rank == card.getRank() || symbol == card.getSymbol()) {
            return true;
        }
        return false;
    }

    /**
     * Check if card has a game action.
     *
     * @param card the card
     * @return the game rule for the card
     */
    @Override
    public Optional<String> checkIfCardHasGameRule(Card card) {
        if (activeGameRuleset.containsKey(card.getRank())) {
            logger.info("card was found to have a rank");
            return Optional.of(activeGameRuleset.get(card.getRank()));
        }
        logger.info("no rank found");
        return Optional.empty();
    }
}

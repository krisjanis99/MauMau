package de.htwberlin.rulesetManagement.impl;

import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class GameRuleServiceImpl implements GameRuleService {
    private static final Logger logger = LogManager.getLogger(GameRuleServiceImpl.class);


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
     * @param card  the card
     * @param cards list of card top be added on
     * @return the boolean which says if the card can be placed
     */
    @Override
    public boolean cardPlaceable(Card card, List<Card> cards) {
        logger.info("getting the top card ");
        Card topCard = cards.get(cards.size() - 1);
        logger.info("determining the symbol and rank of top card ");
        Card.Symbol currentSymbol = topCard.getSymbol();
        Card.Rank currentRank = topCard.getRank();
        logger.info("checking if the card can be placed ");
        if (currentRank == card.getRank() || currentSymbol == card.getSymbol()) {
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

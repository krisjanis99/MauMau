package de.htwberlin.service;

import de.htwberlin.entity.Card;

import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class GameRuleServiceImpl implements GameRuleService {

    private final Map<Card.Rank, String> CLASSIC_RULESET = Map.ofEntries(
            entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS;"),
            entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT;"),
            entry(Card.Rank.JACK, "WISH_NEW_SYMBOL;")
    );

    private final Map<Card.Rank, String> ADDITIONAL_RULESET = Map.ofEntries(
            entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS;"),
            entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT;"),
            entry(Card.Rank.NINE, "CHANGE_DIRECTION;"),
            entry(Card.Rank.TEN, "JOKER;"),
            entry(Card.Rank.JACK, "WISH_NEW_SYMBOL;")
    );

    /**
     * Gets game rule set.
     *
     * @param gameRuleSet the game rule set
     *0 - classic rules
     *1 - additional rules
     * @return the chosen game rule set
     */
    @Override
    public Optional<Map<Card.Rank, String>> getGameRuleSet(int gameRuleSet) {
        if(gameRuleSet == 0){
            return Optional.of(CLASSIC_RULESET);
        }
        else if(gameRuleSet == 1){
            return Optional.of(ADDITIONAL_RULESET);
        }
        return Optional.empty();
    }
}

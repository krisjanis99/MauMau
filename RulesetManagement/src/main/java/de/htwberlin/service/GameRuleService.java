package de.htwberlin.service;

import de.htwberlin.entity.Card;

import java.util.Map;
import java.util.Optional;

/**
 * The interface Game rule service.
 */
public interface GameRuleService {

    /**
     * Gets game rule set.
     *
     * @param gameRuleSet the game rule set
     * 0 - classic rules
     * 1 - additional rules
     *
     * @return the chosen game rule set
     */
    Optional<Map<Card.Rank, String>> getGameRuleSet(int gameRuleSet);

}

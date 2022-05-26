package de.htwberlin.rulesetManagement.export;

import de.htwberlin.cardManagement.export.Card;

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

}

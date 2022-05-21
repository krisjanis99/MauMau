package de.htwberlin.service;

import de.htwberlin.entity.Card;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameServiceTest {

    private GameService gameService;

    private Map<Card.Rank, String> ruleset;

    @Before
    public void setup() {
        gameService = new GameServiceImpl();
        ruleset = Map.ofEntries(
                entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS"),
                entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT"),
                entry(Card.Rank.JACK, "WISH_NEW_SYMBOL")
        );
    }

    @Test
    public void checkIfCardHasGameRule_testNonRuleCard() {
        //given
        Card normalCard = new Card(Card.Rank.KING, Card.Symbol.CLUBS);

        //when
        Optional<String> rule = gameService.checkIfCardHasGameRule(ruleset, normalCard);

        //then
        assertTrue(rule.isEmpty());
    }

    @Test
    public void checkIfCardHasGameRule_testRuleCard() {
        //given
        Card ruleCard = new Card(Card.Rank.SEVEN, Card.Symbol.CLUBS);

        //when
        Optional<String> rule = gameService.checkIfCardHasGameRule(ruleset, ruleCard);

        //then
        assertTrue(rule.isPresent());
        assertEquals("NEXT_PLAYER_DRAWS_CARDS", rule.get());


    }


}

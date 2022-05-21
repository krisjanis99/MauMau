package de.htwberlin.service;

import de.htwberlin.entity.Card;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;
import static org.junit.Assert.*;

public class GameRuleServiceTest {

    private GameRuleService gameRuleService;


    @Before
    public void setup() {
        gameRuleService = new GameRuleServiceImpl();

    }

    @Test
    public void getGameRuleSet_getClassicRuleSet() {
        //given
        Map<Card.Rank, String> expectedResult = Map.ofEntries(
                entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS"),
                entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT"),
                entry(Card.Rank.JACK, "WISH_NEW_SYMBOL")
        );

        //when
        Optional<Map<Card.Rank, String>> result = gameRuleService.getGameRuleSet(0);

        //then
        assertTrue(result.isPresent());
        assertEquals(result.get(), expectedResult);
    }

    @Test
    public void getGameRuleSet_getAdditionalRuleSet() {
        //given
        Map<Card.Rank, String> expectedResult = Map.ofEntries(
                entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS"),
                entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT"),
                entry(Card.Rank.NINE, "CHANGE_DIRECTION"),
                entry(Card.Rank.TEN, "JOKER"),
                entry(Card.Rank.JACK, "WISH_NEW_SYMBOL")
        );

        //when
        Optional<Map<Card.Rank, String>> result = gameRuleService.getGameRuleSet(1);

        //then
        assertTrue(result.isPresent());
        assertEquals(result.get(), expectedResult);
    }

    @Test
    public void getGameRuleSet_notExistingRuleSet() {
        //given

        //when
        Optional<Map<Card.Rank, String>> result = gameRuleService.getGameRuleSet(2);

        //then
        assertFalse(result.isPresent());
    }

}

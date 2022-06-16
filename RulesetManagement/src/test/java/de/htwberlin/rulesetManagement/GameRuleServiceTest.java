package de.htwberlin.rulesetManagement;

import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.export.CardDeckService;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import de.htwberlin.rulesetManagement.impl.GameRuleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;
import static org.junit.Assert.*;

public class GameRuleServiceTest {

    private GameRuleService gameRuleService;

    @Mock
    private CardDeckService cardDeckServiceMock;


    @Before
    public void setup() {
        gameRuleService = new GameRuleServiceImpl();
    }

    Map<Card.Rank, String> getRuleSet() {
        return Map.ofEntries(
                entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS"),
                entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT"),
                entry(Card.Rank.JACK, "WISH_NEW_SYMBOL")
        );
    }

    List<Card> getFullCardDeck() {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                cards.add(new Card(Card.Rank.values()[j], Card.Symbol.values()[i]));
            }
        }

        return cards;
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


    @Test
    public void cardPlaceable_CardCanBePlaced() {
        //given
        List<Card> cards = List.of(new Card(Card.Rank.NINE, Card.Symbol.DIAMONDS));
        Card card = new Card(Card.Rank.TEN, Card.Symbol.DIAMONDS);
        Map<Card.Rank, String> gameRuleSet = getRuleSet();

        //when then
        assertTrue(gameRuleService.cardPlaceable(card, cards, gameRuleSet));
    }

    @Test
    public void cardPlaceable_CardCannotBePlaced() {
        //given
        List<Card> cards = List.of(new Card(Card.Rank.NINE, Card.Symbol.DIAMONDS));
        Card card = new Card(Card.Rank.TEN, Card.Symbol.HEARTS);
        Map<Card.Rank, String> gameRuleSet = getRuleSet();

        //when then
        assertFalse(gameRuleService.cardPlaceable(card, cards, gameRuleSet));
    }

    @Test
    public void checkIfCardHasGameRule_testNonRuleCard() {
        //given
        Map<Card.Rank, String> ruleset = getRuleSet();
        Card normalCard = new Card(Card.Rank.KING, Card.Symbol.CLUBS);

        //when
        Optional<String> rule = gameRuleService.checkIfCardHasGameRule(normalCard);

        //then
        assertTrue(rule.isEmpty());
    }

    @Test
    public void checkIfCardHasGameRule_testRuleCard() {
        //given
        Map<Card.Rank, String> ruleset = getRuleSet();
        Card ruleCard = new Card(Card.Rank.SEVEN, Card.Symbol.CLUBS);

        //when
        Optional<String> rule = gameRuleService.checkIfCardHasGameRule(ruleCard);

        //then
        assertTrue(rule.isPresent());
        assertEquals("NEXT_PLAYER_DRAWS_CARDS", rule.get());

    }

}

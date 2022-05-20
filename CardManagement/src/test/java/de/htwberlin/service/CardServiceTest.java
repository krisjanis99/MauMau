package de.htwberlin.service;

import de.htwberlin.entity.Card;
import de.htwberlin.entity.GameRule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardServiceTest {

    CardService cardService;

    @Before
    public void initializeCardService() {
        cardService = new CardServiceImpl();
    }

    @Test
    public void getCardAsString_testInitializedCard() {
        //given
        Card card = new Card(Card.Rank.ACE, Card.Symbol.DIAMONDS);

        //when
        String result = cardService.getCardAsString(card);

        //then
        assertEquals(result, "ACE DIAMONDS");
    }

    @Test
    public void getCardAsString_testNullCard() {
        //given
        Card nullCard = new Card(null, null);

        //when
        String result = cardService.getCardAsString(nullCard);

        //then
        assertTrue(!result.isEmpty());
    }

    @Test
    public void checkIfCardHasGameRule_testNonRuleCard() {
        //given
        Card normalCard = new Card(Card.Rank.KING, Card.Symbol.CLUBS);

        //when
        GameRule rule = cardService.checkIfCardHasGameRule(normalCard);

        //then
        assertEquals(GameRule.NONE, rule);
    }

    @Test
    public void checkIfCardHasGameRule_testRuleCard() {
        //given
        Card ruleCard = new Card(Card.Rank.SEVEN, Card.Symbol.CLUBS);

        //when
        GameRule rule = cardService.checkIfCardHasGameRule(ruleCard);

        //then
        assertEquals(GameRule.NEXT_PLAYER_DRAWS_CARDS, rule);


    }


}

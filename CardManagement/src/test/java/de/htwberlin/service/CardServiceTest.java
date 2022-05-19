package de.htwberlin.service;

import de.htwberlin.entity.Card;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

public class CardServiceTest {

    CardService cardService;

    @Before
    public void initializeCardService(){
        cardService = new CardServiceImpl();
    }

    @Test
    public void getCardAsString_testInitializedCard(){
        //given
        Card nullCard = new Card(Card.Rank.ACE, Card.Symbol.DIAMONDS);

        //when
        String result = cardService.getCardAsString(nullCard);

        //then
        assertEquals(result, "ACE DIAMONDS");
    }

    @Test
    public void getCardAsString_testNullCard(){
        //given
        Card nullCard = new Card(null, null);

        //when
        String result = cardService.getCardAsString(nullCard);

        //then
        assertTrue(!result.isEmpty());
    }



}

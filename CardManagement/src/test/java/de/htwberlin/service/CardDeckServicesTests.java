package de.htwberlin.service;

import de.htwberlin.entity.Card;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


import java.util.List;

public class CardDeckServicesTests {
    CardDeckService cardDeckService ;

    @Before
    public void initializeCardService() {
        cardDeckService = new CardDeckServiceImpl();
    }

    // test needs to be editted
    @Test
    public void shuffleDeck_testShuffleNotTheSame(){
        //given
        List<Card> deck = cardDeckService.getNewDeck();

        //when
        List<Card> deck1 = cardDeckService.shuffleDeck(deck);
        List<Card> deck2 = cardDeckService.shuffleDeck(deck);

        //then
        assertArrayEquals(deck1.toArray(),deck2.toArray());
    }
}

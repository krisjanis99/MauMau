package de.htwberlin.service;

import de.htwberlin.entity.Card;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

public class CardDeckServicesTests {
    CardDeckService cardDeckService;

    @Before
    public void initializeCardService() {
        cardDeckService = new CardDeckServiceImpl();
    }

    // test needs to be editted
    @Test
    public void shuffleDeck_testShuffleNotTheSame() {
        //given
        List<Card> deck = cardDeckService.getNewDeck();

        //when
        List<Card> deck1 = cardDeckService.shuffleDeck(deck);
        List<Card> deck2 = cardDeckService.shuffleDeck(deck);

        //then
        assertNotEquals(deck1, deck2);
    }

    @Test
    public void getNewDeck_testDeckNotEmpty() {
        //given
        List<Card> fullDeck = cardDeckService.getNewDeck();

        //When
        List<Card> emptyDeck = null;

        //then
        assertNotEquals(fullDeck, emptyDeck);

    }

    @Test
    public void getNewDeck_testNewDeckGenerated() {
        //given
        List<Card> firstDeck = cardDeckService.getNewDeck();

        //When
        List<Card> secondDeck = cardDeckService.getNewDeck();

        //then
        assertNotEquals(firstDeck, secondDeck);

    }


}

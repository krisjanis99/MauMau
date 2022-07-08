package de.htwberlin.cardManagement;

import de.htwberlin.cardManagement.entity.Card;
import de.htwberlin.cardManagement.export.CardDeckService;
import de.htwberlin.cardManagement.impl.CardDeckServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CardDeckServicesTests {
    CardDeckService cardDeckService;

    @Before
    public void initializeCardService() {
        cardDeckService = new CardDeckServiceImpl();
    }

    @Test
    public void shuffleDeck_testShuffleNotTheSame() {
        //given
        List<Card> deck = cardDeckService.getNewDeck();

        //when
        List<Card> deck1 = cardDeckService.shuffleDeck(deck);
        List<Card> deck2 = cardDeckService.shuffleDeck(cardDeckService.shuffleDeck(deck1));

        //then
        //assertFalse((deck.get(1).getSymbol().equals(deck2.get(1).getSymbol())) || (deck.get(1).getRank().equals(deck2.get(1).getRank())));
        assertNotEquals(deck, deck1);
        assertNotEquals(deck, deck2);
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
    public void getNewDeck_testdeckCorrect() {
        //given
        List<Card.Rank> keys =
                new ArrayList<Card.Rank>(EnumSet.allOf(Card.Rank.class));
        List<Card.Symbol> values =
                new ArrayList<Card.Symbol>(EnumSet.allOf(Card.Symbol.class));

        //When
        List<Card> firstDeck = cardDeckService.getNewDeck();

        Map<Card.Rank, List<Card.Symbol>> map = new HashMap<>();
        for (int i = 0; i < firstDeck.size(); i++) {

            map.computeIfAbsent(firstDeck.get(i).getRank(), k -> new ArrayList<>()).add(firstDeck.get(i).getSymbol());
        }

        //then
        for (Card.Rank key : map.keySet()) {
            assertTrue(keys.contains(key));
        }
        for (List<Card.Symbol> value : map.values()) {
            assertTrue(values.equals(value));
        }
        assertTrue(map.size() == 7);

    }


}

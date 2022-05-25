package de.htwberlin.cardManagement.impl;

import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.export.CardDeckService;

import java.util.*;

public class CardDeckServiceImpl implements CardDeckService {


    /**
     * Shuffles given deck of cards.
     *
     * @param deck the deck
     * @return suffled deck of cards
     */
    @Override
    public List<Card> shuffleDeck(List<Card> deck) {
        Random r = new Random();
        Card[] deckarray = new Card[deck.size()];
        deck.toArray(deckarray);
        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = deckarray.length - 1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = r.nextInt(i);

            // Swap arr[i] with the element at random index
            Card temp = deckarray[i];
            deckarray[i] = deckarray[j];
            deckarray[j] = temp;

        }
        return Arrays.asList(deckarray);
    }
  


    /**
     * creates a new (french) deck for the game
     *
     * @return the new deck
     */
    @Override
    public List<Card> getNewDeck() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                cards.add(new Card(Card.Rank.values()[j], Card.Symbol.values()[i]));
            }
        }
        return cards;
    }

}




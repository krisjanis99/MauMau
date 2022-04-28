package de.htwberlin.service;

import de.htwberlin.entity.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeckServiceImpl implements CardDeckService {


    /**
     * Shuffles given deck of cards.
     *
     * @param deck the deck
     * @return suffled deck of cards
     */
    @Override
    public List<Card> shuffleDeck(List<Card> deck) {
        Collections.shuffle(deck);
        return deck;
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




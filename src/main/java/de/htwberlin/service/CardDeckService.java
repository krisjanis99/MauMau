package de.htwberlin.service;

import de.htwberlin.entity.Card;

import java.util.List;

/**
 * The interface Card deck service.
 */
public interface CardDeckService {

    /**
     * Shuffles given deck of cards.
     *
     * @param deck the deck
     * @return suffled deck of cards
     */
    List<Card> shuffleDeck(List<Card> deck);

    /**
     * creates a new deck.
     *
     * @return the new deck
     */
    List<Card> getNewDeck();

}

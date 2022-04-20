package de.htwberlin.service;

import de.htwberlin.entity.Card;
import de.htwberlin.entity.CardDeck;

/**
 * The interface Card deck service.
 */
public interface CardDeckService {

    /**
     * Gets a new deck.
     *
     * @return the new deck
     */
    CardDeck getNewFrenchDeck();

    Card takeTopCard(CardDeck deck);

    /**
     * Reshuffles the given deck of cards.
     * @param deck the deck
     * @return the card deck
     */
    CardDeck reshuffleDeck(CardDeck deck);

}

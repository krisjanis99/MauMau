package de.htwberlin.cardManagement.export;

import de.htwberlin.cardManagement.entity.Card;

import java.util.List;

/**
 * The interface for creating and changing card decks.
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
     * creates a new (french) deck for the game
     *
     * @return the new deck
     */
    List<Card> getNewDeck();

    /**
     * Gets the last placed card from a deck.
     * It doesn't remove the card, just gives an info, which card was placed.
     *
     * @param deck the deck
     * @return the last placed card. If the deck is empty, an exception is thrown.
     * @throws NoCardFoundException is thrown when the deck is empty
     */
    Card getLastPlacedCardOnDeck(List<Card> deck);

}

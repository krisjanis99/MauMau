package de.htwberlin.cardManagement.export;

import java.util.List;
import java.util.Optional;

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
     * @return the last placed card as an optional. If the deck is empty, the optional is empty.
     */
    Optional<Card> getLastPlacedCardOnDeck(List<Card> deck);

}

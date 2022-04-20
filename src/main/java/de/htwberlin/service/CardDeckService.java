package de.htwberlin.service;

import de.htwberlin.entity.Card;

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
     * creates a new deck.
     *
     * @return the new deck
     */
    List<Card> getNewDeck();

    /**
     * Takes the top card off deck optional.
     *
     * @param deck the deck
     * @return the optional card. if the deck is empty, an optional.empty will be returned
     */
    Optional<Card> takeTopCardOffDeck(List<Card> deck);

}

package de.htwberlin.service;

import de.htwberlin.entity.Card;

import java.util.List;
import java.util.Optional;

/**
 * The interface Card deck service.
 */
public interface CardDeckService {

    List<Card> shuffleDeck(List<Card> deck);

    List<Card> getNewDeck();

    Optional<Card> takeTopCardOffDeck(List<Card> deck);

}

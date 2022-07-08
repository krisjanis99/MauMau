package de.htwberlin.cardManagement.impl;

import de.htwberlin.cardManagement.entity.Card;
import de.htwberlin.cardManagement.export.CardDeckService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class CardDeckServiceImpl implements CardDeckService {
    private static final Logger logger = LogManager.getLogger(CardDeckServiceImpl.class);

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
        logger.info("Deck array was created and shuflling is initiated");

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
        logger.info("deck was shuffeled");
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
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 7; j++) {
                    cards.add(new Card(Card.Rank.values()[j], Card.Symbol.values()[i]));
                }
            }
            logger.info("Card deck was created");
        } catch (Exception e) {
            logger.fatal("deck couldn't be created");
        }

        return cards;
    }

    /**
     * Gets the last placed card from a deck.
     * It doesn't remove the card, just gives an info, which card was placed.
     *
     * @param deck the deck
     * @return the last placed card
     */
    @Override
    public Optional<Card> getLastPlacedCardOnDeck(List<Card> deck) {

        try {
            return Optional.of(deck.get(deck.size() - 1));
        }catch (IndexOutOfBoundsException e){
            logger.error("Card deck was empty.");
            return Optional.empty();
        }

    }

}




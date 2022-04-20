package de.htwberlin.service;

import de.htwberlin.entity.Card;
import de.htwberlin.entity.CardDeck;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeckServiceImpl implements CardDeckService {

    @Override
    public Card takeTopCard(CardDeck deck) {
        return null;
    }

    @Override
    public CardDeck reshuffleDeck(CardDeck deck){
        Collections.shuffle(deck.cards);
        return deck;
    }

    @Override
    public CardDeck getNewFrenchDeck(){
        ArrayList<Card> cards = new ArrayList<>();
        for(int i = 0; i<4; i++){
            for (int j = 0; j<7; j++){
                cards.add(new Card(Card.Rank.values()[j], Card.Symbol.values()[i]));
            }
        }
        return new CardDeck(cards);
    }

}




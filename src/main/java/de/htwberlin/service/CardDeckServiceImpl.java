package de.htwberlin.service;

import de.htwberlin.entity.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CardDeckServiceImpl implements CardDeckService {


    @Override
    public List<Card> shuffleDeck(List<Card> deck){
        Collections.shuffle(deck);
        return deck;
    }

    @Override
    public List<Card> getNewDeck(){
        List<Card> cards = new ArrayList<>();
        for(int i = 0; i<4; i++){
            for (int j = 0; j<7; j++){
                cards.add(new Card(Card.Rank.values()[j], Card.Symbol.values()[i]));
            }
        }
        return cards;
    }

}




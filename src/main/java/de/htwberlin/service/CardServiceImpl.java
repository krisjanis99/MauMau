package de.htwberlin.service;

import de.htwberlin.entity.Card;

public class CardServiceImpl implements CardService{


    @Override
    public int getCardRankAsInt(Card card) {
        switch(card.getRank()){
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case JACK: return 11;
            case QUEEN: return 12;
            case KING: return 13;
            case ACE: return 14;
            default:
                return 0;
        }
    }

    @Override
    public String getCardAsString(Card card) {
        return card.getRank() + " " + card.getSymbol();
    }
}

package de.htwberlin.service;

import de.htwberlin.entity.Card;
import de.htwberlin.entity.GameRule;

public class CardServiceImpl implements CardService{

    @Override
    public String getCardAsString(Card card) {
        return card.getRank() + " " + card.getSymbol();
    }

    @Override
    public GameRule CheckIfCardHasGameRule(Card card) {
        switch(card.getRank()){
            case SEVEN: return GameRule.NEXT_PLAYER_DRAWS_CARDS;
            case EIGHT: return GameRule.NEXT_PLAYER_SITS_OUT;
            case JACK: return GameRule.WISH_NEW_SYMBOL;
            default:
                return GameRule.NONE;
        }
    }
}

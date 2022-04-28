package de.htwberlin.service;

import de.htwberlin.entity.Card;
import de.htwberlin.entity.GameRule;

public class CardServiceImpl implements CardService {

    /**
     * Gets card as string.
     *
     * @param card the card
     * @return the card as string
     */
    @Override
    public String getCardAsString(Card card) {
        return card.getRank() + " " + card.getSymbol();
    }

    /**
     * Check if card has a game action.
     *
     * @param card the card
     * @return the game rule for the card
     */
    @Override
    public GameRule checkIfCardHasGameRule(Card card) {
        switch (card.getRank()) {
            case SEVEN:
                return GameRule.NEXT_PLAYER_DRAWS_CARDS;
            case EIGHT:
                return GameRule.NEXT_PLAYER_SITS_OUT;
            case JACK:
                return GameRule.WISH_NEW_SYMBOL;
            case NINE:
                return GameRule.CHANGE_DIRECTION;
            default:
                return GameRule.NONE;
        }
    }
}

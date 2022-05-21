package de.htwberlin.service;

import de.htwberlin.entity.Card;

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

}

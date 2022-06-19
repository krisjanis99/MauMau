package de.htwberlin.cardManagement.impl;

import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.export.CardService;



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

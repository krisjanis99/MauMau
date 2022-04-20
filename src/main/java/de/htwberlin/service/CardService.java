package de.htwberlin.service;

import de.htwberlin.entity.Card;

public interface CardService {

    int getCardRankAsInt(Card card);

    String getCardAsString(Card card);
}

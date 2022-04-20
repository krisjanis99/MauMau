package de.htwberlin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Card {

    public enum Symbol { CLUBS, DIAMONDS, HEARTS, SPADES; }

    public enum Rank { SEVEN, EIGHT,
        NINE, TEN, JACK, QUEEN, KING, ACE; }

    final Rank rank;

    final Symbol symbol;

}

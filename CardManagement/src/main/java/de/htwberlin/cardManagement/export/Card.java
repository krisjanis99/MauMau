package de.htwberlin.cardManagement.export;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Card {

    final Rank rank;
    final Symbol symbol;

    public enum Symbol {CLUBS, DIAMONDS, HEARTS, SPADES;}

    public enum Rank {
        SEVEN, EIGHT,
        NINE, TEN, JACK, QUEEN, KING, ACE;
    }

}

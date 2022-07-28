package de.htwberlin.cardManagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Card {

    Rank rank;
    Symbol symbol;
    @Id
    @GeneratedValue
    private Long id;

    public Card(Rank rank, Symbol symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public enum Symbol {CLUBS, DIAMONDS, HEARTS, SPADES;}

    public enum Rank {
        SEVEN, EIGHT,
        NINE, TEN, JACK, QUEEN, KING, ACE;
    }
}

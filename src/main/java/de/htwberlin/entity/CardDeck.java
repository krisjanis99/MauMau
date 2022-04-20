package de.htwberlin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CardDeck {

    public ArrayList<Card> cards;

}

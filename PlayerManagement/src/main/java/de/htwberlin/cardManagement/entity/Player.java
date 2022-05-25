package de.htwberlin.cardManagement.entity;

import de.htwberlin.cardManagement.export.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Player {

    int id;

    String name;

    List<Card> playerCards;

    Boolean hasCalledMau;

}
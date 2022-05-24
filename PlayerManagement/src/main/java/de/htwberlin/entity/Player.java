package de.htwberlin.entity;

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

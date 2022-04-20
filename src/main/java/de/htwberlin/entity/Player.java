package de.htwberlin.entity;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Player {

    List<Card> playerCards;

    Boolean hasCalledMau;

}

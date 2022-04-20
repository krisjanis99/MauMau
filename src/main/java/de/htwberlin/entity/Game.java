package de.htwberlin.entity;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class Game {

    final Player player1;

    final Player player2;

    ArrayList<Card> placedCards;

    String GameStatus;

}

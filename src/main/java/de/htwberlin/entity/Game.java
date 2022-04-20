package de.htwberlin.entity;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class Game {

    final Player player1;

    final Player player2;

    ArrayList<Card> cardDeck;

    ArrayList<Card> placedCardDeck;

    String gameStatus;

}

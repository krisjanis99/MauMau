package de.htwberlin.entity;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class Game {

    public enum GameStatus { NOT_STARTED, ONGOING, PLAYER_1_WON, PLAYER_2_WON }

    final Player player1;

    final Player player2;

    ArrayList<Card> cardDeck;

    ArrayList<Card> placedCardDeck;

    GameStatus currentGameStatus;

}

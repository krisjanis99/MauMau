package de.htwberlin.entity;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Game {

    final List<Player> playerList;

     List<Player> winnersRanked;

    Boolean gameEnded;

    List<Card> cardDeck;

    List<Card> placedCardDeck;

    Player currentActivePlayer;

    GameRule currentGameRule;

    int cardsToDraw;

}

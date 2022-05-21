package de.htwberlin.entity;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class Game {

    final List<Player> playerList;

    List<Player> winnersRankedList;

    Boolean gameEnded;

    List<Card> cardDeck;

    List<Card> placedCardDeck;

    Player currentActivePlayer;

    Map<Card.Rank, String> GameRuleSet;

    String currentGameRule;

    int cardsToDraw;

    Boolean currentDirectionIsClockwise;

    Card.Symbol currentSymbol;

    Card.Rank currentRank;
}

package de.htwberlin.gameManagement.export;

import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
public class Game {

    final List<Player> playerList;

    List<Player> winnersRankedList;

    Boolean gameEnded;

    List<Card> cardDeck;

    List<Card> placedCardDeck;

    Player currentActivePlayer;

    String currentGameRule;

    int cardsToDraw;

    Boolean currentDirectionIsClockwise;

    Card.Symbol currentSymbol;

    Card.Rank currentRank;
}

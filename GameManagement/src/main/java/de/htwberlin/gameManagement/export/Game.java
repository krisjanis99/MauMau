package de.htwberlin.gameManagement.export;

import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Game {

    public Game(List<Player> playerList, List<Card> cardDeck) {
        this.playerList = playerList;
        this.cardDeck = cardDeck;
        this.placedCardDeck = new ArrayList<>();
        this.winnersRankedList = new ArrayList<>();
        this.cardsToDraw = 0;
        this.currentDirectionIsClockwise = true;
        this.gameEnded = false;
        int randomPlayerIndex = (int) (Math.random() * playerList.size());
        this.currentActivePlayer = playerList.get(randomPlayerIndex);
    }

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

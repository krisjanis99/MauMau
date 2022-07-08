package de.htwberlin.gameManagement.entity;

import de.htwberlin.playerManagement.entity.Player;
import de.htwberlin.cardManagement.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Game {

    @GeneratedValue
    @Id
    private Long id;

    @OneToMany
    List<Player> playerList;

    @OneToMany
    List<Player> winnersRankedList;

    Boolean gameEnded;

    @OneToMany
    List<Card> cardDeck;

    @OneToMany
    List<Card> placedCardDeck;

    @OneToOne
    Player currentActivePlayer;

    String currentGameRule;

    int cardsToDraw;

    Boolean currentDirectionIsClockwise;

    Card.Symbol currentSymbol;

    Card.Rank currentRank;

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
}

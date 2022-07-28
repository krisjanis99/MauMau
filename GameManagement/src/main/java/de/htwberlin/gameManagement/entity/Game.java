package de.htwberlin.gameManagement.entity;

import de.htwberlin.cardManagement.entity.Card;
import de.htwberlin.playerManagement.entity.Player;
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

    int turnNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "playerlist_id")
    List<Player> playerList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "winnersrankedlist_id")
    List<Player> winnersRankedList;
    Boolean gameEnded;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carddeck_id")
    List<Card> cardDeck;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "placedcarddeck_id")
    List<Card> placedCardDeck;
    @OneToOne(cascade = CascadeType.ALL)
    Player currentActivePlayer;
    String currentGameRule;
    int cardsToDraw;
    Boolean currentDirectionIsClockwise;
    Card.Symbol currentSymbol;
    Card.Rank currentRank;
    @GeneratedValue
    @Id
    private Long id;

    public Game(List<Player> playerList, List<Card> cardDeck) {
        this.turnNumber = 0;
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

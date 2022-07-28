package de.htwberlin.playerManagement.entity;

import de.htwberlin.cardManagement.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Player {

    String name;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Card> playerCards;
    Boolean hasCalledMau;
    Boolean isVirtualPlayer;
    @GeneratedValue
    @Id
    private Long id;

    public Player(String name, List<Card> playerCards, Boolean hasCalledMau, Boolean isVirtualPlayer) {
        this.name = name;
        this.playerCards = playerCards;
        this.hasCalledMau = hasCalledMau;
        this.isVirtualPlayer = isVirtualPlayer;
    }
}

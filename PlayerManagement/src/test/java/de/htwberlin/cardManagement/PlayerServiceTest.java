package de.htwberlin.cardManagement;

import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.impl.PlayerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


public class PlayerServiceTest {

    private final PlayerServiceImpl playerServiceImp ;

    public PlayerServiceTest(){
        playerServiceImp = new PlayerServiceImpl();
    }


    @Test
    public void createPlayerTest_successfulPlayerCreation(){

        //given
        List<Card> givenPlayerCards = new ArrayList<Card>();
        givenPlayerCards.add(new Card(Card.Rank.EIGHT, Card.Symbol.CLUBS));
        givenPlayerCards.add(new Card(Card.Rank.NINE, Card.Symbol.HEARTS));
        givenPlayerCards.add(new Card(Card.Rank.TEN, Card.Symbol.CLUBS));
        givenPlayerCards.add(new Card(Card.Rank.KING, Card.Symbol.DIAMONDS));
        givenPlayerCards.add(new Card(Card.Rank.NINE, Card.Symbol.SPADES));
        givenPlayerCards.add(new Card(Card.Rank.TEN, Card.Symbol.CLUBS));
        String playerName = "jacky";

        //when
        Optional<Player> player = this.playerServiceImp.createPlayer(playerName, givenPlayerCards);

        //then
        assertTrue(player.isPresent());
        assertEquals( player.get().getName(), playerName);
        assertEquals( player.get().getPlayerCards(), givenPlayerCards);
    }

    @Test
    public void CountNewPlayerCardTest_notRightNumberOfCards(){

        //given
        List<Card> givenPlayerCards = new ArrayList<Card>();
        givenPlayerCards.add(new Card(Card.Rank.EIGHT, Card.Symbol.CLUBS));
        givenPlayerCards.add(new Card(Card.Rank.NINE, Card.Symbol.HEARTS));
        givenPlayerCards.add(new Card(Card.Rank.TEN, Card.Symbol.CLUBS));
        String playerName = "gladys";

        //when
        Optional<Player> player = this.playerServiceImp.createPlayer(playerName, givenPlayerCards);

        //then
        assertFalse( player.isPresent())  ;
    }
}

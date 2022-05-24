package de.htwberlin.service;

import de.htwberlin.entity.Player;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;


public class PlayerServiceTest {


    private final PlayerServiceImpl playerServiceImp ;

    public PlayerServiceTest(){
        playerServiceImp = new PlayerServiceImpl();
    }

    @Test
    @DisplayName("to Test if the Player name is correct ")
    public void createPlayerTest(){

        String playerName = "jacky";

        Player player = this.playerServiceImp.createPlayer(playerName);

        assertEquals( player.getName(), playerName)  ;

    }

    @Test
    @DisplayName("to check if the List of random card created is of 6 Length  ")
    public void CountNewPlayerCardTest(){

        String playerName = "gladys";

        Player player = this.playerServiceImp.createPlayer(playerName);

        assertEquals( player.getPlayerCards().size(), 6)  ;

    }
}

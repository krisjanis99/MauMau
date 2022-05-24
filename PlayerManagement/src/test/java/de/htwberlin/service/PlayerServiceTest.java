package de.htwberlin.service;

import de.htwberlin.entity.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PlayerServiceTest {


    private final PlayerServiceImpl playerServiceImp ;

    public PlayerServiceTest(){
        playerServiceImp = new PlayerServiceImpl();
    }

    @Test
    public void createPlayerTest(){

        String playerName = "jacky";

        Player player = this.playerServiceImp.createPlayer(playerName);

        assertEquals( player.getName(), playerName)  ;

    }

    @Test
    public void CountNewPlayerCardTest(){

        String playerName = "gladys";

        Player player = this.playerServiceImp.createPlayer(playerName);

        assertEquals( player.getPlayerCards().size(), 6)  ;

    }
}

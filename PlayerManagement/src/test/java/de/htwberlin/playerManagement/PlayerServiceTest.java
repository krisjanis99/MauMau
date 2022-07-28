package de.htwberlin.playerManagement;

import de.htwberlin.playerManagement.entity.Player;
import de.htwberlin.playerManagement.export.PlayerCreationFailedException;
import de.htwberlin.playerManagement.export.PlayerService;
import de.htwberlin.playerManagement.impl.PlayerServiceImpl;
import de.htwberlin.playerManagement.impl.VirtualPlayerServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.*;


public class PlayerServiceTest {

    private PlayerService playerService;


    @Before
    public void setUp(){
        playerService = new PlayerServiceImpl();
    }

    @Test
    public void createPlayerTest_successfulPlayerCreation() throws PlayerCreationFailedException {

        //given
        String playerName = "jacky";

        //when
        Player player = playerService.createPlayer(playerName);

        //then
        assertEquals(player.getName(), playerName);
    }

    @Test
    public void CountNewPlayerCardTest_checkIfPlayersHaveDifferentNames() throws PlayerCreationFailedException {

        //given

        //when
        Player player1 = playerService.createPlayer("Bob");
        Player player2 = playerService.createPlayer("Max");

        //then
        assertNotEquals(player1.getName(), player2.getName());
    }
}

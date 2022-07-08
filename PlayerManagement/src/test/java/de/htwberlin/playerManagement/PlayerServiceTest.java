package de.htwberlin.playerManagement;

import de.htwberlin.playerManagement.entity.Player;
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
    public void createPlayerTest_successfulPlayerCreation() {

        //given
        String playerName = "jacky";

        //when
        Optional<Player> player = playerService.createPlayer(playerName);

        //then
        assertTrue(player.isPresent());
        assertEquals(player.get().getName(), playerName);
    }

    @Test
    public void CountNewPlayerCardTest_checkIfPlayersHasDifferentIds() {

        //given
        String playerName = "gladys";

        //when
        Optional<Player> player1 = playerService.createPlayer("gladys");
        Optional<Player> player2 = playerService.createPlayer("jacky");

        //then
        assertNotEquals(player1.get().getId(), player2.get().getId());
    }
}

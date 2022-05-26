package de.htwberlin.cardManagement;

import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.impl.PlayerServiceImpl;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;


public class PlayerServiceTest {

    private final PlayerServiceImpl playerServiceImp;

    public PlayerServiceTest() {
        playerServiceImp = new PlayerServiceImpl();
    }


    @Test
    public void createPlayerTest_successfulPlayerCreation() {

        //given
        String playerName = "jacky";

        //when
        Optional<Player> player = this.playerServiceImp.createPlayer(playerName);

        //then
        assertTrue(player.isPresent());
        assertEquals(player.get().getName(), playerName);
    }

    @Test
    public void CountNewPlayerCardTest_checkIfPlayersHasDifferentIds() {

        //given
        String playerName = "gladys";

        //when
        Optional<Player> player1 = this.playerServiceImp.createPlayer("gladys");
        Optional<Player> player2 = this.playerServiceImp.createPlayer("jacky");

        //then
        assertNotEquals(player1.get().getId(), player2.get().getId());
    }
}

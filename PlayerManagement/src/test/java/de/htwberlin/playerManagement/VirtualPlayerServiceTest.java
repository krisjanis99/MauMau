package de.htwberlin.playerManagement;

import de.htwberlin.playerManagement.entity.Player;
import de.htwberlin.playerManagement.export.PlayerCreationFailedException;
import de.htwberlin.playerManagement.export.VirtualPlayerService;
import de.htwberlin.playerManagement.impl.VirtualPlayerServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class VirtualPlayerServiceTest {

    private VirtualPlayerService virtualPlayerService;

    @Before
    public void setUp(){
        virtualPlayerService = new VirtualPlayerServiceImpl(new Random());
    }


    @Test
    public void testCreateVirtualPlayer_successfulPlayerCreation() throws PlayerCreationFailedException {

        //when
        Player player = virtualPlayerService.createVirtualPlayer();

        //then
        assertTrue(player.getIsVirtualPlayer());
    }


    @Test
    public void testGenerateRandomMove() {
        //when
        int result = virtualPlayerService.generateRandomMove(1, 10);

        //then
        assertTrue(result >= 1);
        assertTrue(result <= 10);
    }


}

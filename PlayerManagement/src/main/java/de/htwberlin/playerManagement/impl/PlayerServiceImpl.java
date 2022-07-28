package de.htwberlin.playerManagement.impl;

import de.htwberlin.playerManagement.entity.Player;
import de.htwberlin.cardManagement.entity.Card;
import de.htwberlin.playerManagement.export.PlayerCreationFailedException;
import de.htwberlin.playerManagement.export.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


public class PlayerServiceImpl implements PlayerService {
    private static final Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    @Override
    public Player createPlayer(String name) throws PlayerCreationFailedException {
        try {
            Player player = new Player( name, new ArrayList<Card>(), false, false);
            logger.info("player created");
            return player;
        }catch (Exception e){
            throw new PlayerCreationFailedException(String.format("Player couldn't be created: %s", e));
        }

    }

}
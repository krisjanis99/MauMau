package de.htwberlin.cardManagement.impl;

import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


public class PlayerServiceImpl implements PlayerService {
    private static final Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    private static int ID;

    @Override
    public Optional<Player> createPlayer(String name) {
        int playerID = ID;
        logger.info("player was given an ID");
        ID++;
        logger.info("player created");
        return Optional.of(new Player(playerID, name));

    }
}
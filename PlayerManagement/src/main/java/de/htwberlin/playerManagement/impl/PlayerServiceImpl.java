package de.htwberlin.playerManagement.impl;

import de.htwberlin.playerManagement.entity.Player;
import de.htwberlin.cardManagement.entity.Card;
import de.htwberlin.playerManagement.export.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Optional;


public class PlayerServiceImpl implements PlayerService {
    private static final Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    @Override
    public Optional<Player> createPlayer(String name) {
        Player player = new Player( name, new ArrayList<Card>(), false, false);
        logger.info("player created");
        return Optional.of(player);
    }

}
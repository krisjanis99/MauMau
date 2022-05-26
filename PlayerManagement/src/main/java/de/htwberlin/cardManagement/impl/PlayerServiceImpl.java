package de.htwberlin.cardManagement.impl;

import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.PlayerService;

import java.util.Optional;


public class PlayerServiceImpl implements PlayerService {

    private static int ID;

    @Override
    public Optional<Player> createPlayer(String name) {
        int playerID = ID;
        ID++;
        return Optional.of(new Player(playerID, name));
    }
}
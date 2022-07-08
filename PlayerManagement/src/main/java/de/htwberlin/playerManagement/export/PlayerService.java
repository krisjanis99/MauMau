package de.htwberlin.playerManagement.export;

import de.htwberlin.playerManagement.entity.Player;

import java.util.Optional;

public interface PlayerService {

    /**
     * create a new player
     *
     * @param name of the Player
     * @return initialized and configured player
     */
    Optional<Player> createPlayer(String name);
}

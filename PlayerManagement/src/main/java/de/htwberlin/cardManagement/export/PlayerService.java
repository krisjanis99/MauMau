package de.htwberlin.cardManagement.export;

import de.htwberlin.cardManagement.entity.Player;

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

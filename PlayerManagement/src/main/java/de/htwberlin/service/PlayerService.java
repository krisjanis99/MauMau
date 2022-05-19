package de.htwberlin.service;

import de.htwberlin.entity.Player;

public interface PlayerService {

    /**
     * create a new player
     *
     * @param name of the Player
     * @return Player as a Object
     */
    Player createPlayer(String name);
}

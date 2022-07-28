package de.htwberlin.playerManagement.export;

import de.htwberlin.playerManagement.entity.Player;

/**
 * The interface for methods involving creating or changing a Player.
 */
public interface PlayerService {


    /**
     * Creates a new player.
     *
     * @param name the name of player
     * @return the configured player
     * @throws PlayerCreationFailedException is thrown if there is an error in player creation
     */
    Player createPlayer(String name) throws PlayerCreationFailedException;
}

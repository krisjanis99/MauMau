package de.htwberlin.playerManagement.export;

import de.htwberlin.playerManagement.entity.Player;

import java.util.Optional;

/**
 * The interface Virtual player move service.
 */
public interface VirtualPlayerService {

    /**
     * create a new virtual player
     *
     * @return initialized and configured virtual player
     */
    Optional<Player> createVirtualPlayer();

    /**
     *
     * @param min the min int
     * @param max the max int
     * @return the generated int
     */
    int generateRandomMove(int min, int max);

}

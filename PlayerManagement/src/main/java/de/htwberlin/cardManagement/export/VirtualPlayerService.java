package de.htwberlin.cardManagement.export;

import de.htwberlin.cardManagement.entity.Player;

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
     * Generate random int which dictates the move.
     *
     * @param min the min
     * @param max the max
     * @return the generated int
     */
    int generateRandomMove(int min, int max);

}

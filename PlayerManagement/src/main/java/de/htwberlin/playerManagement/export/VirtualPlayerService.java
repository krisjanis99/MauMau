package de.htwberlin.playerManagement.export;

import de.htwberlin.playerManagement.entity.Player;

/**
 * The interface Virtual player move service.
 */
public interface VirtualPlayerService {

    /**
     * creates and returns a new virtual player
     *
     * @return initialized and configured virtual player
     * @throws PlayerCreationFailedException is htown when the player creation failed
     */
    Player createVirtualPlayer() throws PlayerCreationFailedException;

    /**
     * Generate random move int.
     *
     * @param min the min int
     * @param max the max int
     * @return the generated int
     */
    int generateRandomMove(int min, int max);

}

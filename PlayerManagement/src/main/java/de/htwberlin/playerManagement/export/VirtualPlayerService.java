package de.htwberlin.playerManagement.export;

import de.htwberlin.playerManagement.entity.Player;

/**
 * The interface for methods involving creating or changing a Virtual Player.
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
     * Generates random move. In game, a move is chosen by an int, so this method generates an int in a given range.
     *
     * @param min the min int
     * @param max the max int
     * @return the generated int for a random move
     */
    int generateRandomMove(int min, int max);

}

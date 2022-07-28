package de.htwberlin.persistGameManagement.export;

import de.htwberlin.gameManagement.entity.Game;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * The interface for saving, loading or deleting the Data from the game.
 */
public interface DAOService {

    /**
     * Saves the given game and returns a boolean, if the save was successful.
     *
     * @param game the game which should be saved
     * @return a boolean if the game was saved successfully
     */
    boolean persist(Game game);

    /**
     * Update the game in database.
     *
     * @param game the game which should be updated
     * @return the updated game
     */
    Game update(Game game);

    /**
     * Finds a game by its id. If no game exist with the id, an empty optional is given back.
     *
     * @param id the id
     * @return the optional of the found game
     */
    Optional<Game> findGameById(Long id);

    /**
     * Finds all games.
     *
     * @return the list of all games
     */
    List<Game> findAllGames();

    /**
     * Removes the given game from the database.
     *
     * @param game the game which should be removed
     * @return the boolean if the removal was successful
     */
    boolean removeGame(Game game);

    /**
     * Remove all games and gives back the number of deleted games.
     *
     * @return the number of games
     */
    int removeAllGames();

    /**
     * Gets the entity manager.
     *
     * @return the entity manager
     */
    EntityManager getEntityManager();

}

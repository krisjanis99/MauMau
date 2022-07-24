package de.htwberlin.persistGameManagement.export;

import de.htwberlin.gameManagement.entity.Game;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface DAOService {

    boolean persist(Game game);

    Game update(Game game);

    Optional<Game> findGameById(Long id);

    List<Game> findAllGames();

    boolean removeGame(Game game);

    int removeAllGames();

    EntityManager getEntityManager();

}

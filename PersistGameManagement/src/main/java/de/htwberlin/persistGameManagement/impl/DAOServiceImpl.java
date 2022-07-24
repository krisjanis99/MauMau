package de.htwberlin.persistGameManagement.impl;

import de.htwberlin.gameManagement.entity.Game;
import de.htwberlin.persistGameManagement.export.DAOService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOServiceImpl implements DAOService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaMauMauService");

    private EntityManager em = emf.createEntityManager();


    @Override
    public boolean persist(Game game) {
        try{
            em.getTransaction().begin();
            em.persist(game);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Game update(Game game) {
        try{
            em.getTransaction().begin();
            em.persist(game);
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    @Override
    public Optional<Game> findGameById(Long id) {
        Game game = null;
        try{
            em.getTransaction().begin();
            game = em.find(Game.class, id);
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(game);
    }

    @Override
    public List<Game> findAllGames() {
        List<Game> allGames = new ArrayList<>();
        try{
            em.getTransaction().begin();
            TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g ORDER BY g.id", Game.class);
            allGames = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allGames;
    }

    @Override
    public boolean removeGame(Game game) {
        try {
            em.getTransaction().begin();
            em.remove(game);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int removeAllGames() {
        int deletedGames = 0;
        try {
            em.getTransaction().begin();
            deletedGames = em.createQuery("DELETE FROM Game").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deletedGames;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}

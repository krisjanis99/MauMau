package de.htwberlin.cardManagement.export;

import de.htwberlin.cardManagement.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    /**
     * create a new player
     *
     * @param name of the Player
     * @param newPlayerCards beginning cards for the player
     * @return Player as a Object
     */
    Optional<Player> createPlayer(String name, List<Card> newPlayerCards);
}

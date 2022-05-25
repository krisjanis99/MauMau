package de.htwberlin.cardManagement.impl;

import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.PlayerService;

import java.util.List;
import java.util.Optional;


public class PlayerServiceImpl implements PlayerService {

    @Override
    public Optional<Player> createPlayer(String name, List<Card> newPlayerCards) {
        if (newPlayerCards.size()!=6){
            return Optional.empty();
        }
        return Optional.of(new Player(1, name, newPlayerCards, false));
    }
}
package de.htwberlin.service;

import de.htwberlin.entity.Card;
import de.htwberlin.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerServiceImpl implements PlayerService{

    private final CardDeckServiceImpl cardDeckService;

    public PlayerServiceImpl(){
        cardDeckService = new CardDeckServiceImpl();
    }

    @Override
    public Player createPlayer(String name) {
        List<Card> newPlayerCard = new ArrayList<>();
        cardDeckService.getNewDeck().stream().forEach(card ->{
            if(newPlayerCard.size()!= 6){
                newPlayerCard.add(card);
            }
        });
        return new Player(1, name, newPlayerCard, false);
    }
}

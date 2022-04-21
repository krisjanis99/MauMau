package de.htwberlin;

import de.htwberlin.entity.Card;
import de.htwberlin.service.CardDeckService;
import de.htwberlin.service.CardDeckServiceImpl;
import de.htwberlin.service.CardService;
import de.htwberlin.service.CardServiceImpl;

import java.util.List;

public class App {

    public static void main( String[] args ){

        CardDeckService cardDeckService = new CardDeckServiceImpl();
        CardService cardService = new CardServiceImpl();

        List<Card> cards = cardDeckService.getNewDeck();

        for(int i = 0; i < cards.size(); i++){
            System.out.println(cardService.getCardAsString(cards.get(i)));
        }

    }
}

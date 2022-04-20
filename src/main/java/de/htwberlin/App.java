package de.htwberlin;

import de.htwberlin.entity.Card;
import de.htwberlin.service.CardDeckService;
import de.htwberlin.service.CardDeckServiceImpl;

import java.util.ArrayList;

public class App 
{

    public static void main( String[] args )
    {
        CardDeckService cardDeckService = new CardDeckServiceImpl();

        ArrayList<Card> cards = cardDeckService.getNewFrenchDeck().getCards();

        for (int i = 0; i < cards.size(); i++){
            System.out.println(cards.get(i).getRank() +  " " + cards.get(i).getSymbol());
        }

    }
}

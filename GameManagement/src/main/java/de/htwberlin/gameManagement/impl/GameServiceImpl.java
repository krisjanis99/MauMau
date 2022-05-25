package de.htwberlin.gameManagement.impl;


import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.gameManagement.export.Game;
import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.gameManagement.export.GameService;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
public class GameServiceImpl implements GameService {


    /**
     * Starts and configures a new game.
     *
     * @param players the players which play the game
     * @return a new configured game
     */
    @Override
    public Game startNewGame(List<Player> players) {
        return null;
    }


    /**
     * Place card in the ongoing game.
     *
     * @param game the ongoing the game
     * @param card the card to be placed
     * @return the game with a placed card
     */
    @Override
    public Game placeCard(Game game, Card card) {
        return null;
    }


    /**
     * Take top card off hidden deck in the game.
     *
     * @param game the ongoing game
     * @return changed game
     */


    @Override
    public Game takeTopCardOffDeck(Game game) {
        return null;
    }

    /**
     * check if a card can be placed on the the placed card deck
     * @param card the card to be placed
     * @param placedCardDeck the deck on which the card to be placed
     * @return ture if the car can be placed on the placerdCardDeck, other than that then false
     */
   @Override
   public boolean cardPlaceable(Card card,List<Card> placedCardDeck ){
       Card lastCard = placedCardDeck.get(placedCardDeck.size()-1);
       if ((card.getRank()==Card.Rank.JACK)&&(lastCard.getRank()==Card.Rank.JACK)){
           return false;
       }
       else if(card.getRank()==lastCard.getRank()){
           return true ;
       }
       else if(card.getRank()== Card.Rank.JACK){
           return true;
       }
       else if(card.getRank()== Card.Rank.TEN){
           return true ;
       }

    return false ;
   }

    /**
     * Check if card has a game action.
     *
     * @param ruleset the imported Ruleset used in game
     * @param card    the card
     * @return the game rule for the card
     */
    @Override
    public Optional<String> checkIfCardHasGameRule(Map<Card.Rank, String> ruleset, Card card) {
        return Optional.ofNullable(ruleset.get(card.getRank()));
    }


}
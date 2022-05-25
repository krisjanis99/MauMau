import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.export.CardDeckService;
import de.htwberlin.cardManagement.export.CardService;
import de.htwberlin.cardManagement.impl.CardDeckServiceImpl;
import de.htwberlin.cardManagement.impl.CardServiceImpl;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import de.htwberlin.rulesetManagement.impl.GameRuleServiceImpl;
import de.htwberlin.gameManagement.export.GameService;
import de.htwberlin.gameManagement.impl.GameServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        CardDeckService cardDeckService = new CardDeckServiceImpl();
        CardService cardService = new CardServiceImpl();
        GameService gameService = new GameServiceImpl();
        GameRuleService gameRuleService = new GameRuleServiceImpl();

        List<Card> cards = cardDeckService.getNewDeck();

        Map<Card.Rank, String> currentRuleset = gameRuleService.getGameRuleSet(0).get();

        for (int i = 0; i < cards.size(); i++) {
            System.out.println(cardService.getCardAsString(cards.get(i)));
            System.out.println(gameService.checkIfCardHasGameRule(currentRuleset, cards.get(i)));
        }

        List<Card> newPlayerCard = new ArrayList<>();
        cardDeckService.getNewDeck().stream().forEach(card ->{
            if(newPlayerCard.size()!= 6){
                newPlayerCard.add(card);
            }
        });


    }
}

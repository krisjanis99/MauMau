import de.htwberlin.entity.Card;
import de.htwberlin.service.*;

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


    }
}

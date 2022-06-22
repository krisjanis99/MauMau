import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.export.CardDeckService;
import de.htwberlin.cardManagement.export.CardService;
import de.htwberlin.cardManagement.impl.CardDeckServiceImpl;
import de.htwberlin.cardManagement.impl.CardServiceImpl;
import de.htwberlin.cardManagement.impl.PlayerServiceImpl;
import de.htwberlin.gameManagement.export.GameService;
import de.htwberlin.gameManagement.impl.GameServiceImpl;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import de.htwberlin.rulesetManagement.impl.GameRuleServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class App {

    static  Card initialCard ;
    static List<Card> mixedCard;
    static  BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    public static void main(String[] args)  {

        // Enter data using BufferReader


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

        cards =  cardDeckService.shuffleDeck(cards);

        List<Card> newPlayerCard = new ArrayList<>();
        cardDeckService.getNewDeck().stream().forEach(card -> {
            if (newPlayerCard.size() != 6) {
                newPlayerCard.add(card);
            }
        });


        /**
         * @author Gladys$
         *
         * Starting Game Control ................................
         * ......................................................
         * ......................................................
         *
         */
        System.out.println("...................................................................... ");
        System.out.println("..................... Welcome the Mau Mau Game........................ ");
        System.out.println(".....................  The Rule of the Game  ......................... ");
        System.out.println("...................................................................... ");
        System.out.println("...................................................................... ");

        List<Optional<Player>> getListOfPlayer =  getListPlayer(cards);
        initialCard = cards.get(cards.size()-getListOfPlayer.size());

        System.out.println("..............The initial card is been is picked....................... ");
        System.out.println("....................................................................... ");
        System.out.println(initialCard.getRank() +"  symbols :"+ initialCard.getSymbol());
        System.out.println("....................................................................... ");



    }

    private static List<Optional<Player>> getListPlayer (List<Card> randomCard){

        try
        {
            System.out.println("How many Player wanna play ? ");

            String numberOfPlayer = reader.readLine();

            int number = Integer.parseInt(numberOfPlayer);

            if(number >=5){
                System.out.println("Max number of player 5, the are just " + randomCard.size() + "of present ");
            }
            else
            {
                ArrayList<Optional<Player>> players =  new ArrayList<>(number);

                for ( int i = 1; i <= number; i++){
                    System.out.println(" Please give the :" + i + " player name :");
                    String playerName =  reader.readLine();
                    System.out.println(" Please Player NAME :"+ playerName + " player name :");
                    Optional<Player> player = Optional.of(new Player(1, playerName));

                    List<Card> playerCard = new ArrayList<>(5);

                    for(int j= 0; j<=4; j++){
                        Card card = randomCard.get(j);
                        playerCard.add(card);
                    }
                    player.get().setPlayerCards(playerCard);
                    /**
                     * removing card after collecting it from the random Cards
                     * total number of cards is max 5 Cards ...
                     */
                    for(int j= 0; j<=4; j++){
                        randomCard.remove(j);

                    }
                    for (Card card  : playerCard) {
                        System.out.println(card.getRank() +"  symbols :"+ card.getSymbol());
                    }

                    players.add(player);


                }
                return  players;
            }



        }
        catch (Exception exception){
            System.out.println("Problem when taking the numbers of players " + exception.toString());
            return getListPlayer(randomCard);
        }

       return  new ArrayList<>();

    }
}

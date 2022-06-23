package impl;

import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.export.CardService;
import de.htwberlin.cardManagement.export.PlayerService;
import de.htwberlin.gameManagement.export.Game;
import de.htwberlin.gameManagement.export.GameService;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import export.MauMauUi;
import org.picocontainer.annotations.Inject;

import java.util.ArrayList;
import java.util.List;

public class MauMauUiController implements MauMauUi {

    @Inject
    MauMauUiView view;

    @Inject
    GameService gameService;

    @Inject
    CardService cardService;

    @Inject
    GameRuleService gameRuleService;

    @Inject
    PlayerService playerService;


    @Override
    public void run() {
        view.printWelcomeMsg();

        int playerCount = 0;
        while (playerCount < 2 || playerCount > 5) {
            view.printNotification("How many players will play? The maximal count is 5.");
            playerCount = view.getUserInputAsInt();
        }

        List<Player> playerList = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) {
            view.askForPlayerName(i);
            String name = view.getUserInputAsString();
            playerList.add(playerService.createPlayer(name).get());
        }
        Game game = gameService.startNewGame(playerList).get();
        view.printGameStartingMsg();

        while (!game.getGameEnded()) {

            game = executeTurn(game);

            for (Player player : game.getPlayerList()) {
                if (player.getPlayerCards().isEmpty()) {
                    game.getPlayerList().remove(player);
                    game.getWinnersRankedList().add(player);
                    view.printNotification(String.format("The Player %s finished the game.", player.getName()));
                }
            }

            if (game.getPlayerList().size() < 2) {
                game.setGameEnded(true);
                game.getPlayerList().remove(game.getPlayerList().get(0));
                game.getWinnersRankedList().add(game.getPlayerList().get(0));
                view.printNotification("The game has ended!");
                view.printWinnerList(game.getWinnersRankedList());
            }
        }


    }

    private Game executeTurn(Game game) {
        view.printTurnStartingMessage(game);

        while (true) {
            view.printPlayerCards(game.getCurrentActivePlayer());

            int playerInput = view.getUserInputAsInt();

            if (playerInput == game.getCurrentActivePlayer().getPlayerCards().size()) {

                game = gameService.takeTopCardOffDeck(game);
                Card drawnCard = game.getCurrentActivePlayer().getPlayerCards().get(game.getCurrentActivePlayer().getPlayerCards().size() - 1);
                view.printNotification("You drawed: " + cardService.getCardAsString(drawnCard));

            } else if (playerInput <= game.getCurrentActivePlayer().getPlayerCards().size()) {

                Card cardToBePlaced = game.getCurrentActivePlayer().getPlayerCards().get(playerInput);
                boolean placementValid = gameRuleService.cardPlaceable(cardToBePlaced, game.getCurrentSymbol(), game.getCurrentRank());

                if (gameRuleService.checkIfCardHasGameRule(cardToBePlaced).isPresent()) {
                    String rule = gameRuleService.checkIfCardHasGameRule(cardToBePlaced).get();
                    gameService.placeCard(game, cardToBePlaced);
                    game = applyGameRule(game, rule);
                    return gameService.switchToNextPlayer(game);
                } else if (placementValid) {
                    gameService.placeCard(game, cardToBePlaced);
                    game = checkIfCardsNeedToBeDrawn(game);
                    return gameService.switchToNextPlayer(game);
                } else {
                    view.printNotification("The placement is not valid! Try again!");
                }
            }
        }
    }

    private Game checkIfCardsNeedToBeDrawn(Game game) {
        if (game.getCardsToDraw() > 0) {
            List<Card> drawnCards = new ArrayList<>();
            for (int i = 0; game.getCardsToDraw() > i; i++) {
                game = gameService.takeTopCardOffDeck(game);
                drawnCards.add(game.getCurrentActivePlayer().getPlayerCards().get(game.getCurrentActivePlayer().getPlayerCards().size() - 1));
            }
            view.notifyPlayerAboutTheDrawnCards(drawnCards);
            game.setCardsToDraw(0);
        }
        return game;
    }

    private Game applyGameRule(Game game, String rule) {
        switch (rule) {
            case "NEXT_PLAYER_DRAWS_CARDS":
                game.setCardsToDraw(game.getCardsToDraw() + 2);
                view.printNotification("Gamerule: The next Player will need to draw cards!");
                break;
            case "NEXT_PLAYER_SITS_OUT":
                game = gameService.switchToNextPlayer(game);
                view.printNotification("Gamerule: The next Player sits out!");
                break;
            case "CHANGE_DIRECTION":
                game.setCurrentDirectionIsClockwise(!game.getCurrentDirectionIsClockwise());
                view.printNotification("Gamerule: The direction has switched!");
                break;
            case "WISH_NEW_SYMBOL":
                view.printPlayerWishesSymbol();
                int wishedSymbol = view.getUserInputAsInt();
                game.setCurrentSymbol(Card.Symbol.values()[wishedSymbol]);
                view.printNotification("Gamerule: The current game symbol is now " + Card.Symbol.values()[wishedSymbol]);
                break;
        }
        return game;
    }

}

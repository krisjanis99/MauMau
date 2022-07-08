package impl;

import de.htwberlin.cardManagement.entity.Card;
import de.htwberlin.playerManagement.entity.Player;
import de.htwberlin.cardManagement.export.*;
import de.htwberlin.gameManagement.entity.Game;
import de.htwberlin.gameManagement.export.GameService;
import de.htwberlin.playerManagement.export.PlayerService;
import de.htwberlin.playerManagement.export.VirtualPlayerService;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import export.MauMauUi;
import org.picocontainer.annotations.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MauMauUiController implements MauMauUi {

    @Inject
    MauMauUiView view;

    @Inject
    GameService gameService;

    @Inject
    CardService cardService;

    @Inject
    CardDeckService cardDeckService;

    @Inject
    GameRuleService gameRuleService;

    @Inject
    PlayerService playerService;

    @Inject
    VirtualPlayerService virtualPlayerService;


    @Override
    public void run() {
        view.printWelcomeMsg();

        int playerCount = 0;
        while (playerCount < 2 || playerCount > 4) {
            view.printNotification("How many players will play? The maximal count is 4.");
            playerCount = view.getUserInputAsInt(2, 4);
        }

        int virtualPlayerCount = -1;
        while (virtualPlayerCount >= 5 || virtualPlayerCount < 0) {
            view.printNotification(String.format("How many of the players are virtual? The maximal count is %d.", playerCount));
            virtualPlayerCount = view.getUserInputAsInt(0, playerCount);
        }

        List<Player> playerList = new ArrayList<>();
        for (int i = 1; i <= (playerCount - virtualPlayerCount); i++) {
            view.askForPlayerName(i);
            String name = view.getUserInputAsString();
            playerList.add(playerService.createPlayer(name).get());
        }

        for (int i = 1; i <= virtualPlayerCount; i++) {
            playerList.add(virtualPlayerService.createVirtualPlayer().get());
        }

        Game game = gameService.startNewGame(playerList).get();
        view.printGameStartingMsg(playerList);

        int turnNumber = 0;

        //sometimes the bots play way to long, so the max turns are 300
        while (!game.getGameEnded() && turnNumber<=300) {
            view.printTurnStartingMessage(game, turnNumber);
            game = executeTurn(game);
            for (int i = 0; i < game.getPlayerList().size(); i++) {
                if (game.getPlayerList().get(i).getPlayerCards().isEmpty()) {
                    game.getWinnersRankedList().add(game.getPlayerList().get(i));
                    view.printNotification(String.format("The Player %s finished the game.", game.getPlayerList().get(i).getName()));
                }
            }
            game.getPlayerList().removeAll(game.getWinnersRankedList());

            if (game.getPlayerList().size() < 2) {
                game.setGameEnded(true);
            }
            turnNumber++;
        }

        checkForWinners(game);

    }

    private void checkForWinners(Game game) {

        if(game.getPlayerList().size()>1){
            view.printNotification("The game finished because it took too long. The players are ranked by their number of cards.");
            Collections.sort(game.getPlayerList(), Comparator.comparingInt(p -> p.getPlayerCards().size()));
            game.getWinnersRankedList().addAll(game.getPlayerList());
        }else{
            game.getWinnersRankedList().add(game.getPlayerList().get(0));
        }

        view.printNotification("The game is finished.\nThe Players won in following order:");
        StringBuilder winners = new StringBuilder();
        for (int i = 0; i < game.getWinnersRankedList().size(); i++) {
            winners.append(String.format("\n %d: %s, left cards: %d", (i+1), game.getWinnersRankedList().get(i).getName(), game.getWinnersRankedList().get(i).getPlayerCards().size()));
        }
        view.printNotification(winners.toString());
        view.printNotification("The game has ended!");

    }

    private Game executeTurn(Game game) {

        if (game.getCurrentActivePlayer().getIsVirtualPlayer()){
            return executeVirtualPlayerTurn(game);
        } else {
            return executeRealPlayerMove(game);
        }

    }

    private Game executeRealPlayerMove(Game game) {
        while (true) {
            view.printNotification("The last placed card on the deck is a " + cardService.getCardAsString(game.getPlacedCardDeck().get(game.getPlacedCardDeck().size() - 1)));
            view.printPlayerCards(game.getCurrentActivePlayer());

            int playerInput = view.getUserInputAsInt(0, (game.getCurrentActivePlayer().getPlayerCards().size()));

            if (playerInput == 0) {

                game = gameService.takeTopCardOffDeck(game);
                Card drawnCard = game.getCurrentActivePlayer().getPlayerCards().get(game.getCurrentActivePlayer().getPlayerCards().size() - 1);
                view.printNotification("You drawed: " + cardService.getCardAsString(drawnCard));

            } else if ((playerInput-1) <= game.getCurrentActivePlayer().getPlayerCards().size()) {
                playerInput = playerInput-1;
                Card cardToBePlaced = game.getCurrentActivePlayer().getPlayerCards().get(playerInput);
                boolean placementValid = gameRuleService.cardPlaceable(cardToBePlaced, game.getCurrentSymbol(), game.getCurrentRank());

                if (gameRuleService.checkIfCardHasGameRule(cardToBePlaced).isPresent()) {
                    String rule = gameRuleService.checkIfCardHasGameRule(cardToBePlaced).get();
                    gameService.placeCard(game, cardToBePlaced);
                    view.printNotification("You placed a " + cardService.getCardAsString(cardToBePlaced));
                    game = applyGameRule(game, rule);
                    game = checkIfCardsNeedToBeDrawn(game);
                    return gameService.switchToNextPlayer(game);
                } else if (placementValid) {
                    gameService.placeCard(game, cardToBePlaced);
                    view.printNotification("You placed a " + cardService.getCardAsString(cardToBePlaced));
                    game = checkIfCardsNeedToBeDrawn(game);
                    return gameService.switchToNextPlayer(game);
                } else {
                    view.printNotification("The placement is not valid! Try again!");
                }
            }
        }
    }

    private Game executeVirtualPlayerTurn(Game game) {
        while (true) {

            int virtualPlayerInput = virtualPlayerService.generateRandomMove(0, game.getCurrentActivePlayer().getPlayerCards().size());

            if (virtualPlayerInput == 0) {
                game = gameService.takeTopCardOffDeck(game);
                Card drawnCard = game.getCurrentActivePlayer().getPlayerCards().get(game.getCurrentActivePlayer().getPlayerCards().size() - 1);
                view.printNotification("The virtual Player draws a : " + cardService.getCardAsString(drawnCard));

            } else if (virtualPlayerInput <= game.getCurrentActivePlayer().getPlayerCards().size()) {

                Card cardToBePlaced = game.getCurrentActivePlayer().getPlayerCards().get(virtualPlayerInput);
                boolean placementValid = gameRuleService.cardPlaceable(cardToBePlaced, game.getCurrentSymbol(), game.getCurrentRank());

                if (gameRuleService.checkIfCardHasGameRule(cardToBePlaced).isPresent()) {
                    String rule = gameRuleService.checkIfCardHasGameRule(cardToBePlaced).get();
                    gameService.placeCard(game, cardToBePlaced);
                    view.printNotification("The virtual Player places a  " + cardService.getCardAsString(cardToBePlaced));
                    game = applyGameRule(game, rule);
                    game = checkIfCardsNeedToBeDrawn(game);
                    return gameService.switchToNextPlayer(game);
                } else if (placementValid) {
                    gameService.placeCard(game, cardToBePlaced);
                    view.printNotification("The virtual Player places a : " + cardService.getCardAsString(cardToBePlaced));
                    game = checkIfCardsNeedToBeDrawn(game);
                    return gameService.switchToNextPlayer(game);
                }
            }
        }
    }

    private Game checkIfCardsNeedToBeDrawn(Game game) {
        Card lastPlacedCard = cardDeckService.getLastPlacedCardOnDeck(game.getPlacedCardDeck()).get();
        if (game.getCardsToDraw() > 0 && lastPlacedCard.getRank() != Card.Rank.SEVEN) {
            view.printNotification(String.format("Current Player needs to draw %d additional cards.", game.getCardsToDraw()));
            List<Card> drawnCards = new ArrayList<>();
            for (int i = 0; game.getCardsToDraw() > i; i++) {
                game = gameService.takeTopCardOffDeck(game);
                Card drawnCard = game.getCurrentActivePlayer().getPlayerCards().get(game.getCurrentActivePlayer().getPlayerCards().size() - 1);
                drawnCards.add(drawnCard);
                view.printNotification(String.format("The card %s was drawn.", cardService.getCardAsString(drawnCard)));
            }
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
                int wishedSymbol = -1;
                if (game.getCurrentActivePlayer().getIsVirtualPlayer()){
                    wishedSymbol = virtualPlayerService.generateRandomMove(0, 3);
                }else{
                    view.printNotification("Which Symbol do u wish for?\n 0: CLUBS\n 1: DIAMONDS\n 2: HEARTS\n 3: SPADES");
                    wishedSymbol = view.getUserInputAsInt(0, 3);
                }
                game.setCurrentSymbol(Card.Symbol.values()[wishedSymbol]);
                view.printNotification("Gamerule: The current game symbol is now " + Card.Symbol.values()[wishedSymbol]);
                break;
        }
        return game;
    }

}

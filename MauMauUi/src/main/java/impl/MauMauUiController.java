package impl;

import de.htwberlin.cardManagement.entity.Card;
import de.htwberlin.cardManagement.export.CardDeckService;
import de.htwberlin.cardManagement.export.CardService;
import de.htwberlin.gameManagement.entity.Game;
import de.htwberlin.gameManagement.export.CardNotPlacedException;
import de.htwberlin.gameManagement.export.GameService;
import de.htwberlin.persistGameManagement.export.DAOService;
import de.htwberlin.playerManagement.entity.Player;
import de.htwberlin.playerManagement.export.PlayerCreationFailedException;
import de.htwberlin.playerManagement.export.PlayerService;
import de.htwberlin.playerManagement.export.VirtualPlayerService;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import de.htwberlin.rulesetManagement.export.GameTechnicalErrorException;
import export.GameInitialziationException;
import export.MauMauUi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.picocontainer.annotations.Inject;

import java.util.*;

public class MauMauUiController implements MauMauUi {
    private static final Logger logger = LogManager.getLogger(MauMauUiController.class);

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

    @Inject
    DAOService daoService;


    @Override
    public void run() {
        view.printWelcomeMsg();
        List<Game> availableGames = daoService.findAllGames();
        view.printNotification("Do you want to start a new game or continue an old game? \n0: start a new game\n1: continue a unfinished game");
        int saveGamePlayerInput = view.getUserInputAsInt(0, 1);
        Game game = null;
        if (saveGamePlayerInput == 0) {
            try {
                game = configureNewGame();
            } catch (GameInitialziationException | PlayerCreationFailedException e) {
                logger.debug("An error happened while configuring a new game.");
                e.printStackTrace();
            }
        } else if (availableGames.isEmpty()) {
            view.printNotification("No unfinished games found. Start a new game first.");
            try {
                game = configureNewGame();
            } catch (GameInitialziationException | PlayerCreationFailedException e) {
                logger.debug("An error happened while configuring a new game.");
                e.printStackTrace();
            }
        } else {
            StringBuilder gamesString = new StringBuilder();
            for (Game g : availableGames) {
                gamesString.append(String.format("\n %d: ID: %d, played Turns: %d", availableGames.indexOf(g), g.getId(), g.getTurnNumber()));
            }
            view.printNotification(String.format("Here are all started games: %s", gamesString.toString()));
            int userInput = view.getUserInputAsInt(0, availableGames.size() - 1);
            game = daoService.findGameById(availableGames.get(userInput).getId()).orElseThrow(NoSuchElementException::new);
        }

        //sometimes the bots play way to long, so the max turns are 300
        while (!game.getGameEnded() && game.getTurnNumber() <= 300) {
            daoService.update(game);
            try {
                view.printTurnStartingMessage(game);
            } catch (GameInitialziationException e) {
                logger.debug("error configuring instiallizing the game");
                e.printStackTrace();
            }
            try {
                game = executeTurn(game);
            } catch (CardNotPlacedException e) {
                logger.debug("problem with placing the card");
                e.printStackTrace();
            } catch (GameTechnicalErrorException e) {
                logger.debug("game error while exceuting the turns ");
                e.printStackTrace();
            }
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
            game.setTurnNumber(game.getTurnNumber() + 1);
        }
        checkForWinners(game);
    }


    private Game configureNewGame() throws GameInitialziationException, PlayerCreationFailedException {
        view.printNotification("Configure a new game!");
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
            playerList.add(playerService.createPlayer(name));
        }
        for (int i = 1; i <= virtualPlayerCount; i++) {
            playerList.add(virtualPlayerService.createVirtualPlayer());
        }
        Game game = gameService.startNewGame(playerList).get();
        daoService.persist(game);
        view.printNotification(String.format("A new game with the the ID %d was created.", game.getId()));
        view.printGameStartingMsg(playerList);
        return game;
    }


    private void checkForWinners(Game game) {

        if (game.getPlayerList().size() > 1) {
            view.printNotification("The game finished because it took too long. The players are ranked by their number of cards.");
            Collections.sort(game.getPlayerList(), Comparator.comparingInt(p -> p.getPlayerCards().size()));
            game.getWinnersRankedList().addAll(game.getPlayerList());
        } else {
            game.getWinnersRankedList().add(game.getPlayerList().get(0));
        }
        view.printNotification("The game is finished.\nThe Players won in following order:");
        StringBuilder winners = new StringBuilder();
        for (int i = 0; i < game.getWinnersRankedList().size(); i++) {
            winners.append(String.format("\n %d: %s, left cards: %d", (i + 1), game.getWinnersRankedList().get(i).getName(), game.getWinnersRankedList().get(i).getPlayerCards().size()));
        }
        view.printNotification(winners.toString());
        view.printNotification("The game has ended!");
        daoService.removeGame(game);
    }


    private Game executeTurn(Game game) throws CardNotPlacedException, GameTechnicalErrorException {
        if (game.getCurrentActivePlayer().getIsVirtualPlayer()) {
            return executeVirtualPlayerTurn(game);
        } else {
            return executeRealPlayerMove(game);
        }
    }


    private Game executeRealPlayerMove(Game game) throws GameTechnicalErrorException, CardNotPlacedException {
        while (true) {
            view.printNotification("The last placed card on the deck is a " + cardService.getCardAsString(game.getPlacedCardDeck().get(game.getPlacedCardDeck().size() - 1)));
            view.printPlayerCards(game.getCurrentActivePlayer());
            int playerInput = view.getUserInputAsInt(0, (game.getCurrentActivePlayer().getPlayerCards().size()));
            if (playerInput == 0) {
                game = gameService.takeTopCardOffDeck(game);
                Card drawnCard = game.getCurrentActivePlayer().getPlayerCards().get(game.getCurrentActivePlayer().getPlayerCards().size() - 1);
                view.printNotification("You drawed: " + cardService.getCardAsString(drawnCard));
            } else if ((playerInput - 1) <= game.getCurrentActivePlayer().getPlayerCards().size()) {
                playerInput = playerInput - 1;
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

    private Game executeVirtualPlayerTurn(Game game) throws CardNotPlacedException, GameTechnicalErrorException {
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
        Card lastPlacedCard = cardDeckService.getLastPlacedCardOnDeck(game.getPlacedCardDeck());
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
                view.printNotification("Gamerule: The direction of the game has switched!");
                break;
            case "WISH_NEW_SYMBOL":
                int wishedSymbol = -1;
                if (game.getCurrentActivePlayer().getIsVirtualPlayer()) {
                    wishedSymbol = virtualPlayerService.generateRandomMove(0, 3);
                } else {
                    view.printNotification("Which Symbol do you choose?\n 0: CLUBS\n 1: DIAMONDS\n 2: HEARTS\n 3: SPADES");
                    wishedSymbol = view.getUserInputAsInt(0, 3);
                }
                game.setCurrentSymbol(Card.Symbol.values()[wishedSymbol]);
                view.printNotification(String.format("Gamerule: The player gets to choose a symbol. \nThe symbol %s was chosen.", Card.Symbol.values()[wishedSymbol]));
                break;
        }
        return game;
    }
}

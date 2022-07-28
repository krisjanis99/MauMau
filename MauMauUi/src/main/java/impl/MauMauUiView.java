package impl;


import de.htwberlin.gameManagement.entity.Game;
import de.htwberlin.playerManagement.entity.Player;
import de.htwberlin.rulesetManagement.export.GameTechnicalErrorException;
import export.GameInitialziationException;

import java.util.List;
import java.util.Scanner;

public class MauMauUiView {

    Scanner reader;

    public MauMauUiView() {
        reader = new Scanner(System.in);
    }

    void printSep() {
        System.out.println("........................................................................ ");
    }

    void printWelcomeMsg() {
        printSep();
        System.out.println(".....................Welcome to the Mau Mau Game..................... ");
        System.out.println("..................The famous card game from Germany.................. ");
        printSep();
        System.out.println("....................This game saves automatically.................... ");
        printSep();
    }

    int getUserInputAsInt(int min, int max) {
        System.out.printf("Enter a number between %d and %d: \n", min, max);
        int input = -1000;
        while (reader.hasNext()) {
            if (reader.hasNextInt()) {
                input = reader.nextInt();
                if (input >= min && input <= max) {
                    reader.nextLine();
                    return input;
                }
            }
            System.out.println("Invalid input.");
            reader.nextLine();
        }
        return input;
    }

    String getUserInputAsString() {
        System.out.println("Enter a String: ");
        String input = reader.nextLine();
        while (input.isEmpty()) {
            System.out.println("The input was empty. Please enter a String: ");
            input = reader.nextLine();
        }
        return input;
    }

    void askForPlayerName(int playerNumber) {
        printSep();
        System.out.printf("................. What is the name of the %d. player?.................%n", playerNumber);
    }

    void printGameStartingMsg(List<Player> playerList) throws GameInitialziationException {
        if (playerList == null || playerList.size() < 2) {
            throw new GameInitialziationException("no enough players are present");
        }
        printSep();
        System.out.println("The game is about to start!");
        System.out.println("Here are the players which will play:");
        for (Player player : playerList) {
            System.out.println(player.getName());
        }
        printSep();
        System.out.println("Each player gets 5 cards!");
        printSep();
    }

    void printTurnStartingMessage(Game game) throws GameInitialziationException {
        if (game == null) {
            throw new GameInitialziationException("Game argument is null");
        }
        printSep();
        System.out.printf("Time for the next turn! It's turn %d. \n", game.getTurnNumber());
        System.out.printf("The player %s is up!\n", game.getCurrentActivePlayer().getName());
        System.out.printf("The last placed card was %s %s!\n", game.getPlacedCardDeck().get(game.getPlacedCardDeck().size() - 1).getSymbol(),
                game.getPlacedCardDeck().get(game.getPlacedCardDeck().size() - 1).getRank());
        System.out.printf("The current Symbol in game is %s!\n", game.getCurrentSymbol());
        System.out.printf("You will need to draw additional %d cards!\n", game.getCardsToDraw());
        printSep();
    }

    void printPlayerCards(Player currentPlayer) throws GameTechnicalErrorException {
        if (currentPlayer.getPlayerCards().size() == 0) {
            throw new GameTechnicalErrorException("player has no cards");
        }
        printSep();
        System.out.printf("Here are your current Cards, %s!", currentPlayer.getName());
        int i;
        System.out.printf("\n %d: Draw a new card!", 0);
        for (i = 0; i < currentPlayer.getPlayerCards().size(); i++) {
            System.out.printf("\n %d: %s %s", (i + 1), currentPlayer.getPlayerCards().get(i).getSymbol(), currentPlayer.getPlayerCards().get(i).getRank());
        }
        System.out.println("\nType the number of card you want to place");
    }

    public void printNotification(String msg) {
        printSep();
        System.out.println(msg);
        printSep();
    }

}

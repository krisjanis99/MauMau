package impl;


import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.gameManagement.export.Game;

import java.util.List;
import java.util.Scanner;

public class MauMauUiView {

    Scanner reader;

    public MauMauUiView() {
        reader = new Scanner(System.in);
    }

    void printWelcomeMsg() {
        System.out.println("...................................................................... ");
        System.out.println("..................... Welcome to the Mau Mau Game..................... ");
        System.out.println("...................................................................... ");
    }

    int getUserInputAsInt() {
        System.out.println("Enter a number: ");
        int input = 0;
        while (reader.hasNext()) {
            if (reader.hasNextInt()) {
                input = reader.nextInt();
                if (input >= 0) {
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
        System.out.println("...................................................................... ");
        System.out.printf("................. What is the name of the %d. player?.................%n", playerNumber);
    }

    void printGameStartingMsg() {
        System.out.println("....................................................................... ");
        System.out.println("The game is about to start!");
        System.out.println("Each player gets 5 cards!");
        System.out.println("........................................................................ ");
    }

    void printTurnStartingMessage(Game game) {
        System.out.println("....................................................................... ");
        System.out.println("Time for the next turn!");
        System.out.printf("The player %s is up!\n", game.getCurrentActivePlayer().getName());
        System.out.printf("The last placed card was %s %s!\n", game.getPlacedCardDeck().get(game.getPlacedCardDeck().size() - 1).getSymbol(),
                game.getPlacedCardDeck().get(game.getPlacedCardDeck().size() - 1).getRank());
        System.out.printf("The current Symbol in game is %s!\n", game.getCurrentSymbol());
        System.out.printf("You will need to draw additional %d cards!\n", game.getCardsToDraw());
        System.out.println("....................................................................... ");
    }

    void printPlayerCards(Player currentPlayer) {
        System.out.println("....................................................................... ");
        System.out.printf("Here are your current Cards, %s!", currentPlayer.getName());
        int i;
        for (i = 0; i < currentPlayer.getPlayerCards().size(); i++) {
            System.out.printf("\n %d: %s %s", (i), currentPlayer.getPlayerCards().get(i).getSymbol(), currentPlayer.getPlayerCards().get(i).getRank());
        }
        System.out.printf("\n %d: Draw a new card!", (i));
        System.out.println("\nType the number of card you want to place");
    }


    public void printPlayerWishesSymbol() {
        System.out.println(".......................................................................\n");
        for (int i = 0; i < Card.Symbol.values().length; i++) {
            System.out.printf(" %d: %s\n", (i), Card.Symbol.values()[i]);
        }
    }

    void notifyPlayerAboutTheDrawnCards(List<Card> drawnCards) {
        System.out.println("....................................................................... ");
        System.out.println("You drawed the following cards:");
        for (int i = 0; i < drawnCards.size(); i++) {
            System.out.printf("\n %d: %s %s", (i), drawnCards.get(i).getSymbol(), drawnCards.get(i).getRank());
        }
        System.out.println("....................................................................... ");
    }

    ;

    public void printNotification(String msg) {
        System.out.println("....................................................................... ");
        System.out.println(msg);
        System.out.println("....................................................................... ");
    }

    public void printWinnerList(List<Player> winnersRankedList) {
        System.out.println("....................................................................... ");
        System.out.println("The game is finished.\n The Players won in following order:");
        for (int i = 0; i < winnersRankedList.size(); i++) {
            System.out.printf("\n %d: %s", (i), winnersRankedList.get(i).getName());
        }
        System.out.println("....................................................................... ");

    }
}

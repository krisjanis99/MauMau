package de.htwberlin.gameManagement;

import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.export.CardDeckService;
import de.htwberlin.gameManagement.export.Game;
import de.htwberlin.gameManagement.export.GameService;
import de.htwberlin.gameManagement.impl.GameServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private CardDeckService cardDeckServiceMock;

    private GameService gameService;

    @Before
    public void setup() {

        gameService = new GameServiceImpl(cardDeckServiceMock);

    }

    Map<Card.Rank, String> getRuleSet() {
        return Map.ofEntries(
                entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS"),
                entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT"),
                entry(Card.Rank.JACK, "WISH_NEW_SYMBOL")
        );
    }

    List<Player> getListOfPlayers() {
        Player player1 = new Player(1, "player1", new ArrayList<Card>(), false, false);
        Player player2 = new Player(2, "player2", new ArrayList<Card>(), false, false);
        return List.of(player1, player2);
    }

    List<Card> getFullCardDeck() {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                cards.add(new Card(Card.Rank.values()[j], Card.Symbol.values()[i]));
            }
        }

        return cards;
    }

    @Test
    public void startNewGame_successfulStart() {
        //given
        List<Card> cards = getFullCardDeck();
        List<Player> playerList = getListOfPlayers();
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(cardDeckServiceMock.shuffleDeck(anyList())).thenReturn(cards);

        //when
        Optional<Game> result = gameService.startNewGame(playerList);

        //then
        verify(cardDeckServiceMock).getNewDeck();
        verify(cardDeckServiceMock).shuffleDeck(anyList());
        assertTrue(result.isPresent());
        assertEquals(playerList, result.get().getPlayerList());
        assertEquals(cards.size() - 10, result.get().getCardDeck().size());
    }

    @Test
    public void startNewGame_failedStart() {
        //given
        List<Card> cards = getFullCardDeck();
        List<Player> playerList = null;
        Map<Card.Rank, String> gameRuleSet = getRuleSet();
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(cardDeckServiceMock.shuffleDeck(anyList())).thenReturn(cards);

        //when
        Optional<Game> result = gameService.startNewGame(playerList);

        //then
        verify(cardDeckServiceMock).getNewDeck();
        verify(cardDeckServiceMock).shuffleDeck(anyList());
        assertTrue(result.isEmpty());
    }


    @Test
    public void placeCard_placingSuccessful() {
        //given
        List<Card> cards = getFullCardDeck();
        List<Player> playerList = getListOfPlayers();
        Map<Card.Rank, String> gameRuleSet = getRuleSet();
        Card card = new Card(Card.Rank.TEN, Card.Symbol.HEARTS);
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(cardDeckServiceMock.shuffleDeck(anyList())).thenReturn(cards);
        Game game = gameService.startNewGame(playerList).get();

        //when
        Game result = gameService.placeCard(game, card);

        //then
        verify(cardDeckServiceMock).getNewDeck();
        verify(cardDeckServiceMock).shuffleDeck(anyList());
        assertEquals(5, result.getCurrentActivePlayer().getPlayerCards().size());
        assertEquals(18, result.getCardDeck().size());
    }

    @Test
    public void placeCard_placingNotSuccessful() {
        //given
        List<Card> cards = getFullCardDeck();
        cards.add(new Card(Card.Rank.NINE, Card.Symbol.DIAMONDS));
        List<Player> playerList = getListOfPlayers();
        Map<Card.Rank, String> gameRuleSet = getRuleSet();
        Card card = new Card(Card.Rank.TEN, Card.Symbol.SPADES);
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(cardDeckServiceMock.shuffleDeck(anyList())).thenReturn(cards);
        Game game = gameService.startNewGame(playerList).get();

        //when
        Game result = gameService.placeCard(game, card);

        //then
        verify(cardDeckServiceMock).getNewDeck();
        verify(cardDeckServiceMock).shuffleDeck(anyList());
        assertNotEquals(result.getCardDeck().get(result.getCardDeck().size() - 1), card);
    }

    @Test
    public void takeTopCardOffDeck_deckHasMultipleCards() {
        //given
        List<Card> cards = getFullCardDeck();
        List<Player> playerList = getListOfPlayers();
        Player activePlayer = playerList.get(0);
        Card drawnCard = cards.get(cards.size() - 1);
        Map<Card.Rank, String> gameRuleSet = getRuleSet();
        Card card = new Card(Card.Rank.TEN, Card.Symbol.SPADES);
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(cardDeckServiceMock.shuffleDeck(anyList())).thenReturn(cards);
        Game game = gameService.startNewGame(playerList).get();
        game.setCurrentActivePlayer(activePlayer);

        //when
        Game result = gameService.takeTopCardOffDeck(game);

        //then
        verify(cardDeckServiceMock).getNewDeck();
        verify(cardDeckServiceMock).shuffleDeck(anyList());
        assertTrue(result.getCurrentActivePlayer().getPlayerCards().contains(drawnCard));
    }

    @Test
    public void takeTopCardOffDeck_deckIsEmptyAndNoCardsAreDrawed() {
        //given
        List<Card> cards = getFullCardDeck();
        List<Player> playerList = getListOfPlayers();
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(cardDeckServiceMock.shuffleDeck(anyList())).thenReturn(cards);
        Game game = gameService.startNewGame(playerList).get();
        game.setCardDeck(new ArrayList<>());

        //when
        Game result = gameService.takeTopCardOffDeck(game);

        //then
        verify(cardDeckServiceMock).getNewDeck();
        verify(cardDeckServiceMock).shuffleDeck(anyList());
        assertTrue(game.getCardDeck().isEmpty());
        assertEquals(game, result);
    }

    @Test
    public void switchToNextPlayer_switchClockwiseSuccessful() {
        //given
        List<Card> cards = getFullCardDeck();
        List<Player> playerList = getListOfPlayers();
        Card card = new Card(Card.Rank.TEN, Card.Symbol.HEARTS);
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(cardDeckServiceMock.shuffleDeck(anyList())).thenReturn(cards);
        Game game = gameService.startNewGame(playerList).get();
        Player lastPlayer = playerList.get(0);
        game.setCurrentActivePlayer(lastPlayer);
        game.setCurrentDirectionIsClockwise(true);

        //when
        Game result = gameService.switchToNextPlayer(game);

        //then
        verify(cardDeckServiceMock).getNewDeck();
        verify(cardDeckServiceMock).shuffleDeck(anyList());
        assertNotEquals(lastPlayer, game.getCurrentActivePlayer());
        assertEquals(playerList.indexOf(game.getCurrentActivePlayer()), 1);
    }

    @Test
    public void switchToNextPlayer_switchAntiClockwiseSuccessful() {
        //given
        List<Card> cards = getFullCardDeck();
        List<Player> playerList = getListOfPlayers();
        Card card = new Card(Card.Rank.TEN, Card.Symbol.HEARTS);
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(cardDeckServiceMock.shuffleDeck(anyList())).thenReturn(cards);
        Game game = gameService.startNewGame(playerList).get();
        Player lastPlayer = playerList.get(1);
        game.setCurrentActivePlayer(lastPlayer);
        game.setCurrentDirectionIsClockwise(false);

        //when
        Game result = gameService.switchToNextPlayer(game);

        //then
        verify(cardDeckServiceMock).getNewDeck();
        verify(cardDeckServiceMock).shuffleDeck(anyList());
        assertNotEquals(lastPlayer, game.getCurrentActivePlayer());
        assertEquals(playerList.indexOf(game.getCurrentActivePlayer()), 0);
    }

}

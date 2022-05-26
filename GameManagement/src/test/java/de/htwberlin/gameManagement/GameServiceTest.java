package de.htwberlin.gameManagement;

import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.export.CardDeckService;
import de.htwberlin.gameManagement.export.Game;
import de.htwberlin.gameManagement.export.GameService;
import de.htwberlin.gameManagement.impl.GameServiceImpl;
import de.htwberlin.rulesetManagement.export.GameRuleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class GameServiceTest {

    @Mock
    private GameRuleService gameRuleServiceMock;

    @Mock
    private CardDeckService cardDeckServiceMock;

    private GameService gameService;

    @Before
    public void setup() {

        gameService = new GameServiceImpl();

    }

    Map<Card.Rank, String> getRuleSet() {
        return Map.ofEntries(
                entry(Card.Rank.SEVEN, "NEXT_PLAYER_DRAWS_CARDS"),
                entry(Card.Rank.EIGHT, "NEXT_PLAYER_SITS_OUT"),
                entry(Card.Rank.JACK, "WISH_NEW_SYMBOL")
        );
    }

    List<Player> getListOfPlayers() {
        Player player1 = new Player(1, "player1");
        Player player2 = new Player(2, "player2");
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
        Map<Card.Rank, String> gameRuleSet = getRuleSet();
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(gameRuleServiceMock.getGameRuleSet(anyInt())).thenReturn(Optional.of(gameRuleSet));

        //when
        Optional<Game> result = gameService.startNewGame(playerList, 1);

        //then
        assertTrue(result.isPresent());
        assertEquals(playerList, result.get().getPlayerList());
        assertEquals(gameRuleSet, result.get().getGameRuleSet());
        assertEquals(cards, result.get().getCardDeck());
    }

    @Test
    public void startNewGame_failedStart() {
        //given
        List<Card> cards = getFullCardDeck();
        List<Player> playerList = null;
        Map<Card.Rank, String> gameRuleSet = getRuleSet();
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(gameRuleServiceMock.getGameRuleSet(anyInt())).thenReturn(Optional.of(gameRuleSet));

        //when
        Optional<Game> result = gameService.startNewGame(playerList, 5);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    public void cardPlaceable_CardCanBePlaced() {
        //given
        List<Card> cards = List.of(new Card(Card.Rank.NINE, Card.Symbol.DIAMONDS));
        Card card = new Card(Card.Rank.TEN, Card.Symbol.DIAMONDS);
        Map<Card.Rank, String> gameRuleSet = getRuleSet();

        //when then
        assertTrue(gameService.cardPlaceable(card, cards, gameRuleSet));
    }

    @Test
    public void cardPlaceable_CardCannotBePlaced() {
        //given
        List<Card> cards = List.of(new Card(Card.Rank.NINE, Card.Symbol.DIAMONDS));
        Card card = new Card(Card.Rank.TEN, Card.Symbol.HEARTS);
        Map<Card.Rank, String> gameRuleSet = getRuleSet();

        //when then
        assertFalse(gameService.cardPlaceable(card, cards, gameRuleSet));
    }

    @Test
    public void placeCard_placingSuccessful() {
        //given
        List<Card> cards = List.of(new Card(Card.Rank.NINE, Card.Symbol.HEARTS));
        List<Player> playerList = getListOfPlayers();
        Map<Card.Rank, String> gameRuleSet = getRuleSet();
        Card card = new Card(Card.Rank.TEN, Card.Symbol.HEARTS);
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(gameRuleServiceMock.getGameRuleSet(anyInt())).thenReturn(Optional.of(gameRuleSet));
        Game game = gameService.startNewGame(playerList, 1).get();

        //when
        Game result = gameService.placeCard(game, card);

        //then
        assertEquals(result.getCardDeck().get(result.getCardDeck().size() - 1), card);
    }

    @Test
    public void placeCard_placingNotSuccessful() {
        //given
        List<Card> cards = List.of(new Card(Card.Rank.NINE, Card.Symbol.DIAMONDS));
        List<Player> playerList = getListOfPlayers();
        Map<Card.Rank, String> gameRuleSet = getRuleSet();
        Card card = new Card(Card.Rank.TEN, Card.Symbol.SPADES);
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(gameRuleServiceMock.getGameRuleSet(anyInt())).thenReturn(Optional.of(gameRuleSet));
        Game game = gameService.startNewGame(playerList, 1).get();

        //when
        Game result = gameService.placeCard(game, card);

        //then
        assertNotEquals(result.getCardDeck().get(result.getCardDeck().size() - 1), card);
    }

    @Test
    public void takeTopCardOffDeck_deckHasMultipleCards() {
        //given
        List<Card> cards = getFullCardDeck();
        List<Player> playerList = getListOfPlayers();
        Player activePlayer = playerList.get(0);
        Card drawedCard = cards.get(cards.size() - 1);
        Map<Card.Rank, String> gameRuleSet = getRuleSet();
        Card card = new Card(Card.Rank.TEN, Card.Symbol.SPADES);
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(gameRuleServiceMock.getGameRuleSet(anyInt())).thenReturn(Optional.of(gameRuleSet));
        Game game = gameService.startNewGame(playerList, 1).get();
        game.setCurrentActivePlayer(activePlayer);

        //when
        Game result = gameService.takeTopCardOffDeck(game);

        //then
        assertTrue(result.getCurrentActivePlayer().getPlayerCards().contains(drawedCard));
    }

    @Test
    public void takeTopCardOffDeck_deckIsEmptyAndNoCardsAreDrawed() {
        //given
        List<Card> cards = List.of();
        List<Player> playerList = getListOfPlayers();
        Player activePlayer = playerList.get(0);
        activePlayer.setPlayerCards(List.of(new Card(Card.Rank.NINE, Card.Symbol.DIAMONDS)));
        Map<Card.Rank, String> gameRuleSet = getRuleSet();
        when(cardDeckServiceMock.getNewDeck()).thenReturn(cards);
        when(gameRuleServiceMock.getGameRuleSet(anyInt())).thenReturn(Optional.of(gameRuleSet));
        Game game = gameService.startNewGame(playerList, 1).get();
        game.setCurrentActivePlayer(activePlayer);

        //when
        Game result = gameService.takeTopCardOffDeck(game);

        //then
        assertEquals(activePlayer.getPlayerCards(), result.getCurrentActivePlayer().getPlayerCards());
    }

    @Test
    public void checkIfCardHasGameRule_testNonRuleCard() {
        //given
        Map<Card.Rank, String> ruleset = getRuleSet();
        Card normalCard = new Card(Card.Rank.KING, Card.Symbol.CLUBS);

        //when
        Optional<String> rule = gameService.checkIfCardHasGameRule(ruleset, normalCard);

        //then
        assertTrue(rule.isEmpty());
    }

    @Test
    public void checkIfCardHasGameRule_testRuleCard() {
        //given
        Map<Card.Rank, String> ruleset = getRuleSet();
        Card ruleCard = new Card(Card.Rank.SEVEN, Card.Symbol.CLUBS);

        //when
        Optional<String> rule = gameService.checkIfCardHasGameRule(ruleset, ruleCard);

        //then
        assertTrue(rule.isPresent());
        assertEquals("NEXT_PLAYER_DRAWS_CARDS", rule.get());

    }


}

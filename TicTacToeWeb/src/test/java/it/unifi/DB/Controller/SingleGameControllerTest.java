package it.unifi.DB.Controller;

import it.unifi.gameutility.Board;
import it.unifi.gameutility.Player;
import it.unifi.interfaces.SingleGameDatabase;
import it.unifi.utility.SingleGame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SingleGameControllerTest {


    SingleGameController singleGameController;
    SingleGameDatabase singleGameDatabase;
    private List<SingleGame> singleGameList;


    @Before
    public void setUp() throws Exception {
        singleGameDatabase = mock(SingleGameDatabase.class);
        singleGameController = new SingleGameController(singleGameDatabase);
        singleGameList = new ArrayList<>();
        when(singleGameDatabase.getAllSingleGame()).thenReturn(singleGameList);
    }


    private SingleGame createDummySingleGame(String username) {
        Board b = new Board((short) 3);
        Player p = new Player(username);
        SingleGame s = new SingleGame(b, p);
        singleGameList.add(s);
        return new SingleGame(b, p);

    }

    @Test
    public void getAllSingleGameWithOneGame() throws Exception {
        SingleGame s1 = createDummySingleGame("alfredo");
        List<SingleGame> retrievedList = singleGameController.getAllSingleGame();
        verify(singleGameDatabase).getAllSingleGame();
        assertEquals(retrievedList.size(), 1);
        /*
        TODO: come posso fare la verifica dei due giochi se sono uguali?
        non posso fare la verifica che i due
        giochi siano uguali per via del seriale generato casualmente
        ci sono soluzioni?
         */
    }

    @Test
    public void getAllSingleGameWithNoGame() throws Exception {
        List<SingleGame> retrievedList = singleGameController.getAllSingleGame();
        verify(singleGameDatabase).getAllSingleGame();
        assertEquals(retrievedList.size(), 0);
    }

    @Test
    public void getAllSingleGameWithTwoGame() throws Exception {
        createDummySingleGame("alfredo");
        createDummySingleGame("laura");
        List<SingleGame> retrievedList = singleGameController.getAllSingleGame();
        verify(singleGameDatabase).getAllSingleGame();
        assertEquals(retrievedList.size(), 2);
    }

    @Test
    public void getSingleGameByUsername() throws Exception {
        SingleGame s1 = new SingleGame(new Board((short) 3), new Player("tommaso"));
        List<SingleGame> singleGames = new ArrayList<>();
        singleGames.add(s1);
        when(singleGameDatabase.getSingleGameByUsername("tommaso")).thenReturn(singleGames);
        List<SingleGame> singleGames1 = singleGameController.getSingleGameByUsername("tommaso");
        verify(singleGameDatabase).getSingleGameByUsername("tommaso");
        assertEquals(singleGames1.size(), 1);
    }

    @Test
    public void addSingleGame() throws Exception {
        SingleGame s1 = new SingleGame(new Board((short) 3), new Player("luca"));
        singleGameController.addSingleGame(s1);
        verify(singleGameDatabase).addSingleGame(s1);
    }

}
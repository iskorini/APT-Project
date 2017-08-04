package it.unifi.DB.Controller;

import it.unifi.gameutility.Player;
import it.unifi.interfaces.UserDatabase;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {


    private UserDatabase userDatabase;
    private List<Player> playerList;
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        playerList = new LinkedList<>();
        userDatabase = mock(UserDatabase.class);
        userController = new UserController(userDatabase);
        when(userDatabase.getAllUser()).thenReturn(playerList);
    }

    @Test
    public void getAllUserWithOneUser() throws Exception {
        Player p1 = new Player("pippo");
        playerList.add(p1);
        assertEquals(1, userController.getAllUser().size());
        verify(userDatabase).getAllUser();
    }

    @Test
    public void getAllUserWithNoUser() throws Exception {
        assertEquals(0, userController.getAllUser().size());
        verify(userDatabase).getAllUser();
    }

    @Test
    public void getAllUserWithMoreUser() throws Exception {
        Player p1 = new Player("pippo");
        Player p2 = new Player("caio");
        playerList.add(p1);
        playerList.add(p2);
        assertEquals(2, userController.getAllUser().size());
        verify(userDatabase).getAllUser();
    }

    @Test
    public void getUserByID() throws Exception {
        Player p1 = new Player("giovanni");
        when(userDatabase.getUserByID("giovanni")).thenReturn(p1);
        assertEquals(p1.getUsername(), userController.getUserByID("giovanni").getUsername());
        verify(userDatabase).getUserByID("giovanni");
    }

    @Test
    public void addUser() throws Exception {
        Player p1 = new Player("paolo");
        userController.addUser(p1);
        verify(userDatabase).addUser(p1);
    }

    @Test
    public void updatePlayer() throws Exception {
        Player p1 = new Player("ermenegildo");
        userController.updatePlayer(p1);
        verify(userDatabase).updateUser(p1);
    }

}
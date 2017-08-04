package it.unifi.DB.Controller;

import it.unifi.exception.EntryAlreadyInsertedException;
import it.unifi.exception.UserNotExistingException;
import it.unifi.gameutility.Player;
import it.unifi.interfaces.UserDatabase;

import java.util.List;

public class UserController {


    private UserDatabase userDatabase;

    public UserController(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public List<Player> getAllUser() {
        return this.userDatabase.getAllUser();
    }

    public Player getUserByID(String username) {
        return this.userDatabase.getUserByID(username);
    }

    public void addUser(Player p) throws EntryAlreadyInsertedException {
        this.userDatabase.addUser(p);
    }

    public void updatePlayer(Player p) throws UserNotExistingException {
        this.userDatabase.updateUser(p);
    }
}

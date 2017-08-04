package it.unifi.interfaces;

import it.unifi.exception.EntryAlreadyInsertedException;
import it.unifi.exception.UserNotExistingException;
import it.unifi.gameutility.Player;

import java.util.List;

public interface UserDatabase {

    List<Player> getAllUser();

    Player getUserByID(String username);

    boolean addUser(Player p) throws EntryAlreadyInsertedException;

    boolean updateUser(Player p) throws UserNotExistingException;

}

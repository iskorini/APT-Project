package it.unifi.interfaces;

import it.unifi.exception.EntryAlreadyInsertedException;
import it.unifi.exception.UserNotExistingException;
import it.unifi.gameutility.Player;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserDatabase {

    List<Player> getAllUser();

    Player getUserByID(String username);

    boolean addUser(Player p) throws EntryAlreadyInsertedException;

    boolean updateUser(Player p) throws UserNotExistingException;

}

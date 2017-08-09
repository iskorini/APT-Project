package it.unifi.interfaces;

import it.unifi.exception.EntryAlreadyInsertedException;
import it.unifi.exception.UserNotExistingException;
import it.unifi.utility.SingleGame;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SingleGameDatabase {

    List<SingleGame> getAllSingleGame();

    List<SingleGame> getSingleGameByUsername(String username);

    boolean addSingleGame(SingleGame singleGame) throws EntryAlreadyInsertedException;


}

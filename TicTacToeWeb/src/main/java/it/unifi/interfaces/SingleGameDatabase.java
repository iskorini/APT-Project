package it.unifi.interfaces;

import it.unifi.exception.EntryAlreadyInsertedException;
import it.unifi.exception.UserNotExistingException;
import it.unifi.utility.SingleGame;

import java.util.List;

public interface SingleGameDatabase {

    List<SingleGame> getAllSingleGame();

    List<SingleGame> getSingleGameByUsername(String username);

    boolean addSingleGame(SingleGame singleGame) throws EntryAlreadyInsertedException;


}

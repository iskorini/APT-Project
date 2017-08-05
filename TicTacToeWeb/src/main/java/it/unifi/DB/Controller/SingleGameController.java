package it.unifi.DB.Controller;

import it.unifi.exception.EntryAlreadyInsertedException;
import it.unifi.exception.UserNotExistingException;
import it.unifi.interfaces.SingleGameDatabase;
import it.unifi.utility.SingleGame;

import java.util.List;


public class SingleGameController {


    private SingleGameDatabase singleGameDatabase;

    public SingleGameController(SingleGameDatabase singleGameDatabase) {
        this.singleGameDatabase = singleGameDatabase;
    }


    public List<SingleGame> getAllSingleGame() {
        return this.singleGameDatabase.getAllSingleGame();
    }

    public List<SingleGame> getSingleGameByUsername(String username) {
        return this.singleGameDatabase.getSingleGameByUsername(username);
    }

    public void addSingleGame(SingleGame singleGame) throws EntryAlreadyInsertedException {
        this.singleGameDatabase.addSingleGame(singleGame);
    }
    /*
    TODO: vedere eccezione in questo metodo
     */

}

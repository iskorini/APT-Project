package it.unifi.AI;

import it.unifi.exception.NotValidValueException;
import it.unifi.gameutility.Board;
import it.unifi.interfaces.NPC;

public abstract class Opponent implements NPC {

    private Board board;

    @Override
    public Board doMove(Board b){
        return null;
    }

}

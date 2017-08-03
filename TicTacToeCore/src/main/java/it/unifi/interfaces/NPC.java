package it.unifi.interfaces;

import it.unifi.exception.NotValidValueException;
import it.unifi.gameutility.Board;

public interface NPC {

    public Board doMove(Board b);

}

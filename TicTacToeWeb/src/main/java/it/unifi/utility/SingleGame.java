package it.unifi.utility;

import it.unifi.gameutility.Board;
import it.unifi.gameutility.Player;

public class SingleGame {

    private Board board;
    private Player player;

    public SingleGame(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer() {
        return player;
    }
}

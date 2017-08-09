package it.unifi.utility;

import it.unifi.gameutility.Board;
import it.unifi.gameutility.Player;

import java.util.UUID;

public class SingleGame {

    private Board board;
    private Player player;
    private String gameID;
    private String result;

    public SingleGame() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleGame that = (SingleGame) o;

        return gameID != null ? gameID.equals(that.gameID) : that.gameID == null;
    }

    @Override
    public int hashCode() {
        return gameID != null ? gameID.hashCode() : 0;
    }

    public SingleGame(Board board, Player player) {
        this.board = board;
        this.player = player;
        this.gameID = UUID.randomUUID().toString();
        this.result = "0";
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

    public String getGameID() {
        return gameID;
    }
}

package it.unifi.gameutility;


import it.unifi.exception.NotValidValueException;

import java.util.Arrays;

/**
 * Created by iskor on 16/07/2017.
 */
public class Board {

    private short board[][];
    private short dimension;

    public Board() {
    }

    public Board(short a) {
        this.board = new short[a][a];
        this.dimension = a;
        this.initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board.length; j++) {
                board[i][j] = -1;
            }
        }
    }

    public short getPosition(short a, short b) throws IndexOutOfBoundsException {
        return this.board[a][b];
    }

    public void setPosition(short a, short b, short value) throws NotValidValueException {
        try {
            if (value != 0 || value != 1) {
                throw new NotValidValueException(value+" is not valid");
            } else {
                board[a][b] = value;
            }

        } catch (NotValidValueException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public short[][] getBoard() {
        return board;
    }


    public short getDimension() {
        return dimension;
    }



}

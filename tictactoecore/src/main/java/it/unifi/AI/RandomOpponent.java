package it.unifi.AI;

import it.unifi.exception.NotValidValueException;
import it.unifi.gameutility.Board;

public class RandomOpponent extends Opponent {


    @Override
    public Board doMove(Board b){
        Board boardAfterMove = new Board(b);
        short[] cell = findRandomCell(boardAfterMove);
        try {
            boardAfterMove.setPosition(cell[0], cell[1], (short)1);

        } catch (NotValidValueException e) {
            e.printStackTrace();
        }
        return boardAfterMove;
    }

    public short[] findRandomCell(Board board){
        int cell = generateRandomCell(board);
        for(short i = 0; i < board.getDimension(); i++){
            for(short j = 0; j < board.getDimension(); j++){
                if(board.getPosition(i, j) == -1){
                    if(cell == 0){
                        return new short[]{i, j};
                    }
                    cell--;
                }
            }
        }
        return new short[0];
    }

    public int generateRandomCell(Board board){
        return (int)(Math.random() * (countFreeCells(board)));
    }

    public int countFreeCells(Board board){
        int count = 0;
        for(short i = 0; i < 3; i++){
            for(short j = 0; j < 3; j++){
                if (board.getPosition(i, j) == -1){
                    count++;
                }
            }
        }
        return count;
    }
}
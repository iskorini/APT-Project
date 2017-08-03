package it.unifi.AI;

import it.unifi.exception.NotValidValueException;
import it.unifi.gameutility.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomOpponentTest {
    private RandomOpponent randomOpponent;
    private Board board;

    @Before
    public void init(){
        board = new Board((short)3);
        randomOpponent = new RandomOpponent();
    }


    @Test
    public void testCountFreeCells() throws NotValidValueException {
        Board exampleBoard = exampleBoard();
        assertEquals(6, randomOpponent.countFreeCells(exampleBoard));
    }

    @Test
    public void testSelectedCellIsInRange() throws NotValidValueException {
        Board exampleBoard = exampleBoard();
        for(int i = 0; i < 100; i++){
            int cell = randomOpponent.generateRandomCell(exampleBoard);
            assertTrue(cell>=0);
            assertTrue(cell<6);
        }
    }

    @Test
    public void testSelectedCellIsFree() throws NotValidValueException {
        Board exampleBoard = exampleBoard();
        for(int i = 0; i<100; i++){
            short[] cell = randomOpponent.findRandomCell(exampleBoard);
            assertEquals(-1, exampleBoard.getPosition(cell[0], cell[1]));
        }

    }

    @Test
    public void testDoMove() throws NotValidValueException {
        Board exampleBoardBeforeMove = exampleBoard();
        Board exampleBoardAfterMove = randomOpponent.doMove(exampleBoardBeforeMove);
        assertEquals(randomOpponent.countFreeCells(exampleBoardBeforeMove)-1, randomOpponent.countFreeCells(exampleBoardAfterMove));
        assertTrue(isCorrectMove(exampleBoardBeforeMove, exampleBoardAfterMove));
    }


    private Boolean isCorrectMove(Board before, Board after){
        int changes = 0;
        for(short i = 0; i < 3; i++){
            for(short j = 0; j < 3; j++){
                if(before.getPosition(i, j) != after.getPosition(i, j)){
                    changes++;
                }
            }
        }
        return changes == 1;
    }

    private Board exampleBoard() throws NotValidValueException {
        Board exampleBoard = new Board((short)3);
        exampleBoard.setPosition((short) 1, (short)2, (short) 0);
        exampleBoard.setPosition((short) 0, (short)0, (short) 1);
        exampleBoard.setPosition((short) 2, (short)0, (short) 0);

        return exampleBoard;
    }


}
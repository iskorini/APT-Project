package it.unifi.gameutility;

import it.unifi.exception.NotValidValueException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by iskor on 16/07/2017.
 */
public class BoardTest {

    private Board board;

    @Before
    public void init(){
        board = new Board((short)3);
    }

    @Test
    public void testGetPosition() throws Exception {
        board.setPosition((short) 0, (short) 0, (short) 0);
        assertEquals(board.getPosition((short) 0, (short) 0), (short) 0);
    }

    @Test
    public void testSetPositionWhenEmpty() throws NotValidValueException {
        board.setPosition((short) 0, (short) 0, (short) 0);
        assertEquals(board.getPosition((short) 0, (short) 0), (short)0);
    }

    @Test
    public void testSetPoistionWhenNotEmpty() throws NotValidValueException {
        board.setPosition((short) 0, (short) 0, (short) 0);
        board.setPosition((short) 0, (short) 0, (short) 1);
        assertEquals(board.getPosition((short) 0, (short) 0), (short)1);
    }

/*    @Test
    public void testInvalidSetArgument() {
        try {
            board.setPosition((short) 0, (short) 0, (short) 3);
            fail("DIOCANE.");
        } catch(NotValidValueException e) {
            assertNotNull(e.getMessage());
        }
    }*/

}
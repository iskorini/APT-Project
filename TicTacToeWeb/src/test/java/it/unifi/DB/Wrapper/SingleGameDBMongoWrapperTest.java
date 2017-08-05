package it.unifi.DB.Wrapper;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import it.unifi.gameutility.Board;
import it.unifi.gameutility.Player;
import it.unifi.utility.SingleGame;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class SingleGameDBMongoWrapperTest extends AbstractDBMongoWrapperTest {


    private SingleGameDBMongoWrapper singleGameDBMongoWrapper;
    private MongoCollection dbCollection;

    @Before
    public void setUp() throws Exception {
        DB db = super.mongoClient.getDB("GameDB");
        db.getCollection("SingleGameCollection").drop();
        Jongo jongo = new Jongo(db);
        singleGameDBMongoWrapper = new SingleGameDBMongoWrapper(mongoClient);
        dbCollection = jongo.getCollection("SingleGameCollection");
    }

    private SingleGame addSingleGameToDB(String username) {
        Board b1 = new Board((short) 3);
        Player p1 = new Player(username);
        SingleGame s1 = new SingleGame(b1, p1);
        dbCollection.insert(s1);
        return s1;
    }


    @Test
    public void getAllSingleGameWithOneGame() throws Exception {
        addSingleGameToDB("Carmen");
        assertEquals(singleGameDBMongoWrapper.getAllSingleGame().size(), 1);
        assertEquals(singleGameDBMongoWrapper.getAllSingleGame().get(0).getPlayer(), new Player("Carmen"));
    }

    @Test
    public void getAllSingleGameWithTwoGame() throws Exception {
        addSingleGameToDB("Roberto");
        addSingleGameToDB("Filomena");
        assertEquals(singleGameDBMongoWrapper.getAllSingleGame().size(), 2);
    }

    @Test
    public void getAllSingleGameWithNoGame() throws Exception {
        assertEquals(singleGameDBMongoWrapper.getAllSingleGame().size(), 0);
    }

    @Test
    public void getSingleGameByUsername() throws Exception {
        addSingleGameToDB("Angela");
        assertEquals(singleGameDBMongoWrapper.getSingleGameByUsername("Angela").size(), 1);

    }

    @Test
    public void getSingleGameByUsernameWithNoGame() throws Exception {
        assertEquals(singleGameDBMongoWrapper.getSingleGameByUsername("Tim").size(), 0);
    }

    @Test
    public void getSingleGameByUsernameWithTwoGame() throws Exception {
        addSingleGameToDB("Dante");
        addSingleGameToDB("Dante");
        assertEquals(singleGameDBMongoWrapper.getSingleGameByUsername("Dante").size(), 2);
    }

    @Test
    public void addSingleGame() throws Exception {
        SingleGame s1 = addSingleGameToDB("Ursula");
        assertEquals(singleGameDBMongoWrapper.getSingleGameByUsername("Ursula").size(), 1);
        assertEquals(singleGameDBMongoWrapper.getSingleGameByUsername("Ursula").get(0), s1);
    }

}
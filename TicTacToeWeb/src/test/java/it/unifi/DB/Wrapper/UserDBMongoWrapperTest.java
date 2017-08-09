package it.unifi.DB.Wrapper;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import it.unifi.bean.MongoClientProviderBean;
import it.unifi.exception.EntryAlreadyInsertedException;
import it.unifi.exception.UserNotExistingException;
import it.unifi.gameutility.Player;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDBMongoWrapperTest extends AbstractDBMongoWrapperTest {


    private UserDBMongoWrapper userDBMongoWrapper;
    private MongoCollection dbCollection;
    @Mock
    MongoClientProviderBean mongoClientProviderBean;

    @Before
    public void setUp() throws Exception {
        DB db = mongoClient.getDB("GameDB");
        db.getCollection("UserCollection").drop();
        Jongo jongo = new Jongo(db);
        when(mongoClientProviderBean.getMongoClient()).thenReturn(mongoClient);
        userDBMongoWrapper = new UserDBMongoWrapper(mongoClientProviderBean);
        dbCollection = jongo.getCollection("UserCollection");

    }

    private void addUserToFakeMongo(String username) {
        dbCollection.insert(new Player(username));
    }

    @Test
    public void getAllUserWithOneUser() throws Exception {
        addUserToFakeMongo("Antonio");
        assertEquals(userDBMongoWrapper.getAllUser().get(0).getUsername(), "Antonio");
        assertEquals(userDBMongoWrapper.getAllUser().size(), 1);
    }

    @Test
    public void getAllUserWithNoUser() throws Exception {
        assertTrue(userDBMongoWrapper.getAllUser().isEmpty());
    }

    @Test
    public void getAllUserWithMoreUser() throws Exception {
        addUserToFakeMongo("Tancredi");
        addUserToFakeMongo("Ranieri");
        assertEquals(userDBMongoWrapper.getAllUser().size(), 2);
    }

    @Test
    public void getUserByID() throws Exception {
        addUserToFakeMongo("Gaetano");
        assertEquals(userDBMongoWrapper.getUserByID("Gaetano"), new Player("Gaetano"));
    }

    @Test
    public void getUserByIDWhenUserDoesntExists() throws Exception {
        assertEquals(userDBMongoWrapper.getUserByID("Non esisto"), null);
    }

    @Test
    public void addUser() throws Exception {
        Player p1 = new Player("Michele");
        userDBMongoWrapper.addUser(p1);
        assertEquals(userDBMongoWrapper.getUserByID("Michele"), p1);
    }

    @Test(expected = EntryAlreadyInsertedException.class)
    public void addUserWhenUserAlreadyExists() throws Exception {
        addUserToFakeMongo("Rosario");
        userDBMongoWrapper.addUser(new Player("Rosario"));
    }

    @Test
    public void updateUser() throws Exception {
        addUserToFakeMongo("Astolfo");
        Player p1 = new Player("Astolfo");
        p1.setPoints("30");
        userDBMongoWrapper.updateUser(p1);
        assertEquals(userDBMongoWrapper.getUserByID("Astolfo").getPoints(), "30");
    }

    @Test(expected = UserNotExistingException.class)
    public void updateUserWhenUserDoesntExists() throws Exception {
        userDBMongoWrapper.updateUser(new Player("Bartolomeo"));
    }

}
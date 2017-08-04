package it.unifi.DB.Wrapper;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import it.unifi.exception.EntryAlreadyInsertedException;
import it.unifi.exception.UserNotExistingException;
import it.unifi.gameutility.Player;
import it.unifi.interfaces.UserDatabase;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserDBMongoWrapper implements UserDatabase {

    private MongoClient mongoClient;
    private MongoCollection mongoCollection;


    public UserDBMongoWrapper(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        DB db = mongoClient.getDB("GameDB");
        Jongo jongo = new Jongo(db);
        mongoCollection = jongo.getCollection("UserCollection");
    }

    @Override
    public List<Player> getAllUser() {
        Iterable<Player> iterable = mongoCollection.find().as(Player.class);
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Player getUserByID(String username) {
        return mongoCollection.findOne("{username: #}", username).as(Player.class);
    }

    @Override
    public boolean addUser(Player p) throws EntryAlreadyInsertedException {
        if (mongoCollection.count("{username: #}", p.getUsername()) == 1) {
            throw new EntryAlreadyInsertedException("User " + p.getUsername() + " already inserted");
        }
        mongoCollection.insert(p);
        return true;
    }

    @Override
    public boolean updateUser(Player p) throws UserNotExistingException {
        if (mongoCollection.count("{username: #}", p.getUsername()) == 0) {
            throw new UserNotExistingException("User " + p.getUsername() + " doesn't exist");
        }
        mongoCollection.update("{username: #}", p.getUsername()).with(p);
        return true;
    }
}

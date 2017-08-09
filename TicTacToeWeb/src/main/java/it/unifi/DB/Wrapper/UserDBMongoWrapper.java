package it.unifi.DB.Wrapper;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import it.unifi.bean.MongoClientProviderBean;
import it.unifi.exception.EntryAlreadyInsertedException;
import it.unifi.exception.UserNotExistingException;
import it.unifi.gameutility.Player;
import it.unifi.interfaces.UserDatabase;
import org.apache.log4j.Logger;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Stateless
public class UserDBMongoWrapper implements UserDatabase {


    private static final Logger logger = Logger.getLogger(UserDBMongoWrapper.class);
    private MongoClient mongoClient;
    private MongoCollection mongoCollection;

    @Inject
    public UserDBMongoWrapper(MongoClientProviderBean mongoClientProviderBean) {
        logger.info("creato MongoDBWrapper -> mongoclient iniettato con successo");
        this.mongoClient = mongoClientProviderBean.getMongoClient();
        DB db = mongoClient.getDB("GameDB");
        Jongo jongo = new Jongo(db);
        mongoCollection = jongo.getCollection("UserCollection");
    }

    public UserDBMongoWrapper() {
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

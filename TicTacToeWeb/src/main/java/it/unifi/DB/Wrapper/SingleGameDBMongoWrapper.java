package it.unifi.DB.Wrapper;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import it.unifi.bean.MongoClientProviderBean;
import it.unifi.exception.EntryAlreadyInsertedException;
import it.unifi.interfaces.SingleGameDatabase;
import it.unifi.utility.SingleGame;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Stateless
public class SingleGameDBMongoWrapper implements SingleGameDatabase {


    private MongoClient mongoClient;
    private MongoCollection mongoCollection;

    @Inject
    public SingleGameDBMongoWrapper(MongoClientProviderBean mongoClientProviderBean) {
        this.mongoClient = mongoClientProviderBean.getMongoClient();
        DB db = mongoClient.getDB("GameDB");
        Jongo jongo = new Jongo(db);
        mongoCollection = jongo.getCollection("SingleGameCollection");
    }

    public SingleGameDBMongoWrapper() {
    }

    @Override
    public List<SingleGame> getAllSingleGame() {
        Iterable<SingleGame> iterable = mongoCollection.find().as(SingleGame.class);
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<SingleGame> getSingleGameByUsername(String username) {
        Iterable<SingleGame> iterable = mongoCollection.find(
                "{'player.username': #}", username
        ).as(SingleGame.class);
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public boolean addSingleGame(SingleGame singleGame) throws EntryAlreadyInsertedException {
        if (mongoCollection.count("{gameID: #}", singleGame.getGameID()) == 1) {
            throw new EntryAlreadyInsertedException("Game " + singleGame.getGameID() + " already inserted");
        }
        mongoCollection.insert(singleGame);
        return true;
    }
}

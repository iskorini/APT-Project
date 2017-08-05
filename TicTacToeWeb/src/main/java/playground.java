import com.mongodb.DB;
import com.mongodb.MongoClient;
import it.unifi.gameutility.Board;
import it.unifi.gameutility.Player;
import it.unifi.utility.SingleGame;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class playground {


    public static void main(String[] args) throws UnknownHostException {
        Player pippo1 = new Player("pippo");
        pippo1.setPoints("333");
        SingleGame s1 = new SingleGame(new Board((short) 3), pippo1);
        SingleGame s2 = new SingleGame(new Board((short) 3), new Player("pippo"));
        SingleGame s3 = new SingleGame(new Board((short) 3), new Player("peppa"));
        SingleGame s4 = new SingleGame(new Board((short) 3), new Player("peppa"));
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("GameDB");
        Jongo jongo = new Jongo(db);
        MongoCollection mongoCollection;
        mongoCollection = jongo.getCollection("SingleGameCollection");
        s2.getPlayer().setPoints("40");
        mongoCollection.insert(s1);
        mongoCollection.insert(s2);
        /*
        mongoCollection.insert(s1);
        mongoCollection.insert(s2);
        mongoCollection.insert(s3);
        mongoCollection.insert(s4);
        */
        Iterable<SingleGame> iterable = mongoCollection.find(
                "{'player.username': #}", "pippo"
        ).as(SingleGame.class);
        List<SingleGame> singleGameList = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        for (SingleGame s :
                singleGameList) {
            System.out.println(s.getPlayer());
        }
    }

}

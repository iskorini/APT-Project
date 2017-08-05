package it.unifi.DB.Wrapper;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.MongoCollection;
import org.junit.Before;

public abstract class AbstractDBMongoWrapperTest {


    protected MongoClient mongoClient;

    @Before
    public void init() throws Exception {
        Fongo fongo = new Fongo("UnitTest fake server");
        mongoClient = fongo.getMongo();
    }


}

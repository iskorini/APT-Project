package it.unifi.DB.Wrapper;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import it.unifi.bean.MongoClientProviderBean;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractDBMongoWrapperTest {


    protected MongoClient mongoClient;

    @Mock
    MongoClientProviderBean mongoClientProviderBean;


    @Before
    public void init() throws Exception {
        Fongo fongo = new Fongo("UnitTest fake server");
        mongoClient = fongo.getMongo();
        when(mongoClientProviderBean.getMongoClient()).thenReturn(mongoClient);
    }


}

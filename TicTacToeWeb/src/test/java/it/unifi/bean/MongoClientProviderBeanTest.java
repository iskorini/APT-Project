package it.unifi.bean;


import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class MongoClientProviderBeanTest {

    MongoClientProviderBean mongoClientProviderBean;

    @Before
    public void setUp() throws Exception {
        mongoClientProviderBean = new MongoClientProviderBean();
    }

    @Test
    public void getMongoClient() throws Exception {
        mongoClientProviderBean.init();
        assertNotNull(mongoClientProviderBean.getMongoClient());
    }

    @Test
    public void getMongoClientWithoutInit() throws Exception {
        assertNull(mongoClientProviderBean.getMongoClient());
    }

    @Test
    public void getMongoClientAfterTearDown() throws Exception {
        mongoClientProviderBean.init();
        mongoClientProviderBean.tearDown();
        /*
        TODO: trovare modo per verificare che è stato chiamato il close sul mongoclient
         */
    }
}

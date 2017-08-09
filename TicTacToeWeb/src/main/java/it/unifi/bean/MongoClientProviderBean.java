package it.unifi.bean;

import com.mongodb.MongoClient;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import java.net.UnknownHostException;


@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Startup
public class MongoClientProviderBean {

    private Logger logger = Logger.getLogger(MongoClientProviderBean.class);
    private MongoClient mongoClient = null;

    @Lock(LockType.READ)
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    @PostConstruct
    public void init() {
        try {
            String address = "mongo-host";
            int port = 27017;
            //TODO: inject del indirizzo e porta
            mongoClient = new MongoClient(address, port);
            logger.info("Inizializzato mongoclient (" + address + ", " + port + ")");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void tearDown() {
        mongoClient.close();
        logger.info("Mongoclient chiuso");
    }
}

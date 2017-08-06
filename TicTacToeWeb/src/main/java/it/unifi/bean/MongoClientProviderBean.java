package it.unifi.bean;

import com.mongodb.MongoClient;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.net.UnknownHostException;

@Singleton(name = "MongoClientProviderEJB")
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MongoClientProviderBean {
    private MongoClient mongoClient = null;

    @Lock(LockType.READ)
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    @PostConstruct
    public void init() {
        try {
            //TODO: inject del indirizzo e porta
            mongoClient = new MongoClient("mongo-host", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

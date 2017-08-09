package it.unifi.servlet;


import it.unifi.DB.Controller.SingleGameController;
import it.unifi.bean.MongoClientProviderBean;
import it.unifi.gameutility.Board;
import it.unifi.gameutility.Player;
import it.unifi.interfaces.NPC;
import it.unifi.utility.SingleGame;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/game")
public class GameServlet {

    private static final Logger logger = Logger.getLogger(GameServlet.class);

    private SingleGameController singleGameController;
    private MongoClientProviderBean mongoClientProviderBean;

    NPC npc;

    public GameServlet() {
        logger.info("Creata gameservlet con costruttore senza parametri");
    }

    @Inject
    public GameServlet(MongoClientProviderBean mongoClientProviderBean) {
        this.mongoClientProviderBean = mongoClientProviderBean;
        logger.info("Creata gameservlet con costruttore senza parametri");
    }

    @GET
    @Path("/init/{user}/{dim1}")
    @Produces("application/json")
    public SingleGame initGame(
            @PathParam("user") String user,
            @PathParam("dim1") String dimension)
    {
        Player p = new Player(user);
        Board b = new Board(Short.parseShort(dimension));
        return new SingleGame(b, p);
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public SingleGame generateResponse(SingleGame s) {
        return s;
    }
}

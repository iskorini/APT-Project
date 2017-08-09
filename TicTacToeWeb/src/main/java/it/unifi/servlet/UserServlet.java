package it.unifi.servlet;


import com.mongodb.MongoClient;
import com.sun.org.apache.regexp.internal.RE;
import it.unifi.DB.Controller.UserController;
import it.unifi.DB.Wrapper.UserDBMongoWrapper;
import it.unifi.bean.MongoClientProviderBean;
import it.unifi.exception.EntryAlreadyInsertedException;
import it.unifi.exception.UserNotExistingException;
import it.unifi.gameutility.Player;
import it.unifi.interfaces.UserDatabase;
import org.apache.log4j.Logger;
import org.jboss.resteasy.core.ServerResponse;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserServlet {

    private Logger logger = Logger.getLogger(UserServlet.class);

    private UserController userController;

    public UserServlet() {
        logger.info("Creata Servlet con costruttore senza parametri");
    }

    @Inject
    public UserServlet(UserController userController) {
        logger.info("Creata Servlet con costruttore inject");
        this.userController = userController;
    }


    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getUser(@PathParam("id") String username) {
        logger.info("Cerco user: " + username);
        Player p = userController.getUserByID(username);
        if (p == null) {
            logger.info(username + " non trovato");
            return Response.status(404).build();
        }
        logger.info(username + " restituito");
        return Response.status(200).entity(p).build();
    }

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addUser(Player p) {
        Player p1 = userController.getUserByID(p.getUsername());
        if (p1 == null) {
            try {
                userController.addUser(new Player(p.getUsername()));
                logger.info(p.getUsername() + ": aggiunto");
            } catch (EntryAlreadyInsertedException e) {
                logger.error(p.getUsername() + ": errore inserimento -> " + e.toString());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            try {
                userController.updatePlayer(p);
                logger.info(p.getUsername() + ": aggiornato");
            } catch (UserNotExistingException e) {
                logger.error(p.getUsername() + ": errore aggiornamento -> " + e.toString());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        logger.info(p.getUsername() + ": OK");
        return Response.status(Response.Status.OK).entity(p).build();
    }

}

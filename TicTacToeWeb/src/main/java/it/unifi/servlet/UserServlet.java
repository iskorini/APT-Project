package it.unifi.servlet;


import it.unifi.gameutility.Player;


import javax.ws.rs.*;

@Path("/user")
public class UserServlet {

    /*
    TODO: gestione del db
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Player getUser(@PathParam("id") String username ) {
        return new Player(username);

    }

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    public Player updateUser(@PathParam("id") String username) {
        return  new Player(username);
    }

}

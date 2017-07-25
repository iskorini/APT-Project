package it.unifi.servlet;


import it.unifi.gameutility.Board;
import it.unifi.gameutility.Player;
import it.unifi.utility.SingleGame;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/game")
public class GameServlet {

    @GET
    @Path("/init/{user}/{dim1}")
    @Produces("application/json")
    public SingleGame initGame(
            @PathParam("user") String user,
            @PathParam("dim1") String dimension)
    {
        /*
        TODO: Database
         */
        Player p = new Player(user);
        Board b = new Board(Short.parseShort(dimension));
        return new SingleGame(b, p);
    }
}

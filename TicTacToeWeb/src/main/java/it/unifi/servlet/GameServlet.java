package it.unifi.servlet;


import it.unifi.gameutility.Board;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/game")
public class GameServlet {

    @GET
    @Path("/init/{dim1}")
    @Produces("application/json")
    public Board getWhiteBoard(@PathParam("dim1") String dimension) {
        return new Board(Short.parseShort(dimension));
    }


}

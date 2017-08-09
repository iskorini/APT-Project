package it.unifi.servlet;


import it.unifi.DB.Controller.UserController;
import it.unifi.gameutility.Player;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.jongo.MongoCollection;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServletTest {


    private UserServlet userServlet; // SUT

    @Mock
    UserController userController;
    private Dispatcher dispatcher;

    @Before
    public void setUp() throws Exception {
        dispatcher = MockDispatcherFactory.createDispatcher();
        UserServlet userServlet = new UserServlet(userController);
        dispatcher.getRegistry().addSingletonResource(userServlet);

    }

    private MockHttpRequest createRequestByUser(Player p) {
        MockHttpRequest request = null;
        try {
            request = MockHttpRequest.put("/user/" + p.getUsername());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON_TYPE);
        try {
            request.content(new ObjectMapper().writeValueAsBytes(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Test
    public void testConstructorWithNoParameter() throws Exception {
        userServlet = new UserServlet();
        assertNotNull(userServlet);
    }

    @Test
    public void testAddNewUser() throws Exception {
        Player p = new Player("Gianfranco");
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(createRequestByUser(p), response);
        verify(userController).addUser(p);
        assertEquals(response.getStatus(), 200);
    }

    @Test
    public void testUpdateUser() throws Exception {
        Player p1 = new Player("Luigi");
        when(userController.getUserByID("Luigi")).thenReturn(p1);
        Player updated = new Player("Luigi");
        updated.setPoints("30");
        MockHttpRequest request = createRequestByUser(updated);
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        verify(userController).updatePlayer(updated);
        assertEquals(response.getStatus(), 200);
    }

    @Test
    public void testGetUserByIdWhenUserExist() throws Exception {
        Player p1 = new Player("Stefano");
        when(userController.getUserByID("Stefano")).thenReturn(p1);
        MockHttpRequest request = MockHttpRequest.get("/user/" + p1.getUsername());
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        verify(userController).getUserByID("Stefano");
        assertEquals(response.getStatus(), 200);
    }

    @Test
    public void testGetUserByIdWhenUserNotExist() throws Exception {
        MockHttpRequest request = MockHttpRequest.get("/user/Pasquale");
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        verify(userController).getUserByID("Pasquale");
        assertEquals(response.getStatus(), 404);
    }

}
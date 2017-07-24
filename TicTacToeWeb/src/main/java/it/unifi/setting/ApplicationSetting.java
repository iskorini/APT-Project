package it.unifi.setting;

import it.unifi.servlet.GameServlet;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by iskor on 18/07/2017.
 */
public class ApplicationSetting extends Application {


    private Set<Object> singletons = new HashSet<Object>();;

    public ApplicationSetting() {
        singletons.add(new GameServlet());

    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}

package app;


import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/rest/*", initParams = @WebInitParam(name = "javax.ws.rs.Application", value = "app.Application"))
public class RestServlet extends ServletContainer {

}

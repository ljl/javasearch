package app;

import search.SearchClient;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        SearchClient.getClient();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        SearchClient.shutdown();
    }
}

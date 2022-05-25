package server.test.services.dbservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.test.services.servlets.SignInServlet;
import server.test.services.servlets.SignUpServlet;

public class DBServiceMain {
    public static void main(String[] args) throws Exception {
        DBService dbService = new DBServiceImpl();
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(new SignUpServlet(dbService)), "/signup");
        servletContextHandler.addServlet(new ServletHolder(new SignInServlet(dbService)), "/signin");
        Server server = new Server(8080);
        server.setHandler(servletContextHandler);
        server.start();
        System.out.println("Server started!");
        server.join();
    }
}

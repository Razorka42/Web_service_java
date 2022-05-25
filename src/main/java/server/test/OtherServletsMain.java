package server.test;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import server.test.services.servlets.AllRequestServlet;
import server.test.services.servlets.WebSocketMessageReturnServlet;

public class OtherServletsMain {
    public static void main(String[] args) throws Exception {

        AllRequestServlet allRequestServlet = new AllRequestServlet();
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(allRequestServlet), "/mirror");
        servletContextHandler.addServlet(new ServletHolder(new WebSocketMessageReturnServlet()), "/chat");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setBaseResource(Resource.newResource("public_html"));
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, servletContextHandler});

        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
        System.out.println("Server started");
        server.join();
    }
}

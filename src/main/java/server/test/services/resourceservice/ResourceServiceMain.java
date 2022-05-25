package server.test.services.resourceservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.test.services.resourceservice.resources.TestResource;
import server.test.services.servlets.ResourceServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class ResourceServiceMain {
    public static void main(String[] args) throws Exception {
        ResourceService resourceService = new ResourceServiceImp(new TestResource("Tully", 23));
        ResourceServerControllerMBean resourceStatistics = new ResourceServerController(resourceService);
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName resourceStatisticsName = new ObjectName("Admin:type=ResourceServerController");
        mBeanServer.registerMBean(resourceStatistics, resourceStatisticsName);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(new ResourceServlet(resourceService)), "/resources");
        Server server = new Server(8080);
        server.setHandler(servletContextHandler);
        server.start();
        System.out.println("Server started!");
        server.join();
    }
}

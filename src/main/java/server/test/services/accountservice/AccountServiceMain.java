package server.test.services.accountservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.test.services.servlets.AdminServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class AccountServiceMain {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountServiceImpl();
        AccountServiceControllerMBean statistics = new AccountServiceController(accountService);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServiceController.usersLimit");
        mbs.registerMBean(statistics, name);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(new AdminServlet(accountService)), "/admin");
        Server server = new Server(8080);
        server.setHandler(servletContextHandler);
        server.start();
        System.out.println("Server started!");
        server.join();
    }
}

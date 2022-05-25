package server.test.services.servlets;

import org.eclipse.jetty.http.HttpStatus;
import server.test.services.resourceservice.ResourceService;
import server.test.services.resourceservice.resources.TestResource;
import server.test.services.resourceservice.sax.ReadXMLFileSAX;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceServlet extends HttpServlet {
    private ResourceService resourceService;

    public ResourceServlet(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        Object resourceObject = ReadXMLFileSAX.readXML(path);
        resourceService.setTestResource((TestResource) resourceObject);
        response.setStatus(HttpStatus.OK_200);
    }
}

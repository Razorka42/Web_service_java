package server.test.services.servlets;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import server.test.services.sockets.WebSocketMessageReturn;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "SocketServlet", urlPatterns = {"/chat"})
public class WebSocketMessageReturnServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        webSocketServletFactory.setCreator((servletUpgradeRequest, servletUpgradeResponse) -> new WebSocketMessageReturn());
    }


}

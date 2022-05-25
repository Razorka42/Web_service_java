package server.test.services.servlets;

import com.google.gson.Gson;
import server.test.services.accountservice.model.UserProfile;
import server.test.services.dbservice.DBException;
import server.test.services.dbservice.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final DBService dbService;

    public SignInServlet(DBService dbService) {
        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        Gson gson = new Gson();
        UserProfile currentUser = null;
        try {
            currentUser = dbService.getUserByLogin(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        if (currentUser != null && currentUser.getPass().equals(pass)) {
            String json = gson.toJson("Authorized: " + login);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            String json = gson.toJson("Unauthorized");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}

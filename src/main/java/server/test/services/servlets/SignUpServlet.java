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

public class SignUpServlet extends HttpServlet {
    private final DBService dbService;

    public SignUpServlet(DBService dbService) {
        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        UserProfile userWithSuchLogin = null;
        try {
            userWithSuchLogin = dbService.getUserByLogin(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        if (userWithSuchLogin != null) {
            String json = gson.toJson("This login is already exist: " + login);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            try {
                dbService.addUser(new UserProfile(login, pass, null));
            } catch (DBException e) {
                e.printStackTrace();
            }

            String json = gson.toJson("Congratulations! You have registered!");
            response.setContentType("application/json");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}

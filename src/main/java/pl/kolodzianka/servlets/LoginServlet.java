package pl.kolodzianka.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kolodzianka.jsonUtils.JsonUserLists;
import pl.kolodzianka.entities.User;
import pl.kolodzianka.utils.CookieUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        String username = req.getParameter("user");
        String password = req.getParameter("pwd");

        User loguser = new User();
        loguser.setUsername(username);
        loguser.setPassword(password);


        String path = "/Users/ania/Projekty/Sklepik/src/main/webapp/WEB-INF/users.json";


        JsonUserLists users = mapper.readValue(new File(path), JsonUserLists.class);

        for (int i = 1; i < users.getUsers().size(); i++) {
            User u = users.getUsers().get(i);
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                CookieUtils.createCookie(resp, loguser);
                resp.sendRedirect("/shopServlet");
            }

        }
        RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
        resp.getWriter().println("<p><font color=red>Niepoprawny username lub hasło!</font></p>");
        rd.include(req, resp);
    }


}




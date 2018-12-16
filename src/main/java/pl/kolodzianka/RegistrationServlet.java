package pl.kolodzianka;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kolodzianka.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    protected final User user = new User();
    protected  JsonUserLists userList = new JsonUserLists();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        ObjectMapper mapper = new ObjectMapper();
        String path = "/Users/ania/Projekty/Sklepik/src/main/webapp/WEB-INF/users.json";
        userList = mapper.readValue(new File(path), JsonUserLists.class);

        user.setId((long)userList.getUsers().size() +1);
        user.setUsername(req.getParameter("user"));
        user.setPassword(req.getParameter("pwd"));
        user.setName(req.getParameter("name"));
        user.setSurname(req.getParameter("surname"));
        user.setRole("user");

        userList.getUsers().add(user);


        JsonGenerator g = mapper.getFactory().createGenerator(new FileOutputStream(new File (path)));

        mapper.writeValue(g, userList);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        resp.getWriter().println("<font color=red>Zarejestrowano uzytkownika: " + user.toString() + "</font>");
        rd.include(req, resp);

    }
}

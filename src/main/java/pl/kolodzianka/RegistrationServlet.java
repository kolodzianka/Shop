package pl.kolodzianka;


import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kolodzianka.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    protected final User user = new User();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        user.setId((long)Math.random());
        user.setUsername(req.getParameter("user"));
        user.setPassword("pwd");
        user.setName(req.getParameter("name"));
        user.setSurname(req.getParameter("surname"));
        user.setRole("user");

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File("WEB-INF/users.json"), user);

    }
}

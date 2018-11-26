package pl.kolodzianka;


import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet("/RegistrationServlet")
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

        Gson gson = new Gson();
        gson.toJson(user,(new FileWriter("/Users/ania/Projekty/Sklepik/src/main/webapp/WEB-INF/users.js")));


    }
}

package pl.kolodzianka;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kolodzianka.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();



        String username = req.getParameter("user");
        String password = req.getParameter("pwd");

        User loguser = new User();
        loguser.setUsername(username);
        loguser.setPassword(password);


        String path = "/Users/ania/Projekty/Sklepik/src/main/webapp/WEB-INF/users.json";


        JsonUserLists users =  mapper.readValue(new File (path), JsonUserLists.class);

        if (loguser.getUsername() != null && loguser.getPassword() != null && loguser.equals(users.getUsers())){
//            Cookie loginCookie = new Cookie("jakisKlient", user);
//            loginCookie.setMaxAge(30*60);
//            resp.addCookie(loginCookie);
            resp.sendRedirect("sklep.jsp");
        }
        else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            resp.getWriter().println("<font color=red>Zaloguj się lub załóż konto</font>");
            rd.include(req, resp); // dokleja powyzszy tekst
        }





        out.println("Zalogowano: " + users.toString());

    }
}

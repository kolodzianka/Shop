package pl.kolodzianka;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kolodzianka.entities.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet("/shoppingbox")
public class ShoppingBoxServlet extends HttpServlet {

    public String user;
    JsonProductsList boxList;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (readCookie("zalogowany", req).isPresent()) {
            user = readCookie("zalogowany", req).get();
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            resp.getWriter().println("<font color=red>Aby dodac do koszyka zaloguj się lub załóż konto.</font>");
            rd.include(req, resp);
        }
        req.getSession().setAttribute("user", user);

        boxList = new JsonProductsList();
        boxList.setProducts((List<Product>) req.getSession().getAttribute("box"));

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        String path = "/Users/ania/Projekty/Sklepik/src/main/webapp/WEB-INF/" + user + ".json";
        File f = new File(path);
        if (f.exists() && f.isDirectory()) {

            try {
                boxList.setProducts((List<Product>)mapper.readValue(new File(path), JsonProductsList.class));
                JsonGenerator g = mapper.getFactory().createGenerator(new FileOutputStream(new File(path)));

                mapper.writeValue(g, boxList);

            } catch (FileNotFoundException e) {
                out.println(e + "Nie znaleziono pliku.");
            }
        } else {
            mapper.writeValue(new FileOutputStream(user+".json"),boxList);
        }
        req.setAttribute("userbox", boxList.getProducts());
        RequestDispatcher reqD = req.getRequestDispatcher("userbox.jsp");
        reqD.forward(req, resp);


    }

    public Optional<String> readCookie(String key, HttpServletRequest req) {
        return Arrays.stream(req.getCookies())
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }


}

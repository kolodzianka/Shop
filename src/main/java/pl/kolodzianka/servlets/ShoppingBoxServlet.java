package pl.kolodzianka.servlets;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kolodzianka.jsonUtils.JsonProductsList;
import pl.kolodzianka.entities.Product;
import pl.kolodzianka.jsonUtils.JsonShoppingBoxUtil;
import pl.kolodzianka.utils.CookieUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet("/shoppingbox")
public class ShoppingBoxServlet extends HttpServlet {

    public String user;
    JsonShoppingBoxUtil boxList;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CookieUtils.readCookie("zalogowany", req).isPresent()) {
            user = CookieUtils.readCookie("zalogowany", req).get();
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            resp.getWriter().println("<font color=red>Aby dodac do koszyka zaloguj się lub załóż konto.</font>");
            rd.include(req, resp);
        }
        req.getSession().setAttribute("user", user);

        boxList = new JsonShoppingBoxUtil();
        boxList.setUserName(user);
        boxList.setProducts((List<Product>) req.getSession().getAttribute("box"));

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        String path = "/Users/ania/Projekty/Sklepik/src/main/webapp/WEB-INF/boxes.json";
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
            mapper.writeValue(new FileOutputStream(path),boxList);
        }
        req.getSession().setAttribute("userbox", boxList.getProducts());
        RequestDispatcher reqD = req.getRequestDispatcher("userbox.jsp");
        reqD.forward(req, resp);


    }




}

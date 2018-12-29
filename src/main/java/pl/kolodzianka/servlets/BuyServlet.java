package pl.kolodzianka.servlets;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kolodzianka.jsonUtils.JsonShoppingBoxUtil;
import pl.kolodzianka.utils.CookieUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/buyServlet")
public class BuyServlet extends HttpServlet {

    protected JsonShoppingBoxUtil boughtProducts = new JsonShoppingBoxUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String path = "/Users/ania/Projekty/Sklepik/src/main/webapp/WEB-INF/boxes.json";
        boughtProducts = mapper.readValue(new File(path), JsonShoppingBoxUtil.class);

        Optional<String> userName = CookieUtils.readCookie("zalogowany",req);

        boughtProducts.getProducts().clear();
        JsonGenerator g = mapper.getFactory().createGenerator(new FileOutputStream(new File(path)));

        mapper.writeValue(g, boughtProducts);

        // dodanie kwoty zakupow do totalCashSpend usera

        resp.sendRedirect("/shopServlet");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

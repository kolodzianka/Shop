package pl.kolodzianka;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/shopServlet")
public class ShopServlet extends HttpServlet {

    protected  JsonProductsList productsList = new JsonProductsList();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        String path = "/Users/ania/Projekty/Sklepik/src/main/webapp/WEB-INF/products.json";
        productsList = mapper.readValue(new File(path), JsonProductsList.class);

        req.setAttribute("products", productsList.getProducts());
        RequestDispatcher reqD = req.getRequestDispatcher("shop.jsp");
        reqD.forward(req,resp);
    }
}

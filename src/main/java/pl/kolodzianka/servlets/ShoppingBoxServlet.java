package pl.kolodzianka.servlets;

import pl.kolodzianka.DaoImplements.BoxesDaoJsonImpl;
import pl.kolodzianka.entities.Product;
import pl.kolodzianka.utils.CookieUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/shoppingbox")
public class ShoppingBoxServlet extends HttpServlet {

    public String user;

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
        PrintWriter out = resp.getWriter();

        List<Product> products = ((List<Product>) req.getSession().getAttribute("box"));
        BoxesDaoJsonImpl boxesDaoJson = new BoxesDaoJsonImpl();
        boxesDaoJson.saveOrUpdate(user,products);


        req.getSession().setAttribute("userbox", products);
        RequestDispatcher reqD = req.getRequestDispatcher("userbox.jsp");
        reqD.forward(req, resp);


    }




}

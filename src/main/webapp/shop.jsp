<%@ page import="pl.kolodzianka.JsonProductsList" %>
<%@ page import="pl.kolodzianka.entities.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: ania
  Date: 02/12/2018
  Time: 09:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<Product> box = (List<Product>) request.getSession().getAttribute("box");
    if (box == null) {
        box = new ArrayList<>();

    }


    List<Product> products = (List<Product>) request.getAttribute("products");

    String productName = request.getParameter("order");
    if (productName != null) {
        for (Product item : products) {
            if (item.getName().equals(productName)) {
                box.add(item);
            }
        }
    }
    request.getSession().setAttribute("box", box);


%>
<html>
<head>
    <title>Sklep</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }

        .kosz {
            position: absolute;
            top: 8px;
            right: 16px;
            font-size: 18px;
        }
        .idz {
            position: absolute;
            bottom: 8px;
            right: 16px;
            font-size: 18px;
        }

    </style>
</head>
<body>
<div><h1>Produkty:</h1></div>
<div><p class="kosz" >Koszyk: <%=box.size()%>
</p></div>
<table>
    <tr>
        <th>Nazwa</th>
        <th>Opis</th>
        <th>Cena</th>
        <th>Dodaj do koszyka</th>
    </tr>

    <%for (int i = 0; i < products.size(); i++) {%>
    <tr>
        <td><%=products.get(i).getName()%>
        </td>
        <td><%=products.get(i).getDescription()%>
        </td>
        <td><%=products.get(i).getPrice()%>
        </td>
        <td>
            <form action="/shopServlet" method="post">
                <input type="hidden" name="order" value="<%=products.get(i).getName()%>">
                <input type="submit" value="Dodaj do koszyka"/>
            </form>
        </td>
    </tr>
    <% } %>

</table>

<form action="/shoppingbox" method="post">
    <p class="idz"><input type="submit" value="Idz do koszyka"></p>
</form>


</body>
</html>

<%@ page import="pl.kolodzianka.JsonProductsList" %>
<%@ page import="pl.kolodzianka.entities.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ania
  Date: 02/12/2018
  Time: 09:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    </style>
</head>
<body>
<h1>Produkty:</h1>
<table>
    <tr>
        <th>Nazwa</th>
        <th>Opis</th>
        <th>Cena</th>
        <th>Dodaj do koszyka</th>
    </tr>
    <% List<Product> products = (List<Product>)request.getAttribute("products");%>
    <%for (int i = 0 ; i < products.size(); i++){%>
    <tr>
        <td><%=products.get(i).getName()%></td>
        <td><%=products.get(i).getDescription()%></td>
        <td><%=products.get(i).getPrice()%></td>
        <td>
            <form action="/shoppingbox" method="post">
            <input type="submit" onclick="alert('Dodano do koszyka')" value="Dodaj do koszyka"></td>
        </form>
    </tr>

    <% } %>

</table>




</body>
</html>

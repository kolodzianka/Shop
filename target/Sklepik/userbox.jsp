<%@ page import="pl.kolodzianka.entities.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ania
  Date: 15/12/2018
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> productsInBox = (List<Product>)request.getSession().getAttribute("userbox");
    System.out.println(productsInBox);
    String productToDelete = request.getParameter("productToDelete");
    System.out.println(productToDelete);
    if(productToDelete != null){
        for (int i =0; i< productsInBox.size(); i++) {
            if(productsInBox.get(i).getName().equals(productToDelete)){
                productsInBox.remove(i);
                break;
            }
        }
    }
    request.getSession().setAttribute("userbox", productsInBox);
    Double suma =0.0;

%>
<html>
<head>
    <title>Twój koszyk</title>
    <style>
        .go {
        position: absolute;
        bottom: 8px;
        right: 16px;
        font-size: 18px;
    }

    </style>
</head>
<body>
<h1>Twoje zakupy:</h1>
<table>
    <tr>
        <th>Nazwa</th>
        <th>Opis</th>
        <th>Cena</th>
        <th></th>
    </tr>

    <%for (int i = 0; i < productsInBox.size(); i++){%>
    <tr>
        <td><%=productsInBox.get(i).getName()%></td>
        <td><%=productsInBox.get(i).getDescription()%></td>
        <td><%=productsInBox.get(i).getPrice()%></td>
        <td>
            <form action="/userbox.jsp" method="post">
                <input type="hidden" name="productToDelete" value="<%=productsInBox.get(i).getName()%>">
                <input type="submit" value="Usun"/>
            </form>
        </td>
    </tr>
    <% suma += productsInBox.get(i).getPrice(); %>

    <% } %>
    <tr>
        <td></td>
        <td>Suma:</td>
        <td><%=suma%></td>
    </tr>

</table>

<form action="/shopServlet" method="post">
    <p class="go"><input type="submit" value="Powrót do zakupów"></p>
</form>
<form action="/userbox.jsp" method="post">
    <p><input type="button" onclick="alert('Dziękujemy za zamówienie')" value="Zapłać i zamów"></p>
</form>

</body>
</html>

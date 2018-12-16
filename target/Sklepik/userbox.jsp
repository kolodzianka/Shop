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
    List<Product> products = (List<Product>)request.getAttribute("userbox");
    String productToDelete = request.getParameter("delete");
    if(productToDelete != null){
        for (Product item: products) {
            if(item.equals(productToDelete)){
                products.remove(item);
            }
        }
    }
    request.getSession().setAttribute("userbox", products);
    int suma =0;

%>
<html>
<head>
    <title>Twój koszyk</title>
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

    <%for (int i = 0 ; i < products.size(); i++){%>
    <tr>
        <td><%=products.get(i).getName()%></td>
        <td><%=products.get(i).getDescription()%></td>
        <td><%=products.get(i).getPrice()%></td>
        <td>
            <form action="/userbox.jsp" method="post">
                <input type="hidden" name="delete" value="<%=products.get(i).getName()%>">
                <input type="submit" value="Usun"/>
            </form>
        </td>
    </tr>
    <% suma += products.get(i).getPrice(); %>

    <% } %>
    <tr>
        <td></td>
        <td>Suma:</td>
        <td><%=suma%></td>
    </tr>

</table>

</body>
</html>

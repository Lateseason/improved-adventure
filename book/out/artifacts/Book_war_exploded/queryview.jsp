<%@ page import="java.util.List" %>
<%@ page import="com.softeem.bean.Book" %><%--
  Created by IntelliJ IDEA.
  User: Heroic
  Date: 2022/7/24
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>view</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
<% List<Book> list = (List<Book>) request.getAttribute("result");%>
<table border="1">
    <tr>
        <th>图书id</th>
        <th>图书名</th>
        <th>价格</th>
        <th>作者</th>
        <th>已卖</th>
        <th>库存</th>
        <th>图片路径</th>
    </tr>
    <%for (Book book : list) {%>
    <tr>
        <td><%=book.getId()%>
        </td>
        <td><%=book.getName()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td><%=book.getAuthor()%>
        </td>
        <td><%=book.getSales()%>
        </td>
        <td><%=book.getStock()%>
        </td>
        <td><%=book.getImgpath()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>

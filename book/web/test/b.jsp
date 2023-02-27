<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        out.println("myname:"+request.getParameter("myname"));
        out.println("mypass:"+request.getParameter("mypass"));
    %>
</body>
</html>

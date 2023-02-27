<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script>
        function myfunction() {
            var name = document.getElementById("name").value;
            document.location.href = "SearchBookServlet?name = " + name;
        }
    </script>
</head>
<body>
<form action="QueryBookServlet">
    关键字：<input type="text" name="keyword" id="name">
    <input type="submit" value="搜索">
</form>
</body>
</html>

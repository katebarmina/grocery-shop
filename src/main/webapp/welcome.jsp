<%--
  Created by IntelliJ IDEA.
  User: Kate
  Date: 26.06.2022
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
Welcome <%=request.getParameter("email").replace("@gmail.com","")%>!
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String path = request.getContextPath();
    String bastPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户列表</title>
    <link href="/css/main.css" rel="stylesheet"/>
</head>
<body>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>生日</th>
        <th>性别</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user" varStatus="status">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
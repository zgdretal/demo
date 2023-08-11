<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zhoupengxiao
  Date: 16/5/18
  Time: 下午12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加页面</title>
    <link rel="stylesheet" href="/resources/message-template/template-add-and-modify.css"/>
</head>
<body>

<div class="content-head">
    <div class="content-searchBox">
        <form action="/addUser" method="get">
            <td style="width: 200px;">用户姓名</td>
            <input class="content-search" name="name" value="${name}" type="search" placeholder="请输入姓名"><br>

            <td style="width: 200px;">电话号码</td>
            <input class="content-search" name="phone" value="${phone}" type="search" placeholder="请输入姓名"><br>

            <button class="content-search-btn" type="submit" >添加</button>
        </form>
    </div>

</body>
<script src="/resources/message-template/message-template.js"/>
</html>

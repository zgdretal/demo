
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
姓名: ${user.userName}<input type="text" name="email" class="bg" />request:${name11}<br>
玩家id: ${user.uid}<br>
玩家余额: ${name11}<br>

<div>${name11}</div>

<br>
================
<br>
对手信息列表：<br>
================
<br>

<c:forEach items="${userList}" var="userItem" varStatus="status">

    <tr>


        <form action="sumNum" method="GET">
            <input type="text" name="uid" value="${userItem.uid}" style ="display:none" readonly/><br>
            <input type="text" name="sourceUid" value="${user.uid}" style ="display:none" readonly /><br>
            对手名字：<input type="text" name="userName" value="${userItem.userName}" readonly/><br>
            对手余额：<input type="text" name="uid" value="${userItem.account}" readonly/><br>
            数鸡: <input type="text" name="num" />
            <input type="submit" value="收款（鸡的个数）" />
        </form>
    </tr>

</c:forEach>




</body>

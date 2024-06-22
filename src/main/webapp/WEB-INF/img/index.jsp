
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
玩家: ${user.name}<input type="text" name="email" class="bg" />request:${user}<br>
玩家id: ${user.uid}<br>
玩家余额: ${user.name}<br>

<div>${user}</div>


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


<br>
================
<br>
待支付列表：<br>
================

<c:forEach items="${payTranserInfoList}" var="payTranserInfo" varStatus="status">

    <tr>


        <form action="count" method="GET">
            <input type="text" name="uid" value="${user.uid}" style ="display:none" readonly/>
            <input type="text" name="targetUid" value="${payTranserInfo.targetUserId}" style ="display:none" readonly/>
            <input type="text" name="uuid" value="${payTranserInfo.id}" style ="display:none" readonly/>
            你待向 ${payTranserInfo.targetUserName} 支付金额 <input type="text" name="num" value="${payTranserInfo.targetNum}"  readonly/> 元
            <input type="submit" value="确认支付" />
            <%--<input type="submit" value="支付取消" />--%><br>
        </form>
    </tr>

</c:forEach>
<br>

<br>
待收款列表：<br>
================

<c:forEach items="${shoukuanTranserInfoList}" var="shoukuanTranser" varStatus="status">

    <tr>


        <form action="sumNum" method="GET">
            <input type="text" name="uid" value="${shoukuanTranser.targetUserId}" style ="display:none" readonly/>
            你待向 ${shoukuanTranser.targetUserName} 收款 <input type="text" name="uid" value="${shoukuanTranser.targetNum}"  readonly/> 元
            <br>
        </form>
    </tr>

</c:forEach>
<br>
<br>


</body>

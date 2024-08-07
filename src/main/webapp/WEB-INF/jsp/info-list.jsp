<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: zhoupengxiao
  Date: 16/5/15
  Time: 上午10:29
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resources/customer-list/customer-list.css"/>
    <title>用户列表</title>
</head>
<body  background="../test.jpeg" background-repeat="no-repeat">


<input id="sort" type="hidden" value="${sort}"/>

<div class="content">
    <div class="content-head">
        <div class="content-searchBox">
            <form action="/searchUser" method="get">
                <input class="content-search" name="search" value="${search}" type="search" placeholder="请输入搜索信息">
                <button class="content-search-btn" type="submit">搜索</button>
                <td>
                    <a class="look-buy-list link-btn" href="/add">添加人员</a>
                </td>
            </form>
        </div>
    </div>
    <table class="content-list">
        <tr class="list-title">
            <td style="width: 200px;">用户姓名</td>
            <td style="width: 200px;">手机号码</td>
            <td style="width: 200px;">备注</td>
            <%--<td>
                购买日期
                <a id="pup" class="sort-up" href="/smsInfo/sortby?term=${term}&sort=1"></a>
                <a id="pdown" class="sort-down" href="/smsInfo/sortby?term=${term}&sort=2"></a>
            </td>
            <td>
                短信发送日期
                <a id="sup" class="sort-up" href="/smsInfo/sortby?term=${term}&sort=3"></a>
                <a id="sdown" class="sort-down" href="/smsInfo/sortby?term=${term}&sort=4"></a>
            </td>--%>
            <td style="width: 200px;">操作</td>
        </tr>
        <c:choose>
            <c:when test="${totalCount == 0}">
                <div class="content">
                    <div class="list-none">很遗憾，你没有添加记录</div>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${infoList}" var="info" varStatus="index">
                    <c:choose>
                        <c:when test="${index.count%2!=0}">
                            <tr class="list-top">
                        </c:when>
                        <c:otherwise>
                            <tr class="list-bottom">
                        </c:otherwise>
                    </c:choose>
                        <td>${info.name}</td>
                        <td>${info.phone}</td>
                        <td>${info.groupId}</td>
                        <%--<td>2023-08-11</td>
                        <td>2023-08-11</td>--%>
                        <td>
                            <a class="list-delete" href="/userDetail?id=${info.id}">查看详情</a>
                            <a class="delete-user" href="/deleteUser?id=${info.id}">删除</a>
                            <%--<a class="list-delete" href="/order/listById?id=${info.invoiceId}">购买信息</a>
                            <a class="list-delete delete-btn" href="javascript:void(0)" data-value="${info.id}&fromOrderPage=false">删除</a>--%>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>

    <table class="list-page">
        <tr>
            <c:if test="${currentPage>1}">
                <td><a href="/smsInfo/list?term=${term}&currentPage=1">首页</a></td>
                <td><a href="/smsInfo/list?term=${term}&currentPage=${currentPage-1}">上一页</a></td>
            </c:if>
            <c:if test="${totalPage>1}">
                <c:forEach begin="1" end="${totalPage}" var="i">
                    <td><a <c:if test="${i==currentPage}">class="current-page"</c:if> href="/smsInfo/list?term=${term}&currentPage=${i}">${i}</a></td>
                </c:forEach>
            </c:if>
            <c:if test="${currentPage<totalPage}">
                <td><a href="/smsInfo/list?term=${term}&currentPage=${currentPage+1}">下一页</a></td>
                <td><a href=/smsInfo/list?term=${term}&currentPage=${totalPage}>尾页</a></td>
            </c:if>
            <c:if test="${totalPage>1}">
                <td>共${totalPage}页</td>
            </c:if>

        </tr>
    </table>
    <div id="listSure" class="list-sure" style="display: none">
        <div class="list-sure-title"></div>
        <div class="list-sure-content">
            确认删除信息？
        </div>
        <div class="list-sure-btn">
            <a class="list-sure-btn1" href="javascript:void(0)">确认</a>
            <a class="list-sure-btn2" href="javascript:void(0)">取消</a>
        </div>
    </div>
</div>
</body>
<script src="/resources/core/jq/jquery-1.11.1.js"></script>
<script src="/resources/customer-list/customer-list.js"></script>
</html>

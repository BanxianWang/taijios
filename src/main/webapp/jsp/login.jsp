<%--
  Created by IntelliJ IDEA.
  User: wrm
  Date: 2019/4/21
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${login==0}">
        登录失败，没有该用户
    </c:if>
    <c:if test="${login==1}">
        登录失败，密码错误
    </c:if>
<form method="post" action="/jsp/userLogin">
    <input type="text" name="username">
    <input type="password" name="password">
    <input type="submit" name="登录">
</form>
</body>
</html>

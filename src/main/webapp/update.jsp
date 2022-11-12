<%@ page import="domain.User" %><%--
  Created by IntelliJ IDEA.
  User: letingsun
  Date: 11/11/22
  Time: 1:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <base href="${request.contextPath}"/>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>update User information</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<%
    User user = (User) request.getAttribute("user");
%>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">update User information</h3>
    <form action="${pageContext.request.contextPath}/ModifyUserServlet" method="post">
        <input type="hidden" name = "id" id = "id" value="${user.id}"/>
        <div class="form-group">
            <label for="name">name：</label>
            <input type="text" class="form-control" id="name" name="name"  readonly="readonly" placeholder="name" value="${user.name}"/>
        </div>

        <div class="form-group">
            <label>gender：</label>
            <input type="radio" name="gender" value="male" checked="checked"/>male
            <input type="radio" name="gender" value="female"  />female
<%--            <c:if test="${user.gender} == 'male'">--%>
<%--                <input type="radio" name="gender" value="male" checked="checked"/>male--%>
<%--                <input type="radio" name="gender" value="female"  />female--%>
<%--            </c:if>--%>
<%--            <c:if test="${user.gender} == 'female'">--%>
<%--                <input type="radio" name="gender" value="male" checked="checked" />male--%>
<%--                <input type="radio" name="gender" value="female" checked />female--%>
<%--            </c:if>--%>

        </div>

        <div class="form-group">
            <label for="age">age：</label>
            <input type="text" class="form-control" id="age"  name="age" placeholder="age"value="${user.age}" />
        </div>

        <div class="form-group">
            <label for="address">address：</label>
            <select name="address" id="address" class="form-control" >
                <option value="USA">USA</option>
                <option value="CHINA">CHINA</option>
                <option value="UK">UK</option>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" id="qq" name="qq" placeholder="qq" value="${user.qq}"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="email" value="${user.email}"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="submit" />
            <input class="btn btn-default" type="reset" value="reset" />
            <input class="btn btn-default" type="button" value="return"/>
        </div>
    </form>
</div>
</body>
</html>

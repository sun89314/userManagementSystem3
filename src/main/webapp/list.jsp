<%@ page import="java.util.List" %>
<%@ page import="domain.User" %>
<%@ page import="domain.PageBean" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: letingsun
  Date: 11/10/22
  Time: 8:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deleteUser(id){
            if(confirm("do you really want to delete it?")){
                location.href = "${pageContext.request.contextPath}/DeleteUserServlet?id="+id;
            }

        }
        window.onload = function (){
            var buttion = document.getElementById("delSeleted");
            buttion.onclick = function (){
                document.getElementById("selected").submit()
            }
            var buttion2 = document.getElementById("firstCheck");
            buttion2.onclick = function (){
               var checkboxes =  document.getElementsByName("uid");
               for(var i = 0; i < checkboxes.length;i++){
                   checkboxes[i].checked = this.checked;
               }
            }

        }

    </script>
</head>
<body>
<%--<%--%>
<%--    List<User> users = (List<User>) request.getAttribute("users");--%>
<%--    PageBean<User> pb = (PageBean<User>) request.getAttribute("pb");--%>
<%--    Map<String, String[]> condition = (Map<String, String[]>)request.getAttribute("condition");--%>
<%--    //System.out.println(condition.get("name"));--%>

<%--%>--%>
<div class="container">
    <h3 style="text-align: center">User information </h3>
    <div style ="float:left">
        <form class="form-inline" action="${pageContext.request.contextPath}/FIndByPageServlet" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" value="${condition.get("name")[0]}" name = "name" id="name" placeholder="Jane Doe">
            </div>
<%--            <input type="hidden" name="curreentPage" id="curreentPage" value="1">--%>
<%--            <input type="hidden" name="rows" id="rows"value="10">--%>

            <button type="submit"  class="btn btn-default">Search</button>
        </form>
    </div>
    <div style="float:right;margin:5px">
        <a class="btn btn-primary" href="add.jsp">Add user</a>
        <a class="btn btn-primary" href="javascript:void(0)" id = "delSeleted">Delete selected user</a>
    </div>


    <form id ="selected"action="${pageContext.request.contextPath}/deleteSelectedUserServlet" method="post">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCheck"></th>
            <th>ID</th>
            <th>name</th>
            <th>gender</th>
            <th>age</th>
            <th>address</th>
            <th>phone</th>
            <th>email</th>
            <th>modify</th>
        </tr>

<%--        <c:forEach items="${users}" var="user" varStatus="s">--%>
<%--            </tr>--%>
<%--                <td><input type="checkbox" name ="uid" value="${user.id}"></td>--%>
<%--                <td>${s.count}</td>--%>
<%--                <td>${user.name}</td>--%>
<%--                <td>${user.gender}</td>--%>
<%--                <td>${user.age}</td>--%>
<%--                <td>${user.address}</td>--%>
<%--                <td>${user.qq}</td>--%>
<%--                <td>${user.email}</td>--%>
<%--                <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}&name=${user.name}">modify</a>&nbsp;<a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">delete</a></td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>

        <c:forEach items="${pb.list}" var="user" varStatus="s">
            </tr>
            <td><input type="checkbox" name ="uid" value="${user.id}"></td>
            <td>${s.count}</td>
            <td>${user.name}</td>
            <td>${user.gender}</td>
            <td>${user.age}</td>
            <td>${user.address}</td>
            <td>${user.qq}</td>
            <td>${user.email}</td>
            <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}&name=${user.name}">modify</a>&nbsp;<a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">delete</a></td>
            </tr>
        </c:forEach>



    </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin ="1" end="${pb.totalPage}" var="i">

                    <li><a href="${pageContext.request.contextPath}/FIndByPageServlet?curreentPage=${i}&rows=${pb.rows}&name=${condition.name[0]}">${i}</a></li>
                </c:forEach>
<%--                <li><a href="#">1</a></li>--%>
<%--                <li><a href="#">2</a></li>--%>
<%--                <li><a href="#">3</a></li>--%>
<%--                <li><a href="#">4</a></li>--%>
<%--                <li><a href="#">5</a></li>--%>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size:10px; margin-left: 10px">total ${pb.totalCount} user,there are ${pb.totalPage} pages</span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>

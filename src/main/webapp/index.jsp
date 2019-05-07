<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台系统管理</title>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="statics/css/font.css">
    <link rel="stylesheet" href="statics/css/xadmin.css">
</head>
<c:if test="${requestScope.loginFlag != null }">
    <script>
        alert("登录名或密码不正确")
    </script>
</c:if>
<body class="login-bg">

<div class="login">
    <div class="message">管理登录</div>
    <div id="darkbannerwrap"></div>
    <a href="getsevenday">1111</a>
    <form method="post" class="layui-form"  action="login">
        <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
    </form>
</div>






<!-- 底部结束 <script> $(function  () {
        layui.use('form', function(){
            var form = layui.form;
            // layer.msg('玩命卖萌中', function(){
            //   //关闭后的操作
            //   });
            //监听提交
            form.on('submit(login)', function(data){
                // alert(888)
                layer.msg(JSON.stringify(data.field),function(){
                    location.href='main.jsp'
                });
                return false;
            });
        });
    })</script> -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="statics/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="statics/js/xadmin.js"></script>
</body>
</html>
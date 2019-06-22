<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台系统管理</title>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="statics/css/font.css">
    <link rel="stylesheet" href="statics/css/xadmin.css">

</head>

<body class="login-bg">

<div class="login">
    <div class="message">管理登录</div>
    <div id="darkbannerwrap"></div>
    <form method="post" class="layui-form" action="login">
        <input name="phone" placeholder="手机号" type="text" lay-verify="required" class="layui-input">
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码" type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20">
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="statics/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="statics/js/xadmin.js"></script>

<script type="text/javascript">
    $(function () {
        layui.use('form', function () {
            $ = layui.jquery;
            var form = layui.form
                , layer = layui.layer;

            <c:if test="${requestScope.loginFlag != null }">
            layer.alert('手机号或密码不正确', {
                skin: 'layui-layer-molv' //样式类名  自定义样式
                , icon: 5   // icon
            });
            </c:if>
            <c:if test="${requestScope.loginNo == 'no' }">
            layer.alert('该用户被禁用，请联系客服', {
                skin: 'layui-layer-molv' //样式类名  自定义样式
                , icon: 5    // icon
            });
            </c:if>

            <%--<c:if test="${requestScope.loginss == 'no' }">--%>
            <%--layer.alert('请用管理员账户登录', {--%>
                <%--skin: 'layui-layer-molv' //样式类名  自定义样式--%>
                <%--, icon: 6    // icon--%>
            <%--});--%>
            <%--</c:if>--%>
            //监听提交
            form.on('submit(login)', function () {


                }
            );
            return false;
        });
    })
</script>

</body>
</html>
